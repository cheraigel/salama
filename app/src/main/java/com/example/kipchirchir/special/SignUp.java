package com.example.kipchirchir.special;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;
public class SignUp extends AppCompatActivity{

    private EditText name,email,password;
    private TextView member;
    private Button buttonsignup,buttonlogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        name =findViewById(R.id.input_name);
        email =findViewById(R.id.input_email);
        password =findViewById(R.id.input_password);
        buttonsignup =findViewById(R.id.btn_signup);
        member =findViewById(R.id.link_login);
        progressDialog =new ProgressDialog(this);
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
            }
        });

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =name.getText().toString().trim();
                String useremail =email.getText().toString().trim();
                String userpassword =password.getText().toString().trim();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(SignUp.this,"Plesae enter your name", Toast.LENGTH_LONG).show();
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(useremail)){
                    Toast.makeText(SignUp.this,"Plesae enter your email", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userpassword)){
                    Toast.makeText(SignUp.this,"Plesae enter your password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }

                if (password.length()<6){
                    password.setText("");
                    Toast.makeText(SignUp.this,"The minimum password length is 6", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }
                progressDialog.setMessage("please wait...");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(useremail ,userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration success",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
