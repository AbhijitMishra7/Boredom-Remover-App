package com.example.boredomremover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val adapter = ArrayAdapter.createFromResource(this,R.array.options_list,android.R.layout.simple_list_item_1)
        spinner.adapter = adapter
        val queue= Volley.newRequestQueue(this)
        button.setOnClickListener {
            val type=spinner.selectedItem.toString()
            val url= "http://www.boredapi.com/api/activity?type=$type"
            val stringRequest = StringRequest(Request.Method.GET,url,
                Response.Listener {response ->
                    final_text.text=extract(response).toString()
                },
                Response.ErrorListener {error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                }
                )
            queue.add(stringRequest)
        }
    }
    private fun extract(response: String): String {
        val jsonObject=JSONObject(response)
        val activity=jsonObject.getString("activity")
        return activity
    }
}
