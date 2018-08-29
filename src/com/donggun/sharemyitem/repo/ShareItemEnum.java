package com.donggun.sharemyitem.repo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Set;

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
            Set<String> users = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(strings[1]).getEntries();
            commonInventory(items,users.toArray(new String[users.size()]));
        }

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {
            Set<String> users = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(strings[1]).getEntries();
            commonArrmors(items,users.toArray(new String[users.size()]));
        }
    },USERS{
        @Override
        public void setInventory(ItemStack[] items,String[] strings) {
            String[] users = new String[strings.length-2];
            commonInventory(items,users);
        }

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {
            String[] users = new String[strings.length-2];
            commonArrmors(items,users);
        }
    },NULL{
        @Override
        public void setInventory(ItemStack[] items,String[] strings) {}

        @Override
        public void setArrmors(ItemStack[] items,String[] strings) {}
    };

    abstract public void setInventory(ItemStack[] items,String[] strings);
    abstract public void setArrmors(ItemStack[] items,String[] strings);

    protected void commonInventory(ItemStack[] items,String[] users){
        for(String str : users){
            Player target = Bukkit.getPlayer(str);

            if(target == null){
                continue;
            }
            PlayerInventory targetInven = target.getInventory();
            targetInven.setContents(items);
        }
    }

    protected void commonArrmors(ItemStack[] items, String[] users){
        for(String str : users){
            Player target = Bukkit.getPlayer(str);

            if(target == null){
                continue;
            }
            PlayerInventory targetInven = target.getInventory();
            targetInven.setArmorContents(items);
        }
    }
}
