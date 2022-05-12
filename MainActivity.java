package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText name,email,password,address;
    Button button;
    DBMS dbms;
    StudentTable StudentTable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        button=findViewById(R.id.button);

        dbms=new DBMS(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentTable =new StudentTable();

                StudentTable.setName(name.getText().toString().trim());
                StudentTable.setEmail(email.getText().toString().trim());
                StudentTable.setPassword(password.getText().toString().trim());
                StudentTable.setAddress(address.getText().toString().trim());

                if(dbms.saveHourseHolder(StudentTable)!=0)
                    Toast.makeText(getApplicationContext(), String.valueOf("Insert Data Successfully.."),Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), String.valueOf("DO not Insert Data from Table"),Toast.LENGTH_LONG).show();
            }

        });
    }
}