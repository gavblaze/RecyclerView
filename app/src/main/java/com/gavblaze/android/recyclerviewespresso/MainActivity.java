package com.gavblaze.android.recyclerviewespresso;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements WordListAdapter.OnItemClickListener {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private WordListAdapter mWordListAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        mWordListAdapter = new WordListAdapter(mWordList, this);
        mRecyclerView.setAdapter(mWordListAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);

        populateList();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int listSize = mWordList.size();
                mWordList.addLast("+ Word " + listSize);
                mWordListAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(listSize);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mWordList.clear();
            populateList();
            mWordListAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        String wordOfClicked = mWordList.get(position);
        mWordList.set(position, "Clicked! " + wordOfClicked);
        mWordListAdapter.notifyDataSetChanged();
    }

    public void populateList() {
        for (int i = 0; i <= 20; i++) {
            mWordList.add("Word " + i);
        }
    }
}
