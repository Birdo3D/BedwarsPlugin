package fr.birdo.bedwarsshop.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public enum ToolsTypes {

    NULL(0, null),
    PICKAXE_LVL1(1, new Item(Material.WOOD_PICKAXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    PICKAXE_LVL2(2, new Item(Material.WOOD_PICKAXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    PICKAXE_LVL3(3, new Item(Material.WOOD_PICKAXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    AXE_LVL1(4, new Item(Material.WOOD_AXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    AXE_LVL2(5, new Item(Material.WOOD_AXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1)),
    AXE_LVL3(6, new Item(Material.WOOD_AXE, "Wood Pickaxe", 1, 10, MoneyType.IRON, true).addEnchant(Enchantment.DIG_SPEED, 1));

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