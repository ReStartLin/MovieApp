package restart.com.movieapp.http;

import restart.com.movieapp.bean.MovieBean;
import restart.com.movieapp.bean.NewsBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface RetrofitService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<NewsBean> getNews(@Path("type") String type,
                                 @Path("id")String id,
                                 @Path("startPage") int startPage);
    @GET("v2/movie/{total}")
    Observable<MovieBean> getMovie(@Path("total")String total);
}
