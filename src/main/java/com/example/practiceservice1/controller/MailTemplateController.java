package com.example.practiceservice1.controller;

import com.example.practiceservice1.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(path = "/api/mail")

public class MailTemplateController {

    @Autowired
    public MailTemplateService mailTemplateService;

    @PostMapping("/sentTemplatePdf")
    public ResponseEntity<Boolean> sentTemplatePdf() throws IOException {
        Boolean result = mailTemplateService.sentTemplatePdf();
        return ResponseEntity.ok().body(result);
    }
}
