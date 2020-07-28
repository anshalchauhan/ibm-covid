package com.example.ibmhackathon2020;

public class AdmRegister {
    private String SellerId, OwnerName, ShopName,Location, Emailid, Password;
    private Integer PinCode,PhoneNo;

    public AdmRegister() {
    }

    public String getSellerId() {
        return SellerId;
    }

    public void setSellerId(String sellerId) {
        SellerId = sellerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getPinCode() {
        return PinCode;
    }

    public void setPinCode(Integer pinCode) {
        PinCode = pinCode;
    }

    public Integer getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        PhoneNo = phoneNo;
    }
}
