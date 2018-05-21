package com.xuchaoji.craft.simpleLoginMessage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleLoginMessage  extends JavaPlugin implements Listener{
	FileConfiguration config = getConfig();
	@Override
	public void onEnable(){
		config.addDefault("ServerName", "233craft");
		config.addDefault("BossBarTitle", "��e��ӭ������d��l233craft");
		config.addDefault("JoinMessage", "��a�ɹ���¼����������¼�����ѷ���");
		config.options().copyDefaults(true);
		saveConfig();
		//register Listener
		getServer().getPluginManager().registerEvents(this, this);
		System.out.println("[SimpleLoginPlugin]���سɹ�������֧����ϵadmin@xuchaoji.com");
		System.out.println("[SimpleLoginPlugin]loaded! contact admin@xuchaoji.com for help if you need.");
		
	}
	
	@Override
	public void onDisable(){
		//disable plugin 
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Bukkit.broadcastMessage("��a"+event.getPlayer().getName()+"��a������ "+"��e"+config.getString("ServerName"));
		event.setJoinMessage(config.getString("JoinMessage"));
		Player player = event.getPlayer();
		ItemStack bread = new ItemStack(Material.BREAD, 3);
		//�����
		player.getInventory().addItem(bread);
		//ʹ��bossbar��ʾ��ӭ���
		BossBar bar1 = Bukkit.createBossBar(config.getString("BossBarTitle"), BarColor.YELLOW, BarStyle.SOLID, BarFlag.DARKEN_SKY);
		bar1.addPlayer(player);
		System.out.println("[SimpleLoginMessage]attatch bossbar to player!");
		}

}
