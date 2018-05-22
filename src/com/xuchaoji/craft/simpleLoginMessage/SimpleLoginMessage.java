package com.xuchaoji.craft.simpleLoginMessage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class SimpleLoginMessage  extends JavaPlugin implements Listener, CommandExecutor{
	FileConfiguration config = getConfig();
	FileConfiguration itemlist = getConfig();
	@Override
	public void onEnable(){
		//�Զ������������BossBar���ݡ����뻶ӭ��
		config.addDefault("ServerName", "233craft");
		config.addDefault("BossBarTitle", "��e��ӭ������d��l233craft");
		config.addDefault("JoinMessage", "��a�ɹ���¼����������¼�����ѷ���");
		//�Զ����¼���Ｐ����
		config.addDefault("Gift.Type", "BREAD");
		config.addDefault("Gift.Amount", 3);
		config.options().copyDefaults(true);
		saveConfig();
		//������Ʒ�б�����չʾ��¼ʱ�ֳ���Ʒ��Ϣ�����������ļ����Լ������޸���ʾ����
		for (Material material : Material.values()) {
			System.out.println("[SimpleLoginMessage]�Ѽ�����Ʒ��"+material.name());
			String path = "ItemList."+material.name();
		    itemlist.addDefault(path, material.name());
		}
		itemlist.options().copyDefaults(true);
		saveConfig();
		
		//register Command
		this.getCommand("slm").setExecutor(this);
		//register Listener
		getServer().getPluginManager().registerEvents(this, this);
		System.out.println("[SimpleLoginPlugin]���سɹ�������֧����ϵdadmin@xuchaoji.com");
		System.out.println("[SimpleLoginPlugin]loaded! contact admin@xuchaoji.com &afor help if you need.");
		
	}
	
	
	
	@Override
	public void onDisable(){
		//disable plugin 
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage(config.getString("JoinMessage"));
		
		//��ȡ��¼�������ͼ�����
		Material giftType =  Material.getMaterial(config.getString("Gift.Type"));
		String getNameOfGift = "ItemList."+giftType;
		int giftAmount = config.getInt("Gift.Amount");
		ItemStack sendGift = new ItemStack(giftType, giftAmount);
		player.getInventory().addItem(sendGift);
		System.out.println("[SimpleLoginMessage] Send "+config.getString(getNameOfGift)+" X "+giftAmount+" to "+player.getName());
		
		
		
		String rawholdItem = player.getInventory().getItemInMainHand().getType().toString();
		//�滻Material String Ϊ ��Ʒ��
		String path = "ItemList."+rawholdItem;
		String holdItem = config.getString(path);
		
		Bukkit.broadcastMessage("��e"+event.getPlayer().getName()+" ��a�ֳ�  ��d"+ holdItem+ " ��a������ "+"��e"+config.getString("ServerName"));
		//ʹ��bossbar��ʾ��ӭ���
		BossBar bar1 = Bukkit.createBossBar(config.getString("BossBarTitle"), BarColor.YELLOW, BarStyle.SOLID, BarFlag.DARKEN_SKY);
		bar1.addPlayer(player);
		System.out.println("[SimpleLoginMessage]attatch bossbar to player!");
		}
	
	//Command
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 0) {
			Bukkit.broadcastMessage("��a[SimpleLoginMessage] Use \"/slm reload\" to reload config file. ");
		}else {
			reload();
			
		}
			
	
		
		return true;
	}
	public void reload() {
        reloadConfig();
        Bukkit.broadcastMessage("��a[SimpleLoginMessage] config file reloaded! ");
    }

}
