package fr.birdo.bedwarsshop.event;

import fr.birdo.bedwarsshop.BedwarsShop;
import fr.birdo.bedwarsshop.gui.ClassicGui;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClassicPNJ implements Listener {

    // create Main class instance
    private BedwarsShop instance;

    public ClassicPNJ(BedwarsShop pluginInstance) {
        this.instance = pluginInstance;
    }

    @EventHandler
    public void spawnPNJ(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null) {
            World w = e.getPlayer().getLocation().getWorld();
            double x = e.getClickedBlock().getLocation().getX() + 0.5;
            double y = e.getClickedBlock().getLocation().getY() + 1;
            double z = e.getClickedBlock().getLocation().getZ() + 0.5;
            Location clic = new Location(w, x, y, z);
            if (e.getAction() != null) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                        if (e.getItem() != null) {
                            if (e.getItem().getType() == Material.EGG) {
                                if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Classic PNJ Spawn Egg")) {
                                    e.setCancelled(true);
                                    e.getPlayer().getWorld().spawnEntity(clic, EntityType.VILLAGER).setCustomName("Item Shop");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem().getType() == Material.EGG) {
                if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Classic PNJ Spawn Egg")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void openGui(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER) {
            if (e.getRightClicked().getCustomName().equalsIgnoreCase("Item Shop")) {
                e.setCancelled(true);
                ClassicGui.pnj01(e.getPlayer(), "Item Shop");
            }
        }
    }

    @EventHandler
    public void onClicDecoration(InventoryClickEvent e) {

        String inv = e.getInventory().getTitle();

        if (inv.equalsIgnoreCase("Item Shop") || inv.equalsIgnoreCase("Blocks") || inv.equalsIgnoreCase("Weapons") || inv.equalsIgnoreCase("Armors") || inv.equalsIgnoreCase("Tools") || inv.equalsIgnoreCase("Bows") || inv.equalsIgnoreCase("Potions") || inv.equalsIgnoreCase("Other")) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Blocks")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Blocks");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Weapons")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Weapons");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Armors")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Armors");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Tools")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Tools");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Bows")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Bows");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Potions")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Potions");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Other")) {
                    ClassicGui.pnj01((Player) e.getWhoClicked(), "Other");
                }
                e.setCancelled(true);
            }
        }
    }
}
