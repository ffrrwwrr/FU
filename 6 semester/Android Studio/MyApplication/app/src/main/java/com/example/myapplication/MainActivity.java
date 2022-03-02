package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemClickListener {

    Button mainButton;
    TextView mainTextView;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();
    Button ok_btn, cnc_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.main_textview);
        mainTextView.setText("Set in Java!");
        mainButton = findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.main_edittext);

        mainListView = findViewById(R.id.main_listview);
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);
        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);

        ok_btn = findViewById(R.id.ok_btn);
        cnc_btn = findViewById(R.id.cnc_btn);

        ok_btn.setOnClickListener(oclBtn);
        cnc_btn.setOnClickListener(oclBtn);
    }

    @Override
    public void onClick(View view) {
        mainTextView.setText(mainEditText.getText().toString()
        + " is learning Android development!");

        mNameList.add(mainEditText.getText().toString());
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("omg android", position + ": " + mNameList.get(position));
        mainTextView.setText(mNameList.get(position).toString()
                + " is learning Android development!");
    }

    View.OnClickListener oclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ok_btn:
                    // кнопка ОК
                    mainTextView.setText("Нажата кнопка ОК");
                    Toast.makeText(getApplicationContext(), "Нажата кнопка ОК", Toast.LENGTH_LONG).show();
                    break;
                case R.id.cnc_btn:
                    // кнопка Cancel
                    mainTextView.setText("Нажата кнопка Cancel");
                    Toast.makeText(getApplicationContext(), "Нажата кнопка Cancel", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}