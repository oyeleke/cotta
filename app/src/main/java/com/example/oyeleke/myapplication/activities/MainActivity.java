package com.example.oyeleke.myapplication.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oyeleke.myapplication.R;
import com.example.oyeleke.myapplication.presenter.MainContract;
import com.example.oyeleke.myapplication.presenter.MainPresenter;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements MainContract {


    TextView mResultView;
    EditText mUrlView, mKeyView, mValueView;
    MainPresenter mainPresenter;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mResultView = (TextView)findViewById(R.id.tv_results);
        mUrlView = (EditText)findViewById(R.id.et_url_field);
        mKeyView = (EditText)findViewById(R.id.et_key);
        mValueView = (EditText)findViewById(R.id.et_value);

        mainPresenter = new MainPresenter(this);



    }

    public void getReq(View v){
        url = mUrlView.getText().toString().trim();
        if (TextUtils.isEmpty(url) || url == null){
            mUrlView.setError("please input a valid url");
        }else {
            mainPresenter.doAction(url,null,1);
        }
    }
    public void postReq(View v){
        url = mUrlView.getText().toString().trim();
        if (TextUtils.isEmpty(url) || url == null){
            mUrlView.setError("please input a valid url");
        }else {
            mainPresenter.doAction(url,null,2);
        }
    }
    public void putReq(View v){
        url = mUrlView.getText().toString().trim();
        if (TextUtils.isEmpty(url) || url == null){
            mUrlView.setError("please input a valid url");
        }else {
            mainPresenter.doAction(url,null,3);
        }
    }
    public void patchReq(View v){
        url = mUrlView.getText().toString().trim();
        if (TextUtils.isEmpty(url) || url == null){
            mUrlView.setError("please input a valid url");
        }else {
            mainPresenter.doAction(url,null,4);
        }
    }
    public void deleteReq(View v){

        url = mUrlView.getText().toString().trim();
        if (TextUtils.isEmpty(url) || url == null){
            mUrlView.setError("please input a valid url");
        }else {
            mainPresenter.doAction(url,null,5);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showinTextView(String response) {
        mResultView.setText(response);
    }
}
