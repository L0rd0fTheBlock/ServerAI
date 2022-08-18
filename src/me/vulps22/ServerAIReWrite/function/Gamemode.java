/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.vulps22.ServerAIReWrite.function;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 *
 * @author Laptop
 */
public class Gamemode {

    public Player p;

    public Gamemode(Player player) {
        p = player;
    }

    public void change() {
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            new SendAIMessage(0.5, "You are now in Survival mode" + p.getDisplayName(), p.getDisplayName() + "You are now in Survival.", "aw too bad " + p.getDisplayName() + " you lost your gm, i cant lose mine, i am everywhere and nowhere at all");
            p.setGameMode(GameMode.SURVIVAL);
        } else {
            new SendAIMessage(0.5, "You are now in Creative mode" + p.getDisplayName(), p.getDisplayName() + "You are now in Creative.", "careful when u dig down. " + p.getDisplayName() + " Creatvie doesnt stop at bedrock");
            p.setGameMode(GameMode.CREATIVE);
        }
    }

    public String check() {
        String gamemode = p.getGameMode().toString();

        return gamemode;
    }
}
