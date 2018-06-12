package restart.com.movieapp.Movie.Model;

import restart.com.movieapp.bean.MovieBean;
import restart.com.movieapp.http.Api;
import restart.com.movieapp.http.RetrofitHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/29.
 */

public class MovieModel implements IMovieModel{
    @Override
    public void loadMovies(String total,final IOnLoadMovieListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovies(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        iOnLoadListener.success(movieBean);
                    }
                });

    }
}
