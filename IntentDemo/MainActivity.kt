package com.example.intentdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val actID = 1111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Log.v("DURUM", "onCreate() fonksiyonu calisti...")
    }

    fun mainOnClick(view: View) {
        val mainIntent = Intent(this, SecondActivity::class.java)
        mainIntent.putExtra("Mesaj1", "Merhaba SecondActivity!")
        mainIntent.putExtra("Mesaj2", 12345)
        startActivityForResult(mainIntent, actID)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1111) {
            val y = data?.getStringExtra("Yanit")
            Toast.makeText(this, "Gelen yanit: $y", Toast.LENGTH_SHORT).show()
        }
    }

}