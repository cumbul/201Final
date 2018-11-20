/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.util.*;
import java.sql.*;
import static registerlogin.User.SQLPass;

public class UserDriver {
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
    User makeUserObjFromID(int id){//takes in user id as parameter, and returns a user object for that id
        connect();
        User user = null;
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.User where userID = ?;");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        if(rs.next())
                        {
                            String fname = rs.getString("fname");
                            String lname = rs.getString("lname");
                            String uname = rs.getString("username");
                            String email = rs.getString("email");
                            String pass = rs.getString("pass");
                            String url = rs.getString("imageURL");
                            String sq1 = rs.getString("securityQ1");
                            String sa1 = rs.getString("securityA1");
                            String sq2 = rs.getString("securityQ2");
                            String sa2 = rs.getString("securityA2");
                            int numCoins = rs.getInt("coins");
                            int highScore = rs.getInt("highScore");
                            user = new User(fname, lname, uname, email,sq1, sq2, sa1, sa2, pass, url);
                            user.setCoin(numCoins);
                            user.setHighScore(highScore);
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }     
        return user;
    }
    User findByUname(String uname){
        // Connect to DB
        connect();
        User user = null;
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.User where username = ?;");
                ps.setString(1, uname);
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        if(rs.next())
                        {
                            String fname = rs.getString("fname");
                            String lname = rs.getString("lname");
                            String email = rs.getString("email");
                            String pass = rs.getString("pass");
                            String url = rs.getString("imageURL");
                            String sq1 = rs.getString("securityQ1");
                            String sa1 = rs.getString("securityA1");
                            String sq2 = rs.getString("securityQ2");
                            String sa2 = rs.getString("securityA2");
                            int numCoins = rs.getInt("coins");
                            int highScore = rs.getInt("highScore");
                            user = new User(fname, lname, uname, email,sq1, sq2, sa1, sa2, pass, url);
                            user.setCoin(numCoins);
                            user.setHighScore(highScore);
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return user;
    }
    User findByEmail(String email_){//does same thing as findByUname, but for email
        connect();
        User user = null;
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.User where email = ?;");
                ps.setString(1, email_);
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        if(rs.next())
                        {
                            String fname = rs.getString("fname");
                            String lname = rs.getString("lname");
                            String uname = rs.getString("username");
                            String email = rs.getString("email");
                            String pass = rs.getString("pass");
                            String url = rs.getString("imageURL");
                            String sq1 = rs.getString("securityQ1");
                            String sa1 = rs.getString("securityA1");
                            String sq2 = rs.getString("securityQ2");
                            String sa2 = rs.getString("securityA2");
                            int numCoins = rs.getInt("coins");
                            int highScore = rs.getInt("highScore");
                            user = new User(fname, lname, uname, email_,sq1, sq2, sa1, sa2, pass, url);
                            user.setCoin(numCoins);
                            user.setHighScore(highScore);
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return user;
    }
    ArrayList<User> getTopFifteenScores(){//returns array of top 15 scores from all users in the database
        ArrayList<User> topScores = new ArrayList<>();
        ArrayList<User> allUsers = getAllUsersInDatabase();
        int amount = 15;//int to iterate through all user's to get their scores
        if(allUsers.size() < 15)//less than 15 scores exists, so we will only display those 
        {//will fix nullpointer exception that would be thrown if size < 15
            amount = allUsers.size();
        }
        //sort the user array list by high score
        sortUsersByHighScore(allUsers);//now sort the topscores from greatest to least
        for(int i = 0; i < amount; i++)
        {
            topScores.add(allUsers.get(i));
        }
        
        return topScores;
    }
    public void sortUsersByHighScore(ArrayList<User> users){//sorts all the users in a list by high score. highest to lowest
        //uses bubble sort
        int n = users.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (users.get(j).getHighScore() <= users.get(j+1).getHighScore()) 
                { 
                    // swap temp and arr[i] 
                    User temp = users.get(j); 
                    users.set(j, users.get(j+1));
                    users.set(j+1, temp);
                }
    }
    ArrayList<Character> getAllCharactersInDatabase(){//will return a list of all the characters in database as objects
        connect();
        ArrayList<Character> allCharacters = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT * FROM Game.Characters;");
            rs = ps.executeQuery();
            if(!rs.equals(null))//found stuff!
                    {
                        while(rs.next())
                        {
                            int id = rs.getInt("characterID");
                            String cname = rs.getString("cName");
                            String description = rs.getString("description_");
                            int price = rs.getInt("price");
                            String imageURL = rs.getString("imageURL");
                            Character tempCharacter = new Character(id, cname, description, price, imageURL);
                            //make a character object for each character found in the database
                            allCharacters.add(tempCharacter);
                        }
                    }
        }catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return allCharacters;
    }
    ArrayList<User> getAllUsersInDatabase(){//returns a list of all the users in the database
        connect();
        ArrayList<User> allUsers = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT  * FROM Game.User;");
            rs = ps.executeQuery();
            if(!rs.equals(null))//found stuff!
                    {
                        while(rs.next())
                        {
                            String fname = rs.getString("fname");
                            String lname = rs.getString("lname");
                            String uname = rs.getString("username");
                            String email = rs.getString("email");
                            String pass = rs.getString("pass");
                            String url = rs.getString("imageURL");
                            String sq1 = rs.getString("securityQ1");
                            String sa1 = rs.getString("securityA1");
                            String sq2 = rs.getString("securityQ2");
                            String sa2 = rs.getString("securityA2");
                            int numCoins = rs.getInt("coins");
                            int highScore = rs.getInt("highScore");
                            User tempUser = new User(fname, lname, uname, email,sq1, sq2, sa1, sa2, pass, url);
                            //make a User object for each user found in the database
                            tempUser.setCoin(numCoins);
                            tempUser.setHighScore(highScore);
                            allUsers.add(tempUser);
                        }
                    }
        }catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        System.out.println("ALL USERS IN DATABASE ARE: ");
        for(int i = 0; i < allUsers.size(); i++)
        {
            System.out.println(allUsers.get(i).getUname());
        }
        return allUsers;
    }
    ArrayList<User> searchUsernames(String username){//should return list of all users with that name in common
        connect();
        ArrayList<User> usersFound = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT  * FROM Game.User WHERE username LIKE '%"+username+"%';");
            rs = ps.executeQuery();
            if(!rs.equals(null))//found stuff!
                    {
                        while(rs.next())
                        {
                            String fname = rs.getString("fname");
                            String lname = rs.getString("lname");
                            String uname = rs.getString("username");
                            String email = rs.getString("email");
                            String pass = rs.getString("pass");
                            String url = rs.getString("imageURL");
                            String sq1 = rs.getString("securityQ1");
                            String sa1 = rs.getString("securityA1");
                            String sq2 = rs.getString("securityQ2");
                            String sa2 = rs.getString("securityA2");
                            int numCoins = rs.getInt("coins");
                            int highScore = rs.getInt("highScore");
                            User tempUser = new User(fname, lname, uname, email,sq1, sq2, sa1, sa2, pass, url);
                            tempUser.setCoin(numCoins);
                            tempUser.setHighScore(highScore);
                            if(!uname.equals(StartUp.user.getUname()))//list returned does not include current user logged in
                            {
                                usersFound.add(tempUser);
                            }
                        }
                    }
        }catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return usersFound;
    }
    boolean usernameAndPasswordMatch(String password)//uses current user object that exists
    {
        if(StartUp.user.getPassword().equals(password))
        {
            return true;
        }
        return false;
    }
    Set <User> searchByUname(String search){
        Set <User> users = new HashSet<User>();
        return users;
     }
    Set <User> searchByFname(String search){
        Set <User> users = new HashSet<User>();
        return users;
    }
    Set <User> searchByLname(String search){
        Set <User> users = new HashSet<User>();
        return users;
    }
    Set <User> search(String search){
        Set<User> users1 = searchByUname(search);
        Set<User> users2 = searchByFname(search);
        Set<User> users3 = searchByLname(search);
        users1.addAll(users2);
        users1.addAll(users3);
        return users1;
    }
}
