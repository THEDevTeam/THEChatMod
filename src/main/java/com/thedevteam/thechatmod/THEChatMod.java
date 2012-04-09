package com.thedevteam.thechatmod;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;


import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.thedevteam.thechatmod.Channels.ChannelManager;
import com.thedevteam.thechatmod.commands.ChannelCommands;
import com.thedevteam.thechatmod.commands.MessagingCommands;

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
		
		//All of the commands!
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		getGame().getRootCommand().addSubCommands(this, ChannelCommands.class, commandRegFactory);
		getGame().getRootCommand().addSubCommands(this, MessagingCommands.class, commandRegFactory);

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
	public ChannelManager getChannelManager(){
		return cm;
	}

}
