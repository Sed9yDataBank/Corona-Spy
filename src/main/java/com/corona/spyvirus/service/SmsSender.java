package com.corona.spyvirus.service;

import com.corona.spyvirus.model.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}
