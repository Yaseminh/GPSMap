package com.yaseminsolmaz.haritagps;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostandGetLocation {
    ModelView mymodel;

    static java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public void GetLocation(Context context, String url) {
        final MainActivity mainActivity = new MainActivity();
        mymodel = new ModelView();
        System.out.println(mymodel.getLatitude());
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//         * @param method the HTTP method to use
//     * @param url URL to fetch the JSON from
    //   * @param requestBody A {@link String} to post with the request. Null is allowed and
//     *   indicates no parameters will be posted along with request.
//                * @param listener Listener to receive the JSON response
//     * @param errorListener Error listener, or null to ignore errors.
        // Initialize a new JsonObjectRequest instance

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            ModelView modelView = gson.fromJson(response.getString("Data"), ModelView.class);
                            Log.e("response",response.toString());
                            mainActivity.printScreen(modelView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("response",error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
        // Add JsonObjectRequest to the RequestQueue
        ModelView x = mymodel;
}

    public void GetPostLocation(Context context, String url, double latitude, double longitude, Date date) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject jsonBody = new JSONObject();
        try {
            ////Kendi modelinize göre oluşturun
            jsonBody.put("latitude", latitude);
            jsonBody.put("longitude", longitude);
            jsonBody.put("addedtime", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response", String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", String.valueOf(error));
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);


    }
}

