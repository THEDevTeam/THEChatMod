package com.thedevteam.thechatmod.commands;

import org.spout.api.Spout;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.player.Player;

import com.thedevteam.thechatmod.THEChatMod;

public class MessagingCommands {
	
	private THEChatMod plugin;
	
	public MessagingCommands(THEChatMod thechatmod) {
		this.plugin = thechatmod;
	}

	@Command(aliases = { "tell","msg" }, usage = "<player> <msg>" ,desc = "Message a player",min = 1)
	@CommandPermissions("thechatmod.msg")
	public void msg(CommandContext args, CommandSource source){
		Player recever = Spout.getGame().getPlayer(args.getString(0), true);
		if (args.length() == 1){
			if (!(source instanceof Player)){
				source.sendMessage("You are not allowed to start a conversation with "+ recever.getDisplayName());
			}
			plugin.getChannelManager().startConvo((Player)source, recever);
			source.sendMessage("You have started conversation with "+ recever.getDisplayName());
		}else{
			recever.sendMessage(args.getJoinedString(2));
		}
	}



}
