package player;

import db.Util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    // 플레이어 리스트 반환
    public static List<PlayerDto> getPlayerList() {
        List<PlayerDto> playerList = new ArrayList<>();
        try {
            Util.init();

            ResultSet rs = Util.getResult("select * from player");

            while (rs.next()) {
                int pn = rs.getInt("pn");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                String position = rs.getString("position");
                String injury = rs.getString("injury");
                String roster = rs.getString("roster");

                PlayerDto player = new PlayerDto(pn, name, age, height, weight, position, injury, roster);
                playerList.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerList;
    }
}
