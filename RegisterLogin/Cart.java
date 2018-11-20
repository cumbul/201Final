/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.util.*;
import java.sql.*;
import static registerlogin.User.SQLPass;

public class Cart {
    
    // Class variables
    User user = null;
    ArrayList<Character> characters;
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
    // Constructor
    Cart(User u){
        this.user = u;
        characters = new ArrayList<Character>();
    }
    // Add to Database
    boolean addToDB(){
       connect();
       for (int i = 0; i < characters.size();i++){
            try {
                ps = conn.prepareStatement("INSERT IGNORE INTO Game.Cart(userID,characterID)" +
                "values(?, ?);");
                ps.setInt(1, user.getUserID());
                ps.setInt(2, characters.get(i).getCharacterID());
                ps.executeUpdate();
                System.out.println("added cart to DB");
            } catch (SQLException e) {
                System.out.println("SQLException in function \"cart\"");
                e.printStackTrace();
                return false;
            }finally{
                close();
            }
        }
        return true;
    }
    //Methods of the class from detailed design
    boolean addCharacter(Character c){
        characters.add(c);
        // Add to Database
        connect();
        try {
                ps = conn.prepareStatement("INSERT IGNORE INTO Game.Cart(userID,characterID)" +
                "values(?, ?);");
                ps.setInt(1, user.getUserID());
                ps.setInt(2, c.getCharacterID());
                ps.executeUpdate();
                System.out.println("added character to the cart to DB");
            } catch (SQLException e) {
                System.out.println("SQLException in function \"cart add character\"");
                e.printStackTrace();
                return false;
            }finally{
                close();
            }
        return true;
    }
    
    boolean removeCharacter(Character c){
        //Remove from characters set
        characters.remove(c);
        // Remove from the database
        try {
                ps = conn.prepareStatement("DELETE FROM Game.Cart WHERE userID=? and characterID=?;");
                ps.setInt(1, user.getUserID());
                ps.setInt(2, c.getCharacterID());
                ps.executeUpdate();
                System.out.println("removed character from the cart in DB");
            } catch (SQLException e) {
                System.out.println("SQLException in function \"cart remove character\"");
                e.printStackTrace();
                return false;
            }finally{
                close();
            }
        return true;
    }
    // I ignored method isInCart() -- Useless for now
}
