package fr.birdo.bedwarsplugin.utils;

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

    public static void addGenerator(String gen, Location location) {
        FileConfiguration cfg = getConfigFile(gen);
        Object obj = Collections.max(getGenerators(gen));
        cfg.set((int) obj + 1 + ".world", location.getWorld().getName());
        cfg.set((int) obj + 1 + ".x", location.getX());
        cfg.set((int) obj + 1 + ".y", location.getY());
        cfg.set((int) obj + 1 + ".z", location.getZ());
        List<Integer> generators = new ArrayList<>(getGenerators(gen));
        generators.add((int) obj + 1);
        cfg.set("Generators", generators);
        saveFile(cfg, gen);
    }

    public static void removeGenerator(String gen, Location location) {
        FileConfiguration cfg = getConfigFile(gen);
        Object obj = Collections.max(getGenerators(gen));
        for (int i = 1; i <= (int) obj; i++) {
            if (getGeneratorLocation(gen, i).getWorld() == location.getWorld())
                if (getGeneratorLocation(gen, i).getX() == location.getX())
                    if (getGeneratorLocation(gen, i).getY() == location.getY())
                        if (getGeneratorLocation(gen, i).getZ() == location.getZ()) {
                            List<Integer> generators = new ArrayList<>(getGenerators(gen));
                            generators.remove(i);
                            cfg.set("Generators", generators);
                        }
        }
        saveFile(cfg, gen);
    }

    public static Location getGeneratorLocation(String gen, int generator) {
        return new Location(Bukkit.getWorld(getConfigFile(gen).getString(generator + ".world")), getConfigFile(gen).getInt(generator + ".x"), getConfigFile(gen).getInt(generator + ".y"), getConfigFile(gen).getInt(generator + ".z"));
    }

    public static List<Integer> getGenerators(String gen) {
        return getConfigFile(gen).getIntegerList("Generators");
    }

    public static void createSections(String team) {
        FileConfiguration cfg = getConfigFile(team);
        cfg.set("Generators", Arrays.asList(0));
        saveFile(cfg, team);
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
