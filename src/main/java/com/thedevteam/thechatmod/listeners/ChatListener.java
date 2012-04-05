package com.thedevteam.thechatmod.listeners;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.player.Player;
import org.spout.api.plugin.CommonPlugin;

public class ChatListener implements Listener {
	private CommonPlugin plugin;
	public ChatListener(CommonPlugin plugin){
		this.plugin = plugin;
	}
	@EventHandler(order = Order.EARLIEST)
	void onPlayerChat(PlayerChatEvent e) {
		for (Player player : plugin.getGame().getOnlinePlayers()){
			if (player.getEntity().getPosition().getDistance(e.getPlayer().getEntity().getPosition()) <= 50){
				player.sendMessage(e.getMessage());
			}
		}
		e.setCancelled(true);
	}
}
