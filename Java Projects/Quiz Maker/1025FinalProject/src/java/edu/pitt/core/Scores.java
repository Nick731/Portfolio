/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.core;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.StringUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author mattk
 */
public class Scores {
    
    private int userID;
    private String username;
    private int quizID;
    private String quizName;
    private int scoreValue;
    
    private ArrayList<Scores> scoreList;
    
     public JSONObject toJSON(){
        JSONObject score = new JSONObject();
        JSONArray scoreListJSON = new JSONArray();
        
        try{
            score.put("userID", userID);
            score.put("username", username);
            score.put("quizID", quizID);
            score.put("quizName", quizName);
            score.put("score", scoreValue);
            
            for(Scores s : scoreList){
               scoreListJSON.put(s.toJSON());
            }
            score.put("scoreList", scoreListJSON);
            
            
        }catch (JSONException ex) {
            Logger.getLogger(Scores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
        
     }
     
     public Scores(int userID) {
        //birdList = new ArrayList <Bird>();
        String sql = "SELECT users_userID, userName, quizes_quizID, quizName, score FROM users_quiz uq\n" +
                        "LEFT JOIN users u ON  u.userID = uq.users_userID\n" +
                        "LEFT JOIN quizes q ON q.quizID = uq.quizes_quizID WHERE userID = "+ userID +"";
        DbUtilities db = new DbUtilities();

        System.out.println(sql);
        try {
            ResultSet rs = db.getResultSet(sql);
            while (rs.next()) {

                this.userID = rs.getInt("users_userID");
                this.username = rs.getString("userName");
                this.quizID = rs.getInt("quizes_quizID");
                this.quizName = rs.getString("quizName");
                this.scoreValue = rs.getInt("score");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //loadBirdList();
    }
    

     public int getuserID(){
         return userID;
     }
     
     public String getUsername(){
         return username;
     }
     
     public int getQuizID(){
         return quizID;
     }
     
     public String getQuizName(){
         return quizName;
     }
     
     public int getScoreValue(){
         return scoreValue;
     }
  
    
}
