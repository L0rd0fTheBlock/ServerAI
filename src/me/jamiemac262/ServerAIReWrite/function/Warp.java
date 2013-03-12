package me.jamiemac262.ServerAIReWrite.function;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Warp {
	public static File warpListFile = null;
	public static YamlConfiguration warpList = null;
	
	
	public static void setWarp(String warpName, Player player){
		String world = player.getLocation().getWorld().getName();
		int x = player.getLocation().getBlockX();
		int y = player.getLocation().getBlockY();
		int z = player.getLocation().getBlockZ();
		warpListFile = new File("plugins" + File.separator + "ServerAI","Warp.yml");
	    warpList = YamlConfiguration.loadConfiguration(warpListFile);
		warpList.set(warpName + ".world", world);
		warpList.set(warpName + ".x", x);
		warpList.set(warpName + ".y", y);
		warpList.set(warpName + ".z", z);
		try {
			warpList.save(warpListFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public static Location getHome(String warpName, Player player){ 
		warpListFile = new File("plugins" + File.separator + "ServerAI", "Warp.yml");
	    warpList = YamlConfiguration.loadConfiguration(warpListFile);
		
		String world = warpList.getString("warpName.world");
		int x = warpList.getInt(warpName + ".x");
		int y = warpList.getInt(warpName + ".y");
		int z = warpList.getInt(warpName + ".z");
		World w = Bukkit.getWorld(world); 
		Location warpLoc = new Location(w, x, y, z);
		
		return warpLoc;
		}
}
