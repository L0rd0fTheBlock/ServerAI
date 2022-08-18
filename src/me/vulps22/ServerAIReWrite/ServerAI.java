package me.vulps22.ServerAIReWrite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.vulps22.ServerAIReWrite.function.IsMuted;
import me.vulps22.ServerAIReWrite.function.SendAIMessage;
import me.vulps22.ServerAIReWrite.function.SendPrivateAIMessage;
import me.vulps22.ServerAIReWrite.function.Utils;
import me.vulps22.ServerAIReWrite.function.Warning;

public class ServerAI extends JavaPlugin {

    public static ServerAI plugin;
    public final static Logger logger = Logger.getLogger("minecraft");
    public final ServerChatListener playerListener = new ServerChatListener(this);
    public final LoginListener LoginListener = new LoginListener(this);
    public static boolean filtering;
    public static boolean safemode;
    public static boolean cupdates;
    public static boolean ask_updates;
    public static boolean know_maker;
    public static boolean update = false;
    public static String name = "";
    public static long size = 0;
    public static ArrayList<String> MutedPlayersList = new ArrayList<String>();
    Warning warning = new Warning();

    @Override
    public void onEnable() {
        //tell the console SAI is starting
        ServerAI.logger.info("[ServerAI]" + "Running Start-up Sequence");
        //METRICS DO NOT TOUCH
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }//end of METRICS

        //set chat colors
        ChatColor RED = ChatColor.RED;
        ChatColor WHITE = ChatColor.WHITE;
        //plugin manager
        PluginManager pm = getServer().getPluginManager();
        //plugin descrition file
        PluginDescriptionFile pdffile = this.getDescription();

        //create essential folders
        File plug_location = new File("plugins" + File.separator + "ServerAI" + File.separator);
        File Filter_location = new File("plugins" + File.separator + "ServerAI" + File.separator + "Filter.txt");
        File Mute_location = new File("plugins" + File.separator + "ServerAI" + File.separator + "MutedPlayers.txt");//do we need the last file.seperator?dunno, what harm will it do lol true... that will create the serverAI folder
        File Warning_location = new File("plugins" + File.separator + "ServerAI" + File.separator + "Warnings" + File.separator);
        File Players_location = new File(getDataFolder() + File.separator + "Players");
        File warp_location = new File(getDataFolder() + File.separator + "Warp.yml");
        //create config.yml
        FileConfiguration config;
        try {
            if (!plug_location.exists()) {
                plug_location.mkdir();
            }
            if (!Filter_location.exists()) {
                Filter_location.createNewFile();//fixed
            }
            if (!Mute_location.exists()) {
                Mute_location.createNewFile();//fixed
            }
            if (!Warning_location.exists()) {
                Warning_location.mkdir();
            }
            if (!Players_location.exists()) {
                Players_location.mkdir();
            }
            if (!warp_location.exists()) {
                warp_location.createNewFile();//fixed
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        config = getConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        filtering = config.getBoolean("FilterChat");
        safemode = config.getBoolean("SafeMode");
        cupdates = config.getBoolean("Check_for_updates");
        ask_updates = config.getBoolean("ask_for_updates");
        know_maker = config.getBoolean("notice_tekkcraft_team");

        //UPDATER DO NOT TOUCH
        if (cupdates == true) {
            Updater updater = new Updater(this, "s-a-i", this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);

            update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
            name = updater.getLatestVersionString(); // Get the latest version
            size = updater.getFileSize(); // Get latest size

            if (ask_updates == false && ServerAI.update) {
                new Updater(this, "s-a-i", this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, false);
            }

        }
        //END UPDATER
        ServerAI.logger.info(pdffile.getName() + "is enabled");
        ServerAI.logger.info(pdffile.getName() + " SwearFilter" + "is enabled");
        warning.Enable();
        Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Good Day." + " Now Operating Console Systems");
        pm.registerEvents(this.playerListener, this);
        pm.registerEvents(this.LoginListener, this);
        IsMuted.loadMuteList();
    }

    public static void doUpdate() {
        new Updater(plugin, "s-a-i", plugin.getFile(), Updater.UpdateType.NO_VERSION_CHECK, false);
    }

    public void onDisable() {
        ChatColor RED = ChatColor.RED;
        ChatColor WHITE = ChatColor.WHITE;
        warning.Disable();
        Bukkit.getServer().broadcastMessage(RED + "[SAI] " + WHITE + "Good Bye." + " No Longer Operating Console Systems");
        this.saveConfig();
    }
    @Override
   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("warn")){ 
			if (sender.hasPermission("sai.warn")){
				if(args.length < 2){
					sender.sendMessage(warning.RED + "[SAI]" + warning.WHITE + "You must tell me the player at fault and the reason for the Warning");
				}
				else if(args.length >= 2){
					String reason = "";
				
					for (int i = 1; i < args.length; i = i + 1){
				
						reason = reason + " " + args[i];	
					}
					Bukkit.getServer().broadcastMessage(warning.RED + "[SAI] " + warning.GREEN + args[0] + " WARNING: " + reason);
				}
		}
			else
				sender.sendMessage(warning.RED + "[SAI]" + warning.WHITE + "You do NOT have permission to use my Warning Systems");
	
		
}
                else if(cmd.getName().equalsIgnoreCase("b")){
                if(sender.hasPermission("sai.broadcast")){
                    if(args.length > 0){
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                         message = message +  " " + args[i];                        
                    }
                new SendAIMessage(0.5,message,message,message);
                    }else{
                    sender.sendMessage(warning.RED + "[SAI]" + warning.WHITE + "You must tell me what to say" );
                    }
                }else{
                Utils.noPerms();
                }
                }
return false;
}
    
    }
