package match;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.Util;
import player.PlayerDto;
import player.PlayerList;

public class ResultList {

    // 경기 기록 리스트 출력
    public static List<MatchDto> getMatchResultList() {
        List<MatchDto> matchResultList = new ArrayList<>();
        try {
            Util.init();

            ResultSet rs = Util.getResult("select * from match");

            while (rs.next()) {
                String matchName = rs.getString("matchName");
                Date matchDate = rs.getDate("matchDate");
                java.util.Date utilMatchDate = new java.util.Date(matchDate.getTime());
                String opposing = rs.getString("opposing");
                int ourScore = rs.getInt("ourScore");
                int oppScore = rs.getInt("oppScore");
                int yellowCard = rs.getInt("yellowCard");
                int redCard = rs.getInt("redCard");
                String mvp = rs.getString("mvp");

                MatchDto matchDto = new MatchDto(matchName, utilMatchDate, opposing, ourScore, oppScore, yellowCard, redCard, mvp);
                matchResultList.add(matchDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchResultList;
    }

    // mvpName 값을 매개변수로 받아 해당 플레이어 모델 반환
    public PlayerDto getMvpPlayer(String mvpName) {
        List<PlayerDto> playerList = PlayerList.getPlayerList();
        for(PlayerDto player : playerList) {
            if(player.getName().equals(mvpName)) {
                return player;
            }
        } return null;
    }
}
