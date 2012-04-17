package com.thedevteam.thechatmod.channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.player.PlayerLeaveEvent;
import org.spout.api.event.player.PlayerLoginEvent;
import org.spout.api.player.Player;

import com.thedevteam.thechatmod.Message;
import com.thedevteam.thechatmod.THEChatMod;

public class ChannelManager implements Listener {

	private THEChatMod plugin;
	private ArrayList<Channel> channels;
	private HashMap<Player, Player> convos = new HashMap<Player, Player>();
	private HashMap<Player, Channel> active = new HashMap<Player, Channel>();

	public ChannelManager(THEChatMod theChatMod) {
		this.channels = new ArrayList<Channel>();
		this.plugin = theChatMod;
		loadChannels();
	}

	private void loadChannels() {
		
		Set<String> names = plugin.getConfig().getNode("channels").getKeys(false);
		for (String name :names){
			channels.add(new Channel(name, plugin.getConfig().getNode("channels."+name)));
		}

	}
	
	@EventHandler(order = Order.MONITOR)
	void onJoin(PlayerLoginEvent e){
		for (Channel chan : channels){
			if (chan.getName() == plugin.getConfig().getNode("default").getString()){
				active.put(e.getPlayer(), chan);
				System.out.println("Player " + e.getPlayer().getName() +"'s active channel set to" + chan.getName());
			}
		}
			    
	}

	@EventHandler(order = Order.EARLY)
	void onPlayerChat(PlayerChatEvent e) {
		Message msg = new Message(e);
		getActive(e.getPlayer()).broadcast(msg);
		e.setCancelled(true);
	}

	@EventHandler(order = Order.MONITOR)
	void onPlayerQuit(PlayerLeaveEvent e) {
		if (convos.containsValue(e.getPlayer())) {
			for (Entry<Player, Player> con : convos.entrySet()) {
				if (con.getValue() == e.getPlayer()) {
					con.getKey()
							.sendMessage(
									"The Person you have been conversing with has left!");
					convos.remove(con.getKey());
				}
			}
		}
	}

	private ChatDestination getActive(Player player) {
		if (convos.containsKey(player)) {
			return new Conversation(convos.get(player));
		} else {
			return active.get(player);
		}
	}

	public void startConvo(Player source, Player destination) {
		convos.put(source, destination);
	}

	public void stopConvo(Player source) {
		convos.remove(source);
	}

}
