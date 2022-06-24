package fr.birdo.bedwarsplugin.data;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
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

    public static void setBed(String team, boolean hasBed) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Bed.present", hasBed);
        saveFile(cfg, team);
    }

    public static void setSpawn(String team, boolean hasSpawn) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Spawn.hasSpawn", hasSpawn);
        saveFile(cfg, team);
    }

    public static void setGenerator(String team, boolean hasGenerator) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Generator.hasGenerator", hasGenerator);
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

    public static void setBed1Location(String team, Location bedLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Bed.world", bedLocation.getWorld().getName());
        cfg.set("Bed.y", bedLocation.getY());
        cfg.set("Bed1.x", bedLocation.getX());
        cfg.set("Bed1.z", bedLocation.getZ());
        saveFile(cfg, team);
    }

    public static void setBed2Location(String team, Location bedLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Bed2.x", bedLocation.getX());
        cfg.set("Bed2.z", bedLocation.getZ());
        saveFile(cfg, team);
    }

    public static void setSpawnLocation(String team, Location spawnLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Spawn.world", spawnLocation.getWorld().getName());
        cfg.set("Spawn.x", spawnLocation.getX());
        cfg.set("Spawn.y", spawnLocation.getY());
        cfg.set("Spawn.z", spawnLocation.getZ());
        saveFile(cfg, team);
    }

    public static void setGeneratorLocation(String team, Location spawnLocation) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Generator.world", spawnLocation.getWorld().getName());
        cfg.set("Generator.x", spawnLocation.getX());
        cfg.set("Generator.y", spawnLocation.getY());
        cfg.set("Generator.z", spawnLocation.getZ());
        saveFile(cfg, team);
    }

    public static void setSharpness(String team, boolean sharpness) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.sharpness", sharpness);
        saveFile(cfg, team);
    }

    public static void setHeal(String team, boolean heal) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.heal", heal);
        saveFile(cfg, team);
    }

    public static void setDragon(String team, boolean dragon) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.dragon", dragon);
        saveFile(cfg, team);
    }

    public static void setProtection(String team, int protectionLevel) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.protection", protectionLevel);
        saveFile(cfg, team);
    }

    public static void setHaste(String team, int hasteLevel) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.haste", hasteLevel);
        saveFile(cfg, team);
    }

    public static void setForge(String team, int forgeLevel) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Upgrades.forge", forgeLevel);
        saveFile(cfg, team);
    }

    public static void addTrap(String team, int trap) {
        FileConfiguration cfg = getConfigFile(team);
        List<Integer> list = getTraps(team);
        list.add(trap);
        cfg.set("Traps", list);
        saveFile(cfg, team);
    }

    public static void removeTrap(String team, int trap) {
        FileConfiguration cfg = getConfigFile(team);
        List<Integer> list = getTraps(team);
        list.remove(trap);
        cfg.set("Traps", list);
        saveFile(cfg, team);
    }

    public static Location getBed1Location(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Bed.world")), getConfigFile(team).getInt("Bed1.x"), getConfigFile(team).getInt("Bed.y"), getConfigFile(team).getInt("Bed1.z"));
    }

    public static Location getBed2Location(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Bed.world")), getConfigFile(team).getInt("Bed2.x"), getConfigFile(team).getInt("Bed.y"), getConfigFile(team).getInt("Bed2.z"));
    }

    public static Location getSpawnLocation(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Spawn.world")), getConfigFile(team).getInt("Spawn.x"), getConfigFile(team).getInt("Spawn.y"), getConfigFile(team).getInt("Spawn.z"));
    }

    public static Location getGeneratorLocation(String team) {
        return new Location(Bukkit.getWorld(getConfigFile(team).getString("Generator.world")), getConfigFile(team).getInt("Generator.x"), getConfigFile(team).getInt("Generator.y"), getConfigFile(team).getInt("Generator.z"));
    }

    public static Boolean hasSharpness(String team) {
        return getConfigFile(team).getBoolean("Upgrades.sharpness");
    }

    public static Boolean hasHeal(String team) {
        return getConfigFile(team).getBoolean("Upgrades.heal");
    }

    public static Boolean hasDragon(String team) {
        return getConfigFile(team).getBoolean("Upgrades.dragon");
    }

    public static Integer getProtection(String team) {
        return getConfigFile(team).getInt("Upgrades.protection");
    }

    public static Integer getHaste(String team) {
        return getConfigFile(team).getInt("Upgrades.haste");
    }

    public static Integer getForge(String team) {
        return getConfigFile(team).getInt("Upgrades.forge");
    }

    public static List<Integer> getTraps(String team) {
        return getConfigFile(team).getIntegerList("Traps");
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

    public static boolean hasSpawn(String team) {
        return getConfigFile(team).getBoolean("Spawn.hasSpawn");
    }

    public static boolean hasGenerator(String team) {
        return getConfigFile(team).getBoolean("Generator.hasGenerator");
    }

    public static void createSections(String team) {
        FileConfiguration cfg = getConfigFile(team);
        //Players
        cfg.set("Players", new ArrayList<>());
        cfg.set("LivePlayers", new ArrayList<>());
        //Bed
        cfg.set("Bed.present", false);
        cfg.set("Bed.world", "null");
        cfg.set("Bed.y", 0);
        cfg.set("Bed1.x", 0);
        cfg.set("Bed1.z", 0);
        cfg.set("Bed2.x", 0);
        cfg.set("Bed2.z", 0);
        //Spawn Location
        cfg.set("Spawn.hasSpawn", false);
        cfg.set("Spawn.world", "null");
        cfg.set("Spawn.x", 0);
        cfg.set("Spawn.y", 0);
        cfg.set("Spawn.z", 0);
        //Generator Location
        cfg.set("Generator.hasGenerator", false);
        cfg.set("Generator.world", "null");
        cfg.set("Generator.x", 0);
        cfg.set("Generator.y", 0);
        cfg.set("Generator.z", 0);
        //Upgrades
        cfg.set("Upgrades.sharpness", false);
        cfg.set("Upgrades.protection", 0);
        cfg.set("Upgrades.haste", 0);
        cfg.set("Upgrades.forge", 0);
        cfg.set("Upgrades.heal", false);
        cfg.set("Upgrades.dragon", false);
        //Traps
        cfg.set("Traps", new ArrayList<>());
        saveFile(cfg, team);
    }

    private static FileConfiguration getConfigFile(String team) {
        return YamlConfiguration.loadConfiguration(getFile(team));
    }

    private static File getFile(String team) {
        return new File(BedwarsPlugin.teamDataFolderPath + "/" + team + ".yml");
    }

    private static void saveFile(FileConfiguration fileConfiguration, String team) {
        try {
            fileConfiguration.save(getFile(team));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
