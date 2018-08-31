package com.donggun.shareitems.module;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Team;

import java.util.Set;

/**
 * 마인크래프트 인벤토리,아머 공유 모듈
 *
 * @author donggun
 * @version 1.0
 * @see https://hub.spigotmc.org/javadocs/spigot/overview-summary.html
 *
 */
public enum ShareItemEnum {
    ALL {
        @Override
        public void setInventory(ItemStack[] items,String[] strings){
            for(Player target : Bukkit.getOnlinePlayers()){
                PlayerInventory targetInven = target.getInventory();
                targetInven.setContents(items);
            }
        }

        @Override
        public void setArrmors(ItemStack[] items,String[] strings){
            for(Player target : Bukkit.getOnlinePlayers()){
                PlayerInventory targetInven = target.getInventory();
                targetInven.setArmorContents(items);
            }
        }
    },TEAM{
        @Override
        public void setInventory(ItemStack[] items,String[] strings) {
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(strings[2]);
            Set<String> users = team.getEntries();

            commonInventory(items,users.toArray(new String[users.size()]),0);
        }

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {// teamname 2 그리고 버그 잡는거 떄문에 팀을 나눴던 기억이 있다
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(strings[2]);
            Set<String> users = team.getEntries();

            commonArrmors(items,users.toArray(new String[users.size()]),0);
        }
    },USERS{
        @Override
        public void setInventory(ItemStack[] items,String[] strings) {
            commonInventory(items,strings,2);
        }

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {
            commonArrmors(items,strings,2);
        }
    },NULL{
        @Override
        public void setInventory(ItemStack[] items,String[] strings) {}

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {}
    };


    /**
     * 마인크래프트 인벤토리 공유 메소드 enum 타입에 따라 처리
     *
     * @param org.bukkit.inventory.ItemStack[] items    공유 할 아이템 배열
     * @param String[] strings  명령어 인자 값
     *
     * @see ShareItemEnum
     *
     * @return
     * */
    abstract public void setInventory(ItemStack[] items,String[] strings);
    /**
     * 마인크래프트 아머 공유 메소드 enum 타입에 따라 처리
     *
     * @param org.bukkit.inventory.ItemStack[] items    공유 할 아이템 배열
     * @param String[] strings  명령어 인자 값
     *
     * @see ShareItemEnum
     *
     * @return
     * */
    abstract public void setArrmors(ItemStack[] items,String[] strings);


    /**
     *
     * 유저에게 인벤토리를 공유하는 메소드
     *
     * @param org.bukkit.inventory.ItemStack[] items    공유 할 아이템 배열
     * @param String[] strings  명령어 인자 값(유저 리스트)
     * @param int index 명령어 인자 값이 유저리스트 뿐만이 아닌 다른 인자 값도 처리하기 위한 파라미터
     *
     * @return
     * */
    protected void commonInventory(ItemStack[] items,String[] strings,int index){
        for(int i=index;i<strings.length;i++){
            Player target = Bukkit.getPlayer(strings[i]);

            if(target == null){
                continue;
            }

            PlayerInventory targetInven = target.getInventory();
            targetInven.setContents(items);
        }
    }

    /**
     *
     * 유저에게 아머를 공유하는 메소드
     *
     * @param org.bukkit.inventory.ItemStack[] items    공유 할 아이템 배열
     * @param String[] strings  명령어 인자 값(유저 리스트)
     * @param int index 명령어 인자 값이 유저리스트 뿐만이 아닌 다른 인자 값도 처리하기 위한 파라미터
     *
     * @return
     * */
    protected void commonArrmors(ItemStack[] items, String[] strings,int index){
        for(int i=index;i<strings.length;i++){
            Player target = Bukkit.getPlayer(strings[i]);

            if(target == null){
                continue;
            }

            PlayerInventory targetInven = target.getInventory();
            targetInven.setArmorContents(items);
        }
    }
}
