package com.project.ticketseller.helpers;

import com.project.ticketseller.entity.Event;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Event> events;

    public EventExcelExporter(List<Event> events) {
        this.events = events;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Events");

    }

    private void writeHeaderRow(){
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Title");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Description");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Date");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Start Time");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Category");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("Venue");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("Ticket Price");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("Creation Date");
        cell.setCellStyle(style);

    }

    private void writeDataRows(){
        int rowCount = 1;
        for(Event event: events){
            Row row = sheet.createRow(rowCount);

            Cell cell = row.createCell(0);
            cell.setCellValue(event.getId());

            cell = row.createCell(1);
            cell.setCellValue(event.getTitle());

            cell = row.createCell(2);
            cell.setCellValue(event.getDescription());

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(event.getDate()).substring(0,10));

            cell = row.createCell(4);
            cell.setCellValue(event.getStartTime());

            cell = row.createCell(5);
            cell.setCellValue(event.getCategory().getName());

            cell = row.createCell(6);
            cell.setCellValue(event.getVenue().getName());

            cell = row.createCell(7);
            cell.setCellValue(event.getTicketPrice());

            cell = row.createCell(8);
            cell.setCellValue(String.valueOf(event.getCreationDate()).substring(0,10));

            rowCount++;
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
    }

    public void export(HttpServletResponse response) throws IOException {

        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
