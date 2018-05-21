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
	FileConfiguration itemlist = getConfig();
	@Override
	public void onEnable(){
		config.addDefault("ServerName", "233craft");
		config.addDefault("BossBarTitle", "§e欢迎来到§d§l233craft");
		config.addDefault("JoinMessage", "§a成功登录服务器，登录礼物已发放");
		config.options().copyDefaults(true);
		saveConfig();
		for (Material material : Material.values()) {
			System.out.println(material.name());
		    itemlist.addDefault(material.name(), material.name());
		}
		itemlist.options().copyDefaults(true);
		saveConfig();
		//register Listener
		getServer().getPluginManager().registerEvents(this, this);
		System.out.println("[SimpleLoginPlugin]加载成功。技术支持联系admin@xuchaoji.com");
		System.out.println("[SimpleLoginPlugin]loaded! contact admin@xuchaoji.com for help if you need.");
		
	}
	
	@Override
	public void onDisable(){
		//disable plugin 
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage(config.getString("JoinMessage"));
		ItemStack bread = new ItemStack(Material.BREAD, 3);
		//给面包
		player.getInventory().addItem(bread);
		String rawholdItem = player.getInventory().getItemInMainHand().getType().toString();
		//替换Material String 为 物品名
		String holdItem = config.getString(rawholdItem);
		
		Bukkit.broadcastMessage("§a"+event.getPlayer().getName()+"§a手持 §d"+ holdItem+ " §a加入了 "+"§e"+config.getString("ServerName"));
		//使用bossbar显示欢迎语句
		BossBar bar1 = Bukkit.createBossBar(config.getString("BossBarTitle"), BarColor.YELLOW, BarStyle.SOLID, BarFlag.DARKEN_SKY);
		bar1.addPlayer(player);
		System.out.println("[SimpleLoginMessage]attatch bossbar to player!");
		}

}
