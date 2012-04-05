package com.thedevteam.thechatmod.Channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.player.Player;
import org.spout.api.util.config.ConfigurationNode;

import com.thedevteam.thechatmod.TheChatMod;

public class ChannelManager implements Listener {

	private TheChatMod plugin;
	private ArrayList<Channel> channels;
	private HashMap<Player,Player> convos = new HashMap<Player,Player>();
	private HashMap<Player,Channel> active = new HashMap<Player,Channel>();

	public ChannelManager(TheChatMod theChatMod) {
		this.channels = new ArrayList<Channel>();
		this.plugin = theChatMod;
		loadChannels();
	}

	private void loadChannels() {
		for(Entry<String, ConfigurationNode> ch:plugin.getConfig().getNode("channels").getChildren().entrySet()){
			channels.add(new Channel(ch.getKey(),ch.getValue()));
		}
		
	}
	
	@EventHandler(order = Order.EARLY)
	void onPlayerChat(PlayerChatEvent e) {
		getActive(e.getPlayer()).broadcast(e.getPlayer(), e.getMessage());
		e.setCancelled(true);
	}

	private ChatDestination getActive(Player player) {
		if (convos.containsKey(player)){
			return new Conversation(convos.get(player));
		}else{
			return active .get(player);
		}
	}

}
