package com.corona.spyvirus.service.implementation;

import com.corona.spyvirus.configuration.TwilioConfig;
import com.corona.spyvirus.model.Nuntius;
import com.corona.spyvirus.service.TwilioSMS;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("twilio_service")
public class TwilioSMSImplementation implements TwilioSMS {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSMSImplementation.class);
    private final TwilioConfig twilioConfiguration;

    @Autowired
    public TwilioSMSImplementation(TwilioConfig twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSMS(Nuntius nuntius) {

        if (isPhoneNumberValid(nuntius.getPhoneNumber())) {

            PhoneNumber to = new PhoneNumber(nuntius.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());

            String address = nuntius.getAddress();
            String text = nuntius.getText()  + "\n Adress: " + address;
            MessageCreator creator = Message.creator(to, from, text);
            creator.create();
            LOGGER.info("Send SMS {} " + nuntius);
        }
        else {

            throw new IllegalArgumentException("Phone Number ["+nuntius.getPhoneNumber()+"] is not valid");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //Implement Phone Number Validation
        return true;
    }
}
