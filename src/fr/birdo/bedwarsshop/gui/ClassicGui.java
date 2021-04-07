package fr.birdo.bedwarsshop.gui;

import fr.birdo.bedwarsshop.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClassicGui implements Listener {

    public ClassicGui() {
    }

    static ItemStack deco = Items.getDecoration(7);
    static ItemStack deco1 = Items.getDecoration(13);

    public static void pnj01(Player p, String shop) {

        Inventory inv = Bukkit.createInventory(null, 6 * 9, shop);

        inv.setItem(1, Items.getCategory(Material.HARD_CLAY, "Blocks"));
        inv.setItem(2, Items.getCategory(Material.GOLD_SWORD, "Weapons"));
        inv.setItem(3, Items.getCategory(Material.CHAINMAIL_BOOTS, "Armors"));
        inv.setItem(4, Items.getCategory(Material.STONE_PICKAXE, "Tools"));
        inv.setItem(5, Items.getCategory(Material.BOW, "Bows"));
        inv.setItem(6, Items.getCategory(Material.BREWING_STAND_ITEM, "Potions"));
        inv.setItem(7, Items.getCategory(Material.TNT, "Other"));
        inv.setItem(9, deco);
        inv.setItem(17, deco);
        inv.setItem(18, deco);
        inv.setItem(26, deco);
        inv.setItem(27, deco);
        inv.setItem(35, deco);
        inv.setItem(36, deco);
        inv.setItem(44, deco);
        inv.setItem(45, deco);
        inv.setItem(46, deco);
        inv.setItem(47, deco);
        inv.setItem(48, deco);
        inv.setItem(49, deco);
        inv.setItem(50, deco);
        inv.setItem(51, deco);
        inv.setItem(52, deco);
        inv.setItem(53, deco);

        switch (shop) {
            case "Blocks":
                inv.setItem(19, Items.getItem(Material.WOOL, "Wool", 16, 4, "iron", false));
                inv.setItem(20, Items.getItem(Material.HARD_CLAY, "Clay", 16, 12, "iron", false));
                inv.setItem(21, Items.getItem(Material.GLASS, "Glass", 4, 12, "iron", false));
                inv.setItem(22, Items.getItem(Material.ENDER_STONE, "End Stone", 12, 24, "iron", false));
                inv.setItem(23, Items.getItem(Material.LADDER, "Ladder", 16, 4, "iron", false));
                inv.setItem(24, Items.getItem(Material.LOG_2, "Planks", 16, 4, "iron", false));
                inv.setItem(25, Items.getItem(Material.OBSIDIAN, "Obsidian", 4, 4, "emerald", false));
                inv.setItem(10, deco1);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
                break;

            case "Weapons":
                inv.setItem(19, Items.getItem(Material.STONE_SWORD, "Stone Sword", 1, 10, "iron", true));
                inv.setItem(20, Items.getItem(Material.IRON_SWORD, "Iron Sword", 1, 7, "gold", true));
                inv.setItem(21, Items.getItem(Material.DIAMOND_SWORD, "Diamond Sword", 1, 3, "emerald", true));
                inv.setItem(22, Items.getItem(Material.STICK, "Knockback Stick", 1, 4, "gold", true));
                inv.setItem(10, deco);
                inv.setItem(11, deco1);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
                break;

            case "Armors":
                inv.setItem(19, Items.getItem(Material.CHAINMAIL_BOOTS, "Chainmail Armor", 1, 50, "iron", true));
                inv.setItem(20, Items.getItem(Material.IRON_BOOTS, "Iron Armor", 1, 12, "gold", true));
                inv.setItem(21, Items.getItem(Material.DIAMOND_BOOTS, "Diamond Armor", 1, 6, "emerald", true));
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco1);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
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
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco1);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
                break;

            case "Bows":
                inv.setItem(19, Items.getItem(Material.ARROW, "Arrows", 8, 2, "gold", false));
                inv.setItem(20, Items.getItem(Material.BOW, "Bow", 1, 20, "gold", true));
                inv.setItem(21, Items.getbow(1, 1));
                inv.setItem(22, Items.getbow(2, 1));
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco1);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
                break;

            case "Potions":
                inv.setItem(19, Items.getPotion("speed", 1));
                inv.setItem(20, Items.getPotion("jump", 1));
                inv.setItem(21, Items.getPotion("invisibility", 1));
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco1);
                inv.setItem(16, deco);
                break;

            case "Other":
                inv.setItem(19, Items.getItem(Material.GOLDEN_APPLE, "Golden Apple", 1, 3, "gold", false));
                inv.setItem(20, Items.getItem(Material.TNT, "Tnt", 1, 4, "gold", false));
                inv.setItem(21, Items.getItem(Material.ENDER_PEARL, "Ender Pearl", 1, 2, "emerald", false));
                inv.setItem(22, Items.getItem(Material.WATER_BUCKET, "Water Bucket", 1, 4, "gold", false));
                inv.setItem(23, Items.getItem(Material.MILK_BUCKET, "Milk Bucket", 1, 3, "gold", false));
                inv.setItem(24, Items.getItem(Material.SPONGE, "Sponges", 4, 6, "gold", false));
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco1);
                break;

            default:
                inv.setItem(10, deco);
                inv.setItem(11, deco);
                inv.setItem(12, deco);
                inv.setItem(13, deco);
                inv.setItem(14, deco);
                inv.setItem(15, deco);
                inv.setItem(16, deco);
        }
        p.openInventory(inv);
    }

}
