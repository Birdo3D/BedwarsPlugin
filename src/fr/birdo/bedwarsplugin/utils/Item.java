package fr.birdo.bedwarsplugin.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class Item {

    private final Material material;
    private final String name;
    private final int quantity;
    private final int price;
    private final MoneyType moneyType;
    private final boolean unbreakable;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final List<PotionEffect> potionEffects = new ArrayList<>();

    public Item(Material material, String name, int quantity, int price, MoneyType moneyType, boolean unbreakable) {
        this.material = material;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.moneyType = moneyType;
        this.unbreakable = unbreakable;
    }

    public Material getMaterial() {
        return this.material;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public MoneyType getMoneyType() {
        return this.moneyType;
    }

    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    public Item addEnchant(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    public Set<Enchantment> getEnchantments() {
        return this.enchantments.keySet();
    }

    public int getEnchantementLevel(Enchantment enchantment) {
        if (this.enchantments.containsKey(enchantment))
            return this.enchantments.get(enchantment);
        return 0;
    }

    public Item addPotionEffect(PotionEffect potionEffect) {
        this.potionEffects.add(potionEffect);
        return this;
    }

    public List<PotionEffect> getPotionEffects(){
        return this.potionEffects;
    }

    public boolean isPotion() {
        return !potionEffects.isEmpty();
    }
}