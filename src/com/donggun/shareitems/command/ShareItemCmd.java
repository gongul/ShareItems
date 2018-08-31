package com.donggun.shareitems.command;

import com.donggun.shareitems.module.ShareItemAccessEnum;
import com.donggun.shareitems.module.ShareItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 마인크래프트 명령어 처리 클래스(shareInventory,shareArrmors)
 *
 * @author donggun
 * @version 1.0
 * @see ShareCommonCmd
 * @see https://hub.spigotmc.org/javadocs/spigot/overview-summary.html
 *
 */
public class ShareItemCmd implements CommandExecutor, ShareCommonCmd {

    /**
     * 명령어 리스너 메소드 (shareInventory,shareArrmors)
     *
     * @param org.bukkit.command.CommandSender sender   명령어를 보낸 entity
     * @param org.bukkit.command.Command cmd    명령어 관련 객체
     * @param String s  명령어 네임?
     * @param String[] strings  명령어 인자 값
     *
     * @see ShareItemEnum
     *
     * @return boolean 명령어 실행 여부
     * */
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
        }catch(ArrayIndexOutOfBoundsException indexE){// or teamname을 지정해주세요로 변경(enum TEAM에서 에러)
            sender.sendMessage(ChatColor.GREEN+"User not specified to share item or Specify a team name");
        }catch(NullPointerException nullE){ // or team이 없는 팀입니다.
            sender.sendMessage(ChatColor.GREEN+"Offline user or non-existent user or Team doesn't exist");
        } catch (Exception e){
            sender.sendMessage(ChatColor.GREEN+"Unknown error occurred");
        }

        return true;
    }

    /**
     *
     * shareTeam 명령어 실행 타입 체크하는 메소드
     *
     * @param String string 명령어 실행 타입명
     *
     * @see ShareItemAccessEnum
     *
     * @return String 명령어 타입명
     * */
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
