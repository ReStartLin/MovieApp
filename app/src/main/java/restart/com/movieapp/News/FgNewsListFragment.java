package restart.com.movieapp.News;


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
import android.widget.TextView;

import java.util.List;

import restart.com.movieapp.News.Presenter.NewsPresenter;
import restart.com.movieapp.News.view.INewsView;
import restart.com.movieapp.R;
import restart.com.movieapp.bean.NewsBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class FgNewsListFragment extends Fragment implements INewsView{
    private NewsPresenter presenter;
    private int type;
    private SwipeRefreshLayout srl_news;
    private RecyclerView rv_news;
    private ItemNewsAdapter adapter;
    private List<NewsBean.Bean> newsBeanList;
    private TextView tv_news_list;

    public static FgNewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        FgNewsListFragment fgNewsListFragment = new FgNewsListFragment();
        args.putInt("type", type);
        fgNewsListFragment.setArguments(args);
        return fgNewsListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");
        presenter = new NewsPresenter(this);
        rv_news = view.findViewById(R.id.rv_news);
        adapter = new ItemNewsAdapter(getActivity());
        tv_news_list = view.findViewById(R.id.tv_news_list);
        srl_news = view.findViewById(R.id.srl_news);
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new NewsPresenter(this);
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type,0);
            }
        });
        presenter.loadNews(type, 0);
    }

    @Override
    public void showNews(final NewsBean newsBean) {
        switch (type) {
            case FgNewsFragment.NEWS_TYPE_TOP:
                newsBeanList = newsBean.getTop();
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                newsBeanList = newsBean.getNba();
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                newsBeanList = newsBean.getJoke();
                break;
        }
        adapter.setData(newsBeanList);
        rv_news.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rv_news.setAdapter(adapter);
        tv_news_list.setVisibility(View.GONE);
    }

    @Override
    public void hideDialog() {
        srl_news.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_news.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
        tv_news_list.setText("加载失败：" + error);
    }
}
