package com.donggun.shareitems.command;

import com.donggun.shareitems.module.ShareTeamAccessEnum;
import com.donggun.shareitems.module.ShareTeamEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * 마인크래프트 명령어 처리 클래스(shareTeam)
 *
 * @author donggun
 * @version 1.0
 * @see ShareCommonCmd
 * @see https://hub.spigotmc.org/javadocs/spigot/overview-summary.html
 *
 */
public class ShareTeamCmd implements CommandExecutor, ShareCommonCmd {

    /**
     * 명령어 리스너 메소드 (shareTeam)
     *
     * @param org.bukkit.command.CommandSender sender   명령어를 보낸 entity
     * @param org.bukkit.command.Command cmd    명령어 관련 객체
     * @param String s  명령어 네임?
     * @param String[] strings  명령어 인자 값
     *
     * @see ShareTeamEnum
     *
     * @return boolean 명령어 실행 여부
     * */
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

        String enumType = commandCheck(strings[0]);

        try{
            ShareTeamEnum.valueOf(enumType).init(Bukkit.getScoreboardManager().getMainScoreboard(),strings);

            return true;
        }catch(NullPointerException ne){
            sender.sendMessage(ChatColor.GREEN + "This team doesn't exist");
        }catch(IllegalArgumentException ie){
            sender.sendMessage(ChatColor.GREEN + "Please specify a team name");
        }catch (ArrayIndexOutOfBoundsException ae){
            sender.sendMessage(ChatColor.GREEN + "Please specify a team name");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.GREEN + "Unknown error occurred");
        }

        return false;
    }


    /**
     *
     * shareTeam 명령어 실행 타입 체크하는 메소드
     *
     * @param String string 명령어 실행 타입명
     *
     * @see ShareTeamAccessEnum
     *
     * @return String 명령어 타입명
     * */
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
