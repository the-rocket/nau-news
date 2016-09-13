package daniyar.com.datafromhtml;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
//import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private ArrayList<String> mArrayList;
    private Context mContext;

    RecyclerViewAdapter(Context context, ArrayList<String> mArrayList) {
        this.mContext = context;
        this.mArrayList = mArrayList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView newsTitle;
        ImageView newsImage;
        TextView newsTitleofText;
        TextView newsText;

        ViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            newsTitleofText = (TextView) itemView.findViewById(R.id.newsTitleofText);
            newsText = (TextView) itemView.findViewById(R.id.newsText);
        }
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.newsTitle.setText("Talgat Yegizbayev earns gold medal");
        holder.newsTitle.setTypeface(null, Typeface.BOLD);
        holder.newsTitle.setTextSize(20);

        Glide.with(mContext).load("http://www.na.edu/wp-content/uploads/2016/05/talgat-Y-669x272.jpg")
                            .into(holder.newsImage);
        holder.newsTitleofText.setText("Kazakh judo player Yegizbayev Talgat earns gold at the USA Judo Scholastic’s National Championships");
        holder.newsText.setText("Kazakh judo player Yegizbayev Talgat, who is studying at North American University went to Irving, Texas, to compete at the USA Judo Scholastics National Championships, March 8-10. The Scholastics is an event for judo players ages 7-20 to come together and compete for the junior national title. The tournament determines who the best junior athletes are.");
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
