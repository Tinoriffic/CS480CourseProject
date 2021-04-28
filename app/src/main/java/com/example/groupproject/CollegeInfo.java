package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static android.content.Intent.ACTION_VIEW;

// TODO: Modify XML to scroll down

public class CollegeInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView cafeteria;
    private TextView student_center;
    private TextView library;
    private Spinner spinner;
    private String url;
    private WebView webView;

    private static final float zoom = 14.0f;
    String[] spinner_options = {"Boston University", "Boston College", "Northeastern", "MIT", "Harvard University"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_resource);

        cafeteria = (TextView)findViewById (R.id.cafeteria_info);
        student_center  = (TextView)findViewById(R.id.student_center);
        library = (TextView)findViewById(R.id.library);
        webView = (WebView)findViewById(R.id.web);
        spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String chosen = spinner_options[position];
        switch (chosen){
            case "Boston University":
                library.setText("Mugar Memorial Library Hours:\n Sunday: 9AM - 2AM \nMonday to Thursday: 7:30AM - 2AM \nFriday: 7:30AM - 10PM \nSaturday: 10AM - 6PM");
                student_center.setText("Yawkey Center for Student Services Hours: \nMonday to Sunday: 9AM -7PM");
                url = "https://www.bu.edu/dining/where-to-eat/";
                cafeteria.setText(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(cafeteria.getText().toString());
                break;

            case "Boston College":
                library.setText("O'Neill Library Hours: \n Monday to Sunday: 8AM - 11PM");
                student_center.setText("Office Student Services Hours:\n Monday to Friday: 9AM - 5PM ");
                url = "https://www.bc.edu/content/bc-web/offices/auxiliary-services/sites/dining/locations/whats-open.html";
                cafeteria.setText(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(cafeteria.getText().toString());
                break;

            case "Northeastern":
                library.setText("Snell Library Hours: \n Monday to Thursday: 8AM - 12AM \n Friday: 8AM - 10PM \n Saturday: 12PM - 10PM \n Sunday 12PM - 12AM ");
                student_center.setText("Curry Student Center Hours:\n Sunday 10AM - 12AM\n Monday to Thursday 7AM - 12AM \nFriday 7AM - 11PM \nSaturday 8AM - 11PM ");
                url = "https://www.nudining.com/public";
                cafeteria.setText(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(cafeteria.getText().toString());
                break;

            case "MIT":
                // String library_url = "https://libraries.mit.edu/interim-hours/";
                library.setText("Dewey Library, Barker Library, Hayden Library");
                student_center.setText("Stratton Student Center Hours: \nSunday CLOSED\n Monday to Friday: 9AM - 5PM \nFriday: 9AM - 5PM \nSaturday CLOSED ");
                url = "https://studentlife.mit.edu/dining";
                cafeteria.setText(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(cafeteria.getText().toString());
                break;

            case "Harvard University":
                //  String harvard_library_url = "https://library.harvard.edu/libraries";
                library.setText("Andover-Harvard Theological Library, Baker Library, Cabot Science Library");
                student_center.setText("Richard A. and Suan F. Smith Campus Center Hours:\n Sunday: 7AM - 12AM \n Monday to Friday: 9AM - 12AM \nSaturday: 7AM - 1AM ");
                url = "https://dining.harvard.edu/";
                cafeteria.setText(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(cafeteria.getText().toString());
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

