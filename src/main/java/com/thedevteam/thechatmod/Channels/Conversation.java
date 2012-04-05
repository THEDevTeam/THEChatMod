package com.thedevteam.thechatmod.Channels;

import org.spout.api.player.Player;

public class Conversation implements ChatDestination{
	
	private Player dest;

	public Conversation(Player destination){
		this.dest = destination;
	}

	@Override
	public void broadcast(Player player, String msg) {
		dest.sendMessage(msg);
		
	}

}
