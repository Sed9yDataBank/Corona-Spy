package com.corona.spyvirus.service;

import com.corona.spyvirus.model.Nuntius;

public interface TwilioSMS {

    void sendSMS(Nuntius nuntius);
}
