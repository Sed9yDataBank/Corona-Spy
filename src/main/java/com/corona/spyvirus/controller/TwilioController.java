package com.corona.spyvirus.controller;

import com.corona.spyvirus.model.Nuntius;
import com.corona.spyvirus.service.TwilioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/report")
public class TwilioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioController.class);
    private final TwilioService twilioService;

    @Autowired
    public TwilioController(TwilioService twilioService){
        this.twilioService = twilioService;
    }

    @GetMapping("/")
    public String showReportPage() {
        return "report";
    }

    @PostMapping("/sms")
    public String sendSMS(@ModelAttribute("Nuntius") Nuntius nuntius, Model model){

        if (nuntius != null){
            LOGGER.info("Initializing Twilio SMS ...");
            LOGGER.info("User Phone Number: {}", nuntius.getPhoneNumber());
            twilioService.sendSMS(nuntius);

        }else{
            LOGGER.info("Invalid SMS Request");
        }
        return "redirect: ";
    }
}
