package com.example.kipchirchir.special;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private EditText emailtext,passwordtext;
    private Button buttonlogin;
    private TextView linktextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailtext =findViewById(R.id.input_email);
        passwordtext=findViewById(R.id.input_password);
        buttonlogin =findViewById(R.id.btn_login);
        linktextView =findViewById(R.id.link_signup);
        firebaseAuth =FirebaseAuth.getInstance();

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailtext.getText().toString().trim();
                String password = passwordtext.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"enter email",Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_LONG).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            startActivity(new Intent(MainActivity.this, CoolActivity.class));
                            Toast.makeText(getApplicationContext(),"Login suceess",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        linktextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

    }
}
