package daniyar.com.datafromhtml;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> mArrayList;
    private Context mContext;

    NewsAdapter(Context context, ArrayList<News> mArrayList) {
        this.mContext = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        News news = mArrayList.get(position);

        holder.newsTitle.setText(news.getTitle());
        holder.newsText.setText(news.getDescription());

        Glide.with(mContext)
            .load(news.getImageUrl())
            .into(holder.newsImage);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle;
        ImageView newsImage;
        TextView newsText;

        ViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.title);
            newsImage = (ImageView) itemView.findViewById(R.id.photo);
            newsText = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
