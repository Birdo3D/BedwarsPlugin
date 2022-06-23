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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratorDataFile {

    public static void setGenerator(String gen, int index, Location location) {
        FileConfiguration cfg = getConfigFile(gen);
        cfg.set(index + ".world", location.getWorld().getName());
        cfg.set(index + ".x", location.getX());
        cfg.set(index + ".y", location.getY());
        cfg.set(index + ".z", location.getZ());
        saveFile(cfg, gen);
    }

    public static Location getGenerator(String gen, int index) {
        return new Location(Bukkit.getWorld(getConfigFile(gen).getString(index + ".world")), getConfigFile(gen).getInt(index + ".x"), getConfigFile(gen).getInt(index + ".y"), getConfigFile(gen).getInt(index + ".z"));
    }

    public static void createSections() {
        for (String gen : BedwarsPlugin.generators) {
            FileConfiguration cfg = getConfigFile(gen);
            for (int i = 1; i < 9; i++) {
                cfg.set(i + ".world", "null");
                cfg.set(i + ".x", 0);
                cfg.set(i + ".y", 0);
                cfg.set(i + ".z", 0);
            }
            saveFile(cfg, gen);
        }
    }

    private static FileConfiguration getConfigFile(String gen) {
        return YamlConfiguration.loadConfiguration(getFile(gen));
    }

    private static File getFile(String gen) {
        return new File(BedwarsPlugin.generatorDataFolderPath + "/" + gen + ".yml");
    }

    private static void saveFile(FileConfiguration fileConfiguration, String gen) {
        try {
            fileConfiguration.save(getFile(gen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
