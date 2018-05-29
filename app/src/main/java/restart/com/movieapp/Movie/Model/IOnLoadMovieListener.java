package restart.com.movieapp.Movie.Model;

import restart.com.movieapp.bean.MovieBean;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface IOnLoadMovieListener {
    void success(MovieBean movieBean);

    void fail(String error);
}
