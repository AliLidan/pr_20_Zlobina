package com.example.pz_18_zlobina_pr_21_101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.MyAdapter;
import Model.Student;

public class MainActivity extends AppCompatActivity {

 /*   private EditText Name, Age, ID;
    private Button add, del;
    private dbHelper dbHandler;
    private ListView listView;*/
  //  private SQLiteDatabase db = dbHandler.getWritableDatabase();



    EditText ed1,ed2;
    DatabaseHelper databaseHelper;
    ListView l1;
    ArrayList<Student> arrayList;
    MyAdapter myAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editT_name);
        ed2 = (EditText) findViewById(R.id.editT_age);
        l1 = (ListView) findViewById(R.id.list1);


        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();


        laodDataInListView();
    }

    private void laodDataInListView()
    {
        arrayList = databaseHelper.getAllData();
        myAdapter = new MyAdapter(this,arrayList);
        l1.setAdapter(myAdapter);
        //l1 = findViewById(R.id.level1)
        myAdapter.notifyDataSetChanged();

    }


    public void insert(View v)
    {
        boolean result = databaseHelper.insertData(ed1.getText().toString(),ed2.getText().toString());
        if(result)
        {
            Toast.makeText(getApplicationContext(),"Data insert successfylly!",Toast.LENGTH_LONG).show();
        }
        else
            {
                Toast.makeText(getApplicationContext(),"Data NOT insert",Toast.LENGTH_LONG).show();
            }
    }

}

/*
        ID = findViewById(R.id.editT_id);
        Name = findViewById(R.id.editT_name);
        Age = findViewById(R.id.editT_age);

        add = findViewById(R.id.button);
        del = findViewById(R.id.button2);
        listView = findViewById(R.id.list1);

        dbHandler = new dbHelper(MainActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseID = ID.getText().toString();
                String courseName = Name.getText().toString();
                String courseAge = Age.getText().toString();

                if (courseName.isEmpty() && courseAge.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Пожалуйста введите данные!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewRecord(courseName, courseAge);

                Toast.makeText(MainActivity.this, "Запись была добавлена!", Toast.LENGTH_SHORT).show();
                ID.setText("");
                Name.setText("");
                Age.setText("");

            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                dbHandler.Delete(db);

                Toast.makeText(MainActivity.this, "База была удалена!", Toast.LENGTH_SHORT).show();
                ID.setText("");
                Name.setText("");
                Age.setText("");
            }
        });*/