package com.hussam.ex2postpc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String textsSaveKey = "text";
    private String booleanSaveKey = "bool";
    private int numberOfItems;
    private ArrayList<Item> todoList = new ArrayList<>();
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] savedTexts = new String[todoList.size()];
        boolean[] savedClickState = new boolean[todoList.size()];
        numberOfItems = todoList.size();
        for (int i = 0 ; i < numberOfItems; ++i){
            savedTexts[i] = todoList.get(i).getItemText();
            savedClickState[i] = todoList.get(i).isClicked();
        }
        outState.putStringArray(textsSaveKey, savedTexts);
        outState.putBooleanArray(booleanSaveKey, savedClickState);

    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context tempContext = this;
        setContentView(R.layout.activity_main);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(new ArrayList<Item>(), this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        if (savedInstanceState != null){
            String[] retrievedTexts = savedInstanceState.getStringArray(textsSaveKey);
            boolean[] retrievedClicks = savedInstanceState.getBooleanArray(booleanSaveKey);
            for (int i = 0 ; i < retrievedClicks.length ; ++i){
                assert retrievedClicks != null;
                assert retrievedTexts != null;
                boolean ass = retrievedClicks[i];
                todoList.add(new Item(retrievedTexts[i], ass));
            }
            adapter.updateData(todoList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(tempContext));
        }

        final Button CreateButton = findViewById(R.id.CreateButton);
        CreateButton.setText("create");
        final EditText TextEditor = findViewById(R.id.TextEditor);
        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = TextEditor.getText().toString();
                if (msg.length() == 0) {
                    Toast toast = Toast.makeText(tempContext, "In order to do add something you need to ACTUALLY" +
                            " ADD SOMETHING", Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else {
                    todoList.add(new Item(msg, false));
                    TextEditor.setText("");
                    adapter.updateData(todoList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    recyclerView.setLayoutManager(new LinearLayoutManager(tempContext));
                }

            }
        });
    }


}

