/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import static registerlogin.User.SQLPass;
/**
 *
 * @author kivilcimcumbul
 */
public class Challenge {
    // Object variables
    private User winner;
    private User loser;
    private Date time = new Date();
    
    // Database Connection Variables
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    
    //Constuctor
    Challenge (User winner, User loser, Date t){ 
        this.winner = winner;
        this.loser = loser;
        this.time = t;
    }
    
    // Getters
    public User getWinner(){
        return this.winner;
    }
    
    public User getLoser (){
        return this.loser;
    }
    
    public Date getDate(){
        return this.time;
    }
    
    
    // Method for connecting to database
    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Game?user=root&password="+SQLPass+"&useSSL=false");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Method to close DB connection
    public static void close(){
        try{
            if (rs!=null){
                    rs.close();
                    rs = null;
            }
            if(conn != null){
                    conn.close();
                    conn = null;
            }
            if(ps != null ){
                    ps = null;
            }
        }
        catch(SQLException sqle){
            System.out.println("Connection close error");
            sqle.printStackTrace();
        }
    }
    
    //Connect to DB and add to Challenge to DB
     boolean addToDB(){
        // Connect to DB
        connect();
        try {
            ps = conn.prepareStatement("INSERT IGNORE INTO Game.Challenge(winnerID,loserID,cTime)" +
            " values(?, ?, ?);");
            ps.setInt(1, winner.getUserID());
            ps.setInt(2, loser.getUserID());
            ps.setString(3, this.time.toString());
            ps.executeUpdate();
            System.out.println("Challenge added");
            return true;

        } catch (SQLException e) {
            System.out.println("SQLException in function \"challenge addtodb\"");
            e.printStackTrace();
            return false;
        }finally{
            close();
        }
        
    }
}
