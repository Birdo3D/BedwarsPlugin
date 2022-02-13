package fr.birdo.bedwarsshop.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void buyItem(Player player, Item item) {
        Inventory inventory = player.getInventory();
        int moneyAmount = 0;
        for (int i = 0; i < 36; i++)
            if (inventory.getItem(i) != null && inventory.getItem(i).getType() == item.getMoneyType().getMaterial())
                moneyAmount = moneyAmount + inventory.getItem(i).getAmount();
        if (moneyAmount >= item.getPrice()) {
            if (item.getMaterial() == Material.CHAINMAIL_BOOTS) {
                if (CustomConfigurationFile.getArmorType(player) == 0) {
                    CustomConfigurationFile.setArmorType(player, ArmorTypes.CHAINMAIL);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.CHAINMAIL.getIndex(), true)));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.CHAINMAIL.getIndex(), false)));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getMaterial() == Material.IRON_BOOTS) {
                if (CustomConfigurationFile.getArmorType(player) < 2) {
                    CustomConfigurationFile.setArmorType(player, ArmorTypes.IRON);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.IRON.getIndex(), true)));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.IRON.getIndex(), false)));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getMaterial() == Material.DIAMOND_BOOTS) {
                if (CustomConfigurationFile.getArmorType(player) < 3) {
                    CustomConfigurationFile.setArmorType(player, ArmorTypes.DIAMOND);
                    player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.DIAMOND.getIndex(), true)));
                    player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(ArmorTypes.DIAMOND.getIndex(), false)));
                    pay(item, player);
                } else
                    player.sendMessage(ChatColor.RED + "You already own this armor or better !");
            } else if (item.getMaterial() == Material.SHEARS) {
                if (!CustomConfigurationFile.hasShears(player)) {
                    if (hasPlace(item, player)) {
                        CustomConfigurationFile.setShears(player, true);
                        player.getInventory().addItem(Converter.convertToItemStack(new Item(Material.SHEARS, "Shears", 1, 0, MoneyType.NULL, true)));
                        pay(item, player);
                    } else
                        player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
                } else
                    player.sendMessage(ChatColor.RED + "You already own this item !");
            } else {
                if (hasPlace(item, player)) {
                    pay(item, player);
                    player.getInventory().addItem(Converter.convertToItemStack(new Item(item.getMaterial(), item.getName(), item.getQuantity(), 0, MoneyType.NULL, item.isUnbreakable())));
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
}