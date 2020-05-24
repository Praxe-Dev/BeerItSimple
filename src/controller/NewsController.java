package controller;

import business.NewsBusiness;
import exception.ConnectionException;
import exception.DeletionExceiption;
import exception.SQLManageException;
import model.News;

import java.sql.SQLException;
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

    public ArrayList<News> getAllNews() throws SQLManageException {
        return newsBusiness.getAllNews();
    }

    public News getNewsFromId(Integer id) throws SQLManageException {
        return newsBusiness.getNewsFromId(id);
    }

    public void insertNews(News news) throws SQLManageException {
        newsBusiness.insertNews(news);
    }

    public boolean deleteNews(News news) throws DeletionExceiption {
        return newsBusiness.deleteNews(news);
    }

    public void updateNews(News news) throws SQLManageException {
        newsBusiness.updateNews(news);
    }
}
