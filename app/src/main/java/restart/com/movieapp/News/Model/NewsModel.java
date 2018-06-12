package restart.com.movieapp.News.Model;

import restart.com.movieapp.bean.NewsBean;
import restart.com.movieapp.http.Api;
import restart.com.movieapp.http.RetrofitHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/29.
 */

public class NewsModel implements INewsModel {
    @Override
    public void loadNEws(String hostType, int startPage, String id, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
        retrofitHelper.getNews(hostType, id, startPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        iOnLoadListener.success(newsBean);

                    }
                });
    }
}
