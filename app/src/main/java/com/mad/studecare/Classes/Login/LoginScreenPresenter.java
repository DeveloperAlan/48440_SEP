package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
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
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.Models.Users;
import com.mad.studecare.R;

import org.json.JSONArray;
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
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("access_token", API.ACCESS_TOKEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(validEmail(email) && validPassword(password)){

            JsonObjectRequest authenticateUser = new JsonObjectRequest(Request.Method.POST, API.BASE_URL_SIGN_IN, requestBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("response", response.toString());

                    try {
                        Users user = Users.getInstance();
                        user.setAccessToken(response.getString("token"));

                        JSONObject userDetails = response.getJSONObject("user");
                        user.setUserId(userDetails.getString("id"));
                        user.setName(userDetails.getString("name"));
                        user.setEmail(userDetails.getString("email"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new DownloadData(context).execute();
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
            queue.add(authenticateUser);

        }
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
            DoctorsList.GetInstance();
            TimeSlotsList.InitInstance();
            AppointmentsList.InitInstance();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {


            JSONObject requestBody = new JSONObject();
            try {
                requestBody.put("access_token", API.ACCESS_TOKEN);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            // Appointments
            RequestQueue doctorQueue = Volley.newRequestQueue(context);
            JsonObjectRequest getDoctorRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_DOCTORS, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response

                            try {

                                JSONArray array = response.getJSONArray("rows");
                                Log.d("ROWS", array.toString());
                                for(int i = 1; i < array.length(); i++) {
                                    JSONObject tSlot = array.getJSONObject(i);
                                    String name = tSlot.getString("name");
                                    Log.d("VALUE", name);
                                    String id = tSlot.getString("id");
                                    String specialities = tSlot.getString("specialties");
                                    String qualifications = tSlot.getString("qualifications");
                                    int picture = Integer.parseInt(tSlot.getString("picture"));
                                    float rating = Float.parseFloat(tSlot.getString("rating"));
                                    Log.d("VALUE", name + qualifications + Integer.toString(picture) + Float.toString(rating));
                                    DoctorsList.GetInstance().AddToList(new Doctors(id, name, specialities, qualifications, picture, rating));
                                    Log.d("SIZE", Integer.toString(DoctorsList.GetInstance().getList().size()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.d("POSTDOCT", "DOCTOR DOWNLOAD DATA RESPONSE " + response.toString());
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

                @Override
                public Map getHeaders() throws AuthFailureError {
                    Map headers = new HashMap<>();
                    String auth = "Bearer "
                            + API.ACCESS_TOKEN;
//                headers.put("Content-Type", "application/json");
                    headers.put("Authorization", auth);
                    return headers;
                }


            };

            doctorQueue.add(getDoctorRequest);

            //TimeSlot
            RequestQueue timeslotQueue = Volley.newRequestQueue(context);
            JsonObjectRequest getTimeslotRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_TIMESLOTS, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response

                            try {

                                JSONArray array = response.getJSONArray("rows");
                                Log.d("ROWS", array.toString());
                                for(int i = 1; i < array.length(); i++) {
                                    JSONObject tSlot = array.getJSONObject(i);
                                    String id = tSlot.getString("id");
                                    Doctors doctor = null;
                                    for(Doctors doc : DoctorsList.GetInstance().getList()) {
                                        if(tSlot.getString("doctorId").equals(doc.getId())) {
                                            doctor = doc;
                                        }
                                    }
                                    String time = tSlot.getString("time");
                                    String date = tSlot.getString("date");
                                    TimeSlotsList.GetInstance().AddToList(new TimeSlots(id, doctor, time, date));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.d("POSTTIMESLOT", "TIMESLOT DOWNLOAD DATA RESPONSE " + response.toString());
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

                @Override
                public Map getHeaders() throws AuthFailureError {
                    Map headers = new HashMap<>();
                    String auth = "Bearer "
                            + API.ACCESS_TOKEN;
//                headers.put("Content-Type", "application/json");
                    headers.put("Authorization", auth);
                    return headers;
                }

            };
            timeslotQueue.add(getTimeslotRequest);

            // Appointments
            RequestQueue appointmentQueue = Volley.newRequestQueue(context);
            final JsonObjectRequest getAppointmentRequest = new JsonObjectRequest(Request.Method.GET, API.BASE_URL_APPOINTMENTS, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response

                            try {
                                JSONArray array = response.getJSONArray("rows");

                                Log.d("ROWS", array.toString());
                                for(int i = 1; i < array.length(); i++) {
                                    JSONObject tSlot = array.getJSONObject(i);
                                    /*
                                    TimeSlots timeSlot = null;
                                    for(TimeSlots slot : TimeSlotsList.GetInstance().GetList()) {
                                        if(tSlot.getString("timeslotId").equals(slot.getId())) {
                                            timeSlot = slot;

                                        }
                                    }*/
                                    String notes = tSlot.getString("notes");
                                    String userId = tSlot.getString("userId");
                                    String id = tSlot.getString("id");
                                    //if(userId.equals(THE ID OF THE USER THEN U ADD)
                                    //if(Users.getInstance().getUserId().equals(userId)) {
                                        Appointments appointment = new Appointments(id,tSlot.getString("timeslotId"), notes, userId);
                                        if(Users.getInstance().getUserId().equals(userId)) {
                                            AppointmentsList.GetInstance().AddToList(appointment);
                                        }

                                    //}

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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

                @Override
                public Map getHeaders() throws AuthFailureError {
                    Map headers = new HashMap<>();
                    String auth = "Bearer "
                            + API.ACCESS_TOKEN;
//                headers.put("Content-Type", "application/json");
                    headers.put("Authorization", auth);
                    return headers;
                }

            };
            appointmentQueue.add(getAppointmentRequest);


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

    public static boolean validEmail(String email){
        boolean valid = false;
        if (TextUtils.isEmpty(email)) { // empty
            mView.textFieldError("Required","email");
        } else if (!email.contains("@") || !email.contains(".")) { // invalid email format
            mView.textFieldError("Please input a valid email address","email");
        } else { // correct email format
            valid = true;
        }
        return valid;
    }

    public static boolean validPassword(String password){
        boolean valid = false;
        if (TextUtils.isEmpty(password)) {
            mView.textFieldError("Required","password");
        } else if (password.length() < 6) {
            mView.textFieldError("Password must be longer than six characters","password");
        } else {
            valid = true;
        }
        return valid;
    }
}
