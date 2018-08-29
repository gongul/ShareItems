package com.donggun.shareitems.repo;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public enum ShareTeamEnum{
    SET{ // 초기화
        @Override
        public void init(Scoreboard board, String[] strings) throws IllegalArgumentException,IllegalStateException{
            Team prevTeam = board.getTeam(strings[1]);
            prevTeam.unregister();

            Team team = board.registerNewTeam(strings[1]);

            addMembers(team,strings);
        }


    },ADD{  // 멤버 추가
        @Override
        public void init(Scoreboard board, String[] strings) throws IllegalArgumentException{
            Team team = board.getTeam(strings[1]);

            addMembers(team,strings);
        }
    },NULL{
        @Override
        public void init(Scoreboard board, String[] strings) {}
    };

    abstract public void init(Scoreboard board, String[] strings);


    protected void addMembers(Team team,String[] strings){
        String[] playernames = new String[strings.length-2];
        System.arraycopy(strings,2,playernames,0,strings.length);

        for(String str : playernames){
            team.addEntry(Bukkit.getPlayer(str).getName());
        }
    }
}

