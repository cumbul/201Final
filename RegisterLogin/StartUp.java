/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Karalee
 */
//make this the 'client' 

public class StartUp extends Thread{
	public ObjectInputStream ois; 
	public ObjectOutputStream oos;
	public StartUp(String hostname, int port)
	{
		Socket s = null;
		try
		{
			System.out.println("Trying to connect to "+hostname+":"+port);
			s = new Socket(hostname, port);
			System.out.println("Connected!");
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			this.start();
			
		}
		catch(IOException ioe)
		{
			System.out.println("ioe: "+ioe.getMessage());
		}
	}
	public void run()
	{
		try
		{
			while(true)
			{
				Message m = (Message)ois.readObject();
				if(m != null)
				{
					if(m.id == 101)
					{
						handleMessage101(m);//method to determine what the message received means
					}
					else if(m.id == 102)
					{
						handleMessage102(m);//method to determine what the message received means
					}
					else if(m.id == 103)
					{
						handleMessage103(m);//method to determine what the message received means
					}
				}
			}	
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in run: "+ioe.getMessage());
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("cnfe: "+cnfe.getMessage());
		}
	}
	public void handleMessage101(Message m)
	{//make coin request popup
		System.out.println(m.coinAmount+" coin request from "+m.username);
		CoinRequest coinRequest = new CoinRequest();
		coinRequest.setParameters(m.coinAmount, m.username, m.userToRequestCoins);
	}
	public void handleMessage102(Message m)
	{//coin equets has been rejected :(
		CoinRequestRejected reject = new CoinRequestRejected();
		reject.setParameters(m.coinAmount, m.username, m.userToRequestCoins);
		System.out.println(m.username+" has rejected your coin request of "+m.coinAmount+" coins");	
	}
	public void handleMessage103(Message m)
	{//coin request has been accepted
		CoinRequestAccepted accept = new CoinRequestAccepted();
		accept.setParameters(m.coinAmount, m.username, m.userToRequestCoins);
		System.out.println(m.username+" has accpeted your coin request of "+m.coinAmount+" coins");	
		user.setCoin(user.getCoin() + m.coinAmount);//add coins to their bank
	}
	public static boolean isGuest = false;//tells if user is playing as guest or not
    static User user;
    static UserDriver userDriver = new UserDriver();
    static StartUp startUp;
    static ArrayList<Character> charactersList = userDriver.getAllCharactersInDatabase();//list of all existing charcaters in database, will be used later in code in store
    public static void main(String [] args){
    	startUp = new StartUp("localhost", 6789);
        StartScreen start = new StartScreen();
    }
}