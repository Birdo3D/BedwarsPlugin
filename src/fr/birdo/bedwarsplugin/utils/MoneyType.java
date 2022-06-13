package fr.birdo.bedwarsplugin.utils;

import org.bukkit.Material;

public enum MoneyType {

    NULL(null),
    IRON(Material.IRON_INGOT),
    GOLD(Material.GOLD_INGOT),
    EMERALD(Material.EMERALD);

    private final Material material;

    MoneyType(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }
}