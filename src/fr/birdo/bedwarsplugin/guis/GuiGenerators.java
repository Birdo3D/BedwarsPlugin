package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
import fr.birdo.bedwarsplugin.data.GeneratorDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
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

    public static Inventory choseGui() {
        Inventory inventory = Bukkit.createInventory(null, 27, "What generator do you want ?");
        for (int i = 0; i < 27; i++) {
            switch (i) {
                case 11:
                    ItemStack teamGenerator = new ItemStack(Material.IRON_INGOT, 1);
                    ItemMeta teamGeneratorMeta = teamGenerator.getItemMeta();
                    teamGeneratorMeta.setDisplayName(ChatColor.BLUE + "Team's generator");
                    teamGenerator.setItemMeta(teamGeneratorMeta);
                    inventory.setItem(i, teamGenerator);
                    break;
                case 13:
                    ItemStack diamondGenerator = new ItemStack(Material.DIAMOND, 1);
                    ItemMeta diamondGeneratorMeta = diamondGenerator.getItemMeta();
                    diamondGeneratorMeta.setDisplayName(ChatColor.BLUE + "Diamond generators");
                    diamondGenerator.setItemMeta(diamondGeneratorMeta);
                    inventory.setItem(i, diamondGenerator);
                    break;
                case 15:
                    ItemStack emeraldGenerator = new ItemStack(Material.EMERALD, 1);
                    ItemMeta emeraldGeneratorMeta = emeraldGenerator.getItemMeta();
                    emeraldGeneratorMeta.setDisplayName(ChatColor.BLUE + "Emerald generators");
                    emeraldGenerator.setItemMeta(emeraldGeneratorMeta);
                    inventory.setItem(i, emeraldGenerator);
                    break;
                case 26:
                    ItemStack itemClose = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
                    ItemMeta itemCloseMeta = itemClose.getItemMeta();
                    itemCloseMeta.setDisplayName(ChatColor.BLUE + "Close");
                    itemClose.setItemMeta(itemCloseMeta);
                    inventory.setItem(i, itemClose);
                    break;
                default:
                    ItemStack itemGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
                    ItemMeta itemGlassMeta = itemGlass.getItemMeta();
                    itemGlassMeta.setDisplayName(" ");
                    itemGlass.setItemMeta(itemGlassMeta);
                    inventory.setItem(i, itemGlass);
                    break;
            }
        }
        return inventory;
    }

    public static Inventory teamGui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Set team's generator");
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
        inventory.setItem(8, returnItem());
        return inventory;
    }

    public static Inventory diamondGui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Set diamond generators");
        for (int i = 0; i < 8; i++) {
            ItemStack itemTeam = new ItemStack(Material.DIAMOND, i + 1);
            ItemMeta itemTeamMeta = itemTeam.getItemMeta();
            itemTeamMeta.setDisplayName(ChatColor.AQUA + "Generator " + (i + 1));
            Location generatorLocation = GeneratorDataFile.getGenerator("Diamond", i + 1);
            if (generatorLocation.getX() + generatorLocation.getY() + generatorLocation.getZ() != 0) {
                itemTeamMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemTeamMeta.setLore(Arrays.asList(ChatColor.GREEN + "This generator is already registered !", ChatColor.GRAY + "- Generator location : " + generatorLocation.getX() + " " + generatorLocation.getY() + " " + generatorLocation.getZ(), " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position", ChatColor.YELLOW + "Right Click " + ChatColor.GRAY + "to reset generator position"));
            } else {
                itemTeamMeta.setLore(Arrays.asList(ChatColor.RED + "This generator isn't registered !", " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position"));
            }
            itemTeam.setItemMeta(itemTeamMeta);
            inventory.setItem(i, itemTeam);
        }
        inventory.setItem(8, returnItem());
        return inventory;
    }

    public static Inventory emeraldGui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Set emerald generators");
        for (int i = 0; i < 8; i++) {
            ItemStack itemTeam = new ItemStack(Material.EMERALD, i + 1);
            ItemMeta itemTeamMeta = itemTeam.getItemMeta();
            itemTeamMeta.setDisplayName(ChatColor.DARK_GREEN + "Generator " + (i + 1));
            Location generatorLocation = GeneratorDataFile.getGenerator("Emerald", i + 1);
            if (generatorLocation.getX() + generatorLocation.getY() + generatorLocation.getZ() != 0) {
                itemTeamMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                itemTeamMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemTeamMeta.setLore(Arrays.asList(ChatColor.GREEN + "This generator is already registered !", ChatColor.GRAY + "- Generator location : " + generatorLocation.getX() + " " + generatorLocation.getY() + " " + generatorLocation.getZ(), " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position", ChatColor.YELLOW + "Right Click " + ChatColor.GRAY + "to reset generator position"));
            } else {
                itemTeamMeta.setLore(Arrays.asList(ChatColor.RED + "This generator isn't registered !", " ", ChatColor.YELLOW + "Left Click " + ChatColor.GRAY + "to set generator position"));
            }
            itemTeam.setItemMeta(itemTeamMeta);
            inventory.setItem(i, itemTeam);
        }
        inventory.setItem(8, returnItem());
        return inventory;
    }

    private static ItemStack returnItem() {
        ItemStack itemGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        ItemMeta itemGlassMeta = itemGlass.getItemMeta();
        itemGlassMeta.setDisplayName(ChatColor.BLUE + "Back");
        itemGlass.setItemMeta(itemGlassMeta);
        return itemGlass;
    }
}
