package com.reservationapp.util;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.reservationapp.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public byte[] generatePdfFromPassenger(Passenger p) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        document.add(new Paragraph("Passenger Details"));
        document.add(new Paragraph("First Name: " + p.getFirstName()));
        document.add(new Paragraph("Last Name: " + p.getLastName()));
        document.add(new Paragraph("Email: " + p.getEmail()));
        document.add(new Paragraph("Mobile: " + p.getMobile()));
        document.add(new Paragraph("Bus ID: " + p.getBusId()));
        document.add(new Paragraph("Route ID: " + p.getRouteId()));
        document.close();

        return baos.toByteArray();
    }
}
