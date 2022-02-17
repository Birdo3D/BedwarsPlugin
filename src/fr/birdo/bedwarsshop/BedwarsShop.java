package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.utils.PlayerDataFile;
import fr.birdo.bedwarsshop.utils.TeamDataFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BedwarsShop extends JavaPlugin {

    public static String playerDataFolderPath;
    public static String teamDataFolderPath;
    private static final List<String> folders = new ArrayList<>();
    public static final List<String> teams = Arrays.asList("Red", "Blue", "Green", "Yellow", "Aqua", "White", "Pink", "Gray");

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(this), (this));
        Events.ticking(this);
        saveDefaultConfig();
        playerDataFolderPath = getDataFolder().getAbsolutePath() + "/PlayerData";
        teamDataFolderPath = getDataFolder().getAbsolutePath() + "/TeamData";
        folders.add(playerDataFolderPath);
        folders.add(teamDataFolderPath);
        for (String folderName : folders) {
            File folder = new File(folderName);
            if (folder.exists() && folder.list().length > 0) {
                String files[] = folder.list();
                for (String tmp : files) {
                    File file = new File(folder, tmp);
                    file.delete();
                }
                if (folder.list().length == 0)
                    folder.delete();
            } else
                folder.mkdir();
        }
        if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers()) {
                File playerDataFile = new File(BedwarsShop.playerDataFolderPath + "/" + player.getUniqueId() + ".yml");
                if (!playerDataFile.exists()) {
                    try {
                        playerDataFile.createNewFile();
                        PlayerDataFile.createSections(player);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        for (String team : teams) {
            File teamDataFile = new File(BedwarsShop.teamDataFolderPath + "/" + team + ".yml");
            if (!teamDataFile.exists()) {
                try {
                    teamDataFile.createNewFile();
                    TeamDataFile.createSections(team);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void onDisable() {
        for (String folderName : folders) {
            File folder = new File(folderName);
            if (folder.exists())
                if (folder.list().length == 0)
                    folder.delete();
                else {
                    String files[] = folder.list();
                    for (String tmp : files) {
                        File file = new File(folder, tmp);
                        file.delete();
                    }
                    if (folder.list().length == 0)
                        folder.delete();
                }
        }
    }
}