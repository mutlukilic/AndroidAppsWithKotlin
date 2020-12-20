package com.example.intentdemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val m1 = intent.getStringExtra("Mesaj1")
        textM1.text = m1

        val m2 = intent.getIntExtra("Mesaj2",0)
        Toast.makeText(this,"Mesaj2: $m2",Toast.LENGTH_SHORT).show()
    }

    fun secondOnClick(view: View){

        val secondIntent = Intent()
        secondIntent.putExtra("Yanit", "Sana da merhaba MainActivity!")
        setResult(Activity.RESULT_OK,secondIntent)
        finish()
    }
}

