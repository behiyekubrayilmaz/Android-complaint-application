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

public class AddActivity extends AppCompatActivity {
    EditText editMarka,editYorum;
    Button add_button;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editMarka=(EditText) findViewById(R.id.updateMarka);
        editYorum=(EditText) findViewById(R.id.updateYorum);
        add_button=(Button) findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marka = editMarka.getText().toString();
                String yorum = editYorum.getText().toString();
                DatabaseDb sqLiteDatabase = new DatabaseDb(AddActivity.this);
                Intent intent= new Intent(context,MarkalarActivity.class);
                startActivity(intent);
                sqLiteDatabase.addBrand(new ModelDB(marka,yorum));
            }
        });

    }
}