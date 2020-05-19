package dataAccess;

import exception.SQLManageException;
import model.News;

import java.sql.*;
import java.util.GregorianCalendar;

public class NewsDBAccess implements NewsDataAccess {
    private Connection connection;

    public NewsDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }
    public News getRandomNews() throws SQLManageException{
        News randomNews = null;
        String sqlInstruction = "SELECT * FROM news WHERE startingDate <= ? AND endDate >= ? ORDER BY RAND() LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ResultSet data = preparedStatement.executeQuery();

            if(data.next()){
                GregorianCalendar startingDateGC = null;
                GregorianCalendar endDateGC = null;

                Date startingDate = data.getDate("startingDate");
                if (startingDate != null) {
                    startingDateGC = new GregorianCalendar();
                    startingDateGC.setTime(startingDate);
                }

                Date endDate = data.getDate("endDate");
                if (endDate != null) {
                    endDateGC = new GregorianCalendar();
                    endDateGC.setTime(endDate);
                }

                randomNews = new News(
                        data.getInt("id"),
                        data.getString("title"),
                        data.getString("content"),
                        startingDateGC,
                        endDateGC,
                        data.getInt("employeeEntityId")
                );
            }
        } catch(SQLException e){
            throw new SQLManageException(e);
        }
        return randomNews;
    }
}
