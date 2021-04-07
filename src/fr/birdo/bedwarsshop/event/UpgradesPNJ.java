package fr.birdo.bedwarsshop.event;

import fr.birdo.bedwarsshop.BedwarsShop;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class UpgradesPNJ implements Listener {

    // create Main class instance
    private BedwarsShop instance;

    public UpgradesPNJ(BedwarsShop pluginInstance) {
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
                                if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Team Upgrades PNJ Spawn Egg")) {
                                    e.setCancelled(true);
                                    e.getPlayer().getWorld().spawnEntity(clic, EntityType.VILLAGER).setCustomName("Team Upgrades");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem().getType() == Material.EGG) {
                if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Team Upgrades PNJ Spawn Egg")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void openGui(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER) {
            if (e.getRightClicked().getCustomName().equalsIgnoreCase("Team Upgrades")) {
                e.setCancelled(true);
                //MainShop.pnj02(e.getPlayer());
            }
        }
    }
}
