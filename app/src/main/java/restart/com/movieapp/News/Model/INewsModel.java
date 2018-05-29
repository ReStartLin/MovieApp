package restart.com.movieapp.News.Model;

/**
 * Created by Administrator on 2018/5/29.
 */

public interface INewsModel {
    void loadNEws(String hostType,
                  int startPage,
                  String id,
                  IOnLoadListener iOnLoadListener);
}
