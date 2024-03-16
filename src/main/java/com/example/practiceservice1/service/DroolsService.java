package com.example.practiceservice1.service;

import com.example.practiceservice1.config.DroolsConfig;
import com.example.practiceservice1.payload.DroolsLoanDetailsPayload;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {
    @Autowired
    DroolsConfig droolsConfig;
    public DroolsLoanDetailsPayload getValue(){
        DroolsLoanDetailsPayload droolsLoanDetailsPayload = new DroolsLoanDetailsPayload();
        droolsLoanDetailsPayload.setAmount(51000);
        try{
            KieContainer kiviKieContainer= droolsConfig.kieContainer("rules/loanDetail.xlsx");
            KieSession kieSession = kiviKieContainer.newKieSession();
            kieSession.insert(droolsLoanDetailsPayload);
            kieSession.fireAllRules();
            kieSession.dispose();
        }
        catch (Exception e){
            e.getMessage();
        }
        return droolsLoanDetailsPayload;
    }
}
