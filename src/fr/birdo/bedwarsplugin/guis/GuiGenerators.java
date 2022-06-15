package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
import fr.birdo.bedwarsplugin.utils.TeamDataFile;
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
import java.util.List;

public class GuiGenerators {

    public static Inventory Gui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Set team generator");
        List<Integer> bytes = Arrays.asList(14, 11, 13, 4, 9, 0, 2, 8);
        List<ChatColor> chatColors = Arrays.asList(ChatColor.DARK_RED, ChatColor.BLUE, ChatColor.DARK_GREEN, ChatColor.YELLOW, ChatColor.AQUA, ChatColor.WHITE, ChatColor.LIGHT_PURPLE, ChatColor.GRAY);
        for (int i = 0; i < 8; i++) {
            ItemStack itemTeam = new ItemStack(Material.WOOL, 1, bytes.get(i).byteValue());
            ItemMeta itemTeamMeta = itemTeam.getItemMeta();
            itemTeamMeta.setDisplayName(chatColors.get(i) + BedwarsPlugin.teams.get(i) + " Team");
            Location generatorLocation = TeamDataFile.getGeneratorLocation(BedwarsPlugin.teams.get(i));
            if (generatorLocation.getX() + generatorLocation.getY() != 0) {
                itemTeamMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemTeamMeta.setLore(Arrays.asList(ChatColor.GREEN + "This team already has a registered generator !", ChatColor.GRAY + "- Generator location : " + generatorLocation.getX() + " " + generatorLocation.getY() + " " + generatorLocation.getZ(), " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position", ChatColor.YELLOW + "Right Click " + ChatColor.GRAY + "to reset generator position"));
            } else {
                itemTeamMeta.setLore(Arrays.asList(ChatColor.RED + "This team doesn't have registered generator !", " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position"));
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
