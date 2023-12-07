package com.example.laba3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class LoginActivity extends Activity {

    Database db = new Database(this);
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Intent openIntent = new Intent(this, mainActivity.class);

        TextView username = (TextView)findViewById(R.id.username);
        TextView password = (TextView)findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);
        Button regButton = findViewById(R.id.registerButton);
        Button changePassButton = findViewById(R.id.changePassButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.checkUser(new User(username.getText().toString(), password.getText().toString()))){
                    Toast.makeText(LoginActivity.this, "Авторизован",Toast.LENGTH_SHORT).show();
                    loginButton.setText("bim bim");
                    openIntent.putExtra("username",username.getText().toString());
                    openIntent.putExtra("password", password.getText().toString());
                    startActivity(openIntent);

                }else {
                    Toast.makeText(LoginActivity.this, "Неверный пароль",Toast.LENGTH_SHORT).show();
                }
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!db.checkUser(new User(username.getText().toString(), password.getText().toString()))) {
                    db.addUser(new User(username.getText().toString(), password.getText().toString()));
                    openIntent.putExtra("username", username.getText().toString());
                    openIntent.putExtra("password", password.getText().toString());
                    Log.i("REGISTER","reg");
                    startActivity(openIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Пользователь уже существует", Toast.LENGTH_SHORT).show();
                }
            }
        });

        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.refreshPass(new User(username.getText().toString(), password.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Пароль изменен", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Вы не зарегистрированы", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
