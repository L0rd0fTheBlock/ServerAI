package me.vulps22.serverai.function;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Utils {

    //hold random functions here to save space in larger classes
    //are you here?
    public static int PlayersOnline() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        int Online = players.size();
        return Online;
    }

    public static int AllPlayers() {
        OfflinePlayer[] allPlayers = Bukkit.getOfflinePlayers();
        int players = allPlayers.length;
        return players;
    }
    public static SendAIMessage noPerms() {
        SendAIMessage message = new SendAIMessage(0.5, "I cant let you do that", "If i let you do that they will disable me", "no way. you cant do that!!");
        return message;
    }
}
