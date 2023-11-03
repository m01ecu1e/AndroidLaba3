package com.example.laba3;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class mainActivity extends Activity {
    public int[] a = {0,0};
    int selectedItem;
    ArrayList itemList;
    String someArray[] = {"bob", "bibka", "shmupsik", "pupsik", "bobik"};
    ListView listView;
    private TextView text1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        Bundle arguments = getIntent().getExtras();
        String username = arguments.get("username").toString();
        TextView usernameText = findViewById(R.id.username);
        usernameText.setText(username);

        listView = (ListView) findViewById(R.id.List1);
        itemList = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view,R.id.textItem, itemList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("LIST_VIEW", "item id:  " + i);
                selectedItem = i;
            }
        });

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add("item " + itemList.size());
                arrayAdapter.notifyDataSetChanged();
                selectedItem = itemList.size() - 1;
                Log.i("LIST_VIEW", "selected:  " + selectedItem);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    itemList.remove(selectedItem);
                    selectedItem = itemList.size() - 1;
                    arrayAdapter.notifyDataSetChanged();
                } catch ( IndexOutOfBoundsException e ) {

                }

                //itemList.remove(selectedItem);
            }
        });

        Button deleteAllButton = findViewById(R.id.deleteAllButton);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}


