package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.utils.GeneratorDataFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiDiamondGen {

    public static Inventory Gui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Set Diamond generator");
        for (int i = 0; i < 8; i++) {
            ItemStack itemTeam = new ItemStack(Material.DIAMOND, 1);
            ItemMeta itemTeamMeta = itemTeam.getItemMeta();
            itemTeamMeta.setDisplayName(ChatColor.AQUA + "" + (i + 1) + " Diamond generator");
            if (GeneratorDataFile.getGenerators("Diamond").size() >= i + 2) {
                Location generatorLocation = GeneratorDataFile.getGeneratorLocation("Diamond", GeneratorDataFile.getGenerators("Diamond").get(i + 1));
                itemTeamMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemTeamMeta.setLore(Arrays.asList(ChatColor.GREEN + "This generator is registered !", ChatColor.GRAY + "Generator location : " + generatorLocation.getX() + " " + generatorLocation.getY() + " " + generatorLocation.getZ(), " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position", ChatColor.YELLOW + "Right Click " + ChatColor.GRAY + "to reset generator position"));
            } else {
                itemTeamMeta.setLore(Arrays.asList(ChatColor.RED + "This generator isn't registered !", " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position"));
            }
            itemTeam.setItemMeta(itemTeamMeta);
            inventory.setItem(i, itemTeam);
        }
        ItemStack itemGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
        ItemMeta itemGlassMeta = itemGlass.getItemMeta();
        itemGlassMeta.setDisplayName(" ");
        itemGlass.setItemMeta(itemGlassMeta);
        inventory.setItem(8, itemGlass);
        return inventory;
    }
}
