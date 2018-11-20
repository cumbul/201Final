/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.util.*;
import java.sql.*;
import static registerlogin.User.SQLPass;
public class Character {
    
    // Class variables
    private String cName = "";
    private String description = "";
    private int price = 0;
    private String imgURL = "";
    private int characterID = 0;
    
    // Database Connection Variables
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    
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
    // Close the database objects
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
    
    //Constructor
    Character(int id, String cName, String desc, int price, String imgURL){
        this.characterID = id;
        this.cName = cName;
        this.description = desc;
        this.price = price;
        this.imgURL = imgURL;
    }
    // Add character to db
    boolean addToDB(){
        connect();
        try {
            ps = conn.prepareStatement("INSERT IGNORE INTO Game.Characters(cName,description,price,imageURL)" +
            " values(?, ?, ?, ?);");
            ps.setString(1, this.cName);
            ps.setString(2, this.description);
            ps.setInt(3, this.price);
            ps.setString(4, this.imgURL);
            ps.executeUpdate();
            System.out.println("added character to DB");
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException in function \"character\"");
            e.printStackTrace();
            return false;
        }finally{
            close();
        }
    }
    
    //Getters
    String getCname(){
        return this.cName;
    }
    String getDescription(){
        return this.description;
    }
    String getImage(){
        return this.imgURL;
    }
    int getPrice(){
        return this.price;
    }
    int getCharacterID(){
        connect();
        int charID = 0;
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.Characters where cName = ?;");
                ps.setString(1, cName);
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        if(rs.next())
                        {
                            charID = rs.getInt("characterID");
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"get characterid by Cname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return charID;
    }
    //Setters
    Boolean setDescription(String desc){
        this.description = desc;
        // Update description in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.Characters SET description = ? WHERE (cName = ?)");
                ps.setString(1, this.description);
                ps.setString(2, this.cName);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set description\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    Boolean setImage(String url){
        this.imgURL = url;
        // Update image in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.Characters SET imageURL = ? WHERE (cName = ?)");
                ps.setString(1, this.imgURL);
                ps.setString(2, this.cName);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set imgURL\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    Boolean setPrice(int price){
        this.price = price;
        // Update price in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.Characters SET price = ? WHERE (cName = ?)");
                ps.setInt(1, this.price);
                ps.setString(2, this.cName);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set price\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
        
    }
}
