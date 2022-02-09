package fr.birdo.bedwarsshop.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void buyItem(Player player, Item item) {
        Inventory inventory = player.getInventory();
        int moneyAmount = 0;
        for (int i = 0; i < 36; i++)
            if (inventory.getItem(i) != null && inventory.getItem(i).getType() == item.getMoneyType().getMaterial())
                moneyAmount = moneyAmount + inventory.getItem(i).getAmount();
        if (moneyAmount >= item.getPrice()) {
            boolean place = false;
            for (int i = 0; i < 36; i++)
                if (inventory.getItem(i) == null || (inventory.getItem(i).getType() == item.getMaterial() && inventory.getItem(i).getAmount() + item.getQuantity() <= 64))
                    place = true;
            if (place) {
                List<Integer> items = new ArrayList<>();
                List<Integer> ca = new ArrayList<>();
                Inventory playerInventory = player.getInventory();
                int nb1 = 0;
                for (int i = 0; i < 36; i++)
                    if (playerInventory.getItem(i) != null && playerInventory.getItem(i).getType() == item.getMoneyType().getMaterial()) {
                        items.add(playerInventory.getItem(i).getAmount());
                        ca.add(i);
                        nb1 = nb1 + playerInventory.getItem(i).getAmount();
                    }
                if (nb1 >= item.getPrice()) {
                    int nb_sell = item.getPrice();
                    int i = 0;
                    while (nb_sell > 0) {
                        if (nb_sell < items.get(i))
                            playerInventory.setItem(ca.get(i), new ItemStack(item.getMoneyType().getMaterial(), items.get(i) - nb_sell));
                        else
                            playerInventory.clear(ca.get(i));
                        nb_sell = nb_sell - items.get(i);
                        i++;
                    }
                }
                player.getInventory().addItem(Converter.convertToItemStack(item));
            } else
                player.sendMessage(ChatColor.RED + "You don't have enough place in your inventory !");
        } else
            player.sendMessage(ChatColor.RED + "You don't have enough resources to buy this !");
    }
}