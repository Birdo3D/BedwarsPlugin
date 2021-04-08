package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.event.Achat;
import fr.birdo.bedwarsshop.event.Commands;
import fr.birdo.bedwarsshop.event.ClassicPNJ;
import fr.birdo.bedwarsshop.gui.ClassicGui;
import fr.birdo.bedwarsshop.utils.Prices;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class BedwarsShop extends JavaPlugin {

    public ItemStack money01 = new ItemStack(Material.valueOf(this.getConfig().getString("Money01")), 1);
    public ItemStack money02 = new ItemStack(Material.valueOf(this.getConfig().getString("Money02")), 1);
    public ItemStack money03 = new ItemStack(Material.valueOf(this.getConfig().getString("Money03")), 1);

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[BedwarsShop] Plugin demmaré !");
        PluginManager pm = getServer().getPluginManager();
        saveDefaultConfig();
        pm.registerEvents(new Commands(this), this);
        pm.registerEvents(new ClassicPNJ(this), (this));
        pm.registerEvents(new ClassicGui(), (this));
        pm.registerEvents(new Achat(this), (this));
        pm.registerEvents(new Prices(this), (this));
        this.createSection();
        Prices.createFile();

        File pricesConfigFile = new File("plugins/BedwarsShop/prices.yml");
        if(!pricesConfigFile.exists()) {
            try {
                pricesConfigFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[BedwarsShop] Plugin arrêté !");
    }

    private boolean sectionExists(String section) {
        return this.getConfig().getConfigurationSection(section) != null;
    }

    private boolean sectionEmpty(String section) {
        return !sectionExists(section) || this.getConfig().getConfigurationSection(section).getKeys(false).size() == 0;
    }

    public void createSection() {
        if (sectionEmpty("Item1")) {
            this.getConfig().createSection("Item1");
            this.getConfig().set("Item1", "IRON_INGOT");
            this.saveConfig();
        }
        if (sectionEmpty("Item2")) {
            this.getConfig().createSection("Item2");
            this.getConfig().set("Item2", "DIAMOND");
            this.saveConfig();
        }
        if (sectionEmpty("Item3")) {
            this.getConfig().createSection("Item3");
            this.getConfig().set("Item3", "EMERALD");
            this.saveConfig();
        }
    }

    public void createPricesFile(String path, String content) {
        File pricesConfigFile = new File("plugins/BedwarsShop/prices.yml");
        FileConfiguration pricesConfig = YamlConfiguration.loadConfiguration(pricesConfigFile);
        pricesConfig.set(path, content);
        try {
            pricesConfig.save(pricesConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readPricesFile(String path) {
        File pricesConfigFile = new File("plugins/BedwarsShop/prices.yml");
        FileConfiguration pricesConfig = YamlConfiguration.loadConfiguration(pricesConfigFile);
        Object result = pricesConfig.get(path);
        return result;
    }
}