package com.thedevteam.thechatmod.channels;

import org.spout.api.player.Player;

import com.thedevteam.thechatmod.Message;

public class Conversation implements ChatDestination{
	
	private Player dest;

	public Conversation(Player destination){
		this.dest = destination;
	}

	@Override
	public void broadcast(Message msg) {
		dest.sendMessage(msg.format(this));
		
	}

}
