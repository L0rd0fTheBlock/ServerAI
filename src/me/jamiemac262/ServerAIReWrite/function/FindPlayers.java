
package me.jamiemac262.ServerAIReWrite.function;

import java.util.ArrayList;
import java.util.logging.Level;
import me.jamiemac262.ServerAIReWrite.ServerAI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        Player[] player = Bukkit.getOnlinePlayers();
        
        
        
        
        for (int i = 0; i < playernames.length; i++) {
            for (int j = 0; j < player.length; j++) {
                String play = player[j].getPlayerListName().toLowerCase();
                if(play.equals(playernames[i])){
                foundPlayer = player[j];
                }                
            }          
        }
        
        return foundPlayer;
    }

     public static Player findPlayerInArray(String[] playernames, Player p) {
        Player foundPlayer = null;
        Player[] player = Bukkit.getOnlinePlayers();
        
        ArrayList chat = new ArrayList();
        for (int i = 0; i < playernames.length; i++) {
            chat.add(playernames[i]);
        }
        
      for (int i = 0; i < playernames.length; i++) {
            for (int j = 0; j < player.length; j++) {
                String play = player[j].getPlayerListName().toLowerCase();
                if(play.equals(playernames[i])){
                foundPlayer = player[j];
                }
                if(foundPlayer == null && chat.contains("me")){
                foundPlayer = p;
                }
                
            }          
        }
        
        return foundPlayer;
    }
}
