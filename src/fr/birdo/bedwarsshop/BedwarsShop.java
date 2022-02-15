package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.utils.CustomConfigurationFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class BedwarsShop extends JavaPlugin {

    public static String playerDataFolderPath;
    public static String teamDataFolderPath;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(this), (this));
        Events.ticking(this);
        saveDefaultConfig();
        playerDataFolderPath = getDataFolder().getAbsolutePath() + "/PlayerData";
        teamDataFolderPath = getDataFolder().getAbsolutePath() + "/TeamData";
        File folder = new File(playerDataFolderPath);
        File folder1 = new File(teamDataFolderPath);
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
        if (folder1.exists() && folder1.list().length > 0) {
            String files[] = folder1.list();
            for (String tmp : files) {
                File file = new File(folder1, tmp);
                file.delete();
            }
            if (folder1.list().length == 0)
                folder.delete();
        } else
            folder1.mkdir();
        if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers()) {
                File playerDataFile = new File(BedwarsShop.playerDataFolderPath + "/" + player.getUniqueId() + ".yml");
                if (!playerDataFile.exists()) {
                    try {
                        playerDataFile.createNewFile();
                        CustomConfigurationFile.createSections(player);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
    }

    public void onDisable() {
        File folder = new File(playerDataFolderPath);
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