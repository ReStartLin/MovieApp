package restart.com.movieapp.News.Presenter;

import restart.com.movieapp.News.view.INewsView;
import restart.com.movieapp.News.FgNewsFragment;
import restart.com.movieapp.News.Model.INewsModel;
import restart.com.movieapp.News.Model.IOnLoadListener;
import restart.com.movieapp.News.Model.NewsModel;
import restart.com.movieapp.bean.NewsBean;
import restart.com.movieapp.http.Api;

/**
 * Created by Administrator on 2018/5/29.
 */

public class NewsPresenter implements INewsPresenter,IOnLoadListener {
    private INewsModel iNewsModel;
    private INewsView iNewsView;

    public NewsPresenter(INewsView iNewsView) {
        this.iNewsView = iNewsView;
        this.iNewsModel = new NewsModel();
    }

    @Override
    public void success(NewsBean newsBean) {
        iNewsView.hideDialog();
        if (newsBean != null) {
            iNewsView.showNews(newsBean);

        }
    }

    @Override
    public void fail(String error) {
        iNewsView.hideDialog();
        iNewsView.showErrorMsg(error);
    }

    @Override
    public void loadNews(int type, int startPage) {
        iNewsView.showDialog();
        switch (type) {
            case FgNewsFragment.NEWS_TYPE_TOP:
                iNewsModel.loadNEws("headline", startPage, Api.HEADLINE_ID, this);
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                iNewsModel.loadNEws("list", startPage, Api.NBA_ID, this);
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                iNewsModel.loadNEws("list", startPage, Api.JOKE_ID, this);
                break;
        }
    }
}
