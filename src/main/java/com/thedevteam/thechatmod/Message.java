package com.thedevteam.thechatmod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.spout.api.event.player.PlayerChatEvent;

import com.thedevteam.thechatmod.channels.Channel;
import com.thedevteam.thechatmod.channels.ChatDestination;
import com.thedevteam.thechatmod.channels.Conversation;

public class Message {
	
	/*
	 * 
	 * fields avalible:
	 * message
	 * sender - displayname
	 * realsender - username
	 * prefix
	 * suffix
	 * 
	 */
	
	Map<String,String> fields = new HashMap<String,String>();
	
	public Message(PlayerChatEvent e) {
		
		setField("message", e.getMessage());
	setField("sender",e.getPlayer().getDisplayName());
	setField("realsender",e.getPlayer().getName());
	}

	private void setField(String key, String value) {
		fields.put(key, value);
		
	}

	public String format(ChatDestination c) {
		if (c instanceof Conversation){
			return "Player " + getField("sender") + " Sent the message: " + getField("message");
		}else if(c instanceof Channel){
			return getField("prefix") + getField("sender") + getField("suffix") + ": " + getField("message");
		}else{
			return getField("message");
		}
	}

	public String getField(String string) {
		if (!fields.containsKey(string))  return "";
		return fields.get(string);
	}

	
	
	

}
