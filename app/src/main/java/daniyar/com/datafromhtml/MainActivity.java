package daniyar.com.datafromhtml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private ArrayList<News> mArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        initView();

        loadData(new OnLoadListener() {
            @Override
            public void onStart() {
                toggleViews(true);
            }

            @Override
            public void onSuccess(ArrayList<News> newData) {
                mArrayList.addAll(newData);

                setAdapter();
                toggleViews(false);
            }

            @Override
            public void onFail() {
                toggleViews(false);
            }
        }, 1);
    }

    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressView = (ProgressBar) findViewById(R.id.progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setAdapter() {
        mAdapter = new NewsAdapter(MainActivity.this, mArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void toggleViews(boolean loading) {
        if (loading) {
            recyclerView.setVisibility(View.GONE);
            progressView.setVisibility(View.VISIBLE);
        } else {
            progressView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void loadData(final OnLoadListener onLoadListener, int page) {
        final String path = String.format(Locale.ROOT, "category/headlines/page/%d", page);

        HttpClient.get(path, null, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                onLoadListener.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    ArrayList<News> newData = NAUParser.parseNews(responseBody, HttpClient.getAbsoluteUrl(path));

                    onLoadListener.onSuccess(newData);
                } catch (Exception e) {
                    onFailure(statusCode, headers, responseBody, e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Bumber! Network error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                onLoadListener.onFail();
            }
        });
    }

    public interface OnLoadListener {
        void onStart();

        void onSuccess(ArrayList<News> newData);

        void onFail();
    }
}
