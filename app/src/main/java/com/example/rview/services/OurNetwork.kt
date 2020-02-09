package com.example.rview.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

object OurNetwork {
    fun getPost(context: Context, url: String, callback: (JSONArray)->Unit){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        jsonObject.put("title", "preved")
        jsonObject.put("body", "body")
        jsonObject.put("userId", "1")
        val request = jsonObject.toString()


        val jsonArray = JSONArray()




// Request a string response from the provided URL.
        /*
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                callback(response)
            },
            Response.ErrorListener { callback(it.toString()) }){
            override fun getBody(): ByteArray {
                return request.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "content-type: application/json; charset: utf-8"
            }
        }

    */
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { callback(it)
            },
            Response.ErrorListener { error ->
                println("eror")
            }
        )




// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
}