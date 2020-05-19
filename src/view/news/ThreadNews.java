package view.news;

import controller.NewsController;
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
        newsController = new NewsController();
    }

    @Override
    protected Integer call() throws Exception {
        int i = 0;
        while(true){
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

            if (isCancelled()) {
                break;
            }

            final int it = i;
            updateValue(it);
            i++;
        }
        return i;
    }
}
