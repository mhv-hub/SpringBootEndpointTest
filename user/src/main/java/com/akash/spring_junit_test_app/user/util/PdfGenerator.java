package com.akash.spring_junit_test_app.user.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

    public void generateItierary(String filePath) {
        System.out.println("Inside generateItierary()");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(generateTable());
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Exception in generateItinerary " + e);
        }

    }

    private PdfPTable generateTable() {
        PdfPTable table = new PdfPTable(7);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("MAA ENTERPRISES"));
        cell.setColspan(7);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("VIDYAPATI NAGAR, NEAR ULTA SHIV MANDIR & PATEL CLUB"));
        cell.setColspan(7);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("JAMSHEDPUR, JHARKHAND"));
        cell.setColspan(7);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" "));
        cell.setColspan(7);
        cell.setBorder(0);
        table.addCell(cell);

        Font font10 = new Font();
        Font font6 = new Font();
        font10.setSize(10);
        font10.setColor(BaseColor.DARK_GRAY);

        cell = new PdfPCell(new Phrase("Branch : 001-Sidhgora", font10));
        cell.setColspan(2);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Party Ledger", font10));
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Installment Type :", font10));
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Weekly", font10));
        cell.setColspan(1);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", font10));
        cell.setColspan(7);
        cell.setBorder(0);
        table.addCell(cell);

        font6.setSize(4);

        cell = new PdfPCell(new Phrase("Disbursement No : LON001", font6));
        cell.setColspan(3);
        cell.setBorder(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total loan Amount :	30000", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Disbursement Date : 12/12/2022", font6));
        cell.setColspan(3);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Product Name : LOAN", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Member Name : Rakesh Tiwary", font6));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Installment : 1500", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Centre Name : ASTHA MM", font6));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Down Payment : 0", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Centre Address : Bagunhatu", font6));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Outstanding amount : 30000", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Spouse Name :NA", font6));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Disb. Day :	Saturday", font6));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Expected Disb. Date : 12/12/2022", font6));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", font6));
        cell.setColspan(4);
        table.addCell(cell);

        return table;
    }
    // private void generateTable(String dest) {
    // PdfWriter writer = new PdfWriter(dest);

    // // Creating a PdfDocument object
    // PdfDocument pdfDoc = new PdfDocument(writer);

    // // Creating a Document object
    // Document doc = new Document(pdfDoc);

    // // Creating a table
    // float[] pointColumnWidths = { 200F, 200F };
    // Table table = new Table(pointColumnWidths);

    // // Populating row 1 and adding it to the table
    // Cell c1 = new Cell(); // Creating cell 1
    // c1.add("Name"); // Adding name to cell 1
    // c1.setBackgroundColor(Color.DARK_GRAY); // Setting background color
    // c1.setBorder(Border.NO_BORDER); // Setting border
    // c1.setTextAlignment(TextAlignment.CENTER); // Setting text alignment
    // table.addCell(c1); // Adding cell 1 to the table

    // Cell c2 = new Cell();
    // c2.add("Raju");
    // c2.setBackgroundColor(Color.GRAY);
    // c2.setBorder(Border.NO_BORDER);
    // c2.setTextAlignment(TextAlignment.CENTER);
    // table.addCell(c2);

    // // Populating row 2 and adding it to the table
    // Cell c3 = new Cell();
    // c3.add("Id");
    // c3.setBackgroundColor(Color.WHITE);
    // c3.setBorder(Border.NO_BORDER);
    // c3.setTextAlignment(TextAlignment.CENTER);
    // table.addCell(c3);

    // Cell c4 = new Cell();
    // c4.add("001");
    // c4.setBackgroundColor(Color.WHITE);
    // c4.setBorder(Border.NO_BORDER);
    // c4.setTextAlignment(TextAlignment.CENTER);
    // table.addCell(c4);

    // // Populating row 3 and adding it to the table
    // Cell c5 = new Cell();
    // c5.add("Designation");
    // c5.setBackgroundColor(Color.DARK_GRAY);
    // c5.setBorder(Border.NO_BORDER);
    // c5.setTextAlignment(TextAlignment.CENTER);
    // table.addCell(c5);

    // Cell c6 = new Cell();
    // c6.add("Programmer");
    // c6.setBackgroundColor(Color.GRAY);
    // c6.setBorder(Border.NO_BORDER);
    // c6.setTextAlignment(TextAlignment.CENTER);
    // table.addCell(c6);

    // // Adding Table to document
    // doc.add(table);

    // // Closing the document
    // doc.close();

    // System.out.println("Background added successfully..");
    // }

}