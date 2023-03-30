
package software1;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PDFGenerator {
    private static final Font FONT_NORMAL = FontFactory.getFont(FontFactory.TIMES, 10);
    private static final Font FONT_BOLD = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
    private static final Font FONT_BOLD_UNDERLINE = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.UNDERLINE);
    private static final Font FONT_SUBTITLE = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 12);
    private static final Font FONT_TITLE = FontFactory.getFont(FontFactory.TIMES_BOLD, 20);
    /**
     * @param title The title of the document
     * @param range "Daily", "Weekly", "Monthly", "Yearly"
     * @param tables "pendingwalkin-order", "pendingdelivery-order", "completewalkin-order", "completedelivery-order", "pending-delivery", "ongoing-delivery", "complete-delivery"
     * @throws DocumentException
     * @throws FileNotFoundException 
     */
    public static void createReport(String title, String range, String... tables) throws DocumentException, FileNotFoundException {
        Database db = new Database();
        Document document = createDocument(range + " " + title);
        // GENERATE RESPECTIVE TABLES
        for (String s : tables) switch (s) {
            case ("pendingall-order") -> createTableBlock(document, range + " Unpaid Walk-Ins",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                    db.getTransactions(0, 0, range.toLowerCase())
                    );
            case ("pendingwalkin-order") -> createTableBlock(document, range + " Unpaid Walk-Ins",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                    db.getTransactions(0, 1, range.toLowerCase())
                    );
            case ("pendingdelivery-order") -> createTableBlock(document, range + " Unpaid Deliveries",
                        new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                    db.getTransactions(0, 2, range.toLowerCase())
                    );
            case ("completeall-order") -> createTableBlock(document, range + " Unpaid Walk-Ins",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"},
                    db.getTransactions(1, 0, range.toLowerCase())
                    );
            case ("completewalkin-order") -> createTableBlock(document, range + " Paid Walk-Ins",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Amount Paid", "Date"},
                    db.getTransactions(1, 1, range.toLowerCase())
                    );
            case ("completedelivery-order") -> createTableBlock(document, range + " Paid Deliveries",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Amount Paid", "Date"},
                    db.getTransactions(1, 2, range.toLowerCase())
                    );
            case ("pending-delivery") -> createTableBlock(document, range + " Pending Deliveries",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Delivery", "Price", "Status"},
                    db.getDeliveries(0)
                    );
            case ("ongoing-delivery") -> createTableBlock(document, range + " Ongoing Deliveries",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Delivery", "Price", "Status"},
                    db.getDeliveries(1)
                    );
            case ("complete-delivery") -> createTableBlock(document, range + " Complete Deliveries",
                    new String[] {"Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price", "Status"},
                    db.getDeliveries(2)
                    );
            default -> System.out.println("Invalid Table Name: " + s);
        }
        // Close
        document.close();
        db.closeConnection();
    }
    private static Document createDocument(String title) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.LETTER);

        // Create a PDF file with the current date and time as part of the filename
        String filename = title.replaceAll(" ", "") + LocalDateTime.now().toString().substring(0,19).replaceAll(":", "_") + ".pdf";
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
    private static void createTableBlock(Document document, String title, String[] header, ArrayList<ArrayList<Object>> content) throws DocumentException {
        Paragraph p = new Paragraph(title, FONT_BOLD_UNDERLINE);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(newLine());
        document.add(createTable(header, content));
    }
    private static Paragraph newLine() {
        return new Paragraph("\n", FONT_NORMAL);
    }
    private static PdfPTable createTable(String[] headers, ArrayList<ArrayList<Object>> content) {
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
    private static PdfPCell createCell(String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    public static void finances() throws DocumentException, FileNotFoundException{
        Document document = createDocument("Finances");
        Database db = new Database();
        String[] ranges = new String[]{"Daily", "Weekly", "Monthly", "Yearly"};
        //createTableBlock(Document document, String title, String[] header, ArrayList<ArrayList<Object>> content)
        for (String range : ranges){
            createTableBlock(document, range + " Finances",new String[]{"Date","Amount"},db.getFinances(range.toLowerCase()));
            document.add(new Paragraph(range + " Total: Php. " + db.getGrossIncome(range.toLowerCase())));
        }
        db.closeConnection();
        document.close();
    }
    public static void main(String[] args) throws Exception {
        PDFGenerator.finances();
    }
}

class HeaderFooter extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable footerTable = new PdfPTable(1);
        PdfPCell footerCell = new PdfPCell(new Paragraph("Generated " + LocalDateTime.now().toString().substring(0,19) + " | Page " + writer.getPageNumber(), FontFactory.getFont(FontFactory.TIMES, 8)));
        footerCell.setBorderWidth(0);
        footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        footerTable.addCell(footerCell);
        footerTable.setTotalWidth(document.right() - document.left());
        footerTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() - 10, writer.getDirectContent());
    }
}

