package fr.birdo.bedwarsshop.event;

import fr.birdo.bedwarsshop.BedwarsShop;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Achat implements Listener {

    // create Main class instance
    private BedwarsShop instance;

    public Achat(BedwarsShop pluginInstance) {
        this.instance = pluginInstance;
    }

    @EventHandler
    public void buy(InventoryClickEvent e){

        String inv = e.getInventory().getTitle();
        Boolean hasMeta = e.getCurrentItem().hasItemMeta();
        Boolean hasName = e.getCurrentItem().getItemMeta().hasDisplayName();
        String getName = e.getCurrentItem().getItemMeta().getDisplayName();

        if (inv.equalsIgnoreCase("Item Shop") || inv.equalsIgnoreCase("Blocks") || inv.equalsIgnoreCase("Weapons") || inv.equalsIgnoreCase("Armors") || inv.equalsIgnoreCase("Tools") || inv.equalsIgnoreCase("Bows") || inv.equalsIgnoreCase("Potions") || inv.equalsIgnoreCase("Other")) {
            if (e.getCurrentItem() != null) {
                if (hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Blocks") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Weapons") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Armors") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Tools") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Bows") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Potions") || hasMeta && hasName && getName.equalsIgnoreCase(ChatColor.GREEN + "Other")) {
                }else{

                }
            }
        }
    }
}
