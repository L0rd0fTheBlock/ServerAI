package me.jamiemac262.ServerAIReWrite;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
/* this is supposed to work like a local perm manager.... didnt work tho lol*/
public class StaffPermission{
	public static FileConfiguration playerStat = null;
	public static File playerStatFile = null;
	
	
	public static boolean isModerator(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		boolean isMod = playerStat.getBoolean("Moderator");
		return isMod;
	}
	public static void setMod(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		playerStat.set("Moderator", true);
		
	}
	//get and set Admin
	public static boolean isAdminstrator(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		boolean isadmin = playerStat.getBoolean("Admin");
		return isadmin;
	}
	public static void setAdministrator(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		playerStat.set("Admin", true);
		
	}
	//get and set Owner
	public static boolean isOwner(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		boolean isMod = playerStat.getBoolean("Owner");
		return isMod;
	}
	public static void setOwner(Player player){
		playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", player + ".yml");
		playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
		playerStat.set("Owner", true);
		
	}
}
