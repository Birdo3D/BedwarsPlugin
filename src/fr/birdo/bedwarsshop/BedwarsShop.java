package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.event.Commands;
import fr.birdo.bedwarsshop.event.ClassicPNJ;
import fr.birdo.bedwarsshop.gui.ClassicGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsShop extends JavaPlugin {
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[BedwarsShop] Plugin demmaré !");
        PluginManager pm = getServer().getPluginManager();
        saveDefaultConfig();
        pm.registerEvents(new Commands(this), this);
        pm.registerEvents(new ClassicPNJ(this), (this));
        pm.registerEvents(new ClassicGui(), (this));
        this.createSection();
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
}