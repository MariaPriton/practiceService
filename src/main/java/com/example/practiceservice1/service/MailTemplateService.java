package com.example.practiceservice1.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service

public class MailTemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;
    public Boolean sentTemplatePdf() throws IOException {
        String generatedTemplate=generateTemplate();
        if (generatedTemplate!=null){
            File convertedPdf =convertHtmlToPdfByteArray(generatedTemplate);
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
                helper.setTo("mariapriton@gmail.com");
                helper.setSubject("subject");


                helper.setText(generatedTemplate,true);


                File byteToFile=convertBytesToFile(convertedPdf);
                helper.addAttachment("templatePdf.pdf", byteToFile);

                mailSender.send(helper.getMimeMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Boolean.TRUE;
    }

    public static String byteArrToBase64String(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
    public String generateTemplate(){
        try {
            Context context = new Context();
            File file = Paths.get("C:\\Users\\mariapriton\\Pictures\\Camera Roll\\priton.jpg").toFile();
            String convertedBase=byteArrToBase64String(Files.readAllBytes(file.toPath()));
            context.setVariable("base64Image",convertedBase);
            List<String>list = new ArrayList<>();
            list.add("Priton");
            list.add("Surya");
            String content = templateEngine.process("SAMPLE.html", context);
            return content;
        }
        catch (Exception e){
            return null;
        }

    }
    public static File convertHtmlToPdfByteArray(String htmlFilePath) throws IOException {
       /* try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // HTML input file
            File htmlFile = new File(htmlFilePath);

            // Convert HTML to PDF
            ConverterProperties properties = new ConverterProperties();
            HtmlConverter.convertToPdf(new FileInputStream(htmlFile), baos, properties);

            return baos.toByteArray();
        }*/
        File tempFile = File.createTempFile("temp", ".html");
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println(htmlFilePath);
        }
        return tempFile;
    }
    public File convertBytesToFile(File file) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Convert HTML to PDF
            ConverterProperties properties = new ConverterProperties();
            HtmlConverter.convertToPdf(new FileInputStream(file), baos, properties);
            File tempFile = File.createTempFile("templatePdf", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(baos.toByteArray());
            }
            return tempFile;

        }

    }
}
