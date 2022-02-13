package fr.birdo.bedwarsshop;

import fr.birdo.bedwarsshop.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gui {

    private static final int[] pattern = new int[]{10, 11, 12, 13, 14, 15, 16};
    private static final Map<Integer, Item> itemsBlocks = new HashMap<>();
    private static final Map<Integer, Item> itemsWeapons = new HashMap<>();
    private static final Map<Integer, Item> itemsArmors = new HashMap<>();
    private static final Map<Integer, Item> itemsTools = new HashMap<>();
    private static final Map<Integer, Item> itemsBows = new HashMap<>();
    private static final Map<Integer, Item> itemsPotions = new HashMap<>();
    private static final Map<Integer, Item> itemsOther = new HashMap<>();

    public static void initGui(Player player) {
        int pickaxe = CustomConfigurationFile.getPickaxe(player) + 1;
        int axe = CustomConfigurationFile.getAxe(player) + 5;
        if (pickaxe > 4)
            pickaxe = 4;
        if (axe > 8)
            axe = 8;
        //Blocks
        itemsBlocks.put(19, new Item(Material.WOOL, "Wool", 16, 4, MoneyType.IRON, false));
        itemsBlocks.put(20, new Item(Material.HARD_CLAY, "Clay", 16, 12, MoneyType.IRON, false));
        itemsBlocks.put(21, new Item(Material.GLASS, "Glass", 4, 12, MoneyType.IRON, false));
        itemsBlocks.put(22, new Item(Material.ENDER_STONE, "End Stone", 12, 24, MoneyType.IRON, false));
        itemsBlocks.put(23, new Item(Material.LADDER, "Ladder", 8, 4, MoneyType.IRON, false));
        itemsBlocks.put(24, new Item(Material.WOOD, "Planks", 16, 4, MoneyType.GOLD, false));
        itemsBlocks.put(25, new Item(Material.OBSIDIAN, "Obsidian", 4, 4, MoneyType.EMERALD, false));
        //Weapons
        itemsWeapons.put(19, new Item(Material.STONE_SWORD, "Stone Sword", 1, 10, MoneyType.IRON, true));
        itemsWeapons.put(20, new Item(Material.IRON_SWORD, "Iron Sword", 1, 7, MoneyType.GOLD, true));
        itemsWeapons.put(21, new Item(Material.DIAMOND_SWORD, "Diamond Sword", 1, 4, MoneyType.EMERALD, true));
        itemsWeapons.put(22, new Item(Material.STICK, "Knockback Stick", 1, 5, MoneyType.GOLD, true).addEnchant(Enchantment.KNOCKBACK, 1));
        //Armors
        itemsArmors.put(19, new Item(Material.CHAINMAIL_BOOTS, "Chainmail Armor", 1, 30, MoneyType.IRON, true));
        itemsArmors.put(20, new Item(Material.IRON_BOOTS, "Iron Armor", 1, 12, MoneyType.GOLD, true));
        itemsArmors.put(21, new Item(Material.DIAMOND_BOOTS, "Diamond Armor", 1, 6, MoneyType.EMERALD, true));
        //Tools
        itemsTools.put(19, Utils.getToolFromID(pickaxe).getItem());
        itemsTools.put(20, Utils.getToolFromID(axe).getItem());
        itemsTools.put(21, new Item(Material.SHEARS, "Shears", 1, 20, MoneyType.IRON, true));
        //Bows
        itemsBows.put(19, new Item(Material.ARROW, "Arrows", 6, 2, MoneyType.GOLD, false));
        itemsBows.put(20, new Item(Material.BOW, "Bow", 1, 12, MoneyType.GOLD, true));
        itemsBows.put(21, new Item(Material.BOW, "Bow (Power I)", 1, 20, MoneyType.GOLD, true).addEnchant(Enchantment.ARROW_DAMAGE, 1));
        itemsBows.put(22, new Item(Material.BOW, "Bow (Power I, Punch I)", 1, 6, MoneyType.EMERALD, true).addEnchant(Enchantment.ARROW_DAMAGE, 1).addEnchant(Enchantment.ARROW_KNOCKBACK, 1));
        //Potions
        itemsPotions.put(19, new Item(Material.POTION, "Speed II Potion (45 seconds)", 1, 1, MoneyType.EMERALD, false).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45 * 20, 1)));
        itemsPotions.put(20, new Item(Material.POTION, "Jump V Potion (45 seconds)", 1, 1, MoneyType.EMERALD, false).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 45 * 20, 5)));
        itemsPotions.put(21, new Item(Material.POTION, "Invisibility Potion (30 seconds)", 1, 2, MoneyType.EMERALD, false).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 1)));
        //Other
        itemsOther.put(19, new Item(Material.GOLDEN_APPLE, "Golden Apple", 1, 3, MoneyType.GOLD, false));
        itemsOther.put(20, new Item(Material.TNT, "Tnt", 1, 4, MoneyType.GOLD, false));
        itemsOther.put(21, new Item(Material.ENDER_PEARL, "Ender Pearl", 1, 4, MoneyType.EMERALD, false));
        itemsOther.put(22, new Item(Material.WATER_BUCKET, "Water Bucket", 1, 3, MoneyType.GOLD, false));
        itemsOther.put(23, new Item(Material.MILK_BUCKET, "Milk Bucket", 1, 4, MoneyType.GOLD, false));
        itemsOther.put(24, new Item(Material.SPONGE, "Sponges", 4, 3, MoneyType.GOLD, false));
    }

    private static void setInventoryPattern(int[] pattern, int selected, Inventory inventory) {
        for (int i : pattern) {
            inventory.setItem(i, getDecoration(7));
        }
        if (selected != -1) {
            inventory.setItem(selected, getDecoration(13));
        }
    }

    private static void setProtection(int[] pattern, Inventory inventory) {
        ItemStack decoration = getDecoration(8);
        List<Integer> test = new ArrayList<>();
        for (int i : pattern) {
            test.add(i);
        }
        for (int i = 19; i <= 25; i++)
            if (!test.contains(i))
                inventory.setItem(i, decoration);
        for (int i = 28; i <= 34; i++)
            if (!test.contains(i))
                inventory.setItem(i, decoration);
        for (int i = 37; i <= 43; i++)
            if (!test.contains(i))
                inventory.setItem(i, decoration);
    }

    public static void pnj01(Player p, String shop) {
        initGui(p);
        Inventory inv = Bukkit.createInventory(null, 6 * 9, "Item Shop - " + shop);

        inv.setItem(0, getDecoration(7));
        inv.setItem(1, getCategory(Material.HARD_CLAY, "Blocks"));
        inv.setItem(2, getCategory(Material.GOLD_SWORD, "Weapons"));
        inv.setItem(3, getCategory(Material.CHAINMAIL_BOOTS, "Armors"));
        inv.setItem(4, getCategory(Material.STONE_PICKAXE, "Tools"));
        inv.setItem(5, getCategory(Material.BOW, "Bows"));
        inv.setItem(6, getCategory(Material.BREWING_STAND_ITEM, "Potions"));
        inv.setItem(7, getCategory(Material.TNT, "Other"));
        inv.setItem(8, getDecoration(7));
        setInventoryPattern(new int[]{9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53}, -1, inv);

        switch (shop) {
            case "Blocks":
                for (int i : itemsBlocks.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsBlocks.get(i)));
                setInventoryPattern(pattern, 10, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 24, 25}, inv);
                break;

            case "Weapons":
                for (int i : itemsWeapons.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsWeapons.get(i)));
                setInventoryPattern(pattern, 11, inv);
                setProtection(new int[]{19, 20, 21, 22}, inv);
                break;

            case "Armors":
                for (int i : itemsArmors.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsArmors.get(i)));
                setInventoryPattern(pattern, 12, inv);
                setProtection(new int[]{19, 20, 21}, inv);
                break;

            case "Tools":
                for (int i : itemsTools.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsTools.get(i)));
                setInventoryPattern(pattern, 13, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 28, 29, 30, 31, 32, 37}, inv);
                break;

            case "Bows":
                for (int i : itemsBows.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsBows.get(i)));
                setInventoryPattern(pattern, 14, inv);
                setProtection(new int[]{19, 20, 21, 22}, inv);
                break;

            case "Potions":
                for (int i : itemsPotions.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsPotions.get(i)));
                setInventoryPattern(pattern, 15, inv);
                setProtection(new int[]{19, 20, 21}, inv);
                break;

            case "Other":
                for (int i : itemsOther.keySet())
                    inv.setItem(i, Converter.convertToItemStack(itemsOther.get(i)));
                setInventoryPattern(pattern, 16, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 24}, inv);
                break;
        }
        p.openInventory(inv);
    }

    private static ItemStack getDecoration(int metadata) {
        ItemStack decoration = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) metadata);
        ItemMeta decorationM = decoration.getItemMeta();
        decorationM.setDisplayName(" ");
        decoration.setItemMeta(decorationM);
        return decoration;
    }

    private static ItemStack getCategory(Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.GREEN + name);
        item.setItemMeta(itemM);
        return item;
    }

    public static Map<Integer, Item> getItems(String category) {
        switch (category) {
            case "Item Shop - Blocks":
                return itemsBlocks;
            case "Item Shop - Weapons":
                return itemsWeapons;
            case "Item Shop - Armors":
                return itemsArmors;
            case "Item Shop - Tools":
                return itemsTools;
            case "Item Shop - Bows":
                return itemsBows;
            case "Item Shop - Potions":
                return itemsPotions;
            case "Item Shop - Other":
                return itemsOther;
        }
        return null;
    }
}