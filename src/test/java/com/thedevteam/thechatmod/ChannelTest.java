package com.thedevteam.thechatmod;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.spout.api.player.Player;
import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.MemoryConfiguration;
import org.spout.engine.player.SpoutPlayer;

import com.thedevteam.thechatmod.channels.Channel;

public class ChannelTest {
	
	
	private Channel test;
	private Channel localtest;
	private MemoryConfiguration config;

	@Before
	public void setupChannelConfig(){
		config = new MemoryConfiguration();

		ConfigurationHolder lce = new ConfigurationHolder(true,"channels","localtest","local","enabled");
		ConfigurationHolder lcd = new ConfigurationHolder("100","channels","localtest","local","distance");
		ConfigurationHolder ce = new ConfigurationHolder(false,"channels","test","local","enabled");
		ConfigurationHolder cd = new ConfigurationHolder("100","channels","test","local","distance");
		lce.setConfiguration(config);
		lcd.setConfiguration(config);
		ce.setConfiguration(config);
		cd.setConfiguration(config);
		
		
		
	}
	

	
	@Test
	public void newChannelTest(){
		test = new Channel("test", config.getChild("channels.test"));
		
		assertTrue(test.getName() == "test");
		assertFalse(test.isLocal());
		assertEquals(test.getDistance(),-1);
		System.out.println(test.getName()+":"+test.isLocal()+":"+test.getDistance());
		
	}
	@Test
	public void newLocalChannelTest(){
		
		localtest = new Channel("localtest", config.getChild("channels.localtest"));
		System.out.println(localtest.getName()+":"+localtest.isLocal()+":"+localtest.getDistance());
		assertTrue(localtest.getName() == "localtest");
		//assertTrue(localtest.isLocal());
		//assertEquals(localtest.getDistance(),100);
		
		
	}
	
	@Test
	public void listenersTest(){
		
		test = new Channel("test", config.getChild("channels.test"));
		Player p = new SpoutPlayer("testplayer");
		
		test.addListener(p);
		assertTrue(test.getListeners().contains(p));
		test.removeListener(p);
		assertFalse(test.getListeners().contains(p));
	}

}
