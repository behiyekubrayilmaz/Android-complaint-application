package com.example.singlelesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.singlelesson.Database.DatabaseDb;
import com.example.singlelesson.Database.ModelDB;

import java.util.ArrayList;
import java.util.List;

public class MarkalarActivity extends AppCompatActivity {
    ListView listemiz;
    Button add_button;
    List<ModelDB> list;
    DatabaseDb sqLiteDatabase;
    ArrayAdapter<String> mAdapter;
    Context context = this;
    ModelDB seciliMarka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markalar);

        listemiz=(ListView) findViewById(R.id.listemiz);
        add_button=(Button) findViewById(R.id.add_button);

        sqLiteDatabase= new DatabaseDb(MarkalarActivity.this);
        list= sqLiteDatabase.MarkaGetir();
        List<String> listMarka = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            listMarka.add(i,list.get(i).getMarka());
        }
        mAdapter=new ArrayAdapter<String>(context,R.layout.satir_layout,R.id.listMetin,listMarka);
        listemiz.setAdapter(mAdapter);

        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(context,DetailActivity.class);
                intent.putExtra("marka",list.get(i).getId());
                Log.i("idmiz", String.valueOf(list.get(i).getId()));
                startActivityForResult(intent,1);
            }
        });
    }

    public void buttonEkle_Click(View view) {
        Intent intent= new Intent(MarkalarActivity.this,AddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        list= sqLiteDatabase.MarkaGetir();
        List<String> listMarka = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            listMarka.add(i,list.get(i).getMarka());
        }
        mAdapter=new ArrayAdapter<String>(context,R.layout.satir_layout,R.id.listMetin,listMarka);
        listemiz.setAdapter(mAdapter);

    }
}