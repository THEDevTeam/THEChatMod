package com.thedevteam.thechatmod;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.thedevteam.thechatmod.Channels.ChannelManager;

/**
 * 
 *
 */
public class THEChatMod extends CommonPlugin {

	private ChannelManager cm;
	private YamlConfiguration config;

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		this.cm = new ChannelManager(this);

		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}

		File config = new File(this.getDataFolder(), "config.yml");
		if (!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = new YamlConfiguration(config);

		getGame().getEventManager().registerEvents(cm, this);
		log(Level.INFO, "THEChatMod has been enabled");
	}

	public void log(Level warning, String string) {
		getLogger().log(warning, string);
	}

	public YamlConfiguration getConfig() {
		return config;
	}

}
