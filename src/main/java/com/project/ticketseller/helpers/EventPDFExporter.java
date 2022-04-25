package com.project.ticketseller.helpers;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.ticketseller.entity.Event;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EventPDFExporter {

    private List<Event> events;

    public EventPDFExporter(List<Event> events) {
        this.events = events;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Title",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Category",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Venue",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Ticket Price",font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(Event event: events){
            table.addCell(String.valueOf(event.getId()));
            table.addCell(event.getTitle());
            table.addCell(String.valueOf(event.getDate()).substring(0,10));
            table.addCell(event.getCategory().getName());
            table.addCell(event.getVenue().getName());
            table.addCell(String.valueOf(event.getTicketPrice()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("List of all Events"));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {1.0f, 3.5f, 2.0f, 1.5f, 3.0f, 2.0f});
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
