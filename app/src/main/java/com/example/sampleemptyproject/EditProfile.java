package com.example.sampleemptyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditProfile extends AppCompatActivity {
    Button search;
    EditText username;
    EditText dob;
    RadioGroup radiogender; RadioButton m; RadioButton f;
    Button edit;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        search= findViewById(R.id.btnsearch);
        edit = findViewById(R.id.btnedit);
        username = findViewById(R.id.txtusername_);
        dob = findViewById(R.id.txtdob_);
        radiogender = findViewById(R.id.radiobtnmf); m =  findViewById(R.id.male) ;  f =  findViewById(R.id.female);
        delete = findViewById(R.id.btndelete);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor= dbHelper.readAllInfor(1);//1 as para
                while (cursor.moveToNext()){
                    username.setText(cursor.getString(1));
                    dob.setText(cursor.getString(2));
                   // Log.e("xxxxx",cursor.getString(3));
                    if(cursor.getString(3).equals("male")){
                        radiogender.check(m.getId());
                    }else{
                        radiogender.check(f.getId());
                    }

                }

            }
        });

       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String un = String.valueOf(username.getText());
               String bdate = String.valueOf(dob.getText());
               String gender = "";
              if( m.getId() == radiogender.getCheckedRadioButtonId()){
                  gender = "male";
              }
              else{
                  gender = "female";
              }
               dbHelper.updateInfor(1,un,bdate,gender);
           }
       });


       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dbHelper.deleteInfo(1);

           }
       });



    }
}