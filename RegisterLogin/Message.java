package registerlogin;

import java.io.Serializable;

/*
 * CLIENT TO SERVER:
 * 1: send coin request from one player to another: should contain which player they are asking and amount
 * 2: accept the coin request
 * 3: reject the coin request
 * 4: username
 * 
 * SERVER TO CLIENT
 * 101: notify the player that they have a coin request
 * 102: notify the player that their coin request has been rejected
 * 103: notify the player that their coin request has been accepted
 */
public class Message implements Serializable//send stuff between client and server
{
	int id;//hold id of message
	String username = "";//hold username of message
	int coinAmount;//holds coin amount being requested
	String userToRequestCoins;//username of user we wanna request coins from
	public Message(int id, String username)
	{
		this.id = id;
		this.username = username;
	}
}
