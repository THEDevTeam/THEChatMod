package com.thedevteam.thechatmod.Channels;

import org.spout.api.player.Player;

public interface ChatDestination {
	
	public void broadcast(Player player,String msg);

}
