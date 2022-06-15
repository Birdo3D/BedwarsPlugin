package fr.birdo.bedwarsplugin;

import fr.birdo.bedwarsplugin.guis.*;
import fr.birdo.bedwarsplugin.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Events implements Listener {

    private static BedwarsPlugin instance;

    public Events(BedwarsPlugin pluginInstance) {
        instance = pluginInstance;
    }

    private final Map<Player, Block> clickedBlock = new HashMap<>();

    @EventHandler
    public void commandSendEvent(PlayerCommandPreprocessEvent e) {
        String[] args = e.getMessage().split(" ");
        if ((args[0].equalsIgnoreCase("/bw") || args[0].equalsIgnoreCase("/bedwars")) && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("give")) {
                    if (args.length > 2) {
                        if (args[2].equalsIgnoreCase("pnj")) {
                            ItemStack egg = new ItemStack(Material.EGG, 1);
                            ItemMeta eggM = egg.getItemMeta();
                            eggM.setDisplayName("Classic PNJ Spawn Egg");
                            egg.setItemMeta(eggM);
                            e.getPlayer().getInventory().addItem(egg);
                        }
                    }
                } else if (args[1].equalsIgnoreCase("launch")) {
                    Utils.launchGame();
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Lancement de la partie !");
                } else if (args[1].equalsIgnoreCase("diamond")) {
                    e.getPlayer().openInventory(GuiDiamondGen.Gui());
                } else if (args[1].equalsIgnoreCase("generators")) {
                    e.getPlayer().openInventory(GuiGenerators.Gui());
                } else if (args[1].equalsIgnoreCase("teams")) {
                    e.getPlayer().openInventory(GuiTeams.Gui());
                } else if (args[1].equalsIgnoreCase("set")) {
                    if (args.length > 3) {
                        for (Player player : Bukkit.getOnlinePlayers())
                            if (args[2].equalsIgnoreCase(player.getName()))
                                for (String team : BedwarsPlugin.teams)
                                    if (args[3].equalsIgnoreCase(team))
                                        if (PlayerDataFile.getTeam(player).equalsIgnoreCase("null")) {
                                            PlayerDataFile.setTeam(player, team);
                                            TeamDataFile.addPlayer(team, player);
                                            e.getPlayer().sendMessage(ChatColor.GREEN + "Le joueur à bien été ajouté à l'équipe !");
                                        } else
                                            e.getPlayer().sendMessage(ChatColor.RED + "Le joueur appartient déjà à une équipe !");
                    }
                } else if (args[1].equalsIgnoreCase("remove")) {
                    if (args.length > 2) {
                        for (Player player : Bukkit.getOnlinePlayers())
                            if (args[2].equalsIgnoreCase(player.getName())) {
                                for (String team : BedwarsPlugin.teams)
                                    TeamDataFile.removePlayer(team, player);
                                PlayerDataFile.setTeam(player, "null");
                                e.getPlayer().sendMessage(ChatColor.GREEN + "Le joueur à bien été retiré des équipes !");
                            }
                    }
                } else if (args[1].equalsIgnoreCase("beds")) {
                    e.getPlayer().openInventory(GuiBeds.Gui(false));
                } else if (args[1].equalsIgnoreCase("spawns")) {
                    e.getPlayer().openInventory(GuiSpawns.Gui());
                } else if (args[1].equalsIgnoreCase("help")) {
                    e.getPlayer().sendMessage("Commandes disponibles :");
                    e.getPlayer().sendMessage("- /bw give pnj");
                    e.getPlayer().sendMessage("- /bw launch");
                    e.getPlayer().sendMessage("- /bw set 'player' 'team'");
                    e.getPlayer().sendMessage("- /bw remove 'player'");
                    e.getPlayer().sendMessage("- /bw beds");
                    e.getPlayer().sendMessage("- /bw teams");
                    e.getPlayer().sendMessage("- /bw spawns");
                    e.getPlayer().sendMessage("- /bw help");
                    e.getPlayer().sendMessage("- /bw test");
                } else if (args[1].equalsIgnoreCase("test"))
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Le plugin fonctionne correctement !");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void spawnPNJ(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            double x = e.getClickedBlock().getLocation().getX() + 0.5;
            double y = e.getClickedBlock().getLocation().getY() + 1;
            double z = e.getClickedBlock().getLocation().getZ() + 0.5;
            if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                if (e.getItem() != null && e.getItem().getType() == Material.EGG) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Classic PNJ Spawn Egg")) {
                        e.setCancelled(true);
                        e.getPlayer().getWorld().spawnEntity(new Location(e.getPlayer().getLocation().getWorld(), x, y, z), EntityType.VILLAGER).setCustomName("Item Shop");
                    }
                }
            }
        }
    }

    @EventHandler
    public void openGui(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER)
            if (!e.getPlayer().isSneaking())
                if (e.getRightClicked().getCustomName().equalsIgnoreCase("Item Shop")) {
                    e.setCancelled(true);
                    Gui.pnj01(e.getPlayer(), "Blocks");
                }
    }

    @EventHandler
    public void guiClickEvent(InventoryClickEvent e) {
        String invName = "Item Shop - ";
        if (e.getClickedInventory() != null) {
            String inv = e.getClickedInventory().getTitle();
            if (inv.equalsIgnoreCase(invName + "Blocks") || inv.equalsIgnoreCase(invName + "Weapons") || inv.equalsIgnoreCase(invName + "Armors") || inv.equalsIgnoreCase(invName + "Tools") || inv.equalsIgnoreCase(invName + "Bows") || inv.equalsIgnoreCase(invName + "Potions") || inv.equalsIgnoreCase(invName + "Other")) {
                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName())
                    if (e.getCurrentItem().getItemMeta().getDisplayName().length() > 1) {
                        String itemName = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                        if (itemName.equalsIgnoreCase("Blocks") || itemName.equalsIgnoreCase("Weapons") || itemName.equalsIgnoreCase("Armors") || itemName.equalsIgnoreCase("Tools") || itemName.equalsIgnoreCase("Bows") || itemName.equalsIgnoreCase("Potions") || itemName.equalsIgnoreCase("Other"))
                            Gui.pnj01((Player) e.getWhoClicked(), itemName);
                        if (Gui.getItems(inv).get(e.getSlot()) != null)
                            Utils.buyItem((Player) e.getWhoClicked(), Gui.getItems(inv).get(e.getSlot()));
                    }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        boolean exist = false;
        File playerDataFile = new File(BedwarsPlugin.playerDataFolderPath + "/" + e.getPlayer().getUniqueId() + ".yml");
        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
                exist = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (playerDataFile.exists() && exist) {
            PlayerDataFile.createSections(e.getPlayer());
            setupInventory(e.getPlayer());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        int pickaxe = PlayerDataFile.getPickaxe(e.getEntity().getPlayer());
        int axe = PlayerDataFile.getAxe(e.getEntity().getPlayer());
        if (pickaxe == 0 || pickaxe == 1)
            pickaxe++;
        if (axe == 0 || axe == 1)
            axe++;
        PlayerDataFile.setPickaxe(e.getEntity().getPlayer(), Utils.getToolFromID(pickaxe - 1));
        PlayerDataFile.setAxe(e.getEntity().getPlayer(), Utils.getToolFromID(axe - 1));
        e.getDrops().clear();
        for (String team : BedwarsPlugin.teams)
            if (TeamDataFile.getPlayers(team).contains(e.getEntity().getName()))
                TeamDataFile.removeLivePlayer(team, e.getEntity().getPlayer());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        for (String team : BedwarsPlugin.teams)
            if (TeamDataFile.getPlayers(team).contains(e.getPlayer().getName())) {
                if (TeamDataFile.hasBed(team)) {
                    TeamDataFile.addLivePlayer(team, e.getPlayer());
                    setupInventory(e.getPlayer());
                } else {
                    e.getPlayer().setGameMode(GameMode.SPECTATOR);
                }
                e.getPlayer().teleport(TeamDataFile.getSpawnLocation(team));
            }
    }

    @EventHandler
    public void onBedDestroy(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.BED_BLOCK)
            for (String team : BedwarsPlugin.teams)
                if (TeamDataFile.getBed1Location(team).equals(e.getBlock().getLocation()) || TeamDataFile.getBed2Location(team).equals(e.getBlock().getLocation())) {
                    if (!TeamDataFile.getPlayers(team).contains(e.getPlayer().getName())) {
                        TeamDataFile.setBed(team, false);
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Le lit de l'équipe " + team + " vient d'être détruit par " + e.getPlayer().getName() + " !");
                    } else {
                        e.getPlayer().sendMessage(ChatColor.RED + "You can't break your own bed !");
                    }
                }
    }

    private void setupInventory(Player player) {
        player.getInventory().setItem(0, Converter.convertToItemStack(new Item(Material.WOOD_SWORD, "Wooden Sword", 1, 0, MoneyType.NULL, true), false));
        if (PlayerDataFile.hasShears(player))
            player.getInventory().addItem(Converter.convertToItemStack(new Item(Material.SHEARS, "Shears", 1, 0, MoneyType.NULL, true), false));
        if (PlayerDataFile.getPickaxe(player) > 0)
            player.getInventory().addItem(Converter.convertToItemStack(Utils.getToolFromID(PlayerDataFile.getPickaxe(player)).getItem(), false));
        if (PlayerDataFile.getAxe(player) > 0)
            player.getInventory().addItem(Converter.convertToItemStack(Utils.getToolFromID(PlayerDataFile.getAxe(player) + 4).getItem(), false));
        player.getInventory().setHelmet(Converter.convertToItemStack(new Item(Material.LEATHER_HELMET, "Helmet", 1, 0, MoneyType.NULL, true), false));
        player.getInventory().setChestplate(Converter.convertToItemStack(new Item(Material.LEATHER_CHESTPLATE, "Chestplate", 1, 0, MoneyType.NULL, true), false));
        player.getInventory().setLeggings(Converter.convertToItemStack(Utils.getArmor(PlayerDataFile.getArmorType(player), true), false));
        player.getInventory().setBoots(Converter.convertToItemStack(Utils.getArmor(PlayerDataFile.getArmorType(player), false), false));
    }

    @EventHandler
    public void onClickOnArmorSlot(InventoryClickEvent e) {
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE)
            if (e.getClickedInventory() != null)
                if (e.getClickedInventory() instanceof PlayerInventory)
                    if (e.getSlot() > 35 && e.getSlot() < 40)
                        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.TNT) {
            e.getBlockPlaced().getLocation().getBlock().setType(Material.AIR);
            e.getBlockPlaced().getWorld().spawnEntity(new Location(e.getBlockPlaced().getWorld(), e.getBlockPlaced().getX() + 0.5, e.getBlockPlaced().getY(), e.getBlockPlaced().getZ() + 0.5), EntityType.PRIMED_TNT);
        } else if (e.getBlock().getType() == Material.SPONGE) {
            e.getBlockPlaced().getLocation().getBlock().setData((byte) 1);
            World world = e.getBlock().getWorld();
            double x = e.getBlockPlaced().getX();
            double y = e.getBlockPlaced().getY();
            double z = e.getBlockPlaced().getZ();
            for (int x1 = -1; x1 < 2; x1++) {
                for (int y1 = -1; y1 < 2; y1++) {
                    for (int z1 = -1; z1 < 2; z1++) {
                        Location location = new Location(world, x + x1, y + y1, z + z1);
                        if (location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER)
                            location.getBlock().setType(Material.AIR);
                    }
                }
            }
            BukkitRunnable task1 = new BukkitRunnable() {
                @Override
                public void run() {
                    for (int x1 = -2; x1 < 3; x1++) {
                        for (int y1 = -2; y1 < 3; y1++) {
                            for (int z1 = -2; z1 < 3; z1++) {
                                Location location = new Location(world, x + x1, y + y1, z + z1);
                                if (location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER)
                                    location.getBlock().setType(Material.AIR);
                            }
                        }
                    }
                }
            };
            task1.runTaskLater(instance, 5);
            BukkitRunnable task2 = new BukkitRunnable() {
                @Override
                public void run() {
                    for (int x1 = -3; x1 < 4; x1++) {
                        for (int y1 = -3; y1 < 4; y1++) {
                            for (int z1 = -3; z1 < 4; z1++) {
                                Location location = new Location(world, x + x1, y + y1, z + z1);
                                if (location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER)
                                    location.getBlock().setType(Material.AIR);
                            }
                        }
                    }
                }
            };
            task2.runTaskLater(instance, 10);
            BukkitRunnable task3 = new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlockPlaced().getLocation().getBlock().setType(Material.AIR);
                }
            };
            task3.runTaskLater(instance, 15);
        }
    }

    @EventHandler
    public void onPNJDamageEvent(EntityDamageEvent e) {
        if (e.getEntity().getType() == EntityType.VILLAGER)
            if (e.getEntity().getCustomName().equalsIgnoreCase("Item Shop"))
                e.setCancelled(true);
    }

    @EventHandler
    public void removePNJ(PlayerInteractEntityEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE)
            if (e.getPlayer().isSneaking())
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD)
                    if (e.getRightClicked().getType() == EntityType.VILLAGER)
                        if (e.getRightClicked().getCustomName().equalsIgnoreCase("Item Shop"))
                            e.getRightClicked().remove();
    }

    @EventHandler
    public void onBucketUse(PlayerBucketEmptyEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (e.getBucket() == Material.WATER_BUCKET) {
                    e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(Material.WATER_BUCKET, 0));
                    e.getPlayer().updateInventory();
                }
            }
        };
        task.runTaskLater(instance, 1);
    }

    @EventHandler
    public void onConsumeItem(PlayerItemConsumeEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (e.getItem().getType() == Material.MILK_BUCKET || e.getItem().getType() == Material.POTION) {
                    e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(e.getItem().getType(), 0));
                    e.getPlayer().updateInventory();
                }
            }
        };
        task.runTaskLater(instance, 1);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equalsIgnoreCase("container.crafting"))
            if (e.getCurrentItem() != null)
                if (Utils.isTool(e.getCurrentItem().getType()))
                    e.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (Utils.isTool(e.getItemDrop().getItemStack().getType()))
            e.setCancelled(true);
    }

    public static void ticking(Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            checkInventory();
        }, 0L, 1L);
    }

    private static void checkInventory() {
        boolean hasSword;
        int woodSword = -1;
        for (Player player : Bukkit.getOnlinePlayers()) {
            hasSword = false;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) != null) {
                    Material mat = player.getInventory().getItem(i).getType();
                    if (mat == Material.BUCKET || mat == Material.GLASS_BOTTLE)
                        player.getInventory().getItem(i).setAmount(0);
                    if (mat == Material.STONE_SWORD || mat == Material.IRON_SWORD || mat == Material.DIAMOND_SWORD) {
                        if (player.getInventory().contains(Material.WOOD_SWORD))
                            for (int j = 0; j < 36; j++)
                                if (player.getInventory().getItem(j) != null)
                                    if (player.getInventory().getItem(j).getType() == Material.WOOD_SWORD)
                                        player.getInventory().getItem(j).setAmount(0);
                        hasSword = true;
                    }
                    if (mat == Material.WOOD_SWORD) {
                        hasSword = true;
                        woodSword = i;
                    }
                }
                if (i == 35 && !hasSword)
                    player.getInventory().addItem(Converter.convertToItemStack(new Item(Material.WOOD_SWORD, "Wooden Sword", 1, 0, MoneyType.NULL, true), false));
            }
            if (woodSword > -1)
                for (int i = 0; i < 36; i++)
                    if (player.getInventory().getItem(i) != null)
                        if (player.getInventory().getItem(i).getType() == Material.WOOD_SWORD)
                            if (i != woodSword)
                                player.getInventory().getItem(i).setAmount(0);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
            if (event.getItem() != null && event.getItem().getType() == Material.CARROT_STICK) {
                if (event.getClickedBlock().getType() == Material.BED_BLOCK) {
                    this.clickedBlock.put(event.getPlayer(), event.getClickedBlock());
                    event.getPlayer().openInventory(GuiBeds.Gui(true));
                }
            }
        }
    }

    @EventHandler
    public void onClickGui(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Set team bed")) {
            if (event.getSlot() < 8) {
                if (event.getClick().isLeftClick()) {
                    if (this.clickedBlock.get(event.getWhoClicked()) != null) {
                        String team = BedwarsPlugin.teams.get(event.getSlot());
                        Block block = this.clickedBlock.get(event.getWhoClicked());
                        Bed b = (Bed) block.getState().getData();
                        TeamDataFile.setBed1Location(team, block.getLocation());
                        if (b.isHeadOfBed()) {
                            TeamDataFile.setBed2Location(team, block.getRelative(b.getFacing().getOppositeFace()).getLocation());
                        } else {
                            TeamDataFile.setBed2Location(team, block.getRelative(b.getFacing()).getLocation());
                        }
                        TeamDataFile.setBed(team, true);
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le lit de cette équipe à été correctement placé !");
                        this.clickedBlock.remove(event.getWhoClicked());
                    }
                } else if (event.getClick().isRightClick()) {
                    Location bed1 = TeamDataFile.getBed1Location(BedwarsPlugin.teams.get(event.getSlot()));
                    Location bed2 = TeamDataFile.getBed2Location(BedwarsPlugin.teams.get(event.getSlot()));
                    if (bed1.getX() + bed1.getY() + bed2.getX() + bed2.getY() != 0) {
                        TeamDataFile.setBed1Location(BedwarsPlugin.teams.get(event.getSlot()), new Location(event.getWhoClicked().getWorld(), 0, 0, 0));
                        TeamDataFile.setBed2Location(BedwarsPlugin.teams.get(event.getSlot()), new Location(event.getWhoClicked().getWorld(), 0, 0, 0));
                        TeamDataFile.setBed(BedwarsPlugin.teams.get(event.getSlot()), false);
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le lit de cette équipe à été correctement retiré !");
                    }
                }
                event.getWhoClicked().openInventory(GuiBeds.Gui(false));
            }
            event.setCancelled(true);
        } else if (event.getView().getTitle().equalsIgnoreCase("Set team spawn")) {
            if (event.getSlot() < 8) {
                if (event.getClick().isLeftClick()) {
                    String team = BedwarsPlugin.teams.get(event.getSlot());
                    TeamDataFile.setSpawnLocation(team, event.getWhoClicked().getLocation());
                    event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le spawn de cette équipe à été correctement placé !");
                } else if (event.getClick().isRightClick()) {
                    Location spawnLocation = TeamDataFile.getSpawnLocation(BedwarsPlugin.teams.get(event.getSlot()));
                    if (spawnLocation.getX() + spawnLocation.getY() != 0) {
                        TeamDataFile.setSpawnLocation(BedwarsPlugin.teams.get(event.getSlot()), new Location(event.getWhoClicked().getWorld(), 0, 0, 0));
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le spawn de cette équipe à été correctement retiré !");
                    }
                }
                event.getWhoClicked().openInventory(GuiSpawns.Gui());
            }
            event.setCancelled(true);
        } else if (event.getView().getTitle().equalsIgnoreCase("Set team generator")) {
            if (event.getSlot() < 8) {
                if (event.getClick().isLeftClick()) {
                    String team = BedwarsPlugin.teams.get(event.getSlot());
                    TeamDataFile.setGeneratorLocation(team, event.getWhoClicked().getLocation());
                    event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le générateur de cette équipe à été correctement placé !");
                } else if (event.getClick().isRightClick()) {
                    Location generatorLocation = TeamDataFile.getGeneratorLocation(BedwarsPlugin.teams.get(event.getSlot()));
                    if (generatorLocation.getX() + generatorLocation.getY() != 0) {
                        TeamDataFile.setGeneratorLocation(BedwarsPlugin.teams.get(event.getSlot()), new Location(event.getWhoClicked().getWorld(), 0, 0, 0));
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Le générateur de cette équipe à été correctement retiré !");
                    }
                }
                event.getWhoClicked().openInventory(GuiGenerators.Gui());
            }
            event.setCancelled(true);
        } else if (event.getView().getTitle().equalsIgnoreCase("Set Diamond generator")) {
            if (event.getSlot() < 8) {
                if (event.getClick().isLeftClick()) {
                    GeneratorDataFile.addGenerator("Diamond", event.getWhoClicked().getLocation());
                    event.getWhoClicked().sendMessage(ChatColor.GREEN + "Ce générateur a correctement été placé !");
                } else if (event.getClick().isRightClick()) {
                    if (GeneratorDataFile.getGenerators("Diamond").size() >= event.getSlot() + 2) {
                        Location generatorLocation = GeneratorDataFile.getGeneratorLocation("Diamond", GeneratorDataFile.getGenerators("Diamond").get(event.getSlot() + 1));
                        GeneratorDataFile.removeGenerator("Diamond", generatorLocation);
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Ce générateur a correctement été retiré !");
                    }
                }
                event.getWhoClicked().openInventory(GuiDiamondGen.Gui());
            }
            event.setCancelled(true);
        } else if (event.getView().getTitle().equalsIgnoreCase("Teams View")) {
            event.setCancelled(true);
        }
    }
}