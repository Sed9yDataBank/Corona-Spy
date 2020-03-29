package com.corona.spyvirus.controller;

import com.corona.spyvirus.service.TwilioService;
import com.corona.spyvirus.model.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/report")
public class TwilioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioController.class);
    private final TwilioService service;

    @Autowired
    public TwilioController(TwilioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showReportPage() {
        return "report";
    }

    @PostMapping("/sms")
    public String sendSms(@Valid SmsRequest smsRequest) {
        if (smsRequest != null) {
            LOGGER.info("Initializing Twilio SMS ...");
            LOGGER.info("User Phone Number: {}", smsRequest.getPhoneNumber());
            service.sendSms(smsRequest);
        }
        else {
            LOGGER.info("Invalid SMS Request");
        }
        return "redirect: ";
    }
}

