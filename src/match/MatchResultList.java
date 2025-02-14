package match;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.Util;

public class MatchResultList {

    public static List<MatchDto> getMatchResultList() {
        List<MatchDto> matchResultList = new ArrayList<>();
        try {
            Util.init();

            ResultSet rs = Util.getResult("select * from match");

            while (rs.next()) {
                String matchName = rs.getString("matchName");
                String matchDate = rs.getString("matchDate");
                String opposing = rs.getString("opposing");
                int ourScore = rs.getInt("ourScore");
                int oppScore = rs.getInt("oppScore");
                int yellowCard = rs.getInt("yellowCard");
                int redCard = rs.getInt("redCard");
                int mvp = rs.getInt("mvp");

                MatchDto matchDto = new MatchDto(matchName, matchDate, opposing, ourScore, oppScore, yellowCard, redCard, mvp);
                matchResultList.add(matchDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchResultList;
    }

}
