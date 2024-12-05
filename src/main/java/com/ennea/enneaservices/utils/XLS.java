package com.ennea.enneaservices.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class XLS {

    private final DataFormatter formatter = new DataFormatter();
    private final DateTimeFormatter defaultFormatter =
        new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy").toFormatter();
    public final List<String> columns = new ArrayList<>();
    public final List<Map<String, String>> records = new ArrayList<>();
    final boolean force_lower = true;

    public XLS(String filepath, String fmt) {
        impl(filepath, null, fmt);
    }

    public XLS(String filepath, HashMap<String, List<String>> headerMap, int headerRow) {
        try {
            impl(filepath, headerMap, headerRow);
        } catch (Exception e) {
            e.printStackTrace();
            columns.clear();
            records.clear();
        }
    }


    public XLS(String filepath, List<String> exclude, String fmt) {
        impl(filepath, exclude, fmt);
    }

    public static ByteArrayOutputStream write_xls(List<List<Object>> rows, boolean is_xlsx) throws IOException {
        Workbook workbook;

        if(is_xlsx){
            workbook = new XSSFWorkbook();
        } else{
            workbook = new HSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet();

        int row_num = 0;
        for(List<Object> cells : rows){
            Row row = sheet.createRow(row_num++);
            int col_num = 0;
            for(Object cell : cells){
                if(cell.getClass() == Integer.class){
                    row.createCell(col_num++).setCellValue((int) cell);
                } else if(cell.getClass() == Long.class){
                    row.createCell(col_num++).setCellValue((Long) cell);
                } else{
                    row.createCell(col_num++).setCellValue((String) cell);
                }
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOException ret_exception = null;
        try {
            workbook.write(bos);
            bos.close();
            workbook.close();
        } catch (IOException e) {
            ret_exception = e;
        } finally {
            bos.close();
            workbook.close();
            if(ret_exception != null){
                throw ret_exception;
            }
        }
        return bos;
    }

    public static ByteArrayOutputStream write_csv(List<List<Object>> rows, boolean is_tsv) {

        StringBuilder lines = new StringBuilder();
        for(List<Object> cells : rows){
            StringBuilder row = new StringBuilder();
            for(Object cell : cells){
                if(cell == null){
                } else if(cell.getClass() == Integer.class){
                    row.append(cell);
                } else if(cell.getClass() == Long.class){
                    row.append(cell);
                } else if(cell.getClass() == Float.class){
                    row.append(cell);
                } else if(cell.getClass() == Double.class){
                    row.append(cell);
                } else{
                    row.append(cell);
                }

                if(is_tsv){
                    row.append("\t");
                } else{
                    row.append(",");
                }
            }
            lines.append(row);
            lines.append("\n");
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            bos.write(lines.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos;
    }

    private static List<String> read_xsv_header(String filepath, Integer headerRow,
                                                String separator) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        for(int i = 0; i < headerRow - 1; i++){
            br.readLine(); // Skip all lines before header
        }

        String line = br.readLine();
        if(line != null){
            return Arrays.asList(line.split(separator, -1));
        } else{
            return new ArrayList<>();
        }
    }

    private static String sanitizeString(String text) {
        return removeBOM(text);
    }

    private static String removeBOM(String text) {
        if(text != null && text.startsWith("\uFEFF")){
            return text.substring(1);
        }
        return text;
    }

    private void impl(String filepath, HashMap<String, List<String>> headerMap, int headerRow) {
        String fmt = FilenameUtils.getExtension(filepath);
        try {
            switch(fmt.toLowerCase()) {
                case "xls", "xlsx" -> read_excel(filepath, headerMap, headerRow);
                case "csv" -> read_xsv(filepath, headerMap, headerRow, ",");
                case "tsv" -> read_xsv(filepath, headerMap, headerRow, "\\t");
            }
        } catch (IOException e) {
            e.printStackTrace();
            columns.clear();
            records.clear();
        }
    }

    private void impl(String filepath, List<String> exclude, String fmt) {
        try {
            switch(fmt) {
                case "xls", "xlsx" -> read_xls(filepath, exclude);
                case "csv" -> read_csv(filepath, exclude, false);
                case "tsv" -> read_csv(filepath, exclude, true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            columns.clear();
            records.clear();
        }
    }

    private String cellToString(Cell cell) {
        if(cell != null){
            // return cell.getStringCellValue();
            return formatter.formatCellValue(cell);
        }
        return "";
    }

    private String cellToStringBasedOnType(Cell cell) {
        String result = "";

        if(cell != null){
            CellType cellType = cell.getCellType().equals(CellType.FORMULA)
                ? cell.getCachedFormulaResultType() : cell.getCellType();
            if(cellType.equals(CellType.STRING)){
                result = cell.getStringCellValue();
            }
            if(cellType.equals(CellType.NUMERIC)){
                if(DateUtil.isCellDateFormatted(cell)){
                    result = cell.getLocalDateTimeCellValue().toLocalDate().format(defaultFormatter);
                } else{
                    result = formatter.formatCellValue(cell);
                }
            }
            if(cellType.equals(CellType.BOOLEAN)){
                result = String.valueOf(cell.getBooleanCellValue());
            }
        }

        return result.trim();
    }

    private Workbook getWorkbook(String filepath) throws IOException {
        FileInputStream file = new FileInputStream(filepath);
        return WorkbookFactory.create(file);
    }

    private int getZeroBasedHeaderIndex(Integer headerIdx) {
        int result = headerIdx != null ? headerIdx : 0;
        result = result > 0 ? result - 1 : 0;
        return result;
    }

    public List<String> read_xls_header(String filepath, Integer headerRow) {
        String fmt = FilenameUtils.getExtension(filepath);
        try {
            switch(fmt.toLowerCase()) {
                case "xls", "xlsx" -> {
                    return read_excel_header(filepath, headerRow);
                }
                case "csv" -> {
                    return read_xsv_header(filepath, headerRow, ",");
                }
                case "tsv" -> {
                    return read_xsv_header(filepath, headerRow, "\\t");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            columns.clear();
            records.clear();
        }

        return new ArrayList<>();
    }

    public List<String> read_excel_header(String filepath, Integer headerRow) throws IOException {
        List<String> headers = new ArrayList<>();

        int headerIdx = getZeroBasedHeaderIndex(headerRow);
        Workbook workbook = getWorkbook(filepath);
        Sheet sheet = workbook.getSheetAt(0);
        if(sheet.getRow(headerIdx).cellIterator().hasNext()){
            int colNum = sheet.getRow(headerIdx).getLastCellNum();
            for(int j = 0; j < colNum; j++){
                String value = cellToStringBasedOnType(sheet.getRow(headerIdx).getCell(j));
                if(value.contentEquals("")){
                    continue;
                }

                headers.add(value);
            }
        }

        workbook.close();

        return headers;
    }

    private void read_excel(String filepath, HashMap<String, List<String>> headerMap, int headerRow)
        throws IOException {


        int headerIdx = getZeroBasedHeaderIndex(headerRow);
        Workbook workbook = getWorkbook(filepath);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, Integer> colMapByName = new LinkedHashMap<>();
        if(sheet.getRow(headerIdx).cellIterator().hasNext()){
            int colNum = sheet.getRow(headerIdx).getLastCellNum();
            for(int j = 0; j < colNum; j++){
                String value = sanitizeString(cellToStringBasedOnType(sheet.getRow(headerIdx).getCell(j)));
                if(value.contentEquals("")){
                    continue;
                }

                colMapByName.put(value.toLowerCase(), j);
            }
        }

        this.columns.addAll(colMapByName.keySet());
        int rowNum = sheet.getLastRowNum() + 1;
        for(int i = headerIdx + 1; i < rowNum; i++){
            Map<String, String> record = new LinkedHashMap<>();
            Row row;
            try {
                row = sheet.getRow(i);

            } catch (Exception e) {
                continue;
            }
            if(row == null){
                continue;
            }
            boolean valid = false;

            for(String colName : colMapByName.keySet()){

                if(headerMap.containsKey(colName)){
                    Cell cell = row.getCell(colMapByName.get(colName));
                    String value = sanitizeString(cellToStringBasedOnType(cell));
                    List<String> headerList = headerMap.get(colName);
                    for(String header : headerList){
                        if(!valid && !value.isEmpty()){
                            valid = true;
                        }
                        record.put(header, value);
                    }

                }

            }
            if(valid){
                this.records.add(record);
            }
        }
        workbook.close();
    }

    public void read_xsv(String filepath, HashMap<String, List<String>> headerMap, int headerRow, String separator)
        throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filepath));
        for(int i = 0; i < headerRow - 1; i++){
            br.readLine(); // Skip all lines before header
        }

        String line = br.readLine();
        if(line != null){
            Map<Integer, String> colMap = new LinkedHashMap<>();
            String[] headers = line.split(separator, -1);
            for(int i = 0; i < headers.length; i++){
                String header = sanitizeString(headers[i]).toLowerCase();
                colMap.put(i, header);
                this.columns.add(header);
            }

            while((line = br.readLine()) != null) {
                Map<String, String> record = new LinkedHashMap<>();
                String[] values = line.split(separator, -1);
                for(int j = 0; j < values.length; j++){
                    if(headerMap.containsKey(colMap.get(j))){
                        List<String> colNameList = headerMap.get(colMap.get(j));
                        for(String colName : colNameList){
                            record.put(colName, sanitizeString(values[j]));
                        }
                    }
                }
                this.records.add(record);
            }

        }

        br.close();
    }

    private void read_xls(String filepath, List<String> exclude)
        throws IOException {

        Workbook workbook = getWorkbook(filepath);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, Integer> colMapByName = new LinkedHashMap<>();
        if(sheet.getRow(0).cellIterator().hasNext()){
            int colNum = sheet.getRow(0).getLastCellNum();
            for(int j = 0; j < colNum; j++){
                String value = cellToString(sheet.getRow(0).getCell(j));
                if(value.contentEquals("")){
                    continue;
                }

                if(exclude != null && exclude.contains(value)){
                    // remove the columns that are not needed
                    continue;
                }
                colMapByName.put(value, j);
            }
        }

        for(String col : colMapByName.keySet()){
            if(force_lower){
                col = col.toLowerCase();
            }
            this.columns.add(col);
        }

        int rowNum = sheet.getLastRowNum() + 1;
        for(int i = 1; i < rowNum; i++){
            Map<String, String> record = new LinkedHashMap<>();
            Row row = sheet.getRow(i);
            boolean valid = false;
            for(String colName : colMapByName.keySet()){
                Cell cell = row.getCell(colMapByName.get(colName));
                String value = cellToString(cell);
                if(!valid && !value.isEmpty()){
                    valid = true;
                }
                if(force_lower){
                    colName = colName.toLowerCase();
                }
                record.put(colName, value);
            }
            if(valid){
                this.records.add(record);
            }
        }
        workbook.close();
    }

    void read_csv(String filepath, List<String> exclude, boolean is_tsv) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        Map<Integer, String> colMap = new LinkedHashMap<>();
        Pattern csvPattern = Pattern.compile("(?<=^|,)(\"(?:[^\"]|\"\")*\"|[^,\"]*)");

        while((line = br.readLine()) != null) {
            List<String> values = new ArrayList<>();
            Matcher matcher = csvPattern.matcher(line);

            while(matcher.find()) {
                String field = matcher.group(1).replace("\"", "").trim();
                values.add(field);
            }

            if(colMap.isEmpty()){
                for(int j = 0; j < values.size(); j++){
                    colMap.put(j, values.get(j));
                }
            } else{
                Map<String, String> record = new LinkedHashMap<>();
                for(int j = 0; j < values.size(); j++){
                    String colName = colMap.get(j);
                    if(force_lower){
                        colName = colName.toLowerCase();
                    }
                    record.put(colName, values.get(j));
                }
                this.records.add(record);
            }
        }

        for(String col : colMap.values()){
            if(force_lower){
                col = col.toLowerCase();
            }
            this.columns.add(col);
        }

        br.close();
    }
}