package dataAccess;

import exception.SQLManageException;
import model.News;

public interface NewsDataAccess {
    News getRandomNews() throws SQLManageException;
}
