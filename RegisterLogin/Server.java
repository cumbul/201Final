package registerlogin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;



public class Server extends Thread//connect all the players to eachtoher using this
{
	private Vector<ServerThread> serverThreads;
	public Server(int port)
	{
		ServerSocket ss = null;//listens to certain port on this computer, waiting for client to connect to that port, does not let us communicate
		try
		{
			System.out.println("Trying to bind to port: "+port);
			ss = new ServerSocket(port);
			System.out.println("Bound to  port "+port);
			serverThreads = new Vector<ServerThread>();
			while(true) 
			{
				Socket s = ss.accept();//allows clients to communicate with the program, waits for them to connect
				System.out.println("Connection from "+s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
				//we need a data structure to keep track of who has connected to us
			}
		}
		catch(IOException ioe)
		{
			System.out.println("ioe: "+ioe.getMessage());
		}
		finally 
		{
			try 
			{
				if(ss!=null)
				{
					ss.close();
				}
			}
			catch(IOException ioe) 
			{
				System.out.println("ioe closing stuff: "+ioe.getMessage());
			}
		}
	}
	public void broadcast(Message m, ServerThread currentST)
	{
		if(m != null)//message received from serverThread is not null
		{
			if(m.id == 1)
			{
				handleMessage1(m, currentST);//coins bein requested
			}
			else if(m.id == 2)//requested user for coins rejected, tell rejected user he has been rejected
			{
				handleMessage2(m,currentST);
			}
			else if(m.id == 3)//requested user for coins rejected, tell rejected user he has been rejected
			{
				handleMessage3(m,currentST);
			}
			else if(m.id == 4)
			{
				handleMessage4(m,currentST);//add username to serverthread
			}
		}
		
	}
	public void handleMessage1(Message m, ServerThread currentST)
	{
		String username = m.username;
		String usernameToRequest = m.userToRequestCoins;//send a message to this user that we want coins!
		int coinAmount = m.coinAmount;
		//send message to other user now
		System.out.println(username+" request "+coinAmount+" coins from "+usernameToRequest);
		//make message to send to requested user
		Message mToSend = new Message(101, m.username);
		mToSend.coinAmount = coinAmount;
		mToSend.userToRequestCoins = m.userToRequestCoins;
		ServerThread userToSend = getThreadByName(usernameToRequest);
		if(userToSend!=null)
		{
			userToSend.sendMessage(mToSend);//tell user there is a coin request thingy
			
		}
		else
		{
			System.out.println("error, user is null");
		}
	}
	public void handleMessage2(Message m, ServerThread currentST)
	{//coin request has been accepted
		String username = m.username;
		String usernameToRequest = m.userToRequestCoins;//send a message to this user that we want coins!
		int coinAmount = m.coinAmount;
		Message mToSend = new Message(103, usernameToRequest);
		mToSend.userToRequestCoins = username;
		mToSend.coinAmount = coinAmount;
		ServerThread userToSend = getThreadByName(usernameToRequest);
		if(userToSend!=null)
		{
			userToSend.sendMessage(mToSend);//tell user there is a coin request thingy		
		}
		else
		{
			System.out.println("error, user is null");
		}
	}
	public void handleMessage3(Message m, ServerThread currentST)
	{//requested user for coins rejected, tell rejected user he has been rejected
		String username = m.username;
		String usernameToRequest = m.userToRequestCoins;//send a message to this user that we want coins!
		int coinAmount = m.coinAmount;
		Message mToSend = new Message(102, usernameToRequest);
		mToSend.userToRequestCoins = username;
		mToSend.coinAmount = coinAmount;
		ServerThread userToSend = getThreadByName(usernameToRequest);
		if(userToSend!=null)
		{
			userToSend.sendMessage(mToSend);//tell user there is a coin request thingy		
		}
		else
		{
			System.out.println("error, user is null");
		}
	}
	public void handleMessage4(Message m, ServerThread currentST)
	{
		currentST.setUsername(m.username);//sets the username of the serverThread
	}
	public ServerThread getThreadByName(String name)//takes in username and thread should be returned
	{
		ServerThread st = null;
		for(int i = 0; i < serverThreads.size(); i++)
		{
			if(serverThreads.get(i).getUsername().equals(name))//found it
			{
				st = serverThreads.get(i);
			}
		}
		return st;
	}
	public static void main(String [] args)
	{
		new Server(6789);
	}
}
