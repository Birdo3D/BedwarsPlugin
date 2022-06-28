package fr.birdo.bedwarsplugin.data;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
import fr.birdo.bedwarsplugin.utils.ArmorTypes;
import fr.birdo.bedwarsplugin.utils.ToolsTypes;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataFile {

    public static void setArmorType(Player player, ArmorTypes armorType) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("ArmorType", armorType.getIndex());
        saveFile(cfg, player);
    }

    public static void setPickaxe(Player player, ToolsTypes toolsType) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Tools.pickaxe", toolsType.getIndex());
        saveFile(cfg, player);
    }

    public static void setAxe(Player player, ToolsTypes toolsType) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Tools.axe", toolsType.getIndex());
        saveFile(cfg, player);
    }

    public static void setShears(Player player, Boolean shears) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Tools.shears", shears);
        saveFile(cfg, player);
    }

    public static void setTeam(Player player, String team) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Team", team);
        saveFile(cfg, player);
    }

    public static void setKills(Player player, int kills) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Stats.kills", kills);
        saveFile(cfg, player);
    }

    public static void setFinalKills(Player player, int kills) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Stats.f_kills", kills);
        saveFile(cfg, player);
    }

    public static void setBeds(Player player, int beds) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("Stats.beds", beds);
        saveFile(cfg, player);
    }

    public static int getArmorType(Player player) {
        return getConfigFile(player).getInt("ArmorType");
    }

    public static int getPickaxe(Player player) {
        return getConfigFile(player).getInt("Tools.pickaxe");
    }

    public static int getAxe(Player player) {
        return getConfigFile(player).getInt("Tools.axe");
    }

    public static boolean hasShears(Player player) {
        return getConfigFile(player).getBoolean("Tools.shears");
    }

    public static String getTeam(Player player) {
        return getConfigFile(player).getString("Team");
    }

    public static int getKills(Player player) {
        return getConfigFile(player).getInt("Stats.kills");
    }

    public static int getFinalKills(Player player) {
        return getConfigFile(player).getInt("Stats.f_kills");
    }

    public static int getBeds(Player player) {
        return getConfigFile(player).getInt("Stats.beds");
    }

    public static void createSections(Player player) {
        FileConfiguration cfg = getConfigFile(player);
        cfg.set("ArmorType", ArmorTypes.LEATHER.getIndex());
        cfg.set("Tools.pickaxe", ToolsTypes.NULL.getIndex());
        cfg.set("Tools.axe", ToolsTypes.NULL.getIndex());
        cfg.set("Tools.shears", false);
        cfg.set("Team", "null");
        cfg.set("Stats.kills", 0);
        cfg.set("Stats.f_kills", 0);
        cfg.set("Stats.beds", 0);
        saveFile(cfg, player);
    }

    private static FileConfiguration getConfigFile(Player player) {
        return YamlConfiguration.loadConfiguration(getFile(player));
    }

    private static File getFile(Player player) {
        return new File(BedwarsPlugin.playerDataFolderPath + "/" + player.getUniqueId() + ".yml");
    }

    private static void saveFile(FileConfiguration fileConfiguration, Player player) {
        try {
            fileConfiguration.save(getFile(player));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}