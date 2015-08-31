package edu.pitt.is17.npp9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PhoneBook {
    //contacts are stored in an ArrayList
    private ArrayList <Contact> contacts = new ArrayList<Contact>();
    
    //returns the contacts to be used in the tester file
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    
    //saves the contacts and their data to the text file
    public void saveFile(String filename){
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i=0;i<contacts.size();i++) {
                bw.write(contacts.get(i).getFirstName() + "," + contacts.get(i).getLastName() + ",");
                for(int j = 0; j < contacts.get(i).getPhones().size(); j++ ){
                    bw.write(contacts.get(i).getPhones().get(j).getPhoneType() + "/" + contacts.get(i).getPhones().get(j).getPhoneNumber()); 
                    if (j< (contacts.get(i).getPhones().size())-1){
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //searches for a contact based on a string and returns it as a contact
    public Contact searchContact(String name){
        for(int i=0;i<contacts.size();i++){
            if((contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName()).equals(name)){
                return contacts.get(i);
            }
        }
        JOptionPane.showMessageDialog(null, name + " was not found.\nSearch is case sensitive!");
        return null;
    }
    
    //lists the phones for a contact when double clicked on in the list
    public void searchName(String name) {
        String phones = "";
        for(int i=0;i<contacts.size();i++) {
            if((contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName()).equals(name)){
                phones += contacts.get(i).getPhones().size() + " Phone(s) for "+ contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName() + ":\n----------\n";
                for(int j = 0; j < contacts.get(i).getPhones().size(); j++ ){
                    phones += contacts.get(i).getPhones().get(j).getPhoneType() + ": " + contacts.get(i).getPhones().get(j).getPhoneNumber() + " " + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, phones);
    }
    
    //used to delete a contact when the delete button is clicked
    public void deleteContact(String name) {
        int count = 0;
        for(int i=0;i<contacts.size();i++){
            if((contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName()).equals(name)){
                JOptionPane.showMessageDialog(null, contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName() + " deleted.");
                contacts.remove(i);
                count++;
            }
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(null, name + " was not found.\nSearch is case sensitive!");
        }
    }
    
    //contact setter
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    //adds contact to the list of contacts
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
    
    //deletes contact from the list of contacts
    public void deleteContact(Contact contact){
        contacts.remove(contact);
    }
}
