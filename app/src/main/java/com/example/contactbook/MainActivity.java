package com.example.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Contact> contactList;
    ContactAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contact_recycle);

        DB.contactList.clear();
        //contactList = new ArrayList<>();
        DB.contactList.add(new Contact("Maria", "123", "maria@email.com", "15/2/2014"));
        DB.contactList.add(new Contact("Jose", "1234", "jose@email.com", "15/2/2014"));
        DB.contactList.add(new Contact("Joao", "12345", "joao@email.com", "15/2/2014"));

        setData();

        ContactAdapter adapter = new ContactAdapter(this);

        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setData() {
        contactList = new ArrayList<>();

        for (int i = 0; i <DB.contactList.size(); i++){
            contactList.remove(DB.contactList.get(i));

        }
        for (int i = 0; i <DB.contactList.size(); i++){
            contactList.add(DB.contactList.get(i));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setData();

        ContactAdapter adapter = new ContactAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void Fab (View view){
        Intent fab = new Intent(MainActivity.this, NewContactActivity.class);
        startActivity(fab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                settingsOptions();
                return true;
            case R.id.menu_favorite:
                Intent intentFavourity = new Intent(MainActivity.this, FavorityActivity.class);
                startActivity(intentFavourity);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void settingsOptions() {
        Intent intentSettings = new Intent(Settings.ACTION_SETTINGS);
        if (intentSettings.resolveActivity(getPackageManager())!=null){
            startActivity(intentSettings);
        }else{
            Log.d("ImplicitIntents", "Can´t handle this");
        }
    }


}