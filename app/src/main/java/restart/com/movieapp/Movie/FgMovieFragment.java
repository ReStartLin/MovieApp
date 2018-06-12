package restart.com.movieapp.Movie;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import restart.com.movieapp.Movie.Presenter.MoviesPresenter;
import restart.com.movieapp.Movie.adapter.ItemMovieOnAdapter;
import restart.com.movieapp.Movie.adapter.ItemMovieTopAdapter;
import restart.com.movieapp.Movie.view.IMoviesView;
import restart.com.movieapp.R;
import restart.com.movieapp.bean.MovieBean;

public class FgMovieFragment extends Fragment implements IMoviesView {
    private MoviesPresenter moviesPresenter;
    private RecyclerView rv_movie_on;
    private SwipeRefreshLayout srl_movie;
    private ItemMovieOnAdapter movieOnAdapter;

    private ItemMovieTopAdapter movieTopAdapter;
    private RecyclerView rv_movie_top;


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
        moviesPresenter = new MoviesPresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_on);
        rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());

        rv_movie_top = view.findViewById(R.id.rv_movie_top);
        rv_movie_top.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        movieTopAdapter = new ItemMovieTopAdapter(getActivity());


        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovies("in_theaters");
        moviesPresenter.loadMovies("top250");
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies("in_theaters");
                moviesPresenter.loadMovies("top250");
            }
        });
    }

    @Override
    public void showMovies(final MovieBean movieBean) {
        movieOnAdapter.setData(movieBean.getSubjects());
        rv_movie_on.setAdapter(movieOnAdapter);

    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getContext(), "加载出错" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTop(MovieBean movieBean) {
        movieTopAdapter.setData(movieBean.getSubjects());
        rv_movie_top.setAdapter(movieTopAdapter);

    }
}
