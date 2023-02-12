package com.example.login_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eUserName;
    EditText ePassword;
    EditText eEmail;
    Button eLogin;
    TextView eAttemptsInfo;
    Button eRegister;

    String Username = "Admin";
    String Password = "12345678";
    String Email = "abc@gmail.com";

    boolean isValid = false;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUserName = (EditText) findViewById(R.id.etUserName);
        ePassword = findViewById(R.id.etPassword);
        eEmail = findViewById(R.id.etEmail);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eRegister=findViewById(R.id.eRegister);

        eLogin.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String inputName = eUserName.getText().toString();
                String inputPassword = ePassword.getText().toString();
                String inputEmail = eEmail.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly!", Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validate(inputName, inputPassword, inputEmail);

                    if (!isValid) {
                        counter--;
                        Toast.makeText(MainActivity.this, "User Not Registered", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("Number of Attempts Remaining:" + counter);

                        if (counter == 0) {
                            eLogin.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login was successful", Toast.LENGTH_SHORT).show();

                        // add code to got to new activity
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
      eRegister.setOnClickListener(new OnClickListener() {
           @Override
        public void onClick(View view) {
             String Name = eUserName.getText().toString();
            String Password = ePassword.getText().toString();
                String Email = eEmail.getText().toString();


               Toast.makeText(MainActivity.this, "Registration was successful", Toast.LENGTH_SHORT).show();

                // add code to got to new activity
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
               startActivity(intent);
            }

       });
    }
        public boolean validate (String name, String password, String email)
        {
            return name.equals(Username) && password.equals(Password) && email.equals(Email);
        }
    }
