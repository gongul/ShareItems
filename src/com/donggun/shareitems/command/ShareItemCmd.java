package com.donggun.shareitems.command;

import com.donggun.shareitems.repo.ShareItemAccessEnum;
import com.donggun.shareitems.repo.ShareItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShareItemCmd implements CommandExecutor,ShareCommonCmd{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings){

        if (sender instanceof Player){
            if(!sender.isOp()){
                return false;
            }
        }
        if(strings.length == 0){
            return false;
        }

        String enumType = commandCheck(strings[0]);

        try{
            if((cmd.getName().equalsIgnoreCase("shareInventory"))) {
                ShareItemEnum.valueOf(enumType).setInventory(Bukkit.getPlayer(strings[1]).getInventory().getContents(),strings);
            }

            if((cmd.getName().equalsIgnoreCase("shareArrmors"))) {
                ShareItemEnum.valueOf(enumType).setArrmors(Bukkit.getPlayer(strings[1]).getInventory().getArmorContents(),strings);
            }
        }catch(ArrayIndexOutOfBoundsException indexE){
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"user not specified to share item");
        }catch(NullPointerException nullE){
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"offline user or non-existent user");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Unknown error occurred");
            return false;
        }

        return true;
    }

    @Override
    public String commandCheck(String string) {
        String enumType = "NULL";

        for(String str : ShareItemAccessEnum.ALLOW_COMMAND.getStr()){
            if(str.equalsIgnoreCase(string)){
                enumType = str.toUpperCase();
            }
        }

        return enumType;
    }

}
