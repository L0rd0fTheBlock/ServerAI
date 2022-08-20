package me.vulps22.serverai.function;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Warning {
	public static Warning plugin;
	public final static Logger logger = Logger.getLogger("minecraft");
	public ChatColor RED = ChatColor.RED;
	public ChatColor WHITE = ChatColor.WHITE;
	public ChatColor GREEN = ChatColor.GREEN;

        
	public void Disable(){
		
		Warning.logger.info("[ServerAI Warning] is disabled!");
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Good Bye. SAI Warnings No Longer Operating the Warning System");
	}
	

	public void Enable() {
		//set chat colors
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		
		//plugin manager
		//plugin descrition file

		Warning.logger.info("[ServerAI Warning] is enabled!");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Good Day. SAI Warnings version Now Operating the Warning System");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Tracking system Offline pending update");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Broadcasting Enabled");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Muting Offline pending update");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "jailing Offline pending update");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Kicking Offline pending update");
		Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Banning Offline pending update");
}
	
	public void warning(CommandSender sender,Command cmd, String[] args) {
		
		
			if (sender.hasPermission("sai.warn")){
				if(args.length < 2){
					sender.sendMessage(RED + "[SAI]" + WHITE + "You must tell me the player at fault and the reason for the Warning");
				}
				else if(args.length >= 2){
					String reason = "";
				
					for (int i = 1; i < args.length; i = i + 1){
				
						reason = reason + " " + args[i];	
					}
					Bukkit.getServer().broadcastMessage(RED + "[SAI] " + GREEN + args[0] + " WARNING: " + reason);
				}
		}
			else
				sender.sendMessage(RED + "[SAI]" + WHITE + "You do NOT have permission to use my Warning Systems");
	
		
}

}