package com.example.contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class UpdateActivity extends AppCompatActivity {

    private EditText nameET, phoneET, emailET, birthdayET;
    private String /*sex,*/ position;

    //private RadioButton mascBtt, femBtt, otherBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent getPositionView = getIntent();
        position = getPositionView.getStringExtra("userData");

        nameET = findViewById(R.id.name_label);
        phoneET = findViewById(R.id.phone_label);
        emailET = findViewById(R.id.email_label);
        birthdayET = findViewById(R.id.birthday_label);
        //mascBtt = findViewById(R.id.select_masc);
        //femBtt = findViewById(R.id.select_fem);
        //otherBtt = findViewById(R.id.select_other);

        nameET.setText(DB.contactList.get(Integer.parseInt(position)).getName());
        phoneET.setText(DB.contactList.get(Integer.parseInt(position)).getPhone());
        emailET.setText(DB.contactList.get(Integer.parseInt(position)).getEmail());
        birthdayET.setText(DB.contactList.get(Integer.parseInt(position)).getBirthday());

    }


    public void Update(View view) {
        finish();
    }
}