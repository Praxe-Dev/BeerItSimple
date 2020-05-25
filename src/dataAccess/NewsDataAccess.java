package dataAccess;

import exception.*;
import model.News;

import java.util.ArrayList;

public interface NewsDataAccess {
    News getRandomNews() throws ThreadNewsException;

    ArrayList<News> getAllNews() throws DataQueryException;

    News getNewsFromId(Integer id) throws NoRowSelected;

    void insertNews(News news) throws DataQueryException;

    boolean deleteNews(News news) throws DeletionException;

    void updateNews(News news) throws UpdateException;
}
