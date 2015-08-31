package edu.pitt.is17.npp9;

public class Phone {
    //Attributes for the phones
    private String phoneNumber;
    private String phoneType;
    
    //returns the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    //sets the phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //returns the phone type
    public String getPhoneType() {
        return phoneType;
    }

    //sets the phone type
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
    
    //constructor for the phone
    public Phone(String number, String type) {
        this.phoneNumber = number;
        this.phoneType = type;
    }
}
