package edu.pitt.is17.npp9;

//All of the needed modules are imported
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PhoneBook_Tester{
    
    //GUI elements are declared and set to private
    private JFrame myWindow;
    private JFrame addDeletePhoneWindow;
    private JFrame multiplePhoneWindow;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete1;
    private JButton buttonDelete2;
    private JButton buttonSave;
    private JTextField text1;
    private JList list1;
    private DefaultListModel model;
    final PhoneBook book = new PhoneBook();
    
    public PhoneBook_Tester(){
        
        //list1 is created via a model
        list1 = new JList();
        model = new DefaultListModel();
        list1.setModel(model);
        
        ArrayList<String> rawContacts = readFileLineByLine("data/Contacts.txt");
        
        Contact newContact = null;
        
        for (String s : rawContacts) {   //Contacts are created from the ArrayList which holds the read file data
            String contactString = s;
            String [] contactArray = contactString.trim().split(",");
            
            String firstName = contactArray[0];
            String lastName = contactArray[1];
            
            newContact = new Contact(firstName, lastName);
            book.addContact(newContact);
            model.addElement(firstName + " " + lastName);
            
            for (int i = 2; i < contactArray.length; i++){ //Phones are created and added for the contacts
                String wholePhone = contactArray[i];
                String [] wholePhoneArray = wholePhone.trim().split("/");
                
                String phoneType = wholePhoneArray[0];
                
                String phoneNumber = wholePhoneArray[1];
                    
                Phone newPhone = new Phone(phoneNumber, phoneType);
                newContact.addPhone(newPhone);
            }
        }
        
        //The GUI is built here (window and buttons)
        myWindow = new JFrame("I.S. School Phonebook");
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(false);
        myWindow.setBounds(100,100,390,370);
        myWindow.setLayout(null);
        
        JLabel addAnotherPhone = new JLabel("Enter a FIRST and LAST name to add or delete:");
        addAnotherPhone.setBounds(55,0,310,50);
        
        text1 = new JTextField();
        text1.setBounds(65,40,270,25);
        
        //add button is created and given an action when clicked on
        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                String fullName = text1.getText();
                if (fullName != null){
                    addContact(fullName);
                    model.addElement(fullName);
                }
                else{
                    System.out.println("Please enter a FIRST and LAST name.");
                }
            }
        });     
        
        //delete button is created and given an action when clicked on
        buttonDelete1 = new JButton("Delete");
        buttonDelete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                
                String fullName = text1.getText();
                model.removeElement(fullName);
                book.deleteContact(fullName);
            }
        });      
        
        //save button is created and given an action when clicked on
        buttonSave = new JButton("Save File");
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                book.saveFile("data/Contacts.txt");
            }
        });  
        
        //edit button is created and given an action when clicked on
        buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                final String fullName = text1.getText();
                addDeletePhoneWindow = new JFrame("Edit Phones");
                addDeletePhoneWindow.setResizable(false);
                addDeletePhoneWindow.setBounds(200,200,350,150);
                addDeletePhoneWindow.setLayout(null);
                JLabel addAnotherPhone = new JLabel("Add or Delete a phone for " + fullName);
                addAnotherPhone.setBounds(30,20,310,50);
                final Contact contact = book.searchContact(fullName);
                if(contact!=(null)){
               
                    //delete button is created and given an action when clicked on
                    buttonDelete2 = new JButton("Delete");
                    buttonDelete2.setBounds(50,70,100,25);
                    buttonDelete2.addActionListener(new ActionListener() {
                    
                        public void actionPerformed(ActionEvent e)
                        {
                            addDeletePhoneWindow.dispose();
                            
                            String phoneName = JOptionPane.showInputDialog("Enter the phone type you want to delete: ");
                            Contact contact = book.searchContact(fullName);
                            contact.deletePhone(contact, phoneName);
                        }
                    });
                    
                    //add button is created and given an action when clicked on
                    buttonAdd = new JButton("Add");
                    buttonAdd.setBounds(200,70,100,25);
                    buttonAdd.addActionListener(new ActionListener() {
                    
                        public void actionPerformed(ActionEvent e)
                        {
                            addDeletePhoneWindow.dispose();
                        
                            String phoneNumber = JOptionPane.showInputDialog("Enter the phone number you want to add: ");
                            String phoneType = JOptionPane.showInputDialog("Enter the phone type you want to add: ");
                            Phone newPhone = new Phone(phoneNumber, phoneType);
                            contact.addPhone(newPhone);
                        }
                    });  
                //elements are added to the window
                addDeletePhoneWindow.add(addAnotherPhone);
                addDeletePhoneWindow.add(buttonDelete2);
                addDeletePhoneWindow.add(buttonAdd);
                addDeletePhoneWindow.setVisible(true);
                }
            }
        });  
        
        //boarders are set for the buttons
        buttonAdd.setBounds(30,70,100,25);
        buttonDelete1.setBounds(145,70,100,25);
        buttonEdit.setBounds(260,70,100,25);
        buttonSave.setBounds(110,100,170,25);
        
        //boarder is set for the list
        list1.setBounds(20,135,350,195);
        list1.setBorder(BorderFactory.createLineBorder(Color.black));
        list1.addMouseListener(new MouseAdapter() { //handles the double clicking of the list items
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    String name = (String) list.getSelectedValue();
                    book.searchName(name);
                }
            }
        });
        //elements are added to the window and the window is made visible
        myWindow.add(text1);
        myWindow.add(buttonAdd);
        myWindow.add(buttonDelete1);
        myWindow.add(buttonEdit);
        myWindow.add(buttonSave);
        myWindow.add(list1);
        myWindow.add(addAnotherPhone);
        myWindow.setVisible(true);
    }
    
    //reads the file and puts each line into an array
    public static ArrayList<String> readFileLineByLine(String filename) { 
        try {
            FileReader fileReader = new FileReader(filename); 
            BufferedReader bufferedReader = new BufferedReader(fileReader); 
           
            String line = null;
            
            ArrayList <String> rawContacts = new ArrayList<String>();
            
            while ((line = bufferedReader.readLine()) != null) {
                rawContacts.add(line);
            }
            bufferedReader.close();
            fileReader.close();
            return rawContacts;
        } 
        catch (IOException e) { 
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //creates a new contact when the add button is clicked
    public void addContact(String contactName) {
        
        //creates a contact from the string via splitting and putting the values into an array
        String [] contactArray = contactName.split(" ");
        String firstName = contactArray[0];
        String lastName = contactArray[1];
        
        if (contactArray[1] != null){
            Contact newContact = null;
            
            newContact = new Contact(firstName, lastName);
            book.addContact(newContact);
            
            addPhone(newContact);
        }
        else{
            System.out.println("Please enter a FIRST and LAST name.");
        }
        
        list1 = new JList(contactArray);
    }
    
    //lets the user add another phone for the contact
    public void addPhone(final Contact newContact) {

        multiplePhoneWindow = new JFrame("Add Another Phone?");
        multiplePhoneWindow.setResizable(false);
        multiplePhoneWindow.setBounds(200,200,350,150);
        multiplePhoneWindow.setLayout(null);
        JLabel addAnotherPhone = new JLabel("Add another phone for " + newContact.getFirstName() + " " + newContact.getLastName() + "?");
        addAnotherPhone.setBounds(30,20,310,50);
        
        String phoneNum = JOptionPane.showInputDialog("Enter the phone number you want to add: ");
        String phoneType = JOptionPane.showInputDialog("Enter the phone's type: ");
        
        Phone newPhone = new Phone(phoneNum, phoneType);
        
        newContact.addPhone(newPhone);
        
        //yes button is created and given an action when clicked on
        buttonYes = new JButton("Yes");
        buttonYes.setBounds(50,70,100,25);
        buttonYes.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                multiplePhoneWindow.dispose();
                //calls the addPhone method and passes in the current contact
                addPhone(newContact);
            }
        });     
        
        //no button is created and given an action when clicked on
        buttonNo = new JButton("No");
        buttonNo.setBounds(200,70,100,25);
        buttonNo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                multiplePhoneWindow.dispose();
            }
        });  
        
        //elements are added to the window and the window is made visible
        multiplePhoneWindow.add(addAnotherPhone);
        multiplePhoneWindow.add(buttonYes);
        multiplePhoneWindow.add(buttonNo);
        multiplePhoneWindow.setVisible(true);
        
    }

    public static void main(String[] args) {
        new PhoneBook_Tester();  //Starts the program
    }
}
