package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView tvOut;
    EditText ename, esname, eyear, eupdatename;
    Button btnDel, btnAdd, btnGet, btnDelOne, btnUpdate, btnSearch, btnThousand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        tvOut = findViewById(R.id.txt_list);

        ename = findViewById(R.id.et_name);
        esname = findViewById(R.id.et_surname);
        eyear = findViewById(R.id.et_age);
        eupdatename = findViewById(R.id.et_updateName);

        btnDel = findViewById(R.id.btn_dlt);
        btnAdd = findViewById(R.id.btn_add);
        btnGet = findViewById(R.id.btn_viewAll);
        btnDelOne = findViewById(R.id.btn_delOne);
        btnUpdate = findViewById(R.id.btn_Update);
        btnSearch = findViewById(R.id.btn_search);
        btnThousand = findViewById(R.id.btn_thousand);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAll();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data(ename.getText().toString(), esname.getText().toString(),
                        Integer.parseInt(eyear.getText().toString()));
                dbHelper.addOne(data);
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<Data> list = dbHelper.GetAll();
                String text = "";

                for(Data d:list){
                    text = text + d.name + " " + d.surame + " " + d.age + "\n";
                }

                tvOut.setText(text);
            }
        });
        btnDelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteOne(eupdatename.getText().toString());
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateOne(eupdatename.getText().toString(),
                                   ename.getText().toString(),
                                   esname.getText().toString(),
                                   eyear.getText().toString());

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Пример запроса: select * from MY_TABLE where COLUMN_AGE > 2008

                LinkedList<Data> list = dbHelper.search(eupdatename.getText().toString());
                String text = "";

                for(Data d:list){
                    text = text + d.name + " " + d.surame + " " + d.age + "\n";
                }
            }
        });
        btnThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText(" " + dbHelper.Insert1000());
            }
        });
    }
}