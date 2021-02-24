package com.example.mfv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username =(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login =(Button) findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user =firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,ServiceNumber.class));

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(),password.getText().toString());

            }
        });

    }

    private void validate(String username,String password ) {


        if(username.equals("")|| password.equals("")) {


            android.app.AlertDialog alertDialog=new android.app.AlertDialog.Builder(this).create();
            alertDialog.setMessage("Please enter username and password.");

            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    //startActivity(new Intent(MainActivity.this,MainActivity.class));

                    Toast.makeText(getApplicationContext(),
                            "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
            });

            alertDialog.show();

        }

        else {
            progressDialog.setMessage("Wait until you are verifying");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login success.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ServiceNumber.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        progressDialog.dismiss();
                    }
                }

            });
        }
    }



    }
//    public void LoginButton(){
//        loginBtn = (Button) findViewById(R.id.login);
//        usernameTxt = (EditText) findViewById(R.id.username);
//        passwordTxt = (EditText) findViewById(R.id.password);
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(usernameTxt.getText().toString().equals("user") && passwordTxt.getText().toString().equals("pass") ){
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setIcon(R.drawable.ic_baseline_check_circle_24);
//                            builder.setTitle("Login Successful.");
//                            builder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                    startActivity(new Intent(MainActivity.this, ServiceNumber.class));
//                                }
//                            });
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
//                        }
//
//
//
//                    }
//                }
//        );
//
//    }
//}