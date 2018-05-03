package com.codecool.shop.model;

public class Checkout {

    private int userId;
    private String name;
    private String email;
    private String telephone;
    private String billCountry;
    private String billCity;
    private String billZipcode;
    private String billAddress;
    private String shipCountry;
    private String shipCity;
    private String shipZipcode;
    private String shipAddress;

    public Checkout(int userId, String name, String email, String telephone, String billCountry, String billCity, String billZipcode, String billAddress, String shipCountry, String shipCity, String shipZipcode, String shipAddress) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.billCountry = billCountry;
        this.billCity = billCity;
        this.billZipcode = billZipcode;
        this.billAddress = billAddress;
        this.shipCountry = shipCountry;
        this.shipCity = shipCity;
        this.shipZipcode = shipZipcode;
        this.shipAddress = shipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillZipcode() {
        return billZipcode;
    }

    public void setBillZipcode(String billZipcode) {
        this.billZipcode = billZipcode;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipZipcode() {
        return shipZipcode;
    }

    public void setShipZipcode(String shipZipcode) {
        this.shipZipcode = shipZipcode;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", billCountry='" + billCountry + '\'' +
                ", billCity='" + billCity + '\'' +
                ", billZipcode='" + billZipcode + '\'' +
                ", billAddress='" + billAddress + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipZipcode='" + shipZipcode + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                '}';
    }
}
