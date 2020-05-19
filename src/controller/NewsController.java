package controller;

import business.NewsBusiness;
import exception.SQLManageException;
import model.News;

public class NewsController {
    private NewsBusiness newsBusiness;

    public NewsController() {
        setNewsBusiness(new NewsBusiness());
    }

    private void setNewsBusiness(NewsBusiness newsBusiness) {
        this.newsBusiness = newsBusiness;
    }

    public News getRandomNews() throws SQLManageException {
        return newsBusiness.getRandomNews();
    }
}
