package com.example.sqlitedemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Uygulama ilk çalıştırıldığında sadece 1 kez çağrılmalı!!
        //dbOlustur()
    }

    fun buttonClick(view: View){

        val db = openOrCreateDatabase("vizesonuc", Context.MODE_PRIVATE,null)

        val sorgu = "SELECT * FROM notlar WHERE numara LIKE \'${editTextOgrNo.text}%\'"

        Log.d("SORGU",sorgu)

        val cr = db.rawQuery(sorgu,null)

        var sonuclistesi =""

        if (cr.count>0) {
            Toast.makeText(this,"${cr.count} kayıt bulundu...",Toast.LENGTH_LONG).show()

            while (cr.moveToNext()) {
                val not = cr.getInt(cr.getColumnIndex("vize"))
                val isim = cr.getString(cr.getColumnIndex("ad"))
                val soyisim = cr.getString(cr.getColumnIndex("soyad"))
                sonuclistesi += "Öğrenci: $isim $soyisim --> Notu:${not.toString()}\n"
            }
            textNot.text = sonuclistesi
        } else {
            Toast.makeText(this,"Belirtilen Numaralı Öğrenci Bulunamadı!",Toast.LENGTH_LONG).show()
        }
    }

    fun dbOlustur(){
        val db = openOrCreateDatabase("vizesonuc", Context.MODE_PRIVATE,null)

        val sc = Scanner(resources.openRawResource(R.raw.vizesonuclari))

        while(sc.hasNextLine()){
            val sorgu = sc.nextLine()
            Log.d("SORGU", sorgu)
            db.execSQL(sorgu)
        }
    }
}
