/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Karalee
 */

public class StartUp extends javax.swing.JFrame{
	public static boolean isGuest = false;//tells if user is playing as guest or not
    static User user;
    static UserDriver userDriver = new UserDriver();
    static ArrayList<Character> charactersList = userDriver.getAllCharactersInDatabase();//list of all existing charcaters in database, will be used later in code in store
    public static void main(String [] args){
        StartScreen start = new StartScreen();
    }
}