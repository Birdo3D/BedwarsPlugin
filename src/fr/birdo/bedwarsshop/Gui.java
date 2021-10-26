package fr.birdo.bedwarsshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Gui {

    private static final int[] pattern = new int[]{10, 11, 12, 13, 14, 15, 16};

    private static void setInventoryPattern(int[] pattern, int selected, Inventory inventory) {
        for (int i : pattern) {
            inventory.setItem(i, Items.getDecoration(7));
        }
        if (selected != -1) {
            inventory.setItem(selected, Items.getDecoration(13));
        }
    }

    private static void setProtection(int[] pattern, Inventory inventory) {
        ItemStack decoration = Items.getDecoration(8);
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
        Inventory inv = Bukkit.createInventory(null, 6 * 9, "Item Shop - " + shop);

        inv.setItem(0, Items.getDecoration(7));
        inv.setItem(1, Items.getCategory(Material.HARD_CLAY, "Blocks"));
        inv.setItem(2, Items.getCategory(Material.GOLD_SWORD, "Weapons"));
        inv.setItem(3, Items.getCategory(Material.CHAINMAIL_BOOTS, "Armors"));
        inv.setItem(4, Items.getCategory(Material.STONE_PICKAXE, "Tools"));
        inv.setItem(5, Items.getCategory(Material.BOW, "Bows"));
        inv.setItem(6, Items.getCategory(Material.BREWING_STAND_ITEM, "Potions"));
        inv.setItem(7, Items.getCategory(Material.TNT, "Other"));
        inv.setItem(8, Items.getDecoration(7));
        setInventoryPattern(new int[]{9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53}, -1, inv);

        switch (shop) {
            case "Blocks":
                inv.setItem(19, Items.getItem(Material.WOOL, "Wool", 16, 4, "iron", false));
                inv.setItem(20, Items.getItem(Material.HARD_CLAY, "Clay", 16, 12, "iron", false));
                inv.setItem(21, Items.getItem(Material.GLASS, "Glass", 4, 12, "iron", false));
                inv.setItem(22, Items.getItem(Material.ENDER_STONE, "End Stone", 12, 24, "iron", false));
                inv.setItem(23, Items.getItem(Material.LADDER, "Ladder", 16, 4, "iron", false));
                inv.setItem(24, Items.getItem(Material.LOG_2, "Planks", 16, 4, "iron", false));
                inv.setItem(25, Items.getItem(Material.OBSIDIAN, "Obsidian", 4, 4, "emerald", false));
                setInventoryPattern(pattern, 10, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 24, 25}, inv);
                break;

            case "Weapons":
                inv.setItem(19, Items.getItem(Material.STONE_SWORD, "Stone Sword", 1, 10, "iron", true));
                inv.setItem(20, Items.getItem(Material.IRON_SWORD, "Iron Sword", 1, 7, "gold", true));
                inv.setItem(21, Items.getItem(Material.DIAMOND_SWORD, "Diamond Sword", 1, 3, "emerald", true));
                inv.setItem(22, Items.getItem(Material.STICK, "Knockback Stick", 1, 4, "gold", true));
                setInventoryPattern(pattern, 11, inv);
                setProtection(new int[]{19, 20, 21, 22}, inv);
                break;

            case "Armors":
                inv.setItem(19, Items.getItem(Material.CHAINMAIL_BOOTS, "Chainmail Armor", 1, 50, "iron", true));
                inv.setItem(20, Items.getItem(Material.IRON_BOOTS, "Iron Armor", 1, 12, "gold", true));
                inv.setItem(21, Items.getItem(Material.DIAMOND_BOOTS, "Diamond Armor", 1, 6, "emerald", true));
                setInventoryPattern(pattern, 12, inv);
                setProtection(new int[]{19, 20, 21}, inv);
                break;

            case "Tools":
                inv.setItem(19, Items.getItem(Material.WOOD_PICKAXE, "Wood Pickaxe", 1, 10, "iron", true));
                inv.setItem(20, Items.getItem(Material.STONE_PICKAXE, "Stone Pickaxe", 1, 10, "iron", true));
                inv.setItem(21, Items.getItem(Material.IRON_PICKAXE, "Iron Pickaxe", 1, 10, "iron", true));
                inv.setItem(22, Items.getItem(Material.DIAMOND_PICKAXE, "Diamond Pickaxe", 1, 10, "iron", true));
                inv.setItem(23, Items.getTools(Material.DIAMOND_PICKAXE, "Diamond Pickaxe", 1));
                inv.setItem(28, Items.getItem(Material.WOOD_AXE, "Wood Axe", 1, 10, "iron", true));
                inv.setItem(29, Items.getItem(Material.STONE_AXE, "Stone Axe", 1, 10, "iron", true));
                inv.setItem(30, Items.getItem(Material.IRON_AXE, "Iron Axe", 1, 10, "iron", true));
                inv.setItem(31, Items.getItem(Material.DIAMOND_AXE, "Diamond Axe", 1, 10, "iron", true));
                inv.setItem(32, Items.getTools(Material.DIAMOND_AXE, "Diamond Axe", 1));
                inv.setItem(37, Items.getItem(Material.SHEARS, "Shears", 1, 10, "iron", true));
                setInventoryPattern(pattern, 13, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 28, 29, 30, 31, 32, 37}, inv);
                break;

            case "Bows":
                inv.setItem(19, Items.getItem(Material.ARROW, "Arrows", 8, 2, "gold", false));
                inv.setItem(20, Items.getItem(Material.BOW, "Bow", 1, 20, "gold", true));
                inv.setItem(21, Items.getbow(1, 1));
                inv.setItem(22, Items.getbow(2, 1));
                setInventoryPattern(pattern, 14, inv);
                setProtection(new int[]{19, 20, 21, 22}, inv);
                break;

            case "Potions":
                inv.setItem(19, Items.getPotion("speed", 1));
                inv.setItem(20, Items.getPotion("jump", 1));
                inv.setItem(21, Items.getPotion("invisibility", 1));
                setInventoryPattern(pattern, 15, inv);
                setProtection(new int[]{19, 20, 21}, inv);
                break;

            case "Other":
                inv.setItem(19, Items.getItem(Material.GOLDEN_APPLE, "Golden Apple", 1, 3, "gold", false));
                inv.setItem(20, Items.getItem(Material.TNT, "Tnt", 1, 4, "gold", false));
                inv.setItem(21, Items.getItem(Material.ENDER_PEARL, "Ender Pearl", 1, 2, "emerald", false));
                inv.setItem(22, Items.getItem(Material.WATER_BUCKET, "Water Bucket", 1, 4, "gold", false));
                inv.setItem(23, Items.getItem(Material.MILK_BUCKET, "Milk Bucket", 1, 3, "gold", false));
                inv.setItem(24, Items.getItem(Material.SPONGE, "Sponges", 4, 6, "gold", false));
                setInventoryPattern(pattern, 16, inv);
                setProtection(new int[]{19, 20, 21, 22, 23, 24}, inv);
                break;
        }
        p.openInventory(inv);
    }
}