package com.donggun.shareitems.command;

import com.donggun.shareitems.repo.ShareItemAccessEnum;
import com.donggun.shareitems.repo.ShareItemEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShareItemCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("only use player.");
            return false;
        }

        Player player = (Player) sender;

        if(!player.isOp()){
            return false;
        }

        String enumType = "NULL";

        for(String str : ShareItemAccessEnum.ALLOW_COMMAND.getStr()){
            if(str.equalsIgnoreCase(strings[0])){
                enumType = str.toUpperCase();
            }
        }

        // /shareInventory users target targets... /shareInventory all target /shareInventory team target 갑바 공유 시도 필요하기 떄문에 enum 사용
        if((cmd.getName().equalsIgnoreCase("shareInventory"))) {
            ShareItemEnum.valueOf(enumType).setInventory(player.getInventory().getContents(),strings);
        }

        if((cmd.getName().equalsIgnoreCase("shareArrmors"))) {
            ShareItemEnum.valueOf(enumType).setArrmors(player.getInventory().getArmorContents(),strings);
        }



        return true;
    }


}
