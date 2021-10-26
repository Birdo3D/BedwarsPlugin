package fr.birdo.bedwarsshop;

import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsShop extends JavaPlugin {

    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Events(this), (this));
    }

    public void onDisable() {
    }
}