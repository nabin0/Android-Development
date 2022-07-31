package com.nabin.frebasestoredata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.snapshot.StringNode;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        btn = findViewById(R.id.storeBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = database.getReference();

                String username = name.getText().toString();
                String userPassword = password.getText().toString();
                String useremail = email.getText().toString();

                DataHolder obj = new DataHolder(username, useremail, userPassword);

                dbRef.child(username).setValue(obj);

                name.setText("");
                email.setText("");
                password.setText("");
                Toast.makeText(MainActivity.this, "stored", Toast.LENGTH_SHORT).show();
            }
        });

    }
}