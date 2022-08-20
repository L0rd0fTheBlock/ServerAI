package me.vulps22.serverai.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import me.vulps22.serverai.ServerAI;
import me.vulps22.serverai.function.ReadWrite;
import me.vulps22.serverai.function.SendAIMessage;

public class WarnPlayer {

    PlayerChatEvent chat;
    ChatColor RED = ChatColor.RED;
    ChatColor WHITE = ChatColor.WHITE;
    public static boolean Smute;
    public static ArrayList<String> muted = ServerAI.MutedPlayersList;

    public WarnPlayer(Player p) throws IOException {

        File Warn = new File("plugins" + File.separator + "ServerAI" + File.separator + "Warnings" + File.separator + p.getName() + ".txt");
        if (!Warn.exists()) {
            new SendAIMessage(0.5, "I do not allow such language on my servers " + p.getName() + "! If i need to warn you again, i will mute you", "My servers do not accept this low language" + p.getName() + "! Try my limits anymore and you WILL be muted.", "This type of language is Unacceptable... Next time you will be muted.");
            chat.setCancelled(true);
            CreatePlayerWarnFile(p.getName());
        } else {
            String warnings = ReadWrite.getContents(Warn).toString();

            if (warnings.contains("1")) {
                new SendAIMessage(0.5, "I do not allow such language on my servers " + p.getName() + "! You have been warned before, now i am muting you! wou will need an administrator to unmute you", "I warned you before, that language is unacceptable on here. i have just muted you" + p.getName() + "!", "No chance" + p.getName() + "! that language is not allowed! i have muted you now!");
                RecordWarnings("2", p.getName());
                Smute = true;
                muted.add(p.getName());
                SaveMutes();
                Smute = false;
                chat.setCancelled(true);
            } else if (warnings.contains("2")) {
                RecordWarnings("3", p.getName());
                chat.setCancelled(true);
                new SendAIMessage(0.5, p.getName() + " has been kicked from this server for using unallowed words!", p.getName() + " Did not follow the chat filter and has been kicked!", p.getName() + " is a bad influence, they have just tried to use disallowed words 3 times...");
                p.kickPlayer(RED + "[SAI] " + WHITE + "I don't allow this lang. on my servers " + p.getName() + "! Don't push me any more!");
            } else if (warnings.contains("3")) {
                RecordWarnings("4", p.getName());
                new SendAIMessage(0.5, p.getName() + "has been banned from this server for continuously using offensive language", "i have had to ban " + p.getName() + "for their language!", "wow, they just do not learn. " + p.getName() + "has been banned for their language");
                chat.setCancelled(true);
                p.banPlayer(RED + "[SAI] " + WHITE + "I do not allow such language on my servers " + p.getName() + "! You have been banned!");
            }
        }

    }

    public static void CreatePlayerWarnFile(String par1Playername) {
        try {
            File file = new File("plugins" + File.separator + "ServerAI" + File.separator + "Warnings" + File.separator + par1Playername + ".txt");//path and name of file
            if (!file.exists())//if the file is there
            {
                file.createNewFile();
                RecordWarnings("1", par1Playername);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RecordWarnings(String par1WarningNumber, String par1Playername) throws IOException {
        File file = new File("plugins" + File.separator + "ServerAI" + File.separator + "Warnings" + File.separator + par1Playername + ".txt");
        if (file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("" + par1WarningNumber);
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }

    public static void SaveMutes() {
        if (Smute) {
            try {
                File file = new File("plugins" + File.separator + "ServerAI" + File.separator + "MutedPlayers.txt");
                BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < muted.size(); i++) {
                    bufferedwriter.write((new StringBuilder()).append((String) muted.get(i)).append("\r\n").toString());
                }
                bufferedwriter.close();
            } catch (Exception exception) {
                System.err.print(exception.toString());
            }
        }
    }
}
