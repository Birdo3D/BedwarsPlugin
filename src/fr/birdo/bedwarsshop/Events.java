package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.utils.*;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;

public class Events implements Listener {

    public Events(BedwarsShop bedwarsShop) {
    }

    @EventHandler
    public void commandSendEvent(PlayerCommandPreprocessEvent e) {
        String[] args = e.getMessage().split(" ");
        if (args[0].equalsIgnoreCase("/bs") && e.getPlayer().isOp())
            if (args[1].equalsIgnoreCase("give")) {
                if (args[2].equalsIgnoreCase("pnj")) {
                    ItemStack egg = new ItemStack(Material.EGG, 1);
                    ItemMeta eggM = egg.getItemMeta();
                    eggM.setDisplayName("Classic PNJ Spawn Egg");
                    egg.setItemMeta(eggM);
                    e.getPlayer().getInventory().addItem(egg);
                    e.setCancelled(true);
                }
            } else if (args[1].equalsIgnoreCase("test")) {
                e.getPlayer().sendMessage(ChatColor.GREEN + "Le plugin fonctionne correctement !");
                e.setCancelled(true);
            }
    }

    @EventHandler
    public void spawnPNJ(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            double x = e.getClickedBlock().getLocation().getX() + 0.5;
            double y = e.getClickedBlock().getLocation().getY() + 1;
            double z = e.getClickedBlock().getLocation().getZ() + 0.5;
            if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                if (e.getItem() != null && e.getItem().getType() == Material.EGG) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Classic PNJ Spawn Egg")) {
                        e.setCancelled(true);
                        e.getPlayer().getWorld().spawnEntity(new Location(e.getPlayer().getLocation().getWorld(), x, y, z), EntityType.VILLAGER).setCustomName("Item Shop");
                    }
                }
            }
        }
    }

    @EventHandler
    public void openGui(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER) {
            if (e.getRightClicked().getCustomName().equalsIgnoreCase("Item Shop")) {
                e.setCancelled(true);
                Gui.pnj01(e.getPlayer(), "Blocks");
            }
        }
    }

    @EventHandler
    public void guiClickEvent(InventoryClickEvent e) {
        String invName = "Item Shop - ";
        if (e.getClickedInventory() != null) {
            String inv = e.getClickedInventory().getTitle();
            if (inv.equalsIgnoreCase(invName + "Blocks") || inv.equalsIgnoreCase(invName + "Weapons") || inv.equalsIgnoreCase(invName + "Armors") || inv.equalsIgnoreCase(invName + "Tools") || inv.equalsIgnoreCase(invName + "Bows") || inv.equalsIgnoreCase(invName + "Potions") || inv.equalsIgnoreCase(invName + "Other")) {
                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName())
                    if (e.getCurrentItem().getItemMeta().getDisplayName().length() > 1) {
                        String itemName = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                        if (itemName.equalsIgnoreCase("Blocks") || itemName.equalsIgnoreCase("Weapons") || itemName.equalsIgnoreCase("Armors") || itemName.equalsIgnoreCase("Tools") || itemName.equalsIgnoreCase("Bows") || itemName.equalsIgnoreCase("Potions") || itemName.equalsIgnoreCase("Other"))
                            Gui.pnj01((Player) e.getWhoClicked(), itemName);
                        if (Gui.getItems(inv).get(e.getSlot()) != null)
                            Utils.buyItem((Player) e.getWhoClicked(), Gui.getItems(inv).get(e.getSlot()));
                    }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        File playerDataFile = new File(BedwarsShop.playerDataFolderPath + "/" + e.getPlayer().getUniqueId() + ".yml");
        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (playerDataFile.exists()) {
            CustomConfigurationFile.createSections(e.getPlayer());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.getPlayer().getInventory().setItem(0, Converter.convertToItemStack(new Item(Material.WOOD_SWORD, "Wooden Sword", 1, 0, MoneyType.NULL, true)));
        if (CustomConfigurationFile.hasShears(e.getPlayer()))
            e.getPlayer().getInventory().setItem(1, Converter.convertToItemStack(new Item(Material.SHEARS, "Shears", 1, 0, MoneyType.NULL, true)));
        e.getPlayer().getInventory().setHelmet(Converter.convertToItemStack(new Item(Material.LEATHER_HELMET, "Helmet", 1, 0, MoneyType.NULL, true)));
        e.getPlayer().getInventory().setChestplate(Converter.convertToItemStack(new Item(Material.LEATHER_CHESTPLATE, "Chestplate", 1, 0, MoneyType.NULL, true)));
        e.getPlayer().getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(CustomConfigurationFile.getArmorType(e.getPlayer()), true)));
        e.getPlayer().getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(CustomConfigurationFile.getArmorType(e.getPlayer()), false)));
    }
}