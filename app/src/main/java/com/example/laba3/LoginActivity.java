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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Intent openIntent = new Intent(this, mainActivity.class);

        TextView username = (TextView)findViewById(R.id.username);
        TextView password = (TextView)findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("adm") && password.getText().toString().equals("123")) {
                    Toast.makeText(LoginActivity.this, "Авторизован",Toast.LENGTH_SHORT).show();
                    loginButton.setText("bim bim");
                    openIntent.putExtra("username",username.getText().toString());
                    startActivity(openIntent);

                }else {
                    Toast.makeText(LoginActivity.this, "Неверный пароль",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
