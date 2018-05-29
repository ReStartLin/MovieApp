package restart.com.movieapp.http;

import restart.com.movieapp.bean.MovieBean;
import restart.com.movieapp.bean.NewsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface RetrofitService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Call<NewsBean> getNews(@Path("type") String type,
                           @Path("id")String id,
                           @Path("startPage") int startPage);
    @GET("v2/movie/in_theaters")
    Call<MovieBean> getMovie();
}
