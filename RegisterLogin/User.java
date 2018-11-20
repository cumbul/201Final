/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.util.*;
import java.sql.*;
public class User {
    //SQL PASS
    protected static String SQLPass = "csci201l";
    // Variables for User class
    private String fname = "";
    private String lname = "";
    private String uname = "";
    private String email = "";
    private String securityQ1 = ""; //hashed
    private String securityQ2 = ""; //hashed
    private String securityA1 = ""; //hashed
    private String securityA2 = ""; //hashed
    private String password = ""; //hashed
    int numCoins = 0;
    String pic = "";
    private ArrayList <User> friends;
    ArrayList <Challenge> challenges;
    ArrayList <Score> scores;
    private int highScore;
    private int charSelectedID = 1;//int to represent which character will be played as.
    //1 is default= Miller  2=Trojan  3=Sonic  4=Mario
    
    // Database Connection Variables
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    
    // Constructor -- No Default Constructor
    User(String fname, String lname, String uname, String email,String sq1, String sq2, String sa1, String sa2, String pass, String url){
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.email = email;
        this.securityQ1 = sq1;
        this.securityQ2 = sq2;
        this.securityA1 = sa1;
        this.securityA2 = sa2;
        this.password = pass;
        this.pic = url;
        this.friends = new ArrayList<>();
        this.scores = new ArrayList<>();//initialize scores list
        //set initial high score as 0
        this.highScore = 0;
        numCoins = 0;
    }
    // Getters
    String getFname(){
        return fname;
    }
    String getLname(){
        return lname;
    }
    String getUname(){
        return uname;
    }
    String getEmail(){
        return email;
    }
    String  getSecurityQ1(){
        return securityQ1;
    }
    String  getSecurityQ2(){
        return securityQ2;
    } 
    String  getSecurityA1(){
        return securityA1;
    }
    String  getSecurityA2(){
        return securityA2;
    }
    String getPassword(){
        return password;
    }
    String getPic(){
        return pic;
    }
    int getCoin (){
        return numCoins;
    }
    int getHighScore(){
        return highScore;
    }
    int getCharSelectedID(){
        return charSelectedID;
    }
    
    // Method for connecting to database
    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Game?user=root&password="+SQLPass+"&useSSL=false&allowPublicKeyRetrieval=true");
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
    //Register User to Database
    boolean register(){
        // Connect to DB
        connect();
        try {
            ps = conn.prepareStatement("INSERT IGNORE INTO Game.User(fname,lname,username,email,pass,imageURL,securityQ1,securityA1,securityQ2,securityA2,coins)" +
            " values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);");
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, pic);
            ps.setString(7, securityQ1);
            ps.setString(8, securityA1);
            ps.setString(9, securityQ2);
            ps.setString(10, securityA2);
            ps.setInt(11, 0);
            ps.executeUpdate();
            System.out.println("registered");
            return true;

        } catch (SQLException e) {
            System.out.println("SQLException in function \"register\"");
            e.printStackTrace();
            return false;
        }finally{
            close();
        }
        
    }
    
    // Get userID from Database
    int getUserID(){
        connect();
        int userID = 0;
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.User where username = ?;");
                ps.setString(1, uname);
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        if(rs.next())
                        {
                            userID = rs.getInt("userID");
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return userID;
    }
    
    ArrayList<Integer> getCharactersBought(){//returns characters this current user has purchased
        connect();
        ArrayList<Integer> charactersBought = new ArrayList<>();
        try {
                ps = conn.prepareStatement("SELECT  * FROM Game.Cart where userID = "+this.getUserID()+";");
                rs = ps.executeQuery();
                if(!rs.equals(null))
                    {
                        while(rs.next())
                        {
                            int characterID = rs.getInt("characterID");
                            charactersBought.add(new Integer(characterID));
                        }
                    }
        } catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return charactersBought;
    }
    
    
    // Setter - Update to Database
    boolean setFname (String fname){
        this.fname = fname;
        connect();
        // Update name in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET fname = ? WHERE (email = ?)");
                ps.setString(1, this.fname);
                ps.setString(2, this.email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set name\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean setLname (String lname){
        this.lname = lname;
        connect();
        // Update last name in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET lname = ? WHERE (email = ?)");
                ps.setString(1, this.lname);
                ps.setString(2, this.email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set last name\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean setUname (String uname){
        this.uname = uname;
        connect();
        // Update user name in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET uname = ? WHERE (email = ?)");
                ps.setString(1, this.uname);
                ps.setString(2, this.email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set user name\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean setPassword (String password){
        this.password = password;
        connect();
        // Update password in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET pass = ? WHERE (email = ?)");
                ps.setString(1, this.password);
                ps.setString(2, email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set password\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
       
    }
    boolean setPic (String pic){
        this.pic = pic;
        connect();
        // Update picture in Database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET imageURL = ? WHERE (email = ?)");
                ps.setString(1, this.pic);
                ps.setString(2, email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set picture\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean setCoin (int coin){
        this.numCoins = coin;
        connect();
        //Update coins in database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET coins = ? WHERE (email = ?);");
                ps.setInt(1, this.numCoins);
                ps.setString(2, email);
                ps.executeUpdate();
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set coins\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean setHighScore(int score){
        connect();
        //Update score in database
        try {
                ps = conn.prepareStatement("UPDATE Game.User SET highScore = ? WHERE (username = ?);");
                ps.setInt(1, score);
                ps.setString(2, this.uname);
                ps.executeUpdate();
                //update score in current user class
                this.highScore = score;
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set coins\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    boolean buyCharacter(Character character){//add character to the user's bought list in database
        connect();
        if(character == null){
           System.out.println("CHARACTER IS NULL????");

        }
        try {
                ps = conn.prepareStatement("INSERT INTO Game.Cart(userID,characterID)" +
                        " values("+this.getUserID()+","+character.getCharacterID()+");");
                ps.executeUpdate();
                //update score in current user class
                return true;
        } catch (SQLException e) {
                System.out.println("SQLException in function \"set coins\"");
                e.printStackTrace();
                return false;
        }finally{
                close();
        }
    }
    public void setCharSelectedID(int id){//in this project should be 1-4
        charSelectedID = id;//will call this method when character is changed in store screen
    }
    boolean addFriends (User u){
        int friendID = u.getUserID();
        // Connect to DB
        connect();
        try {
            ps = conn.prepareStatement("INSERT IGNORE INTO Game.friends(user1ID,user2ID)" +
            " values("+this.getUserID()+","+u.getUserID()+");");
            ps.executeUpdate();
            System.out.println("friend added");
            return true;

        } catch (SQLException e) {
            System.out.println("SQLException in function \"addFriends\"");
            e.printStackTrace();
            return false;
        }finally{
            close();
        }     
    }
    boolean unAddFriends(User u){//un add a friend and update database
        int friendID = u.getUserID();
        friends.add(u);
        // Connect to DB
        connect();
        try {
            ps = conn.prepareStatement("DELETE FROM Game.friends WHERE user1ID=" +
                 this.getUserID()+" AND user2ID="+u.getUserID()+";");
            ps.executeUpdate();
            System.out.println("friend added");
            return true;

        } catch (SQLException e) {
            System.out.println("SQLException in function \"addFriends\"");
            e.printStackTrace();
            return false;
        }finally{
            close();
        } 
    }
    /*ArrayList<User> getAddedUsers(){//returns a list of all the other users this user has added
        int usersID = StartUp.user.getUserID();
        connect();
        ArrayList<User> addedUsers = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT  * FROM Game.Friends WHERE User1ID="+usersID+";");
            rs = ps.executeQuery();
            if(!rs.equals(null))//found stuff!
                    {
                        while(rs.next())
                        {
                            int addedID = rs.getInt("user2ID");
                            User addedUser = StartUp.userDriver.makeUserObjFromID(addedID);//make user object using id and databases
                            addedUsers.add(addedUser);
                        }
                    }
        }catch (SQLException e) {
                System.out.println("SQLException in function \"getAddedUsers\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return addedUsers;
    }*/
    ArrayList<Integer> getAddedUsersIDs(){//returns a list of all the other users this user has added. list contains USERS ID's!! NOT user objects
        connect();
        ArrayList<Integer> addedUsers = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT  * FROM Game.Friends WHERE User1ID="+StartUp.user.getUserID()+";");
            rs = ps.executeQuery();
            if(!rs.equals(null))//found stuff!
                    {
                        while(rs.next())
                        {
                            int addedID = rs.getInt("user2ID");
                            addedUsers.add(addedID);
                        }
                    }
        }catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        return addedUsers;
    }
    // ****FRIENDS FOREVER****  Boolean removeFriends (User u);
    Boolean isRequestedFriend (User u){
        if(this.friends.contains(u))
            return true;
        else 
            return false;
    }
    Boolean isConfirmedFriend (User u){
       if(this.friends.contains(u) && u.friends.contains(this))
            return true;
        else 
            return false;
    }
    
    void addChallenge (Challenge c){
        this.challenges.add(c);
        
    }
    
    ArrayList<Challenge> getChallenges (){
        return this.challenges;
    }
    
    void addScore(Score s){
        this.scores.add(s);
    }
    
    ArrayList <User> getFriends()//returns all users that have added this user back. they must have mutually added each other to be friends
    {
        ArrayList<Integer> addedUsers = getAddedUsersIDs();//users this user has added rn, but they are not necessarily friends
        int thisUsersID = StartUp.user.getUserID();//id of logged in user
        connect();
        ArrayList<Integer> friendsIDs = new ArrayList<>();
        ArrayList<User> friends = new ArrayList<>();
        try{
            for(int i = 0; i < addedUsers.size(); i++)
            {//get each added person's added users, and check if this user is in that list
                ps = conn.prepareStatement("SELECT  * FROM Game.Friends WHERE User1ID="+addedUsers.get(i)+";");
                rs = ps.executeQuery();
                if(!rs.equals(null))//this user has added that person
                {
                    while(rs.next())
                    {
                        int addedID = rs.getInt("user2ID");
                        if(addedID == thisUsersID)//they have added us back! we are friends
                        {
                            friendsIDs.add(addedUsers.get(i));
                        }
                    }
                }
            }
            
        }catch (SQLException e) {
                System.out.println("SQLException in function \"findByUname\"");
                e.printStackTrace();
        }finally{
                close();
        }
        //now convert friend ids to users. we had to do this seperately due to database connection errors in the loops
        for(int i =  0; i < friendsIDs.size(); i++)
        {
            friends.add(StartUp.userDriver.makeUserObjFromID(friendsIDs.get(i)));
        }
        return friends;
    }
     
    /*ArrayList<Integer> getTopScores(){
        //Add to integer array
        ArrayList<Integer> scoresTopTen = new ArrayList<Integer>();
        //Add just first ten
        for (int i = 0; i < 10; i++){
            int score = this.scores.get(i).getScore();
            scoresTopTen.add(score);
        }
        //Sort
        Collections.sort(scoresTopTen);
        //Reverse
        Collections.reverse(scoresTopTen);
        return scoresTopTen;
    }*/
    
    ArrayList<Integer> getAllScores(){
        //Create a new integer array
        ArrayList<Integer> scoresAllInt = new ArrayList<Integer>();
        // Clone all integers in scores objects into new array created above
        for (int i = 0; i < this.scores.size(); i++){
            int score = this.scores.get(i).getScore();
            scoresAllInt.add(score);
        }
        //Sort
        Collections.sort(scoresAllInt);
        //Reverse
        Collections.reverse(scoresAllInt);
        return scoresAllInt;
    }
    
}   