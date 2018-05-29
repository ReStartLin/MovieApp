package restart.com.movieapp.News.Model;

import restart.com.movieapp.bean.NewsBean;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);

    void fail(String error);
}
