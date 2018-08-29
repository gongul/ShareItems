package com.donggun.sharemyitem.command;

import com.donggun.sharemyitem.repo.ShareTeamAccessEnum;
import com.donggun.sharemyitem.repo.ShareTeamEnum;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShareTeamCmd implements CommandExecutor {
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

        String enumType = "NULL";

        for(String str : ShareTeamAccessEnum.ALLOW_COMMAND.getStr()){
            if(str.equalsIgnoreCase(strings[0])){
                  enumType = str.toUpperCase();
            }
        }

        ShareTeamEnum code = ShareTeamEnum.valueOf(enumType);
        code.init(Bukkit.getScoreboardManager().getMainScoreboard(),strings);

        return true;
    }
}
