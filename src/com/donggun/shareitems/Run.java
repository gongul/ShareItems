package com.donggun.shareitems;

import com.donggun.shareitems.command.ShareItemCmd;
import com.donggun.shareitems.command.ShareTeamCmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Run extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        ShareItemCmd shareInv = new ShareItemCmd();
        ShareTeamCmd shareTeam = new ShareTeamCmd();

        getCommand("shareArrmors").setExecutor(shareInv);
        getCommand("shareInventory").setExecutor(shareInv);
        getCommand("shareTeam").setExecutor(shareTeam);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"--------- share items plugin ---------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Made By DongGun");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Plugin On");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"--------- share items plugin ---------");

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
