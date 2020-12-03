package com.example.horadapapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname, lastname, email, phone, password, repassword;
    Button btnsignup, btnsignin;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnsignin = findViewById(R.id.btnsignin);
        btnsignup = findViewById(R.id.btnsignup);

        dbHelper = new DBHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstnameValue = firstname.getText().toString();
                String lastnameValue = lastname.getText().toString();
                String emailValue = email.getText().toString();
                String phoneValue = phone.getText().toString();
                String passwordValue = password.getText().toString();
                String repasswordlValue = repassword.getText().toString();

                /*if (emailValue.length() > 1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("firstname", firstnameValue);
                    contentValues.put("lastname", lastnameValue);
                    contentValues.put("email", emailValue);
                    contentValues.put("phone", phoneValue);
                    contentValues.put("password", passwordValue);

                    dbHelper.insertUser(contentValues);
                    Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Enter the values!", Toast.LENGTH_SHORT).show();
                }*/

                if (firstnameValue.equals("") || lastnameValue.equals("") || emailValue.equals("") || phoneValue.equals("") || passwordValue.equals("") || repasswordlValue.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordValue.equals(repasswordlValue)) {
                        boolean checkemail = dbHelper.checkemail(emailValue);
                        if (checkemail==false) {
                            /*ContentValues contentValues = new ContentValues();
                            contentValues.put("firstname", firstnameValue);
                            contentValues.put("lastname", lastnameValue);
                            contentValues.put("email", emailValue);
                            contentValues.put("phone", phoneValue);
                            contentValues.put("password", passwordValue);*/

                            boolean insert = dbHelper.insertUser(firstnameValue, lastnameValue, emailValue, phoneValue, passwordValue);
                            if (insert==true) {
                                Toast.makeText(RegisterActivity.this, "Registado com Sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Falha no Registo!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email já Registado! Por favor faça LogIn.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "As passwords não coincidem!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}