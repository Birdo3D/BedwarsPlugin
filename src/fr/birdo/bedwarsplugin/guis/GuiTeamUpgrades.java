package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.data.PlayerDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
import fr.birdo.bedwarsplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiTeamUpgrades {

    public static Inventory guiTeamUpgrades(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "Upgrades & Traps");
        for (int i = 0; i < 6 * 9; i++) {
            switch (i) {
                //Sharpness
                case 10:
                    ItemStack sharpnessItem = new ItemStack(Material.IRON_SWORD, 1);
                    ItemMeta sharpnessItemMeta = sharpnessItem.getItemMeta();
                    if (TeamDataFile.hasSharpness(PlayerDataFile.getTeam(player))) {
                        sharpnessItemMeta.setDisplayName(ChatColor.RED + "Sharpened Swords");
                        if (Utils.hasMoney(player, Material.DIAMOND, 8)) {
                            sharpnessItemMeta.setLore(Arrays.asList("Your team permanently gains", "Sharpness I on all swords and", "axes !", " ", "Cost: " + ChatColor.AQUA + "8 Diamonds", " ", ChatColor.RED + "You don't have enough Diamonds!"));
                        } else {
                            sharpnessItemMeta.setLore(Arrays.asList("Your team permanently gains", "Sharpness I on all swords and", "axes !", " ", "Cost: " + ChatColor.AQUA + "8 Diamonds", " ", ChatColor.RED + "You don't have enough Diamonds!"));
                        }
                    } else {
                        sharpnessItemMeta.setDisplayName(ChatColor.GREEN + "Sharpened Swords");
                        sharpnessItemMeta.setLore(Arrays.asList("Your team permanently gains", "Sharpness I on all swords and", "axes !", " ", "Cost: " + ChatColor.AQUA + "8 Diamonds", " ", ChatColor.RED + "You don't have enough Diamonds!"));
                    }
                    sharpnessItem.setItemMeta(sharpnessItemMeta);
                    inventory.setItem(i, sharpnessItem);
                    break;
                //Protection
                case 11:
                    ItemStack protectionItem = new ItemStack(Material.IRON_CHESTPLATE, 1);
                    ItemMeta protectionItemMeta = protectionItem.getItemMeta();
                    protectionItemMeta.setDisplayName(" ");
                    protectionItem.setItemMeta(protectionItemMeta);
                    inventory.setItem(i, protectionItem);
                    break;
                //Haste
                case 12:
                    ItemStack hasteItem = new ItemStack(Material.GOLD_PICKAXE, 1);
                    ItemMeta hasteItemMeta = hasteItem.getItemMeta();
                    hasteItemMeta.setDisplayName(" ");
                    hasteItem.setItemMeta(hasteItemMeta);
                    inventory.setItem(i, hasteItem);
                    break;
                //Forge Upgrade
                case 19:
                    ItemStack forgeItem = new ItemStack(Material.FURNACE, 1);
                    ItemMeta forgeItemMeta = forgeItem.getItemMeta();
                    forgeItemMeta.setDisplayName(" ");
                    forgeItem.setItemMeta(forgeItemMeta);
                    inventory.setItem(i, forgeItem);
                    break;
                //Heal Pool
                case 20:
                    ItemStack healItem = new ItemStack(Material.BEACON, 1);
                    ItemMeta healItemMeta = healItem.getItemMeta();
                    healItemMeta.setDisplayName(" ");
                    healItem.setItemMeta(healItemMeta);
                    inventory.setItem(i, healItem);
                    break;
                //Dragon Buff
                case 21:
                    ItemStack dragonItem = new ItemStack(Material.DRAGON_EGG, 1);
                    ItemMeta dragonItemMeta = dragonItem.getItemMeta();
                    dragonItemMeta.setDisplayName(" ");
                    dragonItem.setItemMeta(dragonItemMeta);
                    inventory.setItem(i, dragonItem);
                    break;
                //It's a Trap
                case 14:
                    ItemStack trap1Item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
                    ItemMeta trap1ItemMeta = trap1Item.getItemMeta();
                    trap1ItemMeta.setDisplayName(" ");
                    trap1Item.setItemMeta(trap1ItemMeta);
                    inventory.setItem(i, trap1Item);
                    break;
                //Counter-Offensive Trap
                case 15:
                    ItemStack trap2Item = new ItemStack(Material.FEATHER, 1);
                    ItemMeta trap2ItemMeta = trap2Item.getItemMeta();
                    trap2ItemMeta.setDisplayName(" ");
                    trap2Item.setItemMeta(trap2ItemMeta);
                    inventory.setItem(i, trap2Item);
                    break;
                //Invisible Trap
                case 16:
                    ItemStack trap3Item = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
                    ItemMeta trap3ItemMeta = trap3Item.getItemMeta();
                    trap3ItemMeta.setDisplayName(" ");
                    trap3Item.setItemMeta(trap3ItemMeta);
                    inventory.setItem(i, trap3Item);
                    break;
                //Mining fatigue Trap
                case 23:
                    ItemStack trap4Item = new ItemStack(Material.IRON_PICKAXE, 1);
                    ItemMeta trap4ItemMeta = trap4Item.getItemMeta();
                    trap4ItemMeta.setDisplayName(" ");
                    trap4Item.setItemMeta(trap4ItemMeta);
                    inventory.setItem(i, trap4Item);
                    break;
                //Decoration
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                    ItemStack decoItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
                    ItemMeta decoItemMeta = decoItem.getItemMeta();
                    decoItemMeta.setDisplayName(" ");
                    decoItem.setItemMeta(decoItemMeta);
                    inventory.setItem(i, decoItem);
                    break;
                //Trap #1
                case 39:
                    ItemStack trapList1Item = new ItemStack(Material.STAINED_GLASS, 1, (byte) 7);
                    ItemMeta trapList1ItemMeta = trapList1Item.getItemMeta();
                    trapList1ItemMeta.setDisplayName("Trap1");
                    trapList1Item.setItemMeta(trapList1ItemMeta);
                    inventory.setItem(i, trapList1Item);
                    break;
                //Trap #2
                case 40:
                    ItemStack trapList2Item = new ItemStack(Material.STAINED_GLASS, 1, (byte) 7);
                    ItemMeta trapList2ItemMeta = trapList2Item.getItemMeta();
                    trapList2ItemMeta.setDisplayName("Trap2");
                    trapList2Item.setItemMeta(trapList2ItemMeta);
                    inventory.setItem(i, trapList2Item);
                    break;
                //Trap #3
                case 41:
                    ItemStack trapList3Item = new ItemStack(Material.STAINED_GLASS, 1, (byte) 7);
                    ItemMeta trapList3ItemMeta = trapList3Item.getItemMeta();
                    trapList3ItemMeta.setDisplayName("Trap3");
                    trapList3Item.setItemMeta(trapList3ItemMeta);
                    inventory.setItem(i, trapList3Item);
                    break;
                //Void
                default:
                    ItemStack voidItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
                    ItemMeta voidItemMeta = voidItem.getItemMeta();
                    voidItemMeta.setDisplayName(" ");
                    voidItem.setItemMeta(voidItemMeta);
                    inventory.setItem(i, voidItem);
                    break;
            }
        }
        return inventory;
    }
}