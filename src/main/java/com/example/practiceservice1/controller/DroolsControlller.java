package com.example.practiceservice1.controller;

import com.example.practiceservice1.config.DroolsConfig;
import com.example.practiceservice1.payload.DroolsLoanDetailsPayload;
import com.example.practiceservice1.service.DroolsService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequestMapping(path = "/api/drools")
public class DroolsControlller {


    @Autowired
    DroolsService droolsService;


    @PostMapping("/getLoanDetail")
    public ResponseEntity<DroolsLoanDetailsPayload> sentTemplatePdf() throws IOException {
        DroolsLoanDetailsPayload result = droolsService.getValue();
        return ResponseEntity.ok().body(result);
    }


}
