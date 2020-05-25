package view.news;

import controller.NewsController;
import exception.ConnectionException;
import exception.SQLManageException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import model.News;
import view.MainView;

public class ThreadNews extends Task<Integer> {
    private MainView view;
    private NewsController newsController;

    public ThreadNews(MainView view){
        this.view = view;
        try {
            newsController = new NewsController();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer call() throws Exception {
        int i = 0;
        while(!isCancelled()){
            Platform.runLater(()->{
                try {
                    //Get le news random et set la news
                    News news = newsController.getRandomNews();
                    view.setNews(news);
                } catch (SQLManageException e) {
                    e.showMessage();
                }
            });

            Thread.sleep(10000);

            final int it = i;
            updateValue(it);
            i++;
        }
        return i;
    }
}
