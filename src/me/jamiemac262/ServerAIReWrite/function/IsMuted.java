package me.jamiemac262.ServerAIReWrite.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import me.jamiemac262.ServerAIReWrite.ServerAI;

import org.bukkit.entity.Player;

public class IsMuted {

    public static boolean isMuted(Player p) {
        ArrayList<String> muted = ServerAI.MutedPlayersList;
        if (muted.contains(p.getName().toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public static void loadMuteList() {

        try {
            int ait[] = new int[512];
            File file = new File("plugins" + File.separator + "ServerAI" + File.separator + "MutedPlayers.txt");
            if (file.exists()) {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
                String s;
                for (int i = 0; (s = bufferedreader.readLine()) != null; i++) {
                    ServerAI.MutedPlayersList.add(s);
                }
                bufferedreader.close();
            }
        } catch (Exception exception) {
            System.err.print(exception.toString());
        }
    }
}
