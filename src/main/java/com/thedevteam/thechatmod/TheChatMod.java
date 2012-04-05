package com.thedevteam.thechatmod;

import java.util.logging.Level;

import org.spout.api.plugin.CommonPlugin;

import com.thedevteam.thechatmod.listeners.ChatListener;

/**
 * 
 *
 */
public class TheChatMod extends CommonPlugin {

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		log(Level.INFO, "THEChatMod has been enabled");
		getGame().getEventManager().registerEvents(new ChatListener(this), this);
	}

	public void log(Level warning, String string) {
		getLogger().log(warning, string);
	}

}
