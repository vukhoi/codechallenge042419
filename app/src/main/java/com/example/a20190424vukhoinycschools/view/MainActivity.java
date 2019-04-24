package com.example.a20190424vukhoinycschools.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.a20190424vukhoinycschools.R;
import com.example.a20190424vukhoinycschools.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity {
    MainActivityPresenter presenter = new MainActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.populateRecyclerView((RecyclerView) findViewById(R.id.recyclerView)
                , (SearchView) findViewById(R.id.searchView));


    }
}
