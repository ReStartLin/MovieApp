package restart.com.movieapp.Movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import restart.com.movieapp.ADetailActivity;
import restart.com.movieapp.R;
import restart.com.movieapp.bean.MovieBean;

/**
 * Created by Administrator on 2018/6/5.
 */

public class ItemMovieTopAdapter extends RecyclerView.Adapter<ItemMovieTopAdapter.TopViewHolder> {
    private List<MovieBean.SubjectsBean> data = new ArrayList<>();
    private Context context;

    public ItemMovieTopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MovieBean.SubjectsBean> data) {
        this.data = data;

    }
    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_top, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        final MovieBean.SubjectsBean bean = data.get(position);
        Glide.with(context)
                .load(bean.getImages().getSmall())
                .into(holder.imageView);
        String title = bean.getTitle();
        if (title.length() > 4) {
            title = title.substring(0, 4) + "...";
        }
        holder.textView.setText(title);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra("url",bean.getAlt());
                intent.putExtra("title", bean.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public TopViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie_top);
            textView = itemView.findViewById(R.id.tv_movie_top_name);
        }
    }
}

