package com.codekul.newsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dataSet = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet);
        ((ListView)findViewById(R.id.listItems)).setAdapter(adapter);

        final DbHelper helper = new DbHelper(this, "identity", null, 1);

        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(helper);
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btnQuery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                display(helper);
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete(helper);
            }
        });
    }

    private void delete(DbHelper helper) {
        SQLiteDatabase sqDb = helper.getWritableDatabase();
        String table = "myIdentity";
        String whereClause = "myPassword = ?";
        String[] whereArgs = {getPassword()};
        sqDb.delete(table,whereClause,whereArgs);
        sqDb.close();
    }

    private void display(DbHelper helper) {
        dataSet.clear();
        String table = "myIdentity";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        SQLiteDatabase sqDb = helper.getReadableDatabase();

        Cursor cursor = sqDb.query(table,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);

        while (cursor.moveToNext()){
            String myName = cursor.getString(cursor.getColumnIndex("myName"));
            String myPassword = cursor.getString(cursor.getColumnIndex("myPassword"));
            dataSet.add(myName+"\n"+myPassword);
        }
        adapter.notifyDataSetChanged();
        sqDb.close();
    }

    private void displayFew(DbHelper helper){

        dataSet.clear();
        String table = "myIdentity";
        String[] columns = {"myName"};
        String selection = "myPassword = ?";
        String[] selectionArgs = {getPassword()};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        SQLiteDatabase sqDb = helper.getReadableDatabase();

        Cursor cursor = sqDb.query(table,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);

        while (cursor.moveToNext()){
            String myName = cursor.getString(cursor.getColumnIndex("myName"));
            dataSet.add(myName);
        }
        adapter.notifyDataSetChanged();
        sqDb.close();
    }

    private void save(DbHelper helper){
        SQLiteDatabase sqDb = helper.getWritableDatabase();
        String table = "myIdentity";
        String nullColumnHack = null;

        ContentValues values = new ContentValues();
        values.put("myName",getMyName());
        values.put("myPassword",getPassword());
        sqDb.insert(table,nullColumnHack,values);
        sqDb.close();
    }

    private String getMyName(){
        return ((EditText)findViewById(R.id.edtUserName)).getText().toString();
    }

    private String getPassword(){
        return ((EditText)findViewById(R.id.edtPassword)).getText().toString();
    }
}
