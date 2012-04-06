package com.thedevteam.thechatmod.Channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerLeaveEvent;
import org.spout.api.player.Player;
import org.spout.api.util.config.ConfigurationNode;

import com.thedevteam.thechatmod.TheChatMod;

public class ChannelManager implements Listener {

	private TheChatMod plugin;
	private ArrayList<Channel> channels;
	private HashMap<Player, Player> convos = new HashMap<Player, Player>();
	private HashMap<Player, Channel> active = new HashMap<Player, Channel>();

	public ChannelManager(TheChatMod theChatMod) {
		this.channels = new ArrayList<Channel>();
		this.plugin = theChatMod;
		loadChannels();
	}

	private void loadChannels() {
		if (plugin.getConfig() != null
				&& plugin.getConfig().getNode("channels") != null
				&& plugin.getConfig().getNode("channels").getChildren() != null)
			for (Entry<String, ConfigurationNode> ch : plugin.getConfig()
					.getNode("channels").getChildren().entrySet()) {
				channels.add(new Channel(ch.getKey(), ch.getValue()));
			}

	}

	@EventHandler(order = Order.EARLY)
	void onPlayerChat(PlayerChatEvent e) {
		getActive(e.getPlayer()).broadcast(e.getPlayer(), e.getMessage());
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
