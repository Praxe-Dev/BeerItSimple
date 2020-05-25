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

    public News getRandomNews() throws ThreadNewsException {
        return dao.getRandomNews();
    }

    public ArrayList<News> getAllNews() throws DataQueryException {
        return dao.getAllNews();
    }

    public News getNewsFromId(Integer id) throws NoRowSelected, NullObjectException {
        if (id == null)
            throw new NullObjectException(id.getClass().getName());

        return dao.getNewsFromId(id);
    }

    public void insertNews(News news) throws DataQueryException, NullObjectException {
        if (news == null)
            throw new NullObjectException(news.getClass().getName());

        dao.insertNews(news);
    }

    public boolean deleteNews(News news) throws DeletionException, NullObjectException {
        if (news == null)
            throw new NullObjectException(news.getClass().getName());

        return dao.deleteNews(news);
    }

    public void updateNews(News news) throws UpdateException, NullObjectException {
        if (news == null)
            throw new NullObjectException(news.getClass().getName());

        dao.updateNews(news);
    }
}
