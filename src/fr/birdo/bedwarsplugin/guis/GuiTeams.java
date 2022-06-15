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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiTeams {

    public static Inventory Gui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Teams View");
        List<Integer> bytes = Arrays.asList(14, 11, 13, 4, 9, 0, 2, 8);
        List<ChatColor> chatColors = Arrays.asList(ChatColor.DARK_RED, ChatColor.BLUE, ChatColor.DARK_GREEN, ChatColor.YELLOW, ChatColor.AQUA, ChatColor.WHITE, ChatColor.LIGHT_PURPLE, ChatColor.GRAY);
        for (int i = 0; i < 8; i++) {
            ItemStack itemTeam = new ItemStack(Material.WOOL, 1, bytes.get(i).byteValue());
            ItemMeta itemTeamMeta = itemTeam.getItemMeta();
            itemTeamMeta.setDisplayName(chatColors.get(i) + BedwarsPlugin.teams.get(i) + " Team");
            if (!TeamDataFile.getPlayers(BedwarsPlugin.teams.get(i)).isEmpty()) {
                itemTeamMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GREEN + "This team has registered players !");
                lore.add(" ");
                for (String player : TeamDataFile.getPlayers(BedwarsPlugin.teams.get(i)))
                    lore.add(ChatColor.GRAY + "- " + player);
                itemTeamMeta.setLore(lore);
            } else {
                itemTeamMeta.setLore(Arrays.asList(ChatColor.RED + "This team doesn't have registered players !"));
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
