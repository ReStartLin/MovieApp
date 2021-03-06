package restart.com.movieapp.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import restart.com.movieapp.bean.MovieBean;
import restart.com.movieapp.bean.NewsBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/29.
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private RetrofitService retrofitService;

    public RetrofitHelper(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public Observable<NewsBean> getNews(String type, String id, int startPage) {
        return retrofitService.getNews(type, id, startPage);

    }
    public Observable<MovieBean> getMovies(String total) {
        return retrofitService.getMovie(total);
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(3000, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
