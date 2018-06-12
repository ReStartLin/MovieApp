package restart.com.movieapp.Movie.view;

import restart.com.movieapp.bean.MovieBean;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface IMoviesView {
    void showMovies(MovieBean movieBean);

    void hideDialog();

    void showDialog();

    void showErrorMsg(String error);

    void showTop(MovieBean movieBean);
}
