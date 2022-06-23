package fr.birdo.bedwarsplugin;

import fr.birdo.bedwarsplugin.data.GeneratorDataFile;
import fr.birdo.bedwarsplugin.data.PlayerDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BedwarsPlugin extends JavaPlugin {

    public static String playerDataFolderPath;
    public static String teamDataFolderPath;
    public static String generatorDataFolderPath;
    private static final List<String> folders = new ArrayList<>();
    public static final List<String> teams = Arrays.asList("Red", "Blue", "Green", "Yellow", "Aqua", "White", "Pink", "Gray");
    public static final List<String> generators = Arrays.asList("Diamond", "Emerald");

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(this), (this));
        Events.ticking(this);
        saveDefaultConfig();
        playerDataFolderPath = getDataFolder().getAbsolutePath() + "/PlayerData";
        teamDataFolderPath = getDataFolder().getAbsolutePath() + "/TeamData";
        generatorDataFolderPath = getDataFolder().getAbsolutePath() + "/GeneratorData";
        folders.add(playerDataFolderPath);
        folders.add(teamDataFolderPath);
        folders.add(generatorDataFolderPath);
        for (String folderName : folders) {
            File folder = new File(folderName);
            if (!folder.exists())
                folder.mkdir();
        }
        if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers()) {
                File playerDataFile = new File(BedwarsPlugin.playerDataFolderPath + "/" + player.getUniqueId() + ".yml");
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
            File teamDataFile = new File(BedwarsPlugin.teamDataFolderPath + "/" + team + ".yml");
            if (!teamDataFile.exists()) {
                try {
                    teamDataFile.createNewFile();
                    TeamDataFile.createSections(team);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        for (String gen : generators) {
            File generatorDataFile = new File(BedwarsPlugin.generatorDataFolderPath + "/" + gen + ".yml");
            if (!generatorDataFile.exists()) {
                try {
                    generatorDataFile.createNewFile();
                    GeneratorDataFile.createSections();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void onDisable() {
    }
}