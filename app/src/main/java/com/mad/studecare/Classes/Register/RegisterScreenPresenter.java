package com.mad.studecare.Classes.Register;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.mad.studecare.Models.API;
import com.mad.studecare.Models.Users;
import com.mad.studecare.R;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterScreenPresenter implements RegisterScreenContract.presenter {
    String mUsername;
    String mPassword;

    public RegisterScreenPresenter() {
    }


    @Override
    public void RegisterUser(String email, final String password, final Context context) {
        this.mUsername = email;
        this.mPassword = password;
        RequestQueue queue = Volley.newRequestQueue(context);

        final StringRequest request = new StringRequest(Request.Method.POST, API.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("POST", "SUCCESS: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Users newUser = new Users();
                    newUser.setAccessToken(jsonObject.getString(context.getString(R.string.api_access_token)));
//                    newUser.setEmail(jsonObject.getString(context.getString(R.string.api_email)));
                    newUser.setUserId(jsonObject.getString(context.getString(R.string.api_user)));
//                    newUser.setName(jsonObject.getString(context.getString(R.string.api_name)));
                    Log.d("POST", "NEW USER " + newUser.getEmail() + " " + newUser.getAccessToken() + " " + newUser.getUserId());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.d("POST", "ERROR: " + error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> user = new HashMap<>();
                user.put("email", mUsername);
                user.put("password", mPassword);
                user.put("access_token", API.ACCESS_TOKEN);
                return user;
            }
        };
        queue.add(request);
    }

}
