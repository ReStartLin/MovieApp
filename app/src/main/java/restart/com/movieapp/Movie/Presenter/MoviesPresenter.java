package restart.com.movieapp.Movie.Presenter;

import restart.com.movieapp.Movie.Model.IMovieModel;
import restart.com.movieapp.Movie.Model.IOnLoadMovieListener;
import restart.com.movieapp.Movie.Model.MovieModel;
import restart.com.movieapp.Movie.view.IMoviesView;
import restart.com.movieapp.bean.MovieBean;

/**
 * Created by Administrator on 2018/5/29.
 */

public class MoviesPresenter implements IMoviesPresenter,IOnLoadMovieListener {
    private IMovieModel movieModel;
    private IMoviesView moviesView;

    public MoviesPresenter(IMoviesView moviesView) {
        this.moviesView = moviesView;
        this.movieModel = new MovieModel();
    }

    @Override
    public void loadMovies(String total) {
        moviesView.showDialog();
        movieModel.loadMovies(total,this);
    }

    @Override
    public void success(MovieBean movieBean) {
        moviesView.hideDialog();
        if (movieBean != null) {
            if (movieBean.getTotal() == 250) {
                moviesView.showTop(movieBean);
            }else {
                moviesView.showMovies(movieBean);
            }

        }

    }

    @Override
    public void fail(String error) {
        moviesView.hideDialog();
        moviesView.showErrorMsg(error);
    }
}
