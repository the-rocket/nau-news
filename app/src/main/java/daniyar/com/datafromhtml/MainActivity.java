package daniyar.com.datafromhtml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by yernar on 13/09/16.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLinearLayout;
    private ArrayList<String> mArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        init();
        test();
    }

    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initView();
    }

    public void initView() {
        mAdapter = new RecyclerViewAdapter(MainActivity.this, mArrayList);
        mLinearLayout = new LinearLayoutManager(this);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(mAdapter);
    }
    public void test() {
        for (int i = 1; i <= 5; ++i) {
            mArrayList.add("default");
            mAdapter.notifyDataSetChanged();
        }
    }
}
