package fr.birdo.bedwarsshop;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {

    public Events(BedwarsShop bedwarsShop) {
    }

    @EventHandler
    public void commands(PlayerCommandPreprocessEvent e) {
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
            if (e.getPlayer().isOp()) {
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
    public void onClicDecoration(InventoryClickEvent e) {
        String inv = e.getClickedInventory().getTitle();
        String name = "Item Shop - ";
        if (e.getClickedInventory() != null) {
            if (inv.equalsIgnoreCase(name + "Blocks") || inv.equalsIgnoreCase(name + "Weapons") || inv.equalsIgnoreCase(name + "Armors") || inv.equalsIgnoreCase(name + "Tools") || inv.equalsIgnoreCase(name + "Bows") || inv.equalsIgnoreCase(name + "Potions") || inv.equalsIgnoreCase(name + "Other")) {
                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                        case "Blocks":
                            Gui.pnj01((Player) e.getWhoClicked(), "Blocks");
                        case "Weapons":
                            Gui.pnj01((Player) e.getWhoClicked(), "Weapons");
                        case "Armors":
                            Gui.pnj01((Player) e.getWhoClicked(), "Armors");
                        case "Tools":
                            Gui.pnj01((Player) e.getWhoClicked(), "Tools");
                        case "Bows":
                            Gui.pnj01((Player) e.getWhoClicked(), "Bows");
                        case "Potions":
                            Gui.pnj01((Player) e.getWhoClicked(), "Potions");
                        case "Other":
                            Gui.pnj01((Player) e.getWhoClicked(), "Other");
                    }

                    //Achat
                    /*if (e.getSlot() > 16 && !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
                        if (Utils.getNb(Utils.getMoneyItemStack(0), e.getWhoClicked()) >= Integer.parseInt(pricesConfig.getString("blocks.wool.price"))) {
                            e.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOL, 16));
                            e.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, Integer.parseInt(pricesConfig.getString("blocks.wool.price"))));
                        } else {
                            e.getWhoClicked().sendMessage(ChatColor.RED + "You need " + (Integer.parseInt(pricesConfig.getString("blocks.wool.price")) - Utils.getNb(BedwarsShop.money0, e.getWhoClicked())) + " " + Comparator.getMaterial(pricesConfig.getString("blocks.wool.money")).getType() + " to buy this");
                        }
                    }*/
                /*if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Blocks")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Blocks");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Weapons")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Weapons");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Armors")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Armors");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Tools")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Tools");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Bows")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Bows");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Potions")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Potions");
                } else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Other")) {
                    Gui.pnj01((Player) e.getWhoClicked(), "Other");
                }*/
                }
            }
        }
        e.setCancelled(true);
    }
}