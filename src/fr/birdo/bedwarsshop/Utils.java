package fr.birdo.bedwarsshop;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static int getItemStackQuantity(ItemStack material, HumanEntity humanEntity) {
        Inventory inventory = humanEntity.getInventory();
        List<Integer> items = new ArrayList<Integer>();
        List<Integer> ca = new ArrayList<Integer>();
        int nb = 0;
        int a = 0, b = 36;
        while (a < b) {
            if (inventory.getItem(a) != null && inventory.getItem(a).getType() == material.getType()) {
                items.add(inventory.getItem(a).getAmount());
                ca.add(a);
                nb = nb + inventory.getItem(a).getAmount();
            }
            a++;
        }
        return nb;
    }

    public static ItemStack getMoneyItemStack(int type) {
        switch (type) {
            case 0:
                return new ItemStack(Material.IRON_INGOT);
            case 1:
                return new ItemStack(Material.GOLD_INGOT);
            case 2:
                return new ItemStack(Material.EMERALD);
        }
        return null;
    }
}
