package fr.birdo.bedwarsshop.utils;

import fr.birdo.bedwarsshop.BedwarsShop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamDataFile {

    public static void setBed(String team, boolean bed) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Bed.present", bed);
        saveFile(cfg, team);
    }

    public static void addPlayer(String team, Player player) {
        FileConfiguration cfg = getConfigFile(team);
        List<String> players = new ArrayList<>(getPlayers(team));
        players.add(player.getName());
        cfg.set("Players", players);
        saveFile(cfg, team);
    }

    public static void addLivePlayer(String team, Player player) {
        FileConfiguration cfg = getConfigFile(team);
        List<String> players = new ArrayList<>(getLivePlayers(team));
        players.add(player.getName());
        cfg.set("LivePlayers", players);
        saveFile(cfg, team);
    }

    public static void removeLivePlayer(String team, Player player) {
        FileConfiguration cfg = getConfigFile(team);
        List<String> players = new ArrayList<>(getLivePlayers(team));
        players.remove(player.getName());
        cfg.set("LivePlayers", players);
        saveFile(cfg, team);
    }

    public static void removePlayer(String team, Player player) {
        FileConfiguration cfg = getConfigFile(team);
        List<String> players = new ArrayList<>(getPlayers(team));
        players.remove(player.getName());
        cfg.set("Players", players);
        saveFile(cfg, team);
    }

    public static void setBedLocation(String team, Location bedLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Bed.world", bedLocation.getWorld());
        cfg.set("Bed.x", bedLocation.getX());
        cfg.set("Bed.y", bedLocation.getY());
        cfg.set("Bed.z", bedLocation.getZ());
        saveFile(cfg, team);
    }

    public static void setSpawnLocation(String team, Location spawnLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Spawn.world", spawnLocation.getWorld());
        cfg.set("Spawn.x", spawnLocation.getX());
        cfg.set("Spawn.y", spawnLocation.getY());
        cfg.set("Spawn.z", spawnLocation.getZ());
        saveFile(cfg, team);
    }

    public static Location getBedLocation(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Bed.world")), getConfigFile(team).getInt("Bed.x"), getConfigFile(team).getInt("Bed.y"), getConfigFile(team).getInt("Bed.z"));
    }

    public static Location getSpawnLocation(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Spawn.world")), getConfigFile(team).getInt("Spawn.x"), getConfigFile(team).getInt("Spawn.y"), getConfigFile(team).getInt("Spawn.z"));
    }

    public static List<String> getPlayers(String team) {
        return getConfigFile(team).getStringList("Players");
    }

    public static List<String> getLivePlayers(String team) {
        return getConfigFile(team).getStringList("LivePlayers");
    }

    public static boolean hasBed(String team) {
        return getConfigFile(team).getBoolean("Bed.present");
    }

    public static void createSections(String team) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Players", new ArrayList<>());
        cfg.set("LivePlayers", new ArrayList<>());
        cfg.set("Bed.present", false);
        cfg.set("Bed.world", "null");
        cfg.set("Bed.x", 0);
        cfg.set("Bed.y", 0);
        cfg.set("Bed.z", 0);
        cfg.set("Spawn.world", "null");
        cfg.set("Spawn.x", 0);
        cfg.set("Spawn.y", 0);
        cfg.set("Spawn.z", 0);
        saveFile(cfg, team);
    }

    private static FileConfiguration getConfigFile(String team) {
        return YamlConfiguration.loadConfiguration(getFile(team));
    }

    private static File getFile(String team) {
        return new File(BedwarsShop.teamDataFolderPath + "/" + team + ".yml");
    }

    private static void saveFile(FileConfiguration fileConfiguration, String team) {
        try {
            fileConfiguration.save(getFile(team));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
