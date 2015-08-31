/**
* The User.java program is used to access or set information involving the user class.
*
* @author  Nick Peters
* @version 1.0
* @since   2015-01-31 
*/
package edu.pitt.core;

import edu.pitt.utilities.DbUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* The user class is defined. 
*/
public class User {
    //This private attributes of the user class are defined.
    private int userID;
    private String userName;
    private String userPassword;
    private int userLevel;
    private boolean isValidUser = false;

    /**
     * This function receives the userId. It then creates a query with the user id.
     * The query is then sent to the setAllUserProperties function.
     */
    public User(int userID){
        String sql = "SELECT * FROM users WHERE userID = " + userID + "";
        setAllUserProperties(sql);
    }
    
    /**
     * This function receives the email address and password. These items are then used to form a query.
     * The query is then sent to the setAllUserProperties function.
     */
    public User(String userName, String userPassword){
        String sql = "SELECT * FROM users WHERE userName = '" + userName + "' AND userPassword = '" + userPassword + "'";
        setAllUserProperties(sql);
    }
    
    /**
     * The attributes of the user object are set based on the values retrieved from the database
     * after running a query that is passed in from either of the user functions.
     */
    private void setAllUserProperties(String sql){
        System.out.println(sql);
        DbUtilities db = new DbUtilities();
        try {
            ResultSet rs = db.getResultSet(sql);
            while(rs.next()){
                this.userID = rs.getInt("userID");
                this.userName = rs.getString("userName");
                this.userPassword = rs.getString("userPassword");
                this.userLevel = rs.getInt("userLevel");
                this.isValidUser = true;
            }
            db.closeDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return the email
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    /**
     * @return the userLevel
     */
    public int getUserLevel() {
        return userLevel;
    }
    
    /**
     * @return the boolean isValidUser
     */
    public boolean getIsValidUser() {
        return this.isValidUser;
    }
    
}