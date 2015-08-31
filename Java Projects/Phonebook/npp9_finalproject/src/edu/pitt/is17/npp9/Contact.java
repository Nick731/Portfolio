package edu.pitt.is17.npp9;

import java.util.ArrayList;

public class Contact {
    //attributes for the contacts
    private String lastName;
    private String firstName;
    //Phones are stored in an array list
    private ArrayList <Phone> phones = new ArrayList<Phone>();
    
    //returns all phones
    public ArrayList<Phone> getPhones() {
        return phones;
    }
    
  //constructor for the contact
    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //deletes the desired phone for a specific contact
    public void deletePhone(Contact contact, String phoneName) {
        for(int j = 0; j < contact.getPhones().size(); j++ ){
            if(contact.getPhones().get(j).getPhoneType().equals(phoneName)){
                Phone phone = contact.getPhones().get(j);
                contact.deletePhone(phone);
            }
        }
    }
    
    //adds a phone
    public void addPhone(Phone phone){
        phones.add(phone);
    }

    //sets a phone
    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }
    
    //deletes a phone
    public void deletePhone(Phone phone){
        phones.remove(phone);
    }

    //returns the last name
    public String getLastName() {
        return lastName;
    }

    //sets the last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //returns the first name
    public String getFirstName() {
        return firstName;
    }

    //sets the first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
