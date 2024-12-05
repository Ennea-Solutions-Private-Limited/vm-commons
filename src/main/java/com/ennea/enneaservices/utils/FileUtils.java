package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import com.ennea.enneaservices.model.Json.ParserConfigJSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUtils {

    public static final ArrayList<String> sheetExtensions = new ArrayList<>() {{
        add("xls");
        add("xlsx");
        add("csv");
    }};

    public static String mkdirs(String directory) {
        File file = new File(directory);
        if(!file.exists()){
            file.mkdirs();
        }
        return file.toString();
    }

    public static File getFile(String rootDir, String fileName) {
        File f = new File(rootDir + "/" + fileName);
        if(!f.exists()){
            f = new File(rootDir + "/" + fileName);
        }
        return f;
    }

    public static XLS read_xls(String rootDir, String name) {
        File f = getFile(rootDir, name);
        String fmt = FilenameUtils.getExtension(f.getName());
        return new XLS(f.getAbsolutePath(), fmt);
    }

    public static List<String> read_xls_header(String rootDir, String name, Integer headerIdx) {
        File f = getFile(rootDir, name);
        XLS xls = new XLS();
        return xls.read_xls_header(f.getAbsolutePath(), headerIdx);

    }

    public static XLS read_xls_with_parser_config(String rootDir, String name, ParserConfigJSON config) {
        File f = getFile(rootDir, name);
        return new XLS(f.getAbsolutePath(), config.invertHeaderMap(), config.getFileInfo().getHeaderRowNumber());
    }

    public static void delete(File file) {
        File[] contents = file.listFiles();
        if(contents != null){
            for(File f : contents){
                delete(f);
            }
        }
        file.delete();
    }

    public static String createTempDir(String name) {
        try {
            Path tempDir = Files.createTempDirectory(name);
            return tempDir.toString();
        } catch (IOException e) {
            return mkdirs("/tmp/" + name);
        }
    }

    public static String verifyIngestionFile(MultipartFile multipartFile, Long id, String ingestionTempDirPrefix) {
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if(!(Constants.FILE_TYPE_XLSX.equalsIgnoreCase(fileExtension)
             || Constants.FILE_TYPE_XLS.equalsIgnoreCase(fileExtension))){
            final ApiError errorResponse =
                new ApiError(HttpStatus.BAD_REQUEST, ErrorEnum.FILE_TYPE_NOT_SUPPORTED.getMessage(),
                             ErrorEnum.FILE_TYPE_NOT_SUPPORTED.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
        return createTempDirAndCopyFile(multipartFile, id, ingestionTempDirPrefix);
    }

    public static String verifyAndMoveIngestionFile(MultipartFile multipartFile, Long id,
                                                    String ingestionTempDirPrefix) {
        return verifyAndMoveIngestionFile(multipartFile, id, ingestionTempDirPrefix, sheetExtensions);
    }

    public static String verifyAndMoveIngestionFile(MultipartFile multipartFile, Long id, String ingestionTempDirPrefix,
                                                    List<String> extensions) {
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if(StringUtils.isBlank(fileExtension) || !extensions.contains(fileExtension.toLowerCase())){
            final ApiError errorResponse =
                new ApiError(HttpStatus.BAD_REQUEST, ErrorEnum.FILE_TYPE_NOT_SUPPORTED.getMessage(),
                             ErrorEnum.FILE_TYPE_NOT_SUPPORTED.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
        return createTempDirAndCopyFile(multipartFile, id, ingestionTempDirPrefix);
    }

    private static String createTempDirAndCopyFile(MultipartFile multipartFile, Long id,
                                                   String ingestionTempDirPrefix) {
        String tempDir = createTempDir(ingestionTempDirPrefix + id.toString());
        Path filePath = Paths.get(tempDir, multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(filePath);
        } catch (IOException e) {
            log.error("Error occurred while saving the ingestion file for the user {}", id);
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.FAILED_TO_PROCESS_FILE.getMessage(),
                             ErrorEnum.FAILED_TO_PROCESS_FILE.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
        return Paths.get(tempDir).toString();
    }

    public static void deleteDirectory(String dir) {
        FileSystemUtils.deleteRecursively(new File(dir));
    }

    public static double getSizeInMB(MultipartFile file) {
        long bytes = file.getSize();
        return (double) bytes / (1024 * 1024);
    }

    public static boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
