package player;

import db.Util;

import javax.swing.table.AbstractTableModel;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class PlayerList extends AbstractTableModel {

    ArrayList<ArrayList<Object>> data = new ArrayList<>();  //데이타 행을 가져오기 위한것, 다양한 타입이기에-> Object
    ArrayList<String> colName = new ArrayList<>(); //데이타 열을 가져오기 위한것, 컬럼의 제목은 문자형만 있어서
    //그리고 2차원 배열로 저장하기 위해
    private String sql, pn, name, age, height, weight, position, injury, roster;


    @Override
    public int getRowCount() { //row 갯수 가져오기

        return data.size();
    }

    @Override
    public int getColumnCount() {  //컬럼 갯수 가져오기

        return colName.size();
    }

    @Override
    public Object getValueAt(int row, int col) { //row 와 컬럼 데이타 가져오기
        Object value = data.get(row).get(col);

        // "선발여부" 컬럼(마지막 컬럼)에서 값이 "s"이면 "선발"로 변경
        if (col == 7 && "s".equals(value)) {
            return "선발";
        }else if(col == 7 && "m".equals(value)) {
            return "후보";
        }else if(col == 6 && "0".equals(value)) {
            return "없음";
        }else if(col == 6 && "1".equals(value)) {
            return "부상중";
        }

        return value;
        //return data.get(row).get(col);
    }

    @Override
    public String getColumnName(int column) {  //컬럼 이름 반환

        return colName.get(column);
    }



    public void setData() {
        Util.init();
        sql = "SELECT * FROM player";

        ResultSet rs = Util.getResult(sql);
import java.util.List;

        // 기존 데이터 클리어
        data.clear();
        colName.clear();

        String[] list = {"백넘버", "이름", "나이", "키", "몸무게", "포지션", "부상여부", "선발여부"};

        //컬렉션을 활용 해서 컬럼 이름 넣기
        Collections.addAll(colName, list);
        //colName.addAll(Arrays.asList(list)); ArrayList 활용도 가능


        try {
            while (rs.next()) {
                ArrayList<Object> row = new ArrayList<>();

                row.add(rs.getInt("pn"));
                row.add(rs.getString("name"));
                row.add(rs.getInt("age"));
                row.add(rs.getInt("height"));
                row.add(rs.getInt("weight"));
                row.add(rs.getString("position"));
                row.add(rs.getString("injury"));
                row.add(rs.getString("roster"));

                data.add(row);

            }

        } catch (SQLException e) {
            System.out.println("가져온 데이터 저장 오류");
            e.printStackTrace();
        }
        // 모든 데이터를 로드한 후 한 번만 호출
        fireTableDataChanged();

    }

    public static String[][] getPlayers2() {
public class PlayerList {

    // 플레이어 리스트 반환
    public static List<PlayerDto> getPlayerList() {
        List<PlayerDto> playerList = new ArrayList<>();
        try {
            Util.init();
            ResultSet rs = Util.getResult("SELECT * FROM player");

            ArrayList<String[]> playerList = new ArrayList<>();
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

                playerList.add(new String[]{
                        String.valueOf(rs.getInt("pn")),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("age")),
                        String.valueOf(rs.getInt("height")),
                        String.valueOf(rs.getInt("weight")),
                        rs.getString("position"),
                        rs.getString("injury"),
                        rs.getString("roster"),
                });

                PlayerDto player = new PlayerDto(pn, name, age, height, weight, position, injury, roster);
                playerList.add(player);
            }
            return playerList.toArray(new String[playerList.size()][]);
        } catch (SQLException e) {
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0][0];

        }

        return playerList;
    }

}
