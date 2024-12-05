package com.ennea.enneaservices.utils;

import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Data
public class PDFBoxCore {

    private PDDocument pdfDocument;
    private PDPageContentStream contentStream;

    private int[] colWidths;
    private int colHeight;
    private int yPosition;
    private int xPosition;
    private int colPosition;
    private int xInitialPosition;
    private float fontSize;
    private PDFont font;
    private Color fontColor;

    public PDFBoxCore(PDDocument pdfDocument, PDPageContentStream contentStream) {
        this.pdfDocument = pdfDocument;
        this.contentStream = contentStream;
    }

    public static float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
        // https://pdfbox.apache.org/docs/2.0.2/javadocs/org/apache/pdfbox/pdmodel/font/PDFont.html#getStringWidth(java.lang.String)
        return font.getStringWidth(text) / 1000 * fontSize;
    }

    public static float getFontHeight(PDFont font, float fontSize) {
        // Ascender height in points
        float ascent = font.getFontDescriptor().getAscent() / 1000 * fontSize;
        // Descender height in points (negative value)
        float descent = font.getFontDescriptor().getDescent() / 1000 * fontSize;

        // Total height is the distance from the lowest descender to the highest ascender
        return ascent - descent;
    }

    public void addSingleLineText(String text, float xPos, float yPos, PDFont font, float fontSize,
                                  Color color) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setNonStrokingColor(color);
        contentStream.newLineAtOffset(xPos, yPos);
        contentStream.showText(text);
        contentStream.endText();
        contentStream.moveTo(0, 0);
    }

    public void addMultiLineText(List<String> textList, int xPos, int yPos, PDFont font, float fontSize,
                                 Color color) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setNonStrokingColor(color);
        contentStream.newLineAtOffset(xPos, yPos);

        for(String text : textList){
            contentStream.showText(text);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.moveTo(0, 0);
    }

    public void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
        this.colWidths = colWidths;
        this.colHeight = cellHeight;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        xInitialPosition = xPosition;
    }

    public void setTableFont(PDFont font, float fontSize, Color fontColor) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
    }

    public void addCell(String text, Color fillColor) throws IOException {
        contentStream.setStrokingColor(1f);

        if(fillColor != null){
            contentStream.setNonStrokingColor(fillColor);
        }

        contentStream.addRect(xPosition, yPosition, colWidths[colPosition], colHeight);

        if(fillColor != null){
            contentStream.fillAndStroke();
        } else{
            contentStream.stroke();
        }

        contentStream.beginText();
        contentStream.setNonStrokingColor(fontColor);
        // for right-adjusted - (xPosition + colWidths[colPosition] - 20 - fontWidth, yPosition + 10)
        contentStream.newLineAtOffset(xPosition + 20, yPosition + 10); //left-adjusted
        contentStream.showText(text);
        contentStream.endText();

        xPosition = xPosition + colWidths[colPosition];
        colPosition++;

        if(colPosition == colWidths.length){
            colPosition = 0;
            xPosition = xInitialPosition;
            yPosition -= colHeight;
        }
    }


}
