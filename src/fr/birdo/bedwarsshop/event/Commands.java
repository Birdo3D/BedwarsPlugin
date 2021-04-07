package fr.birdo.bedwarsshop.event;

import fr.birdo.bedwarsshop.BedwarsShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements Listener {

    // create Main class instance
    private BedwarsShop instance;

    public Commands(BedwarsShop pluginInstance) {
        this.instance = pluginInstance;
    }

    @EventHandler
    public void giveEgg(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (args[0].equalsIgnoreCase("/BedwarsShop")) {
            p.sendMessage(ChatColor.GREEN+"Le plugin fonctionne correctement !");
            e.setCancelled(true);
        }

        if (args[0].equalsIgnoreCase("/give")) {
            if (p.isOp()) {
                if (args[1].equalsIgnoreCase("pnj")) {
                    if (args[2].equalsIgnoreCase("classic")) {
                        ItemStack egg = new ItemStack(Material.EGG, 1);
                        ItemMeta eggM = egg.getItemMeta();
                        eggM.setDisplayName("Classic PNJ Spawn Egg");
                        egg.setItemMeta(eggM);
                        p.getInventory().addItem(egg);
                    }else if(args[2].equalsIgnoreCase("upgrades")) {
                        ItemStack egg = new ItemStack(Material.EGG, 1);
                        ItemMeta eggM = egg.getItemMeta();
                        eggM.setDisplayName("Team Upgrades PNJ Spawn Egg");
                        egg.setItemMeta(eggM);
                        p.getInventory().addItem(egg);
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
