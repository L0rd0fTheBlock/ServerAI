/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jamiemac262.ServerAIReWrite.function;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Laptop
 */
public class GameTime {
    public Player p;
    
    public GameTime(Player player){
    p = player;
    }
    public void day(){
    for (World world : Bukkit.getWorlds()) {
		            world.setTime(0);
		          }
				new SendAIMessage(0.5,"Sure thing " + p.getName() + "! The time has been set to day","Okay, The time is set to day, Dont yah just love the sun...","Kool, wait the sun is hot not cold...");
			}
    
    public void night(){
    for (World world : Bukkit.getWorlds()) {
		            world.setTime(14000);
		          }
				new SendAIMessage(0.5,"Sure thing " + p.getName() + "! The time has been set to night","Okay i did it, but why? who likes those nasty mobs?","Boo night, Ahhh a zombie!!");
			
    }
}
