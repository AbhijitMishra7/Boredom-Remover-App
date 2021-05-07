package com.example.boredomremover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.first_button);
        val button2=findViewById<Button>(R.id.memeButton)
        val intent=Intent(this,MainActivity2::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
        val intent2=Intent(this,MainActivity3::class.java)
        button2.setOnClickListener {
            startActivity(intent2)
        }
    }

}