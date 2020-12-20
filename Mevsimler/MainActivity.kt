package com.example.mevsimler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Liste olayları
        lstMevsimler.setOnItemClickListener{ _, _, indis, _ ->
            Toast.makeText(this,"Liste elemanı : $indis",Toast.LENGTH_SHORT).show()

            val iv = findViewById<ImageView>(R.id.imageView)

            when (indis){
                0 -> iv.setImageResource(R.drawable.ilkbahar)
                1 -> iv.setImageResource(R.drawable.yaz)
                2 -> iv.setImageResource(R.drawable.sonbahar)
                3 -> iv.setImageResource(R.drawable.kis)
            }

        }

    }

    // RadioButton Olayları
    fun radioClick(view: View){
        val iv = findViewById<ImageView>(R.id.imageView)

        if (view == rbIlkbahar) iv.setImageResource(R.drawable.ilkbahar)
        else if (view == rbYaz) iv.setImageResource(R.drawable.yaz)
        else if (view == rbSonbahar) iv.setImageResource(R.drawable.sonbahar)
        else if (view == rbKis) iv.setImageResource(R.drawable.kis)

        Toast.makeText(this,"RadioButton Tıklandı!",Toast.LENGTH_SHORT).show()
    }
}
