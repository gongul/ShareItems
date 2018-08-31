package com.donggun.shareitems.module;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * 마인크래프트 스코어보드 팀 처리하는 모듈
 *
 * @author donggun
 * @version 1.0
 * @see https://hub.spigotmc.org/javadocs/spigot/overview-summary.html
 *
 */
public enum ShareTeamEnum{
    SET{ // 초기화
        @Override
        public void init(Scoreboard board, String[] strings) throws IllegalArgumentException,IllegalStateException{
            Team prevTeam = board.getTeam(strings[1]);

            if(prevTeam == null){ prevTeam.unregister();}

            Team team = board.registerNewTeam(strings[1]);

            addMembers(team,strings);
        }

    },ADD{  // 멤버 추가
        @Override
        public void init(Scoreboard board, String[] strings) throws IllegalArgumentException{
            Team team = board.getTeam(strings[1]);

            if(team == null){ team = board.registerNewTeam(strings[1]);}

            addMembers(team,strings);
        }
    },NULL{
        @Override
        public void init(Scoreboard board, String[] strings) {}
    };

    /**
     * enum 타입에 따른 팀 초기화 및 추가
     *
     * @param org.bukkit.scoreboard.Scoreboard board    마인크래프트 스코어보드
     * @param String[] strings  명령어 인자 값
     *
     * @return
     * */
    abstract public void init(Scoreboard board, String[] strings);


    /**
     * 팀 멤버 추가
     *
     * @param org.bukkit.scoreboard.Team team    마인크래프트 팀
     * @param String[] strings  명령어 인자 값(유저 리스트)
     *
     * @return
     * */
    protected void addMembers(Team team,String[] strings) {
        for(int i=2;i<strings.length;i++){
            team.addEntry(strings[i]);
        }
    }
}

