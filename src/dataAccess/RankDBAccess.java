package dataAccess;

import model.Rank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDBAccess implements RankDataAccess {
    private Connection connection;

    public RankDBAccess() {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public ArrayList<Rank> getAllRanks() {
        String sqlInstruction = "SELECT * FROM `rank`";
        ArrayList<Rank> rankList;
        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            rankList = new ArrayList<>();
            Rank rank;

            while(data.next()) {
                rank = new Rank(data.getInt("id"),
                        data.getString("label"),
                        data.getInt("creditLimit")
                );

                rankList.add(rank);
            }

            return rankList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
