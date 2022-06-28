package fr.birdo.bedwarsplugin.utils;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
import fr.birdo.bedwarsplugin.data.PlayerDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
import fr.birdo.bedwarsplugin.guis.GuiItemShop;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static void buyItem(Player player, Item item) {
        Inventory inventory = player.getInventory();
        int moneyAmount = 0;
        for (int i = 0; i < 36; i++)
            if (inventory.getItem(i) != null && inventory.getItem(i).getType() == item.getMoneyType().getMaterial())
                moneyAmount = moneyAmount + inventory.getItem(i).getAmount();
        if (moneyAmount >= item.getPrice()) {
            if (item.getMaterial() == Material.CHAINMAIL_BOOTS) {
                if (PlayerDataFile.getArmorType(player) == 0) {
                    PlayerDataFile.setArmorType(player, ArmorTypes.CHAINMAIL);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.CHAINMAIL.getIndex(), true), false));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.CHAINMAIL.getIndex(), false), false));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getMaterial() == Material.IRON_BOOTS) {
                if (PlayerDataFile.getArmorType(player) < 2) {
                    PlayerDataFile.setArmorType(player, ArmorTypes.IRON);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.IRON.getIndex(), true), false));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.IRON.getIndex(), false), false));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getMaterial() == Material.DIAMOND_BOOTS) {
                if (PlayerDataFile.getArmorType(player) < 3) {
                    PlayerDataFile.setArmorType(player, ArmorTypes.DIAMOND);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.DIAMOND.getIndex(), true), false));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.DIAMOND.getIndex(), false), false));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getName().contains("Pickaxe")) {
                if (PlayerDataFile.getPickaxe(player) != 4) {
                    if (PlayerDataFile.getPickaxe(player) > 0) {
                        for (int i = 0; i < 36; i++) {
                            if (player.getInventory().getItem(i) != null) {
                                Material material = player.getInventory().getItem(i).getType();
                                if (material == Material.WOOD_PICKAXE || material == Material.IRON_PICKAXE || material == Material.GOLD_PICKAXE || material == Material.DIAMOND_PICKAXE)
                                    inventory.setItem(i, Converter.convertToItemStack(item, false));
                            }
                        }
                        PlayerDataFile.setPickaxe(player, Objects.requireNonNull(getToolFromID(PlayerDataFile.getPickaxe(player) + 1)));
                        pay(item, player);
                        GuiItemShop.pnj01(player, "Tools");
                    } else if (hasPlace(item, player)) {
                        player.getInventory().addItem(Converter.convertToItemStack(item, false));
                        PlayerDataFile.setPickaxe(player, Objects.requireNonNull(getToolFromID(PlayerDataFile.getPickaxe(player) + 1)));
                        pay(item, player);
                        GuiItemShop.pnj01(player, "Tools");
                    } else
                        player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
                } else
                    player.sendMessage(ChatColor.RED + "You already own this item !");
            } else if (item.getName().contains("Axe")) {
                if (PlayerDataFile.getAxe(player) != 4) {
                    if (PlayerDataFile.getAxe(player) > 0) {
                        for (int i = 0; i < 36; i++) {
                            if (player.getInventory().getItem(i) != null) {
                                Material material = player.getInventory().getItem(i).getType();
                                if (material == Material.WOOD_AXE || material == Material.STONE_AXE || material == Material.IRON_AXE || material == Material.DIAMOND_AXE)
                                    inventory.setItem(i, Converter.convertToItemStack(item, false));
                            }
                        }
                        PlayerDataFile.setAxe(player, Objects.requireNonNull(getToolFromID(PlayerDataFile.getAxe(player) + 1)));
                        pay(item, player);
                        GuiItemShop.pnj01(player, "Tools");
                    } else if (hasPlace(item, player)) {
                        player.getInventory().addItem(Converter.convertToItemStack(item, false));
                        PlayerDataFile.setAxe(player, Objects.requireNonNull(getToolFromID(PlayerDataFile.getAxe(player) + 1)));
                        pay(item, player);
                        GuiItemShop.pnj01(player, "Tools");
                    } else
                        player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
                } else
                    player.sendMessage(ChatColor.RED + "You already own this item !");
            } else if (item.getMaterial() == Material.SHEARS) {
                if (!PlayerDataFile.hasShears(player)) {
                    if (hasPlace(item, player)) {
                        PlayerDataFile.setShears(player, true);
                        player.getInventory().addItem(Converter.convertToItemStack(item, false));
                        pay(item, player);
                    } else
                        player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
                } else
                    player.sendMessage(ChatColor.RED + "You already own this item !");
            } else if (item.getMaterial() == Material.WOOL) {
                if (hasPlace(item, player)) {
                    boolean team = false;
                    for (int i = 0; i < 8; i++) {
                        if (PlayerDataFile.getTeam(player).equalsIgnoreCase(BedwarsPlugin.teams.get(i))) {
                            ItemStack stack = Converter.convertToItemStack(item, false).clone();
                            stack.setDurability(BedwarsPlugin.bytes.get(i).byteValue());
                            player.getInventory().addItem(stack);
                            team = true;
                        } else if (i == 7 && !team) {
                            player.getInventory().addItem(Converter.convertToItemStack(item, false));
                        }
                    }
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
            } else {
                if (hasPlace(item, player)) {
                    pay(item, player);
                    player.getInventory().addItem(Converter.convertToItemStack(item, false));
                } else
                    player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
            }
        } else
            player.sendMessage(ChatColor.RED + "You don't have enough resources to buy this !");
    }

    public static boolean hasPlace(Item item, Player player) {
        Inventory inventory = player.getInventory();
        boolean place = false;
        for (int i = 0; i < 36; i++)
            if (inventory.getItem(i) == null || (inventory.getItem(i).getType() == item.getMaterial() && inventory.getItem(i).getAmount() + item.getQuantity() <= item.getMaterial().getMaxStackSize()))
                place = true;
        return place;
    }

    public static void pay(Item item, Player player) {
        List<Integer> items = new ArrayList<>();
        List<Integer> ca = new ArrayList<>();
        Inventory playerInventory = player.getInventory();
        int nb1 = 0;
        for (int i = 0; i < 36; i++)
            if (playerInventory.getItem(i) != null && playerInventory.getItem(i).getType() == item.getMoneyType().getMaterial()) {
                items.add(playerInventory.getItem(i).getAmount());
                ca.add(i);
                nb1 = nb1 + playerInventory.getItem(i).getAmount();
            }
        if (nb1 >= item.getPrice()) {
            int nb_sell = item.getPrice();
            int i = 0;
            while (nb_sell > 0) {
                if (nb_sell < items.get(i))
                    playerInventory.setItem(ca.get(i), new ItemStack(item.getMoneyType().getMaterial(), items.get(i) - nb_sell));
                else
                    playerInventory.clear(ca.get(i));
                nb_sell = nb_sell - items.get(i);
                i++;
            }
        }
    }

    public static boolean hasMoney(Player player, Material moneyMaterial, int count) {
        int moneyAmount = 0;
        for (int i = 0; i < 36; i++)
            if (player.getInventory().getItem(i) != null && player.getInventory().getItem(i).getType() == moneyMaterial)
                moneyAmount = moneyAmount + player.getInventory().getItem(i).getAmount();
        if (moneyAmount >= count) {
            return true;
        } else {
            return false;
        }
    }

    public static Item getArmor(int armorType, boolean leggings) {
        if (leggings)
            switch (armorType) {
                case 1:
                    return new Item(Material.CHAINMAIL_LEGGINGS, "Chainmail Leggings", 1, 0, MoneyType.NULL, true);
                case 2:
                    return new Item(Material.IRON_LEGGINGS, "Iron Leggings", 1, 0, MoneyType.NULL, true);
                case 3:
                    return new Item(Material.DIAMOND_LEGGINGS, "Diamond Leggings", 1, 0, MoneyType.NULL, true);
                default:
                    return new Item(Material.LEATHER_LEGGINGS, "Leather Leggings", 1, 0, MoneyType.NULL, true);
            }
        else
            switch (armorType) {
                case 1:
                    return new Item(Material.CHAINMAIL_BOOTS, "Chainmail Boots", 1, 0, MoneyType.NULL, true);
                case 2:
                    return new Item(Material.IRON_BOOTS, "Iron Boots", 1, 0, MoneyType.NULL, true);
                case 3:
                    return new Item(Material.DIAMOND_BOOTS, "Diamond Boots", 1, 0, MoneyType.NULL, true);
                default:
                    return new Item(Material.LEATHER_BOOTS, "Leather Boots", 1, 0, MoneyType.NULL, true);
            }
    }

    public static ToolsTypes getToolFromID(int index) {
        for (ToolsTypes type : ToolsTypes.values())
            if (type.getIndex() == index)
                return type;
        return null;
    }

    public static boolean isTool(Material material) {
        return material == Material.SHEARS || material == Material.WOOD_PICKAXE || material == Material.IRON_PICKAXE || material == Material.GOLD_PICKAXE || material == Material.DIAMOND_PICKAXE || material == Material.WOOD_AXE || material == Material.STONE_AXE || material == Material.IRON_AXE || material == Material.DIAMOND_AXE;
    }

    public static void launchGame() {
        boolean launch = true;
        int teamWithPlayers = 0;
        for (String team : BedwarsPlugin.teams) {
            for (String p : TeamDataFile.getPlayers(team))
                if (!Bukkit.getPlayer(p).isOnline())
                    TeamDataFile.removePlayer(team, Bukkit.getPlayer(p));
            if (!TeamDataFile.getPlayers(team).isEmpty()) {
                teamWithPlayers++;
                if (!TeamDataFile.hasBed(team)) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Error: " + team + " team does not have bed!");
                    launch = false;
                } else if (!TeamDataFile.hasSpawn(team)) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Error: " + team + " team does not have spawn point!");
                    launch = false;
                } else if (!TeamDataFile.hasGenerator(team)) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Error: " + team + " team does not have generator!");
                    launch = false;
                }
            } else {
                TeamDataFile.setBed(team, false);
            }
        }
        if (teamWithPlayers < 2) {
            Bukkit.broadcastMessage(ChatColor.RED + "Error: There are not enough teams!");
            launch = false;
        }
        if (launch) {
            BedwarsPlugin.isLaunch = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!PlayerDataFile.getTeam(player).equalsIgnoreCase("null")) {
                    TeamDataFile.addLivePlayer(PlayerDataFile.getTeam(player), player);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.teleport(TeamDataFile.getSpawnLocation(PlayerDataFile.getTeam(player)));
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
            Bukkit.broadcastMessage(ChatColor.GREEN + "Launch Game!");
        }
    }

    public static String integerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        StringBuilder s = new StringBuilder();
        while (input >= 1000) {
            s.append("M");
            input -= 1000;
        }
        while (input >= 900) {
            s.append("CM");
            input -= 900;
        }
        while (input >= 500) {
            s.append("D");
            input -= 500;
        }
        while (input >= 400) {
            s.append("CD");
            input -= 400;
        }
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }
}