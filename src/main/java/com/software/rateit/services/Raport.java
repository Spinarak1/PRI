package com.software.rateit.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.software.rateit.DTO.User.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Raport {
    private static final Logger logger = LoggerFactory.getLogger(Raport.class);

    public static ByteArrayInputStream pdfDocument(List<UserDTO> userDTOS){
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{20,20,20,20});

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
            Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Report of registered users", catFont));
            addEmptyLine(preface, 3);
            preface.add(new Paragraph("Total users registered: " + userDTOS.size(), font1));
            addEmptyLine(preface, 2);
            preface.add(new Paragraph("USERS LIST: ", font1));
            addEmptyLine(preface, 2);




            PdfPCell id = new PdfPCell(new Phrase("USER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL)));
            id.setVerticalAlignment(Element.ALIGN_CENTER);
            id.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(id);

            PdfPCell username = new PdfPCell(new Phrase("USERNAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL)));
            username.setVerticalAlignment(Element.ALIGN_CENTER);
            username.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(username);

            PdfPCell email = new PdfPCell(new Phrase("USER EMAIL", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL)));
            email.setVerticalAlignment(Element.ALIGN_CENTER);
            email.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(email);

            PdfPCell date = new PdfPCell(new Phrase("REGISTRATION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL)));
            date.setVerticalAlignment(Element.ALIGN_CENTER);
            date.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(date);

            userDTOS.forEach(user -> {
                PdfPCell cell1 =  new PdfPCell(new Phrase(user.getId().toString(), font));
                cell1.setVerticalAlignment(Element.ALIGN_CENTER);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);

                PdfPCell cell2 =  new PdfPCell(new Phrase(user.getNick(), font));
                cell2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell2);

                PdfPCell cell3 =  new PdfPCell(new Phrase(user.getEmail(), font));
                cell3.setVerticalAlignment(Element.ALIGN_CENTER);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell3);

                DateFormat simple = new SimpleDateFormat("dd MM yyyy HH:mm:ss");

                PdfPCell cell4 =  new PdfPCell(new Phrase(simple.format(new Date(user.getRegistrationDate())), font));
                cell4.setVerticalAlignment(Element.ALIGN_CENTER);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell4);

            });

            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(preface);
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}


