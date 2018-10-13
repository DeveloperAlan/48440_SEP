package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mad.studecare.Models.API;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.Models.Users;
import com.mad.studecare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by trevorlao on 22/8/18.
 */

public class LoginScreenPresenter implements LoginScreenContract.presenter {
    private static LoginScreenContract.view mView;
    String mUsername;
    String mPassword;

    public LoginScreenPresenter(LoginScreenContract.view view) {
        this.mView = view;
    }

    @Override
    public void authenticate(String email, String password, final Context context) {
        this.mUsername = email;
        this.mPassword = password;
        RequestQueue queue = Volley.newRequestQueue(context);

        final StringRequest request = new StringRequest(Request.Method.POST, API.BASE_URL_SIGN_IN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("POST", "SUCCESS: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Users newUser = new Users();
                    newUser.setAccessToken(jsonObject.getString(context.getString(R.string.api_access_token)));
                    newUser.setUserId(jsonObject.getString(context.getString(R.string.api_user)));
                    Log.d("POST", newUser.getAccessToken() + " " + newUser.getUserId());
                    Log.d("POST", "USER!: " + newUser.getAccessToken());
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
            //This is for Headers If You Needed
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map headers = new HashMap<>();
                String credentials = mUsername + ":" + mPassword;
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }

            protected Map<String, String> getParams() {
                Map<String, String> user = new HashMap<>();
                user.put("access_token", API.ACCESS_TOKEN);
                return user;
            }
        };
        queue.add(request);

        new DownloadData(context).execute();
    }

    private static class DownloadData extends AsyncTask<String, Void, Void> {
        private Context context;

        public DownloadData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            mView.showProgress();
            // Load Singleton
            DoctorsList.InitInstance();
            TimeSlotsList.InitInstance();
            AppointmentsList.InitInstance();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {

            // Appointments
            RequestQueue appointmentQueue = Volley.newRequestQueue(context);
            JsonObjectRequest getAppointmentRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_APPOINTMENTS, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("POST", "APPOINTMENTS DOWNLOAD DATA RESPONSE " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("POST", "APPOINTMENTS DOWNLOAD DATA ERROR: " + error.toString());
                        }
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> user = new HashMap<>();
                    user.put("access_token", API.ACCESS_TOKEN);
                    return user;
                }

            };
            appointmentQueue.add(getAppointmentRequest);

            // Appointments
            RequestQueue timeslotQueue = Volley.newRequestQueue(context);
            JsonObjectRequest getTimeslotRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_TIMESLOTS, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("POST", "TIMESLOT DOWNLOAD DATA RESPONSE " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("POST", "TIMESLOT DOWNLOAD DATA ERROR: " + error.toString());
                        }
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> user = new HashMap<>();
                    user.put("access_token", API.ACCESS_TOKEN);
                    return user;
                }

            };
            timeslotQueue.add(getTimeslotRequest);

            // Appointments
            RequestQueue doctorQueue = Volley.newRequestQueue(context);
            JsonObjectRequest getDoctorRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_USERS, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("POST", "DOCTOR DOWNLOAD DATA RESPONSE " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("POST", "DOCTOR DOWNLOAD DATA ERROR: " + error.toString());
                        }
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> user = new HashMap<>();
                    user.put("access_token", API.ACCESS_TOKEN);
                    return user;
                }

            };
            doctorQueue.add(getDoctorRequest);

            return null;
        }
//
//
//// add it to the RequestQueue
//            queue.add(getRequest);
//
//            Log.d("POST", "DOWNLOAD DATA reached");
//
//            final StringRequest request = new StringRequest(Request.Method.GET, API.BASE_URL_APPOINTMENTS, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    //This code is executed if the server responds, whether or not the response contains data.
//                    //The String 'response' contains the server's response.
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//
//                        // Clear List first
//                        AppointmentsList.GetInstance().ClearList();
//
//                        Iterator<String> keys = jsonObject.keys();
//
//                        while (keys.hasNext())
//                        {
//                            // Get the key
//                            String key = keys.next();
//
//                            // Get the value
//                            JSONObject value = jsonObject.getJSONObject(key);
//
//                            Log.d("POST", "DOWNLOAD DATA onResponse: " + value.toString());
//                            // Do something...
//                        }
//
//                        TimeSlotsList.GetInstance().ClearList();
//                    } catch (JSONException | NullPointerException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //This code is executed if there is an error.
//                    Log.d("POST", "DOWNLOAD DATA ERROR: " + error.toString());
//                }
//            }) {
//                protected Map<String, String> getParams() {
//                    Map<String, String> user = new HashMap<>();
//                    user.put("access_token", API.ACCESS_TOKEN);
//                    return user;
//                }
//            };
//            queue.add(request);
//
//            return null;
//        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mView.hideProgress();
            mView.loginAuthenticated();
        }
    }
}
