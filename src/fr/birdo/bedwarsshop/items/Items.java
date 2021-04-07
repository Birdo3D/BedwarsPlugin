package fr.birdo.bedwarsshop.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Items {

    public static ItemStack getDecoration(int metadata) {

        ItemStack decoration = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) metadata);
        ItemMeta decorationM = decoration.getItemMeta();
        decorationM.setDisplayName(" ");
        decoration.setItemMeta(decorationM);

        return decoration;
    }

    public static ItemStack getbow(int type, int price) {
        if (type == 1) {
            ItemStack bow1 = new ItemStack(Material.BOW, 1);
            ItemMeta bow1M = bow1.getItemMeta();
            bow1M.setDisplayName(ChatColor.AQUA + "Bow (Power I)");
            bow1M.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + price + " Golds"));
            bow1M.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            bow1M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            bow1M.setUnbreakable(true);
            bow1M.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            bow1.setItemMeta(bow1M);

            return bow1;

        } else if (type == 2) {
            ItemStack bow2 = new ItemStack(Material.BOW, 1);
            ItemMeta bow2M = bow2.getItemMeta();
            bow2M.setDisplayName(ChatColor.AQUA + "Bow (Power I, Punch I)");
            bow2M.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + price + " Emeralds"));
            bow2M.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            bow2M.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            bow2M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            bow2M.setUnbreakable(true);
            bow2M.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            bow2.setItemMeta(bow2M);

            return bow2;
        }
        return null;
    }

    public static ItemStack getTools(Material material, String name, int price) {
        ItemStack tool = new ItemStack(material, 1);
        ItemMeta toolM = tool.getItemMeta();
        toolM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + price + " Golds"));
        toolM.setUnbreakable(true);
        toolM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        toolM.addEnchant(Enchantment.DIG_SPEED, 1, true);
        toolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        toolM.setDisplayName(ChatColor.AQUA + name + " (Efficiency I)");
        tool.setItemMeta(toolM);

        return tool;
    }

    public static ItemStack getPotion(String type, int price) {
        if (type.equals("speed")) {
            ItemStack potion = new ItemStack(Material.POTION, 1);
            PotionMeta potionM = (PotionMeta) potion.getItemMeta();
            potionM.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 45 * 20, 1), true);
            potionM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + price + " Emeralds"));
            potionM.setDisplayName(ChatColor.AQUA + "Speed II Potion (45 seconds)");
            potionM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            potion.setItemMeta(potionM);

            return potion;

        } else if (type.equals("jump")) {
            ItemStack potion = new ItemStack(Material.POTION, 1);
            PotionMeta potionM = (PotionMeta) potion.getItemMeta();
            potionM.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 45 * 20, 5), true);
            potionM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + price + " Emeralds"));
            potionM.setDisplayName(ChatColor.AQUA + "Jump V Potion (45 seconds)");
            potionM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            potion.setItemMeta(potionM);

            return potion;

        } else if (type.equals("invisibility")) {
            ItemStack potion = new ItemStack(Material.POTION, 1);
            PotionMeta potionM = (PotionMeta) potion.getItemMeta();
            potionM.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 1), true);
            potionM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + price + " Emeralds"));
            potionM.setDisplayName(ChatColor.AQUA + "Invisibility Potion (30 seconds)");
            potionM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            potion.setItemMeta(potionM);

            return potion;
        }
        return null;
    }

    public static ItemStack getItem(Material material, String name, int quantity, int price, String money, Boolean unbreakable) {

        ItemStack item = new ItemStack(material, quantity);
        ItemMeta itemM = item.getItemMeta();
        if (money.equals("iron")) {
            itemM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.WHITE + price + " Irons"));
        } else if (money.equals("gold")) {
            itemM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + price + " Golds"));
        } else if (money.equals("emerald")) {
            itemM.setLore(Arrays.asList(ChatColor.GRAY + "Cost: " + ChatColor.DARK_GREEN + price + " Emeralds"));
        }
        if (unbreakable == true) {
            itemM.setUnbreakable(true);
            itemM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        itemM.setDisplayName(ChatColor.AQUA + name);
        item.setItemMeta(itemM);

        return item;
    }

    public static ItemStack getCategory(Material material, String name) {

        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.GREEN + name);
        item.setItemMeta(itemM);

        return item;
    }
}