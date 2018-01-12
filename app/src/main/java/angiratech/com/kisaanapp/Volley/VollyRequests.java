package angiratech.com.kisaanapp.Volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import angiratech.com.kisaanapp.Activity.KishanAplication;
import angiratech.com.kisaanapp.Interface.ResponceLisnter;
import angiratech.com.kisaanapp.Utility.MyLog;

public class VollyRequests {

    public void MakePutRequest(final String Tag, final String url, final StringRequest  jsonObject, final ResponceLisnter responceLisnter) {
        MyLog.ShowLog("Url", url);

        StringRequest  putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response.toString());
                        responceLisnter.getResponce(response.toString(), Tag);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        KishanAplication.getInstance().getRequestQueue().add(putRequest);
        putRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public void MakeStrGetRequest(final String Tag, String url, final ArrayList<RequestModel> list, final ResponceLisnter responceLisnter) {
        MyLog.ShowLog("Url", url);

        StringRequest sr = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responceLisnter.getResponce(response, Tag);
                Log.e("RESPONSE", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    responceLisnter.getResponceError(VollyError.TIMEOUT_ERROR, Tag);
                } else if (error instanceof AuthFailureError) {
                    responceLisnter.getResponceError(VollyError.AUTH_ERROR, Tag);
                } else if (error instanceof ServerError) {
                    responceLisnter.getResponceError(VollyError.SERVER_ERROR, Tag);
                } else if (error instanceof NetworkError) {
                    responceLisnter.getResponceError(VollyError.NETWORK_ERROR, Tag);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        MyLog.ShowLog(list.get(i).getKey(), list.get(i).getValue());
                        params.put(list.get(i).getKey(), list.get(i).getValue());
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put(WebKeys.Headerkey, WebKeys.HeaderValue);
                return params;
            }
        };
        KishanAplication.getInstance().getRequestQueue().add(sr);
        sr.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void MakeStrPostRequest(final String Tag, String url, final ArrayList<RequestModel> list, final ResponceLisnter responceLisnter) {
        MyLog.ShowLog("Url", url);

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responceLisnter.getResponce(response, Tag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    responceLisnter.getResponceError(VollyError.TIMEOUT_ERROR, Tag);
                } else if (error instanceof AuthFailureError) {
                    responceLisnter.getResponceError(VollyError.AUTH_ERROR, Tag);
                } else if (error instanceof ServerError) {
                    responceLisnter.getResponceError(VollyError.SERVER_ERROR, Tag);
                } else if (error instanceof NetworkError) {
                    responceLisnter.getResponceError(VollyError.NETWORK_ERROR, Tag);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        MyLog.ShowLog(list.get(i).getKey(), list.get(i).getValue());
                        params.put(list.get(i).getKey(), list.get(i).getValue());
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put(WebKeys.Headerkey, WebKeys.HeaderValue);
                return params;
            }
        };
        KishanAplication.getInstance().getRequestQueue().add(sr);
        sr.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

}

