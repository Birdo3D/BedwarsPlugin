package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.data.PlayerDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
import fr.birdo.bedwarsplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class GuiTeamUpgrades {

    public static Inventory guiTeamUpgrades(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "Upgrades & Traps");
        for (int i = 0; i < 6 * 9; i++) {
            switch (i) {
                //Sharpness
                case 10:
                    ItemStack sharpnessItem = new ItemStack(Material.IRON_SWORD, 1);
                    ItemMeta sharpnessItemMeta = sharpnessItem.getItemMeta();
                    sharpnessItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    int sharpnessPrice = 4;
                    int sharpnessAction = getAction(player, TeamDataFile.hasSharpness(PlayerDataFile.getTeam(player)), 0, sharpnessPrice);
                    sharpnessItemMeta.setDisplayName(getName("Sharpened Swords", sharpnessAction));
                    sharpnessItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Your team permanently gains", ChatColor.GRAY + "Sharpness I on all swords and", ChatColor.GRAY + "axes !", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + sharpnessPrice + " Diamonds", " ", getLore(sharpnessAction)));
                    sharpnessItem.setItemMeta(sharpnessItemMeta);
                    inventory.setItem(i, sharpnessItem);
                    break;
                //Protection
                case 11:
                    int protectionLevel = TeamDataFile.getProtection(PlayerDataFile.getTeam(player));
                    ItemStack protectionItem = new ItemStack(Material.IRON_CHESTPLATE, protectionLevel + 1);
                    ItemMeta protectionItemMeta = protectionItem.getItemMeta();
                    protectionItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    List<Integer> protectionPrice = Arrays.asList(2, 4, 8, 16);
                    int protectionAction = getAction(player, protectionLevel > 0, 4, protectionPrice.get(protectionLevel));
                    protectionItemMeta.setDisplayName(getName("Reinforced Armor " + Utils.integerToRomanNumeral(protectionLevel + 1), protectionAction));
                    String protectionName = "Protection";
                    protectionItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Your team permanently gains", ChatColor.GRAY + "Protection on all armor pieces!", " ", getTierLore(protectionName, 1, protectionLevel, protectionPrice.get(0)), getTierLore(protectionName, 2, protectionLevel, protectionPrice.get(1)), getTierLore(protectionName, 3, protectionLevel, protectionPrice.get(2)), getTierLore(protectionName, 4, protectionLevel, protectionPrice.get(3)), " ", getLore(protectionAction)));
                    protectionItem.setItemMeta(protectionItemMeta);
                    inventory.setItem(i, protectionItem);
                    break;
                //Haste
                case 12:
                    int hasteLevel = TeamDataFile.getProtection(PlayerDataFile.getTeam(player));
                    List<Integer> hastePrice = Arrays.asList(2, 4);
                    ItemStack hasteItem = new ItemStack(Material.GOLD_PICKAXE, 1);
                    ItemMeta hasteItemMeta = hasteItem.getItemMeta();
                    hasteItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    int hasteAction = getAction(player, hasteLevel > 0, 4, hastePrice.get(hasteLevel));
                    hasteItemMeta.setDisplayName(getName("Maniac Miner " + Utils.integerToRomanNumeral(hasteLevel + 1), hasteAction));
                    String hasteName = "Haste";
                    hasteItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "All players on your team", ChatColor.GRAY + "permanently gain Haste.", " ", getTierLore(hasteName, 1, hasteLevel, hastePrice.get(0)), getTierLore(hasteName, 2, hasteLevel, hastePrice.get(1)), " ", getLore(hasteAction)));
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

    private static int getAction(Player player, boolean hasUpgrade, int maxLevel, int price) {
        if (!hasUpgrade || maxLevel > 0)
            if (Utils.hasMoney(player, Material.DIAMOND, price))
                return 1;
            else
                return 0;
        else
            return 2;
    }

    private static String getName(String name, int action) {
        switch (action) {
            case 1:
                return ChatColor.YELLOW + name;
            case 2:
                return ChatColor.GREEN + name;
            default:
                return ChatColor.RED + name;
        }
    }

    private static String getTierLore(String name, int i, int level, int price) {
        if (i <= level)
            return ChatColor.GREEN + "Tier " + i + ": " + name + " " + Utils.integerToRomanNumeral(i) + "," + " " + ChatColor.AQUA + price + " Diamonds";
        else
            return ChatColor.GRAY + "Tier " + i + ": " + name + " " + Utils.integerToRomanNumeral(i) + "," + " " + ChatColor.AQUA + price + " Diamonds";
    }

    private static String getLore(int action) {
        switch (action) {
            case 1:
                return ChatColor.YELLOW + "Click to purchase!";
            case 2:
                return ChatColor.GREEN + "UNLOCKED";
            default:
                return ChatColor.RED + "You don't have enough Diamonds!";
        }
    }
}