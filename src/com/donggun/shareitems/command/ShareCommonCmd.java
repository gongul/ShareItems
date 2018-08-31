package com.donggun.shareitems.command;

/**
 * 마인크래프트 명령어 리스너 공동 모듈을 가지고 있는 인터페이스
 *
 * @author donggun
 * @version 1.0
 * @see ShareCommonCmd
 * @see https://hub.spigotmc.org/javadocs/spigot/overview-summary.html
 *
 */
public interface ShareCommonCmd {
    /**
     * 명령어 실행 타입 체크하는 메소드
     *
     * @param String string 명령어 실행 타입명
     *
     * @return String 명령어 타입명
     * */
    String commandCheck(String str);

}
