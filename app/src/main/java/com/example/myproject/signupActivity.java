package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {
    private EditText name,mail,mob,address,pass;
    private Button submit,logout;
    private FirebaseAuth mAuth;
    private String username,email,phone,usrAddress,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.etName);
        mail=findViewById(R.id.etMail);
        mob=findViewById(R.id.etMob);
        address=findViewById(R.id.etAddress);
        pass=findViewById(R.id.etPass);
        submit=findViewById(R.id.btn_login);
        logout=findViewById(R.id.btn_logout);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=name.getText().toString();
                email=mail.getText().toString();
                phone=mob.getText().toString();
                usrAddress=address.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(signupActivity.this, "signInWithEmail:success", Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(signupActivity.this,MainActivity.class));
                                    //updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(signupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_LONG).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
    }
}