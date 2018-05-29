package restart.com.movieapp.News.view;

import restart.com.movieapp.bean.NewsBean;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface INewsView {
    void showNews(NewsBean newsBean);

    void hideDialog();

    void showDialog();

    void showErrorMsg(String error);
}
