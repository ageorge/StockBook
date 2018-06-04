package com.stock.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Chat server endpoint to pass on the message to all sessions
 * @author anitageorge and bhavya
 *
 */
@ServerEndpoint("/chatserver")
public class ChatServer {
	
	private static Set<Session> mySessions = Collections.synchronizedSet(new HashSet<Session>());
	
	/**
	 * Method to save the session in a set of sessions when a new session is opened
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session)
	{
		System.out.println(session.getId() + " has opened a connection!");
		mySessions.add(session); 
		session.getAsyncRemote().sendText("Connection Established"); 
	}
	
	/**
	 * Method to pass the message onto the rest of the connected users
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + ": " + message);
		for( Session s : mySessions) {
			if(s != session) {
				try {
					System.out.println("Sending to " + s.getId() +
					" from server " + this); s.getAsyncRemote().sendText(message);
				} catch (Exception ioe) {
					System.err.println("Removing Dead Session");
					try {
						mySessions.remove(s);
					}
					catch(Exception e) { 
						System.err.println("Exception on Remove");
					} 
				}
			}
		}
	}
	
	/**
	 * Method to remove the session object from the set on close event
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " +session.getId() +" has ended"); 
		mySessions.remove(session); 
	}
	
	/**
	 * Method to Handle the error 
	 * @param session
	 * @param thr
	 */
	@OnError
	public void onBigError(Session session, Throwable thr) {
		System.err.println("Session " + session.getId() + " closed suddenly"); 
	}
	    
}
