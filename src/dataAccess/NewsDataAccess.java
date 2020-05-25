package dataAccess;

import exception.*;
import model.News;
import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NewsDataAccess {
    News getRandomNews() throws SQLManageException;

    ArrayList<News> getAllNews() throws DataQueryException;

    News getNewsFromId(Integer id) throws NoRowSelected;

    void insertNews(News news) throws DataQueryException;

    boolean deleteNews(News news) throws DeletionExceiption;

    void updateNews(News news) throws UpdateException;
}
