package com.pixelhub.recyclerviewwithsearchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ExampleItemJava> exampleList;
    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "One", "Ten"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Two", "Eleven"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Three", "Twelve"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Four", "Thirteen"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Five", "Fourteen"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Six", "Fifteen"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Seven", "Sixteen"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Eight", "Seventeen"));
        exampleList.add(new ExampleItemJava(R.drawable.ic_sentiment, "Nine", "Eighteen"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
