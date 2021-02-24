package com.example.mfv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ServiceNumber extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    EditText serviceNum;
    Button serviceBtn, logoutBtn;
    String snum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        firebaseAuth = FirebaseAuth.getInstance();

        ServiceButton();
        LogoutBtn();
    }
    public  void LogoutBtn(){
        logoutBtn = (Button) findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ServiceNumber.this,MainActivity.class));
            }
        });

    }

    public void ServiceButton(){
        serviceBtn = (Button) findViewById(R.id.serviceButton);
        serviceNum = (EditText) findViewById(R.id.serviceNumber);



        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snum= serviceNum.getText().toString();
                db=FirebaseFirestore.getInstance();
                DocumentReference user = db.collection("cadet").document(snum);
                System.out.println("user "+user);
                user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            if(doc.getData()!=null){
                                Intent intent=new Intent(ServiceNumber.this,Dashboard.class);

                                intent.putExtra("ServiceNumber",snum);
                                startActivity(intent);

                            }
                            else{
                                Intent intent=new Intent(ServiceNumber.this,ServiceNumber.class);


                                startActivity(intent);
                                Toast.makeText(ServiceNumber.this,"Invalid Service Number",Toast.LENGTH_SHORT).show();
                            }


                        }

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });


    }
}