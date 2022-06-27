package fr.birdo.bedwarsplugin.guis;

import fr.birdo.bedwarsplugin.BedwarsPlugin;
import fr.birdo.bedwarsplugin.data.PlayerDataFile;
import fr.birdo.bedwarsplugin.data.TeamDataFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.List;

public class GuiScoreboard {

    public static void getScoreboard(Player player) {

        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("bedwars", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "BED WARS");

        int j = 5;
        List<String> id = Arrays.asList("R", "B", "G", "Y", "A", "W", "P", "G");
        for (int i = 0; i < 8; i++) {
            if (!TeamDataFile.getPlayers(BedwarsPlugin.teams.get(i)).isEmpty()) {
                Score team = o.getScore(BedwarsPlugin.chatcolors.get(i) + id.get(i) + " " + ChatColor.WHITE + BedwarsPlugin.teams.get(i) + ": " + getTeamStatus(BedwarsPlugin.teams.get(i)));
                team.setScore(j);
                j++;
            }
        }

        for (int i : Arrays.asList(4, j + 1, j + 3)) {
            Score interline = o.getScore("" + ChatColor.COLOR_CHAR + i / 2);
            interline.setScore(i);
        }

        Score event = o.getScore(ChatColor.WHITE + "Diamond II in " + ChatColor.GREEN + "5:59");
        event.setScore(j + 2);

        Score kills = o.getScore(ChatColor.WHITE + "Kills: " + ChatColor.GREEN + PlayerDataFile.getKills(player));
        kills.setScore(3);

        Score final_kills = o.getScore(ChatColor.WHITE + "Final Kills: " + ChatColor.GREEN + PlayerDataFile.getFinalKills(player));
        final_kills.setScore(2);

        Score beds_broken = o.getScore(ChatColor.WHITE + "Beds broken: " + ChatColor.GREEN + PlayerDataFile.getBeds(player));
        beds_broken.setScore(1);

        player.setScoreboard(b);
    }

    private static String getTeamStatus(String team) {
        if (!TeamDataFile.hasBed(team))
            if (TeamDataFile.getLivePlayers(team).size() > 0)
                return ChatColor.GREEN + "" + TeamDataFile.getLivePlayers(team).size();
            else
                return ChatColor.RED + "✘";
        else
            return ChatColor.GREEN + "✔";
    }
}
