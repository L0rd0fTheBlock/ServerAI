package me.jamiemac262.ServerAIReWrite.function;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Home {

    public static File playerStatFile = null;
    public static YamlConfiguration playerStat = null;

    public static void setHome(Player player) {
        String playerName = player.getName();
        String world = player.getLocation().getWorld().getName();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", playerName + ".yml");
        playerStat = YamlConfiguration.loadConfiguration(playerStatFile);
        playerStat.set("home." + ".world", world);
        playerStat.set("home." + ".x", x);
        playerStat.set("home." + ".y", y);
        playerStat.set("home." + ".z", z);
        try {
            playerStat.save(playerStatFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Location getHome(Player player) {
        String playerName = player.getName();
        playerStatFile = new File("plugins" + File.separator + "ServerAI" + File.separator + "Players", playerName + ".yml");
        playerStat = YamlConfiguration.loadConfiguration(playerStatFile);

        String world = playerStat.getString("home.world");
        int x = playerStat.getInt("home.x");
        int y = playerStat.getInt("home.y");
        int z = playerStat.getInt("home.z");
        World w = Bukkit.getWorld(world);
        Location homeLoc = new Location(w, x, y, z);

        return homeLoc;
    }
}
