package com.example.oyeleke.myapplication.Network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.xml.sax.Parser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by oyeleke on 3/10/18.
 */

public class NetworkRequest {

    private RequestQueue queue;
    private RequestCallback callback;
    private String TAG;
    private StringRequest req;

    public NetworkRequest(String TAG){
        queue = VolleySingleton.getmInstance().getRequestQueue();
        this.TAG = TAG;
    }

    public NetworkRequest setCallback(RequestCallback callback){
        this.callback = callback;
        return this;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public void execute(String url, int requestMethod){
        execute(url, requestMethod,null, null,null);
    }
    public void execute(String url, int requestMethod,  Map<String, String> params){
        execute(url,requestMethod,params,null,null);
    }
    public void execute(String url, int requestMethod,  Map<String, String> params, String requestBody){
        execute(url,requestMethod,params,requestBody,null);
    }

    public void execute(String url, int requestMethod, final Map<String, String> params, final String requestBody, final Map<String, String>headers ){
        callback.onRequestStart(TAG);

        req = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onRequestCompleted(TAG, response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        callback.onRequestError(TAG, error,res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        callback.onRequestError(TAG, error);

                    }
                }
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String res = new String(response.data, "UTF-8");
                    return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.error(new ParseError(response));
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(params != null)
                    return params;
                else
                    return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };
        int socketTimeOut = 7000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        req.setTag(TAG);
        queue.add(req);

    }

    public void cancelRequest(){
        if(req != null){
            req.cancel();
            queue.cancelAll(TAG);
            callback.onRequestCancelled();
        }
    }

    public interface RequestCallback{
        void onRequestStart(String tag);
        void onRequestCompleted(String tag,String response)throws JSONException;
        void onRequestError(String tag, VolleyError volleyError, String response);
        void onRequestError(String tag, VolleyError volleyError);
        void onRequestCancelled();

    }

}
