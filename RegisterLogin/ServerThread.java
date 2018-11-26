package registerlogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread
{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server server;
	
	private String username;//username of this player
	public ServerThread(Socket s, Server server)
	{
		this.server = server;//save server instance in this variable
		try
		{
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			this.start();
		}
		catch(IOException ioe)
		{
			System.out.println("ioe : " +ioe.getMessage());
		}
	}
	//public void sendMessage(String line)
	public void sendMessage(Message m)//send Messages objects
	{
		try
		{
			oos.writeObject(m);
			oos.flush();
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
				server.broadcast(m, this);
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
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String name)
	{
		username = name;
	}
}
