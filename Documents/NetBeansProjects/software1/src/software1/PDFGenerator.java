/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software1;

/**
 *
 * @author ADMIN
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class PDFGenerator {
    private final Font FONT_NORMAL = FontFactory.getFont(FontFactory.TIMES, 10);
    private final Font FONT_BOLD = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
    private final Font FONT_BOLD_UNDERLINE = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.UNDERLINE);
    private final Font FONT_SUBTITLE = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 12);
    private final Font FONT_TITLE = FontFactory.getFont(FontFactory.TIMES_BOLD, 20);
    
    public void pendingWalkIn() {
        
    }
    public void pendingDeliver() {
        
    }
    public void completeWalkIn() {
        
    }
    public void completeDeliver() {
        
    }
    public void allTransactionsReport() throws Exception{
        Database db = new Database();
        Document document = createDocument("Transactions Report");
        
        //PENDING WALK-IN TABLE
        createTableBlock(document, "Unpaid Walk-Ins",
                new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                db.getTransactions(0, 1)
                );
        
        //PENDING DELIVERIES TABLE
        createTableBlock(document, "Unpaid Deliveries",
                new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                db.getTransactions(0, 2)
                );
        //COMPLETE WALK-IN TABLE
        createTableBlock(document, "Complete Walk-Ins",
                new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Amount Paid", "Date"},
                db.getTransactions(1, 1)
                );
        
        //COMPLETE DELIVERIES TABLE
        createTableBlock(document, "Complete Deliveries",
                new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Amount Paid", "Date"},
                db.getTransactions(1, 2)
                );
        
        // Close the document
        document.close();
        
        db.closeConnection();
    }
    private Document createDocument(String title) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.LETTER);

        // Create a PDF file with the current date and time as part of the filename
        String filename = title.replaceAll(" ", "") + LocalDate.now() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

        // Add header and footer
        document.open();
        writer.setPageEvent(new HeaderFooter());
        
        // Main Header
        Paragraph p = new Paragraph("QNB WATER", FONT_SUBTITLE);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        Paragraph p2 = new Paragraph(title, FONT_TITLE);
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
        document.add(newLine());
        
        return document;
    }
    private void createTableBlock(Document document, String title, String[] header, ArrayList<ArrayList<Object>> content) throws DocumentException {
        Paragraph p = new Paragraph(title, FONT_BOLD_UNDERLINE);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(newLine());
        document.add(createTable(
                header,
                content
        ));
        document.add(newLine());
    }
    private Paragraph newLine() {
        return new Paragraph("\n", FONT_NORMAL);
    }
    private PdfPTable createTable(String[] headers, ArrayList<ArrayList<Object>> content) {
        PdfPTable table = new PdfPTable(headers.length);
        table.setWidthPercentage(100);
        // Add column headers
        for (String h : headers) {
            table.addCell(createCell(h, Element.ALIGN_CENTER, FONT_BOLD));
        }
        // Add data rows
        for (ArrayList<Object> row : content) {
            for (Object col : row) {
                table.addCell(createCell("" + col, Element.ALIGN_JUSTIFIED, FONT_NORMAL));
            }
        }
        return table;
    }
    // Helper method to create a cell with specified alignment and content
    private PdfPCell createCell(String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}

class HeaderFooter extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable footerTable = new PdfPTable(1);
        PdfPCell footerCell = new PdfPCell(new Paragraph("Page " + writer.getPageNumber(), FontFactory.getFont(FontFactory.TIMES, 8)));
        footerCell.setBorderWidth(0);
        footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        footerTable.addCell(footerCell);
        footerTable.setTotalWidth(document.right() - document.left());
        footerTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() - 10, writer.getDirectContent());
    }
    
    public static void main(String[] args) throws Exception {
        PDFGenerator pdf = new PDFGenerator();
        pdf.allTransactionsReport();
    }
}

