package com.thedevteam.thechatmod;

import java.io.File;
import java.util.logging.Level;

import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.thedevteam.thechatmod.Channels.ChannelManager;

/**
 * 
 *
 */
public class TheChatMod extends CommonPlugin {

	private ChannelManager cm;
	private YamlConfiguration config;

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		log(Level.INFO, "THEChatMod has been enabled");
		this.cm = new ChannelManager(this);
		this.config = new YamlConfiguration(new File(this.getDataFolder(),"config.yml"));
		getGame().getEventManager().registerEvents(cm, this);
	}

	public void log(Level warning, String string) {
		getLogger().log(warning, string);
	}
	
	public YamlConfiguration getConfig(){
		return config;
	}

}
