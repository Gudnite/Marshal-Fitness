package com.example.mfv2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class Performance extends AppCompatActivity {
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private CollectionReference collectionref ;

    int result1[]=new int[10];
    int result2[]=new int[10];
    int result3[]=new int[10];
    int result4[]=new int[10];
    int result5[]=new int[10];
    int result6[]=new int[10];
    int result7[]=new int[10];
    int result8[]=new int[10];

    int[] yAxisData=new int[10];
    int[] yAxisData2=new int[10];
    int[] yAxisData3=new int[10];
    int[] yAxisData4=new int[10];
    int[] yAxisData5=new int[10];
    int[] yAxisData6=new int[10];
    int[] yAxisData7=new int[10];
    int[] yAxisData8=new int[10];
    int[] yAxisData9=new int[10];

    String []axisData=new String[10];

    LineChartView lineChartView;
    LineChartView lineChartView2;
    LineChartView lineChartView3;
    LineChartView lineChartView4;
    LineChartView lineChartView5;
    LineChartView lineChartView6;
    LineChartView lineChartView7;
    LineChartView lineChartView8;
    LineChartView lineChartView9;

    String []month=new String[10];
    String []year=new String[10];
    String []year2=new String[10];
    int total[]=new int[10];
    int k;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        ReadResult();

    }

    private void ReadResult() {
        //take serviceNumber from cadetProfile activity
        String s=getIntent().getStringExtra("ServiceNumber");
        // take the current year
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentDateandTime = sdf.format(new Date());
        //retrieve data from database
        db.collection("result")
                .whereEqualTo("serviceNum", s)
                .whereLessThanOrEqualTo("year", currentDateandTime).orderBy("year", Query.Direction.DESCENDING).limit(10)
                .orderBy("month", Query.Direction.DESCENDING).limit(10)


                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            QuerySnapshot document = task.getResult();

                            int dd= document.size();

                            for(int i=0;i<dd;i++){

                                try {
                                    result1[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("aAndR")));
                                    result2[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("armBending")));
                                    result3[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("rpClimbing")));
                                    result4[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("shtlRun")));
                                    result5[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("stbJump")));
                                    result6[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("pushUps")));
                                    result7[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("sitUps")));
                                    result8[i]= Integer.parseInt(String.valueOf(document.getDocuments().get(i).get("twoMileRun")));
                                    total[i]=(result1[i]+result2[i]+result3[i]+result4[i]+result5[i]+result6[i]+result7[i]+result8[i])/8;

                                    month[i]= String.valueOf(document.getDocuments().get(i).get("month"));
                                    year[i]= String.valueOf(document.getDocuments().get(i).get("year"));
                                    Log.e(String.valueOf(result1[i]),"blaaa");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                            for(k=dd-1;k>=0;k--){
                                yAxisData[j]=result1[k];
                                yAxisData2[j]=result2[k];
                                yAxisData3[j]=result3[k];
                                yAxisData4[j]=result4[k];
                                yAxisData5[j]=result5[k];
                                yAxisData6[j]=result6[k];
                                yAxisData7[j]=result7[k];
                                yAxisData8[j]=result8[k];
                                yAxisData9[j]=total[k];

                                year2[j]=year[k];
                                axisData[j]=month[k];
                                j++;
                            }



                            lineChartView = findViewById(R.id.chart1);
                            lineChartView2 = findViewById(R.id.chart2);
                            lineChartView3 = findViewById(R.id.chart3);
                            lineChartView4 = findViewById(R.id.chart4);
                            lineChartView5 = findViewById(R.id.chart5);
                            lineChartView6 = findViewById(R.id.chart6);
                            lineChartView7 = findViewById(R.id.chart7);
                            lineChartView8 = findViewById(R.id.chart8);
                            lineChartView9 = findViewById(R.id.chart9);

                            List yAxisValues = new ArrayList();
                            List yAxisValues2 = new ArrayList();
                            List yAxisValues3 = new ArrayList();
                            List yAxisValues4 = new ArrayList();
                            List yAxisValues5 = new ArrayList();
                            List yAxisValues6 = new ArrayList();
                            List yAxisValues7 = new ArrayList();
                            List yAxisValues8 = new ArrayList();
                            List yAxisValues9 = new ArrayList();

                            List axisValues = new ArrayList();
                            List axisValues2 = new ArrayList();
                            List axisValues3 = new ArrayList();
                            List axisValues4 = new ArrayList();
                            List axisValues5 = new ArrayList();
                            List axisValues6 = new ArrayList();
                            List axisValues8 = new ArrayList();
                            List axisValues7 = new ArrayList();
                            List axisValues9 = new ArrayList();



                            Line line = new Line(yAxisValues).setColor(Color.parseColor("#A5DEF1"));
                            Line line2 = new Line(yAxisValues2).setColor(Color.parseColor("#A5DEF1"));
                            Line line3 = new Line(yAxisValues3).setColor(Color.parseColor("#A5DEF1"));
                            Line line4 = new Line(yAxisValues4).setColor(Color.parseColor("#A5DEF1"));
                            Line line5 = new Line(yAxisValues5).setColor(Color.parseColor("#A5DEF1"));
                            Line line6 = new Line(yAxisValues6).setColor(Color.parseColor("#A5DEF1"));
                            Line line7 = new Line(yAxisValues7).setColor(Color.parseColor("#A5DEF1"));
                            Line line8 = new Line(yAxisValues8).setColor(Color.parseColor("#A5DEF1"));
                            Line line9 = new Line(yAxisValues9).setColor(Color.parseColor("#A5DEF1"));

                            for (int i = 0; i <dd; i++) {
                                axisValues.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues2.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues3.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues4.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues5.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues6.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues7.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues8.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));
                                axisValues9.add(i, new AxisValue(i).setLabel(year2[i]+":"+axisData[i]));

                            }

                            for (int i = 0; i < dd; i++) {
                                yAxisValues.add(new PointValue(i, yAxisData[i]));
                                yAxisValues2.add(new PointValue(i, yAxisData2[i]));
                                yAxisValues3.add(new PointValue(i, yAxisData3[i]));
                                yAxisValues4.add(new PointValue(i, yAxisData4[i]));
                                yAxisValues5.add(new PointValue(i, yAxisData5[i]));
                                yAxisValues6.add(new PointValue(i, yAxisData6[i]));
                                yAxisValues7.add(new PointValue(i, yAxisData7[i]));
                                yAxisValues8.add(new PointValue(i, yAxisData8[i]));
                                yAxisValues9.add(new PointValue(i, yAxisData9[i]));


                            }

                            List lines = new ArrayList();
                            List lines2 = new ArrayList();
                            List lines3 = new ArrayList();
                            List lines4 = new ArrayList();
                            List lines5 = new ArrayList();
                            List lines6 = new ArrayList();
                            List lines7 = new ArrayList();
                            List lines8 = new ArrayList();
                            List lines9 = new ArrayList();

                            lines.add(line);
                            lines2.add(line2);
                            lines3.add(line3);
                            lines4.add(line4);
                            lines5.add(line5);
                            lines6.add(line6);
                            lines7.add(line7);
                            lines8.add(line8);
                            lines9.add(line9);



                            LineChartData data = new LineChartData();
                            LineChartData data2 = new LineChartData();
                            LineChartData data3 = new LineChartData();
                            LineChartData data4 = new LineChartData();
                            LineChartData data5 = new LineChartData();
                            LineChartData data6 = new LineChartData();
                            LineChartData data7 = new LineChartData();
                            LineChartData data8 = new LineChartData();
                            LineChartData data9 = new LineChartData();


                            data.setLines(lines);
                            data2.setLines(lines2);
                            data3.setLines(lines3);
                            data4.setLines(lines4);
                            data5.setLines(lines5);
                            data6.setLines(lines6);
                            data7.setLines(lines7);
                            data8.setLines(lines8);
                            data9.setLines(lines9);





                            Axis axis = new Axis();
                            axis.setValues(axisValues);
                            axis.setName("Date");
                            axis.setLineColor(1);
                            axis.setTextSize(16);
                            axis.setTextColor(Color.parseColor("#000000"));
                            data.setAxisXBottom(axis);

                            Axis axis2 = new Axis();
                            axis2.setValues(axisValues);
                            axis2.setName("Date");
                            axis2.setLineColor(1);
                            axis2.setTextSize(16);
                            axis2.setTextColor(Color.parseColor("#000000"));
                            data2.setAxisXBottom(axis2);

                            Axis axis3 = new Axis();
                            axis3.setValues(axisValues);
                            axis3.setName("Date");
                            axis3.setLineColor(1);
                            axis3.setTextSize(16);
                            axis3.setTextColor(Color.parseColor("#000000"));
                            data3.setAxisXBottom(axis3);


                            Axis axis4 = new Axis();
                            axis4.setValues(axisValues);
                            axis4.setName("Date");
                            axis4.setLineColor(1);
                            axis4.setTextSize(16);
                            axis4.setTextColor(Color.parseColor("#000000"));
                            data4.setAxisXBottom(axis4);

                            Axis axis5 = new Axis();
                            axis5.setValues(axisValues);
                            axis5.setName("Date");
                            axis5.setLineColor(1);
                            axis5.setTextSize(16);
                            axis5.setTextColor(Color.parseColor("#000000"));
                            data5.setAxisXBottom(axis5);

                            Axis axis6 = new Axis();
                            axis6.setValues(axisValues);
                            axis6.setName("Date");
                            axis6.setLineColor(1);
                            axis6.setTextSize(16);
                            axis6.setTextColor(Color.parseColor("#000000"));
                            data6.setAxisXBottom(axis6);

                            Axis axis7 = new Axis();
                            axis7.setValues(axisValues);
                            axis7.setName("Date");
                            axis7.setLineColor(1);
                            axis7.setTextSize(16);
                            axis7.setTextColor(Color.parseColor("#000000"));
                            data7.setAxisXBottom(axis7);

                            Axis axis8 = new Axis();
                            axis8.setValues(axisValues);
                            axis8.setName("Date");
                            axis8.setLineColor(1);
                            axis8.setTextSize(16);
                            axis8.setTextColor(Color.parseColor("#000000"));
                            data8.setAxisXBottom(axis8);

                            Axis axis9 = new Axis();
                            axis9.setValues(axisValues);
                            axis9.setName("Date");
                            axis9.setLineColor(1);
                            axis9.setTextSize(16);
                            axis9.setTextColor(Color.parseColor("#000000"));
                            data9.setAxisXBottom(axis9);

                            Axis yAxis = new Axis();
                            //yAxis.setName("aandr");
                            yAxis.setTextColor(Color.parseColor("#000000"));
                            yAxis.setTextSize(16);
                            data.setAxisYLeft(yAxis);

                            Axis yAxis2 = new Axis();
                            //yAxis2.setName("armbending");
                            yAxis2.setTextColor(Color.parseColor("#000000"));
                            yAxis2.setTextSize(16);
                            data2.setAxisYLeft(yAxis2);

                            Axis yAxis3 = new Axis();
                            //yAxis3.setName("Push_Ups");
                            yAxis3.setTextColor(Color.parseColor("#000000"));
                            yAxis3.setTextSize(16);
                            data3.setAxisYLeft(yAxis3);

                            Axis yAxis4 = new Axis();
                            //yAxis4.setName("Push_Ups");
                            yAxis4.setTextColor(Color.parseColor("#000000"));
                            yAxis4.setTextSize(16);
                            data4.setAxisYLeft(yAxis4);

                            Axis yAxis5 = new Axis();
                            //yAxis5.setName("Push_Ups");
                            yAxis5.setTextColor(Color.parseColor("#000000"));
                            yAxis5.setTextSize(16);
                            data5.setAxisYLeft(yAxis5);

                            Axis yAxis6 = new Axis();
                            //yAxis6.setName("Push_Ups");
                            yAxis6.setTextColor(Color.parseColor("#000000"));
                            yAxis6.setTextSize(16);
                            data6.setAxisYLeft(yAxis6);

                            Axis yAxis7 = new Axis();
                            //yAxis7.setName("Push_Ups");
                            yAxis7.setTextColor(Color.parseColor("#000000"));
                            yAxis7.setTextSize(16);
                            data7.setAxisYLeft(yAxis7);

                            Axis yAxis8 = new Axis();
                            //yAxis8.setName("Push_Ups");
                            yAxis8.setTextColor(Color.parseColor("#000000"));
                            yAxis8.setTextSize(16);
                            data8.setAxisYLeft(yAxis8);

                            Axis yAxis9 = new Axis();
                            //yAxis9.setName("Push_Ups");
                            yAxis9.setTextColor(Color.parseColor("#000000"));
                            yAxis9.setTextSize(16);
                            data9.setAxisYLeft(yAxis9);


                            lineChartView.setLineChartData(data);
                            lineChartView2.setLineChartData(data2);
                            lineChartView3.setLineChartData(data3);
                            lineChartView4.setLineChartData(data4);
                            lineChartView5.setLineChartData(data5);
                            lineChartView6.setLineChartData(data6);
                            lineChartView7.setLineChartData(data7);
                            lineChartView8.setLineChartData(data8);
                            lineChartView9.setLineChartData(data9);

                            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
                            Viewport viewport2 = new Viewport(lineChartView2.getMaximumViewport());
                            Viewport viewport3 = new Viewport(lineChartView3.getMaximumViewport());
                            Viewport viewport4 = new Viewport(lineChartView4.getMaximumViewport());
                            Viewport viewport5 = new Viewport(lineChartView5.getMaximumViewport());
                            Viewport viewport6 = new Viewport(lineChartView6.getMaximumViewport());
                            Viewport viewport7 = new Viewport(lineChartView7.getMaximumViewport());
                            Viewport viewport8 = new Viewport(lineChartView8.getMaximumViewport());
                            Viewport viewport9 = new Viewport(lineChartView9.getMaximumViewport());

                            viewport.bottom=0;
                            viewport2.bottom=0;
                            viewport3.bottom=0;
                            viewport4.bottom=0;
                            viewport5.bottom=0;
                            viewport6.bottom=0;
                            viewport7.bottom=0;
                            viewport8.bottom=0;
                            viewport9.bottom=0;

                            viewport.top = 100;
                            viewport2.top = 100;
                            viewport3.top = 100;
                            viewport4.top = 100;
                            viewport5.top = 100;
                            viewport6.top = 100;
                            viewport7.top = 100;
                            viewport8.top = 100;
                            viewport9.top = 100;

                            lineChartView.setMaximumViewport(viewport);
                            lineChartView2.setMaximumViewport(viewport);
                            lineChartView3.setMaximumViewport(viewport);
                            lineChartView4.setMaximumViewport(viewport);
                            lineChartView5.setMaximumViewport(viewport);
                            lineChartView6.setMaximumViewport(viewport);
                            lineChartView7.setMaximumViewport(viewport);
                            lineChartView8.setMaximumViewport(viewport);
                            lineChartView9.setMaximumViewport(viewport);

                            lineChartView.setCurrentViewport(viewport);
                            lineChartView2.setCurrentViewport(viewport);
                            lineChartView3.setCurrentViewport(viewport);
                            lineChartView4.setCurrentViewport(viewport);
                            lineChartView5.setCurrentViewport(viewport);
                            lineChartView6.setCurrentViewport(viewport);
                            lineChartView7.setCurrentViewport(viewport);
                            lineChartView8.setCurrentViewport(viewport);
                            lineChartView9.setCurrentViewport(viewport);

                        } else {
                            System.out.println("error");
                        }
                    }
                });
    }
}


