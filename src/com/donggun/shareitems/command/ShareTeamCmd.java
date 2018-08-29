package com.donggun.shareitems.command;

import com.donggun.shareitems.repo.ShareTeamAccessEnum;
import com.donggun.shareitems.repo.ShareTeamEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShareTeamCmd implements CommandExecutor,ShareCommonCmd{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if (sender instanceof Player){
            if(!sender.isOp()){
                return false;
            }
        }
        if(!(cmd.getName().equalsIgnoreCase("shareTeam"))) {
            return false;
        }
        if(strings.length == 0){
            return false;
        }

        String enumType = commandCheck(strings[1]);

        try{
            ShareTeamEnum.valueOf(enumType).init(Bukkit.getScoreboardManager().getMainScoreboard(),strings);
        }catch (Exception e){
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Unknown error occurred");
            return false;
        }

        return true;
    }

    @Override
    public String commandCheck(String string) {
        String enumType = "NULL";

        for(String str : ShareTeamAccessEnum.ALLOW_COMMAND.getStr()){
            if(str.equalsIgnoreCase(string)){
                enumType = str.toUpperCase();
            }
        }

        return enumType;
    }
}
