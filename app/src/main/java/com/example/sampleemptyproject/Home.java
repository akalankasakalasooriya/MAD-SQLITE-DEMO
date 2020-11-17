package com.example.sampleemptyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Button register;
    EditText un;
    EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DBHelper db = new DBHelper(this);
        un = findViewById(R.id.txtboxusername);
        pw = findViewById(R.id.txtboxpassword);

        register = findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,ProfileManagement.class);

                //save data
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                String username = String.valueOf(un.getText());
                String password = String.valueOf(pw.getText());
                if (username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"fill all data",Toast.LENGTH_SHORT).show();
                }else {
                    if(dbHelper.addInfo(username,"",""))
                    {
                        Toast.makeText(getApplicationContext(),"operation success",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"operation failed",Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });




    }
}