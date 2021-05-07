package com.example.boredomremover

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main3.*
import javax.sql.DataSource

class MainActivity3 : AppCompatActivity() {
    var currentImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        loadMeme()
    }
    fun loadMeme(){
        progressBar.visibility= View.VISIBLE
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
        Response.Listener { response ->
            val currentImageUrl=response.getString("url")
            Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    progressBar.visibility=View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?,
                                             target: Target<Drawable>?,
                                             dataSource: com.bumptech.glide.load.DataSource?,
                                             isFirstResource: Boolean): Boolean
                {
                    progressBar.visibility=View.GONE
                    return false
                }
            }).into(findViewById(R.id.imageView))
        },
                Response.ErrorListener { error ->
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
                }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    fun shareOnClick(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"$currentImageUrl")
        val chooser = Intent.createChooser(intent,"Share this meme using")
        startActivity(chooser)

    }
    fun nextOnClick(view: View) {
        loadMeme()
    }
}