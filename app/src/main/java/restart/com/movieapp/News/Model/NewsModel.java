package restart.com.movieapp.News.Model;

import restart.com.movieapp.bean.NewsBean;
import restart.com.movieapp.http.Api;
import restart.com.movieapp.http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/29.
 */

public class NewsModel implements INewsModel {
    @Override
    public void loadNEws(String hostType, int startPage, String id, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
        retrofitHelper.getNews(hostType, id, startPage).enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                if (response.isSuccessful()) {
                    iOnLoadListener.success(response.body());

                } else {
                    iOnLoadListener.fail("");
                }

            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                iOnLoadListener.fail(t.toString());
            }
        });
    }
}
