package business;

import dataAccess.NewsDBAccess;
import dataAccess.NewsDataAccess;
import exception.ConnectionException;
import exception.DeletionExceiption;
import exception.SQLManageException;
import model.News;

import java.sql.SQLException;
import java.util.ArrayList;

public class NewsBusiness {
    private NewsDataAccess dao;

    public NewsBusiness() throws ConnectionException {
        setDao(new NewsDBAccess());
    }

    private void setDao(NewsDBAccess newsDBAccess) {
        dao = newsDBAccess;
    }

    public News getRandomNews() throws SQLManageException {
        return dao.getRandomNews();
    }

    public ArrayList<News> getAllNews() throws SQLManageException {
        return dao.getAllNews();
    }

    public News getNewsFromId(Integer id) throws SQLManageException {
        return dao.getNewsFromId(id);
    }

    public void insertNews(News news) throws SQLManageException {
        dao.insertNews(news);
    }

    public boolean deleteNews(News news) throws DeletionExceiption {
        return dao.deleteNews(news);
    }

    public void updateNews(News news) throws SQLManageException {
        dao.updateNews(news);
    }
}
