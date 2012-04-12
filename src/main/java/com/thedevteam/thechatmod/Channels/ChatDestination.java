package com.thedevteam.thechatmod.channels;

import org.spout.api.player.Player;

import com.thedevteam.thechatmod.Message;

public interface ChatDestination {

	public void broadcast(Message msg);

}
