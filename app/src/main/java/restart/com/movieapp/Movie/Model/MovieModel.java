package restart.com.movieapp.Movie.Model;

import restart.com.movieapp.bean.MovieBean;
import restart.com.movieapp.http.Api;
import restart.com.movieapp.http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/29.
 */

public class MovieModel implements IMovieModel{
    @Override
    public void loadMovies(final IOnLoadMovieListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovie().enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, final Response<MovieBean> response) {
                if (response.isSuccessful()) {
                    iOnLoadListener.success(response.body());

                } else {
                    iOnLoadListener.fail("");
                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });

    }
}
