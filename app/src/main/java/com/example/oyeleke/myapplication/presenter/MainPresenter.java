package com.example.oyeleke.myapplication.presenter;

import android.app.VoiceInteractor;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.oyeleke.myapplication.Network.NetworkRequest;

import org.json.JSONException;

import java.util.Map;

/**
 * Created by oyeleke on 3/10/18.
 */

public class MainPresenter {

    private MainContract view;

    public MainPresenter(MainContract view) {
        this.view = view;
    }

    public void  doAction(String url, Map<String, String>value, int request){

        excuteAction(url,value,request);
    }


    public void excuteAction(String url, final Map<String, String>values, int request){

        final NetworkRequest networkRequest = new NetworkRequest("do_action").setCallback(new NetworkRequest.RequestCallback() {
            @Override
            public void onRequestStart(String tag) {

            }

            @Override
            public void onRequestCompleted(String tag, String response) throws JSONException {

                view.showinTextView(response);
            }

            @Override
            public void onRequestError(String tag, VolleyError volleyError, String response) {

            }

            @Override
            public void onRequestError(String tag, VolleyError volleyError) {

            }

            @Override
            public void onRequestCancelled() {

            }
        });
        int requestChoice = -1;
        switch (request){
            case 1:
                requestChoice= Request.Method.GET;
                break;
            case 2:
                requestChoice = Request.Method.POST;
                break;
            case 3:
                requestChoice = Request.Method.PUT;
                break;
            case 4:
                requestChoice = Request.Method.PATCH;
                break;
            case 5:
                requestChoice = Request.Method.DELETE;
                break;
        }
        if (values != null){
            networkRequest.execute(url,requestChoice,values);
        }
        else {
            networkRequest.execute(url, requestChoice);
        }

    }




}
