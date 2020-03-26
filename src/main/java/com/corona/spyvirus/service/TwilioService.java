package com.corona.spyvirus.service;

import com.corona.spyvirus.model.Nuntius;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioSMS twilioSMS;

    @Autowired
    public TwilioService(@Qualifier("twilio_service") TwilioSMS twilioSMS) {
        this.twilioSMS = twilioSMS;
    }

    public void sendSMS(Nuntius nuntius) {

        twilioSMS.sendSMS(nuntius);
    }


}
