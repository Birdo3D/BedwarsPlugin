package fr.birdo.bedwarsshop.utils;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class Converter {

    public static ItemStack convertToItemStack(Item item, boolean lore) {
        ItemStack itemStack = new ItemStack(item.getMaterial(), item.getQuantity());
        if (item.isPotion()) {
            PotionMeta itemStackMeta = (PotionMeta) itemStack.getItemMeta();
            for (PotionEffect potionEffect : item.getPotionEffects())
                itemStackMeta.addCustomEffect(potionEffect, true);
            if (lore)
                setLore(item, itemStackMeta);
            itemStackMeta.setDisplayName(ChatColor.AQUA + item.getName());
            itemStackMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            itemStack.setItemMeta(itemStackMeta);
        } else {
            ItemMeta itemStackMeta = itemStack.getItemMeta();
            if (lore)
                setLore(item, itemStackMeta);
            if (item.isUnbreakable()) {
                itemStackMeta.setUnbreakable(true);
                itemStackMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            }
            if (item.getEnchantments() != null) {
                for (Enchantment enchantment : item.getEnchantments())
                    itemStackMeta.addEnchant(enchantment, item.getEnchantementLevel(enchantment), true);
                itemStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            itemStackMeta.setDisplayName(ChatColor.AQUA + item.getName());
            itemStack.setItemMeta(itemStackMeta);
        }
        return itemStack;
    }

    private static void setLore(Item item, ItemMeta itemMeta) {
        switch (item.getMoneyType()) {
            case IRON:
                itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.WHITE + item.getPrice() + " Irons"));
                break;
            case GOLD:
                itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + item.getPrice() + " Golds"));
                break;
            case EMERALD:
                itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + item.getPrice() + " Emeralds"));
                break;
        }
    }
}
