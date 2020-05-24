package dataAccess;

import exception.DataQueryException;
import exception.DeletionExceiption;
import exception.SQLManageException;
import model.News;
import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NewsDataAccess {
    News getRandomNews() throws SQLManageException;

    ArrayList<News> getAllNews() throws DataQueryException;

    News getNewsFromId(Integer id) throws SQLManageException;

    void insertNews(News news) throws SQLManageException;

    boolean deleteNews(News news) throws DeletionExceiption;

    void updateNews(News news) throws SQLManageException;
}
