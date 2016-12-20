package com.example.win.trustinnojobagency00;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JobSeekers extends AppCompatActivity {

    static AutoCompleteTextView autoTv;
    static EditText editText;
    static Button btn;
    static TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seekers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // autoComplettextview for city/division
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DataSet.city_div);
        autoTv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoTv.setThreshold(1);
        autoTv.setAdapter(adapter);

       final EmpDatabaseHelper mydb = new EmpDatabaseHelper(this);
        String conv_autocom =autoTv.getText().toString();
        String conv_editText = editText.getText().toString();
        t1=(TextView)findViewById(R.id.textV_found);
        t2=(TextView)findViewById(R.id.textV_result);
        btn= (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityDivison =autoTv.getText().toString();
                String jobTitle = editText.getText().toString();
                Cursor res = mydb.getDataByTitle(jobTitle);
                if (res.getCount() == 0){
                    t2.setText("");
                    t1.setText("Not Found!");}
                else {

                    String str = "";
                    while (res.moveToNext()) {
                        str += "ID : " + res.getString(0) + "\nCOMPANYNAME : " + res.getString(1) + "\nTITLE : " + res.getString(2) + "\nDESCRIPTION : " + res.getString(3) + "\nDATE : " + res.getString(4);
                    }
                    t2.setText(str.toString());
                    t1.setText("Found!");
                }

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "trustinno @2016", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
