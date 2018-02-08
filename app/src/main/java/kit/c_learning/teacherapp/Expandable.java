package kit.c_learning.teacherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sokrim on 2/7/2018.
 */

public class Expandable extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistview);

        expListView = findViewById(R.id.lvExp);

        listAdapter = new ExpandableListAdapter(this, listData);

        prepareListData();

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listData = new ArrayList<>();
        listData.add("Hello");
        listData.add("Hi");
    }
}
