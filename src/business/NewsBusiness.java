package business;

import dataAccess.NewsDBAccess;
import dataAccess.NewsDataAccess;
import exception.SQLManageException;
import model.News;

public class NewsBusiness {
    private NewsDataAccess dao;

    public NewsBusiness(){
        setDao(new NewsDBAccess());
    }

    private void setDao(NewsDBAccess newsDBAccess) {
        dao = newsDBAccess;
    }

    public News getRandomNews() throws SQLManageException {
        return dao.getRandomNews();
    }
}
