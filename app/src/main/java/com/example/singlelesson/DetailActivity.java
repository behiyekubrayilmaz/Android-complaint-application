package com.example.singlelesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singlelesson.Database.DatabaseDb;
import com.example.singlelesson.Database.ModelDB;

public class DetailActivity extends AppCompatActivity {
    EditText updateMarka,updateYorum;
    Button update_button,delete_button;
    Context context = this;
    ModelDB seciliMarka;
    DatabaseDb sqLiteDatabase= new DatabaseDb(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        updateMarka=(EditText) findViewById(R.id.updateMarka);
        updateYorum=(EditText) findViewById(R.id.updateYorum);
        update_button=(Button) findViewById(R.id.update_button);
        delete_button=(Button) findViewById(R.id.delete_button);

        sqLiteDatabase = new DatabaseDb(context);
        Intent intent= getIntent();
        int id = intent.getIntExtra("marka",-1);
        seciliMarka = sqLiteDatabase.markaOku(id);
        updateMarka.setText(seciliMarka.getMarka());
        updateYorum.setText(seciliMarka.getYorum());

    }
    public void delete_Click(View view) {
        sqLiteDatabase.delete(seciliMarka);
        finish();
    }

    public void update_Click(View view) {
        seciliMarka.setMarka(updateMarka.getText().toString());
        seciliMarka.setYorum(updateYorum.getText().toString());
        sqLiteDatabase.update(seciliMarka);
        finish();
    }
}