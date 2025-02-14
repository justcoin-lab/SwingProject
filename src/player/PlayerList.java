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
        return data.get(row).get(col);
    }

    public void setData() {
        Util.init();
        sql = "SELECT * FROM player";

        ResultSet rs = Util.getResult(sql);

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

    }

    public static String[][] getPlayers2() {

        try {
            Util.init();
            ResultSet rs = Util.getResult("SELECT * FROM player");

            ArrayList<String[]> playerList = new ArrayList<>();

            while (rs.next()) {

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

            }
            return playerList.toArray(new String[playerList.size()][]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0][0];

        }

    }

}
