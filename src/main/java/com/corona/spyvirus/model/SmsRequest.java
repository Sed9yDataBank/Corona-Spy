package com.corona.spyvirus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SmsRequest {

    @NotBlank
    private final String phoneNumber; // destination

    @NotBlank
    private final String state;

    @NotBlank
    private final String country;

    @NotBlank
    private final String physicalAddress;

    @NotBlank
    private final String message;

    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("state") String state,
                      @JsonProperty("country") String country,
                      @JsonProperty("physicalAddress") String physicalAddress,
                      @JsonProperty("message") String message
                      ) {
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
        this.physicalAddress = physicalAddress;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", physicalAddress='" + physicalAddress + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
