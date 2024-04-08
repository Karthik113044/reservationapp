package com.reservationapp.util;


import com.reservationapp.entity.Passenger;
import com.reservationapp.repository.PassengerRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class EmailService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailWithAttachment(String to, String subject, byte[] pdfBytes, String fileName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("Please find the attached PDF.");

        ByteArrayResource byteArrayResource = new ByteArrayResource(pdfBytes);

        // Add the attachment using the DataSource
        helper.addAttachment(fileName, byteArrayResource);

        emailSender.send(message);
    }

    public void sendEmailWithExcelAttachment(String to, String subject) throws MessagingException, IOException {
        List<Passenger> passengerList = passengerRepository.findAll(); // Fetch reservations from the database

        // Create Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Passengers List");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("last Name");
        headerRow.createCell(3).setCellValue("Mobile");
        headerRow.createCell(4).setCellValue("Email");
        headerRow.createCell(5).setCellValue("Bus Id");
        headerRow.createCell(6).setCellValue("Route Id");


        // Create data rows
        int rowNum = 1;
        for (Passenger passenger : passengerList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(passenger.getId());
            row.createCell(1).setCellValue(passenger.getFirstName());
            row.createCell(2).setCellValue(passenger.getLastName());
            row.createCell(3).setCellValue(passenger.getMobile());
            row.createCell(4).setCellValue(passenger.getEmail());
            row.createCell(5).setCellValue(passenger.getBusId());
            row.createCell(6).setCellValue(passenger.getRouteId());
        }

        // Write workbook content to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        byte[] excelData = outputStream.toByteArray();

        // Convert byte[] to DataSourc
        // Create a ByteArrayDataSource using the Excel data
        DataSource dataSource = new ByteArrayDataSource(excelData,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");


        // Create MimeMessage and MimeMessageHelper
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("Please find the attached Excel file.");

        // Add Excel file as attachment
        helper.addAttachment("reservations.xlsx", dataSource);

        // Send email
        emailSender.send(message);

        // Close workbook
        workbook.close();
    }
}
