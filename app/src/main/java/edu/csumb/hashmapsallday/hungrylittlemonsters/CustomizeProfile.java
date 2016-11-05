package edu.csumb.hashmapsallday.hungrylittlemonsters;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by BRX01 on 11/4/2016.
 */

public class CustomizeProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner transportSpinner;
    private Button submitButton;
    private EditText weeklyBudget;
    private RadioGroup doCook;
    String TAG = "Customize";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_profile);

        addListenerOnSpinner();

        Button submitPref = (Button)findViewById(R.id.custProfSubmit);
        submitPref.setOnClickListener(this);
    }

    public void addListenerOnSpinner(){
        transportSpinner = (Spinner)findViewById(R.id.prefTransporation);

        transportSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MyApplication myApp = (MyApplication) getApplicationContext();
        myApp.setAddress("prefTransportation", parent.getItemAtPosition(position).toString());
        Log.d(TAG, "Pref Transportation "+myApp.getAddress("prefTransportation").toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        MyApplication myApp = (MyApplication) getApplicationContext();
        if(v.getId() == R.id.custProfSubmit){
            doCook = (RadioGroup)findViewById(R.id.iCook);
            String doCookString = ((RadioButton)findViewById(doCook.getCheckedRadioButtonId())).getText().toString();
            myApp.setAddress("doCook", doCookString);

            weeklyBudget = (EditText)findViewById(R.id.weeklyBudget);
            myApp.setAddress("weeklyBudget", weeklyBudget.getText().toString());

            Intent i = new Intent(this, FeedMonster.java);
            this.finish();
            startActivity(i);
        }
    }
}