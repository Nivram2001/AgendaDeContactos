package com.example.contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.zip.Inflater;

public class NewContactActivity extends AppCompatActivity {

    EditText name, phone, email, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name_label);
        phone = findViewById(R.id.phone_label);
        email = findViewById(R.id.email_label);
        birthday = findViewById(R.id.birthday_label);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        birthday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewContactActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;

                        String date = day + "/" + month + "/" + year;
                        birthday.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    public void Cancel(View view){
        //Intent cancel = new Intent(NewContactActivity.this, MainActivity.class);
        finish();
    }

    public void Create(View view) {
        final String nome = name.getText().toString();
        final String numero = phone.getText().toString();
        final String mail = email.getText().toString();
        final String aniversario = birthday.getText().toString();

        Contact contact = new Contact(nome, numero, mail,aniversario);

        if (nome.isEmpty() || numero.isEmpty() || mail.isEmpty() || aniversario.isEmpty()){
            Toast.makeText(this, "Os campos estão vazios. Por favor complete os campos!!", Toast.LENGTH_SHORT).show();
        }
        else if (!nome.matches("^[A-Za-z]+$")){
            Toast.makeText(this, "O Nome só pode conter letras (maiusculas ou minusculas)", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast.makeText(this, "Email invalido. Confirme o seu Email!!", Toast.LENGTH_SHORT).show();
        }
        else {
            DB.contactList.add(contact);
            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
            //Intent co = new Intent(NewContactActivity.this, MainActivity.class);
            finish();
        }

    }
}