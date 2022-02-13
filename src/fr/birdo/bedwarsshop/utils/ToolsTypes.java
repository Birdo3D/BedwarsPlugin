package fr.birdo.bedwarsshop.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public enum ToolsTypes {

    NULL(0, null),
    PICKAXE_LVL1(1, new Item(Material.WOOD_PICKAXE, "Wood Pickaxe (Efficiency I)", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    PICKAXE_LVL2(2, new Item(Material.IRON_PICKAXE, "Iron Pickaxe (Efficiency III)", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 3)),
    PICKAXE_LVL3(3, new Item(Material.GOLD_PICKAXE, "Gold Pickaxe (Efficiency III, Sharpness I)", 1, 3, MoneyType.GOLD, true).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DAMAGE_ALL, 2)),
    PICKAXE_LVL4(4, new Item(Material.DIAMOND_PICKAXE, "Diamond Pickaxe (Efficiency III)", 1, 6, MoneyType.GOLD, true).addEnchant(Enchantment.DIG_SPEED, 3)),
    AXE_LVL1(5, new Item(Material.WOOD_AXE, "Wood Axe (Efficiency I)", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    AXE_LVL2(6, new Item(Material.STONE_AXE, "Stone Axe (Efficiency I)", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    AXE_LVL3(7, new Item(Material.IRON_AXE, "Iron Axe (Efficiency II)", 1, 3, MoneyType.GOLD, true).addEnchant(Enchantment.DIG_SPEED, 2)),
    AXE_LVL4(8, new Item(Material.DIAMOND_AXE, "Diamond Axe (Efficiency III)", 1, 6, MoneyType.GOLD, true).addEnchant(Enchantment.DIG_SPEED, 3));

    private final int index;
    private final Item item;

    ToolsTypes(int index, Item item) {
        this.index = index;
        this.item = item;
    }

    public int getIndex() {
        return this.index;
    }

    public Item getItem() {
        return this.item;
    }
}