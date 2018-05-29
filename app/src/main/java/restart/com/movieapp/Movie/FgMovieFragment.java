package restart.com.movieapp.Movie;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import restart.com.movieapp.Movie.Presenter.MoviesPresenter;
import restart.com.movieapp.Movie.view.IMoviesView;
import restart.com.movieapp.R;
import restart.com.movieapp.bean.MovieBean;

public class FgMovieFragment extends Fragment implements IMoviesView{
    private MoviesPresenter presenter;
    private TextView tv_movie;
    private SwipeRefreshLayout srl_movies;

    public FgMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_movie = view.findViewById(R.id.tv_movies);
        presenter = new MoviesPresenter(this);
        srl_movies = view.findViewById(R.id.srl_movies);
        srl_movies.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        srl_movies.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMovies();
            }
        });
    }

    @Override
    public void showMovies(final MovieBean movieBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_movie.setText(movieBean.getSubjects().get(0).getTitle()+"       "+ movieBean.getSubjects().get(0).getDirectors().toString());
            }
        });
    }

    @Override
    public void hideDialog() {
        srl_movies.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movies.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
        tv_movie.setText("加载失败");
    }
}
