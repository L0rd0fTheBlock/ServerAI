package me.vulps22.serverai.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.vulps22.serverai.ServerAI;

/**
 *
 * @author jamie
 */
public class FindPlayers {

    public static Player findPlayerInArray(String[] playernames) {
        for (int i = 0; i < playernames.length; i++) {
            String string = playernames[i];
            ServerAI.logger.log(Level.INFO, string);
        }
        Player foundPlayer = null;
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();


        for (int i = 0; i < playernames.length; i++) {
            for (Player player : players) {
                String play = player.getPlayerListName().toLowerCase();
                if (play.equals(playernames[i])) {
                    foundPlayer = player;
                }

            }
        }

        return foundPlayer;
    }

    public static Player findPlayerInArray(String[] playernames, Player p) {
        Player foundPlayer = null;
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        ArrayList chat = new ArrayList();
        for (int i = 0; i < playernames.length; i++) {
            chat.add(playernames[i]);
        }

        for (int i = 0; i < playernames.length; i++) {

            for (Player player : players) {
                String play = player.getPlayerListName().toLowerCase();
                if (play.equals(playernames[i])) {
                    foundPlayer = player;
                }
                if (foundPlayer == null && chat.contains("me")) {
                    foundPlayer = p;
                }
            }
        }

        return foundPlayer;
    }

    public static Player[] findPlayersInArray(String[] playernames, Player p) {
        Player[] foundPlayer = {};
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        ArrayList chat = new ArrayList();
        for (int i = 0; i < playernames.length; i++) {
            chat.add(playernames[i]);
        }
        for (int i = 0; i < playernames.length; i++) {
            for (Player player: players) {
                String play = player.getPlayerListName().toLowerCase();
                if (foundPlayer[0] !=null) {
                    if (play.equals(playernames[i])) {
                        foundPlayer[0] = player;
                    }
                    if (foundPlayer == null && chat.contains("me")) {
                        foundPlayer[0] = p;
                    }
                }else{
                if (play.equals(playernames[i]) && !foundPlayer[0].getPlayerListName().toLowerCase().equals(playernames[i])) {
                        foundPlayer[1] = player;
                    }
                    if (foundPlayer[1] == null && chat.contains("me")) {
                        foundPlayer[1] = p;
                    }
                }

            }

        }
        return foundPlayer;
    }
}
