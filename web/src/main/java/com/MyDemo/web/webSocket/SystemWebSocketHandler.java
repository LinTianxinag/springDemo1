package com.MyDemo.web.webSocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;
	 	 
	    @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	    	    	
	        users.add(session);
	        System.out.println("ConnectionEstablished"+"=>当前在线用户的数量是:"+users.size());
	    }
	 
	    @Override
	    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	    	
	    	System.out.println(message.getPayload());
	    	TextMessage returnMessage = new TextMessage("received at server");
	    		    		
	        sendMessageToUsers(returnMessage);	    		      
	    }
	 
	    @Override
	    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	        if(session.isOpen()){
	          
	        	session.close();
	        }
	        users.remove(session);
	    }
	 
	    
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
	    	
	    	users.remove(session);
	    	System.out.println("ConnectionClosed"+"=>当前在线用户的数量是:"+users.size());

	    }
	 
	    @Override
	    public boolean supportsPartialMessages() {
	        return false;
	    }
	 
	    /**
	     * 给所有在线用户发送消息
	     * @param message
	     */
	    public void sendMessageToUsers(TextMessage message) {
	        for (WebSocketSession user : users) {
	            if (user.isOpen()) {
	            	try {
						user.sendMessage(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
	    } 
}
