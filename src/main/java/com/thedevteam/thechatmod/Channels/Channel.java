package com.thedevteam.thechatmod.Channels;

import java.util.ArrayList;

import org.spout.api.player.Player;
import org.spout.api.util.config.ConfigurationNode;

public class Channel implements ChatDestination {

	private String name;
	private boolean local;
	private int distance;
	private ArrayList<Player> listeners;

	public Channel(String n, ConfigurationNode node) {
		this.name = n;
		this.local = node.getNode("local.enabled").getBoolean();
		if (local == true){
			this.distance = node.getNode("local.distance").getInt();
		}else{
			this.distance = -1;
		}
	}
	
	@Override
	public void broadcast(Player sender,String msg){
		for (Player p: listeners){
			if (local){
				if (p.getEntity().getPosition().getDistance(sender.getEntity().getPosition()) <= distance){
					p.sendMessage(msg);
				}
			}else{
			p.sendMessage(msg);
			}
		}
	}

}
