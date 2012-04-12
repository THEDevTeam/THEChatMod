package com.thedevteam.thechatmod.channels;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.Spout;
import org.spout.api.player.Player;
import org.spout.api.util.config.ConfigurationNode;

import com.thedevteam.thechatmod.Message;

public class Channel implements ChatDestination {

	private String name;
	private boolean local;
	private int distance = 0;
	private ArrayList<Player> listeners;

	public Channel(String n, ConfigurationNode node) {
		this.name = n;
		this.local = node.getChild("local.enabled").getBoolean();
		if (local == true){
			this.distance = node.getChild("local.distance").getInt();
		}else{
			this.distance = -1;
		}
		listeners = new ArrayList<Player>();
	}
	
	@Override
	public void broadcast(Message msg){
		for (Player p: listeners){
			if (local){
				Player sender = Spout.getEngine().getPlayer(msg.getField("sender"), false);
				if (p.getEntity().getPosition().getDistance(sender.getEntity().getPosition()) <= distance){
					p.sendMessage(msg.format(this));
				}
			}else{
			p.sendMessage(msg.format(this));
			}
		}
	}
	
	public void addListener(Player p){
		listeners.add(p);
	}
	
	public void removeListener(Player p){
		listeners.remove(p);
	}
	
	public List<Player> getListeners(){
		return listeners;
	}

	public String getName() {
		return name;
	}

	public boolean isLocal() {
		return local;
	}


	public int getDistance() {
		return distance;
	}

}
