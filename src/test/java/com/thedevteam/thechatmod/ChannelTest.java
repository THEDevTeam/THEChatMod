package com.thedevteam.thechatmod;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.player.Player;
import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.MapConfiguration;

import org.spout.engine.player.SpoutPlayer;

import com.thedevteam.thechatmod.channels.Channel;

public class ChannelTest {
	
	
	private Channel test;
	private Channel localtest;
	private MapConfiguration config;

	@Before
	public void setupChannelConfig(){
		config = new MapConfiguration();
		
		config.getNode("channels","localtest","local","enabled").setValue(true);
		config.getNode("channels","localtest","local","distance").setValue(100);
		config.getNode("channels","local","local","enabled").setValue(false);
		config.getNode("channels","local","local","distance").setValue(100);
		try {
			config.load();
			config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void newChannelTest(){
		test = new Channel("test", config.getNode("channels.test"));
		
		assertTrue(test.getName() == "test");
		assertFalse(test.isLocal());
		assertEquals(test.getDistance(),-1);
	}
	@Test
	public void newLocalChannelTest(){
		
		localtest = new Channel("localtest", config.getNode("channels.localtest"));
		System.out.println(localtest.getName()+":"+localtest.isLocal()+":"+localtest.getDistance());
		assertTrue(localtest.getName() == "localtest");
		assertTrue(localtest.isLocal());
		assertEquals(localtest.getDistance(),100);
	}
	
	@Test
	public void listenersTest(){
		
		test = new Channel("test", config.getNode("channels.test"));
		Player p = new SpoutPlayer("testplayer");
		
		test.addListener(p);
		assertTrue(test.getListeners().contains(p));
		test.removeListener(p);
		assertFalse(test.getListeners().contains(p));
	}

}
