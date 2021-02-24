package com.example.mfv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Dashboard extends AppCompatActivity {
    Button performanceBtn, reportBtn;
    FirebaseFirestore db;
    TextView textDisplay1,textDisplay2,textDisplay3;
    ImageView cadet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        PerformanceButton();
        ReportButton();
        db = FirebaseFirestore.getInstance();
        textDisplay1 = findViewById(R.id.cadetname);
        textDisplay2 = findViewById(R.id.servicenumber);
        textDisplay3 =(TextView) findViewById(R.id.troop);
        performanceBtn=(Button)findViewById(R.id.performanceBtn) ;
        cadet = (ImageView)findViewById(R.id.cadet);
        ReadSingleContact();
    }
    private void ReadSingleContact() {
        String s = getIntent().getStringExtra("ServiceNumber");
        //textDisplay2.setText(getIntent().getStringExtra("ServiceNumber"));
        final DocumentReference user = db.collection("cadet").document(s);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder field1 = new StringBuilder("");
                    StringBuilder field2 = new StringBuilder("");
                    StringBuilder field3 = new StringBuilder("");
                    StringBuilder field4 = new StringBuilder("");
                    field1.append(doc.get("name"));
                    field2.append(doc.get("troop"));
                    field3.append(doc.get("serviceNum"));
                    try {
                        Picasso.get().load(String.valueOf(field4.append(doc.get("image")))).into(cadet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    textDisplay1.setText(field1.toString());
                    textDisplay3.setText(field2.toString());
                    textDisplay2.setText(field3.toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    Intent intent=new Intent(Dashboard.this,Dashboard.class);
                         @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
            public void PerformanceButton() {
                performanceBtn = (Button) findViewById(R.id.performanceBtn);
                performanceBtn.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          String snum = textDisplay2.getText().toString();
                                                          Intent i = new Intent(Dashboard.this, Performance.class);
                                                          i.putExtra("ServiceNumber", snum);
                                                          startActivity(i);
                                                      }


                                                  }
                );
            }

            public void ReportButton() {
                reportBtn = (Button) findViewById(R.id.reportBtn);
                reportBtn.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String snum = textDisplay2.getText().toString();
                                                     Intent i = new Intent(Dashboard.this, Report.class);
                                                     i.putExtra("ServiceNumber", snum);
                                                     startActivity(i);
                                                 }

                                             }
                );
            }
}