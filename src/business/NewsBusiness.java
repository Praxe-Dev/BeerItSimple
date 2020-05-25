package business;

import dataAccess.NewsDBAccess;
import dataAccess.NewsDataAccess;
import exception.*;
import model.News;

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

    public ArrayList<News> getAllNews() throws DataQueryException {
        return dao.getAllNews();
    }

    public News getNewsFromId(Integer id) throws NoRowSelected {
        return dao.getNewsFromId(id);
    }

    public void insertNews(News news) throws DataQueryException {
        dao.insertNews(news);
    }

    public boolean deleteNews(News news) throws DeletionException {
        return dao.deleteNews(news);
    }

    public void updateNews(News news) throws UpdateException {
        dao.updateNews(news);
    }
}
