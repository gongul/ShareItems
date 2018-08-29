package com.donggun.sharemyitem;

import com.donggun.sharemyitem.command.ShareItemCmd;
import com.donggun.sharemyitem.command.ShareTeamCmd;
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

        System.out.println("--------- share my items plugin ---------");
        System.out.println("mady by donggun");
        System.out.println("plugin on");

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
