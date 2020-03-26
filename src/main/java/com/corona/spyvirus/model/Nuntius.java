package com.corona.spyvirus.model;

import javax.validation.constraints.NotBlank;

public class Nuntius {

    //Receiver
    @NotBlank
    private final String phoneNumber;

    //Province State
    @NotBlank
    private final String state;

    //Country Region
    @NotBlank
    private final String country;

    //Hood Coordination
    private final String address;

    @NotBlank
    private final String text;

    public Nuntius(@NotBlank String phoneNumber, @NotBlank String state, @NotBlank String country,
                   String address, @NotBlank String text) {
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
        this.address = address;
        this.text = text;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getText() {
        return text;
    }
}
