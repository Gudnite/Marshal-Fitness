package com.example.mfv2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.EditText;
import android.view.View;
import androidx.annotation.NonNull;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Report extends AppCompatActivity {
    FirebaseFirestore db;

    Switch sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9, sw10;
    Button submitBtn;
    //EditText msg;
    String snum;
    String currentDateandTime;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db = FirebaseFirestore.getInstance();
        switches();

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        currentDateandTime = sdf.format(new Date());

        snum=getIntent().getStringExtra("ServiceNumber");

        submitBtn=(Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeData();
            }
        });

    }
    public void switches(){
        sw1=(Switch) findViewById(R.id.switch1);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.abs);
                    i.setImageResource(R.drawable.abs1);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.abs);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });

        sw2=(Switch) findViewById(R.id.switch2);
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.oblique1);
                    ImageView j = (ImageView) findViewById(R.id.oblique2);
                    i.setImageResource(R.drawable.oblique1);
                    j.setImageResource(R.drawable.oblique2);

                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.oblique1);
                    ImageView j = (ImageView) findViewById(R.id.oblique2);
                    i.setImageResource(R.drawable.blank);
                    j.setImageResource(R.drawable.blank);
                }
            }
        });
        sw3=(Switch) findViewById(R.id.switch3);
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.pectorals);
                    i.setImageResource(R.drawable.pectorals);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.pectorals);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
        sw4=(Switch) findViewById(R.id.switch4);
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.triceps);
                    i.setImageResource(R.drawable.triceps);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.triceps);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
        sw5=(Switch) findViewById(R.id.switch5);
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.biceps);
                    i.setImageResource(R.drawable.biceps);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.biceps);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
        sw6=(Switch) findViewById(R.id.switch6);
        sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.forearms1);
                    ImageView j = (ImageView) findViewById(R.id.forearms2);
                    i.setImageResource(R.drawable.forearms1);
                    j.setImageResource(R.drawable.forearms2);

                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.forearms1);
                    ImageView j = (ImageView) findViewById(R.id.forearms2);
                    i.setImageResource(R.drawable.blank);
                    j.setImageResource(R.drawable.blank);
                }
            }
        });
        sw7=(Switch) findViewById(R.id.switch7);
        sw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.trapezius);
                    i.setImageResource(R.drawable.trapezius);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.trapezius);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
        sw8=(Switch) findViewById(R.id.switch8);
        sw8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.adductors1);
                    ImageView j = (ImageView) findViewById(R.id.adductors2);
                    i.setImageResource(R.drawable.adductors1);
                    j.setImageResource(R.drawable.adductors2);

                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.adductors1);
                    ImageView j = (ImageView) findViewById(R.id.adductors2);
                    i.setImageResource(R.drawable.blank);
                    j.setImageResource(R.drawable.blank);
                }
            }
        });
        sw9=(Switch) findViewById(R.id.switch9);
        sw9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.calf);
                    i.setImageResource(R.drawable.calf);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.calf);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
        sw10=(Switch) findViewById(R.id.switch10);
        sw10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ImageView i = (ImageView) findViewById(R.id.gluteus);
                    i.setImageResource(R.drawable.gluteus);
                }else
                {
                    ImageView i = (ImageView) findViewById(R.id.gluteus);
                    i.setImageResource(R.drawable.blank);
                }
            }
        });
    }

    public void writeData(){

        sw1=(Switch) findViewById(R.id.switch1);
        sw2=(Switch) findViewById(R.id.switch2);
        sw3=(Switch) findViewById(R.id.switch3);
        sw4=(Switch) findViewById(R.id.switch4);
        sw5=(Switch) findViewById(R.id.switch5);
        sw6=(Switch) findViewById(R.id.switch6);
        sw7=(Switch) findViewById(R.id.switch7);
        sw8=(Switch) findViewById(R.id.switch8);
        sw9=(Switch) findViewById(R.id.switch9);
        sw10=(Switch) findViewById(R.id.switch10);

        //msg=(EditText) findViewById(R.id.msg);
        Map<String, Object> data = new HashMap<>();

        if(sw1.isChecked()){
            data.put("abdominis", true);

        }
        else{
            data.put("abdominis", false);
        }

        if(sw2.isChecked()){
            data.put("oblique", true);

        }
        else{
            data.put("oblique", false);
        }

        if(sw3.isChecked()){
            data.put("pectorals", true);

        }
        else{
            data.put("pectorals", false);
        }

        if(sw4.isChecked()){
            data.put("triceps", true);

        }
        else{
            data.put("triceps", false);
        }


        if(sw5.isChecked()){
            data.put("biceps", true);

        }
        else{
            data.put("biceps", false);
        }

        if(sw6.isChecked()){
            data.put("forearms", true);

        }
        else{
            data.put("forearms", false);
        }
        if(sw7.isChecked()){
            data.put("trapezius", true);

        }
        else{
            data.put("trapezius", false);
        }

        if(sw8.isChecked()){
            data.put("adductors", true);
        }
        else{
            data.put("adductors", false);
        }
        if(sw9.isChecked()){
            data.put("calf", true);
        }
        else{
            data.put("calf", false);
        }
        if(sw10.isChecked()){
            data.put("gluteus", true);
        }
        else{
            data.put("gluteus", false);
        }
        data.put("serviceNum",snum);
        //data.put("message", msg.getText().toString());
        //data.put("month",currentDateandTime);
        DocumentReference d = db.collection("fail").document(snum);
        //CollectionReference user=db.collection("fail");
        //db.collection("fail")

        d.update(data)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        //progressDialog.dismiss();
                        sw1.setChecked(false);
                        sw2.setChecked(false);
                        sw3.setChecked(false);
                        sw4.setChecked(false);
                        sw5.setChecked(false);
                        sw6.setChecked(false);
                        sw7.setChecked(false);
                        sw8.setChecked(false);
                        sw9.setChecked(false);
                        sw10.setChecked(false);
                        Toast.makeText(Report.this, "Report sent successfully", Toast.LENGTH_SHORT).show();


                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Report.this, "Report sending failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
