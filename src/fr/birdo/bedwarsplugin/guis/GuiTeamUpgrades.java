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
                    protectionItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Your team permanently gains", ChatColor.GRAY + "Protection on all armor pieces!", " ", getTierLore(protectionName + " " + Utils.integerToRomanNumeral(1), 1, protectionLevel, protectionPrice.get(0)), getTierLore(protectionName + " " + Utils.integerToRomanNumeral(2), 2, protectionLevel, protectionPrice.get(1)), getTierLore(protectionName + " " + Utils.integerToRomanNumeral(3), 3, protectionLevel, protectionPrice.get(2)), getTierLore(protectionName + " " + Utils.integerToRomanNumeral(4), 4, protectionLevel, protectionPrice.get(3)), " ", getLore(protectionAction)));
                    protectionItem.setItemMeta(protectionItemMeta);
                    inventory.setItem(i, protectionItem);
                    break;
                //Haste
                case 12:
                    int hasteLevel = TeamDataFile.getHaste(PlayerDataFile.getTeam(player));
                    List<Integer> hastePrice = Arrays.asList(2, 4);
                    ItemStack hasteItem = new ItemStack(Material.GOLD_PICKAXE, hasteLevel + 1);
                    ItemMeta hasteItemMeta = hasteItem.getItemMeta();
                    hasteItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    int hasteAction = getAction(player, hasteLevel > 0, 4, hastePrice.get(hasteLevel));
                    hasteItemMeta.setDisplayName(getName("Maniac Miner " + Utils.integerToRomanNumeral(hasteLevel + 1), hasteAction));
                    String hasteName = "Haste";
                    hasteItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "All players on your team", ChatColor.GRAY + "permanently gain Haste.", " ", getTierLore(hasteName + " " + Utils.integerToRomanNumeral(1), 1, hasteLevel, hastePrice.get(0)), getTierLore(hasteName + " " + Utils.integerToRomanNumeral(2), 2, hasteLevel, hastePrice.get(1)), " ", getLore(hasteAction)));
                    hasteItem.setItemMeta(hasteItemMeta);
                    inventory.setItem(i, hasteItem);
                    break;
                //Forge Upgrade
                case 19:
                    int forgeLevel = TeamDataFile.getForge(PlayerDataFile.getTeam(player));
                    List<Integer> forgePrice = Arrays.asList(2, 4, 6, 8);
                    ItemStack forgeItem = new ItemStack(Material.FURNACE, forgeLevel + 1);
                    ItemMeta forgeItemMeta = forgeItem.getItemMeta();
                    int forgeAction = getAction(player, forgeLevel > 0, 4, forgePrice.get(forgeLevel));
                    String forgeName;
                    switch (forgeLevel) {
                        case 1:
                            forgeName = "Golden Forge";
                            break;
                        case 2:
                            forgeName = "Emerald Forge";
                            break;
                        case 3:
                            forgeName = "Molten Forge";
                            break;
                        default:
                            forgeName = "Iron Forge";
                            break;
                    }
                    forgeItemMeta.setDisplayName(getName(forgeName, forgeAction));
                    forgeItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Upgrade resource spawning on", ChatColor.GRAY + "your island.", " ", getTierLore("+50% Resources", 1, forgeLevel, forgePrice.get(0)), getTierLore("+100% Resources", 2, forgeLevel, forgePrice.get(1)), getTierLore("Spawn emeralds", 3, forgeLevel, forgePrice.get(2)), getTierLore("+200% Resources", 4, forgeLevel, forgePrice.get(3)), " ", getLore(forgeAction)));
                    forgeItem.setItemMeta(forgeItemMeta);
                    inventory.setItem(i, forgeItem);
                    break;
                //Heal Pool
                case 20:
                    ItemStack healItem = new ItemStack(Material.BEACON, 1);
                    ItemMeta healItemMeta = healItem.getItemMeta();
                    int healPrice = 3;
                    int healAction = getAction(player, TeamDataFile.hasHeal(PlayerDataFile.getTeam(player)), 0, healPrice);
                    healItemMeta.setDisplayName(getName("Heal Pool", healAction));
                    healItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Creates a Regeneration field", ChatColor.GRAY + "around your base!", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + healPrice + " Diamonds", " ", getLore(healAction)));
                    healItem.setItemMeta(healItemMeta);
                    inventory.setItem(i, healItem);
                    break;
                //Dragon Buff
                case 21:
                    ItemStack dragonItem = new ItemStack(Material.DRAGON_EGG, 1);
                    ItemMeta dragonItemMeta = dragonItem.getItemMeta();
                    int dragonPrice = 5;
                    int dragonAction = getAction(player, TeamDataFile.hasDragon(PlayerDataFile.getTeam(player)), 0, dragonPrice);
                    dragonItemMeta.setDisplayName(getName("Dragon Buff", dragonAction));
                    dragonItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Your team will have 2 dragons", ChatColor.GRAY + "instead of 1 during deathmatch!", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + dragonPrice + " Diamonds", " ", getLore(dragonAction)));
                    dragonItem.setItemMeta(dragonItemMeta);
                    inventory.setItem(i, dragonItem);
                    break;
                //It's a Trap
                case 14:
                    ItemStack trap1Item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
                    ItemMeta trap1ItemMeta = trap1Item.getItemMeta();
                    int trap1Price = 1;
                    int trap1Action = getAction(player, TeamDataFile.getTraps(PlayerDataFile.getTeam(player)).contains(1), 0, trap1Price);
                    trap1ItemMeta.setDisplayName(getName("It's a trap!", trap1Action));
                    trap1ItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Inflicts Blindness and Slowness", ChatColor.GRAY + "for 8 seconds.", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + trap1Price + " Diamonds", " ", getLore(trap1Action)));
                    trap1Item.setItemMeta(trap1ItemMeta);
                    inventory.setItem(i, trap1Item);
                    break;
                //Counter-Offensive Trap
                case 15:
                    ItemStack trap2Item = new ItemStack(Material.FEATHER, 1);
                    ItemMeta trap2ItemMeta = trap2Item.getItemMeta();
                    int trap2Price = 1;
                    int trap2Action = getAction(player, TeamDataFile.getTraps(PlayerDataFile.getTeam(player)).contains(2), 0, trap2Price);
                    trap2ItemMeta.setDisplayName(getName("Counter-Offensive Trap", trap2Action));
                    trap2ItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Grants Speed II and Jump Boost", ChatColor.GRAY + "II for 15 seconds to allied", ChatColor.GRAY + "players near your base.", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + trap2Price + " Diamonds", " ", getLore(trap2Action)));
                    trap2Item.setItemMeta(trap2ItemMeta);
                    inventory.setItem(i, trap2Item);
                    break;
                //Invisible Trap
                case 16:
                    ItemStack trap3Item = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
                    ItemMeta trap3ItemMeta = trap3Item.getItemMeta();
                    int trap3Price = 1;
                    int trap3Action = getAction(player, TeamDataFile.getTraps(PlayerDataFile.getTeam(player)).contains(3), 0, trap3Price);
                    trap3ItemMeta.setDisplayName(getName("Alarm Trap", trap3Action));
                    trap3ItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Reveals invisible players as", ChatColor.GRAY + "well as their name and team.", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + trap3Price + " Diamonds", " ", getLore(trap3Action)));
                    trap3Item.setItemMeta(trap3ItemMeta);
                    inventory.setItem(i, trap3Item);
                    break;
                //Mining fatigue Trap
                case 23:
                    ItemStack trap4Item = new ItemStack(Material.IRON_PICKAXE, 1);
                    ItemMeta trap4ItemMeta = trap4Item.getItemMeta();
                    trap4ItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    int trap4Price = 1;
                    int trap4Action = getAction(player, TeamDataFile.getTraps(PlayerDataFile.getTeam(player)).contains(4), 0, trap4Price);
                    trap4ItemMeta.setDisplayName(getName("Miner Fatigue Trap", trap4Action));
                    trap4ItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Inflict Mining Fatigue for 10", ChatColor.GRAY + "seconds.", " ", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + trap4Price + " Diamonds", " ", getLore(trap4Action)));
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
            return ChatColor.GREEN + "Tier " + i + ": " + name + "," + " " + ChatColor.AQUA + price + " Diamonds";
        else
            return ChatColor.GRAY + "Tier " + i + ": " + name + "," + " " + ChatColor.AQUA + price + " Diamonds";
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