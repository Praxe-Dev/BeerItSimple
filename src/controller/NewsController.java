package controller;

import business.NewsBusiness;
import exception.*;
import model.News;

import java.util.ArrayList;

public class NewsController {
    private NewsBusiness newsBusiness;

    public NewsController() throws ConnectionException {
        setNewsBusiness(new NewsBusiness());
    }

    private void setNewsBusiness(NewsBusiness newsBusiness) {
        this.newsBusiness = newsBusiness;
    }

    public News getRandomNews() throws SQLManageException {
        return newsBusiness.getRandomNews();
    }

    public ArrayList<News> getAllNews() throws DataQueryException {
        return newsBusiness.getAllNews();
    }

    public News getNewsFromId(Integer id) throws NoRowSelected, NullObjectException {
        return newsBusiness.getNewsFromId(id);
    }

    public void insertNews(News news) throws DataQueryException, NullObjectException {
        newsBusiness.insertNews(news);
    }

    public boolean deleteNews(News news) throws DeletionException, NullObjectException {
        return newsBusiness.deleteNews(news);
    }

    public void updateNews(News news) throws UpdateException, NullObjectException {
        newsBusiness.updateNews(news);
    }
}
