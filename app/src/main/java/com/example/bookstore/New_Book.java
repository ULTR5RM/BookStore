package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class New_Book extends AppCompatActivity {
    TextView tvInfo;
    EditText tvName;
    // EditText tvAddress;
    New_Book.MyTask mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvName = (EditText) findViewById(R.id.editTextTextPersonName);
        //tvAddress = (EditText) findViewById(R.id.editTextTextPersonAddress);
    }
    public void onclick(View v) {
        mt = new New_Book.MyTask();
        mt.execute(tvName.getText().toString());
    }
    class MyTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Добавление начато");
        }
        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection myConnection=null;
            try {
                URL githubEndpoint = new URL("http://10.0.2.2:8080/bookstore/");
                myConnection =(HttpURLConnection) githubEndpoint.openConnection();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myConnection.setDoOutput(true);
            try {
                myConnection.getOutputStream().write(("id=2&btitle=" + params[0]).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            };
            int i=0;
            try {
                i = myConnection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
// tvInfo.setText(str);
            if (i==200) {
            }
            return null;
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("Книга успешно добавлена");
        }
    }
}