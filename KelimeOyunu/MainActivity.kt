package com.example.kelimeoyunu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    val sozluk = HashMap<String,String>()
    val kelimelistesi = ArrayList<String>()
    val secenekler = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sozlukOku()
        listeyiDoldur()

        listSoru.setOnItemClickListener{ _, _, indeks, _ ->
            if(sozluk[textKelime.text] == secenekler[indeks]){
                Toast.makeText(this,"DOĞRU YANIT",Toast.LENGTH_SHORT).show()

                listeyiDoldur()
            } else {
                Toast.makeText(this,"YANLIŞ YANIT",Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun listeyiDoldur(){

        val r = Random()
        val indis = r.nextInt(kelimelistesi.size)
        val secilenkelime = kelimelistesi[indis]
        textKelime.text = secilenkelime

        secenekler.clear()
        secenekler.add(sozluk[secilenkelime]!!)

        kelimelistesi.shuffle()
        for (digersecenek in kelimelistesi.subList(0,5)){
             if (digersecenek == secilenkelime || secenekler.size == 5)  {
                 continue
             }
            secenekler.add(sozluk[digersecenek]!!)
        }

        secenekler.shuffle()

        val aa = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,secenekler)

        listSoru.adapter=aa
    }

    fun sozlukOku() {

        val sozlukDosyasi = resources.openRawResource(R.raw.anlamlar)
        val okuyucu = Scanner(sozlukDosyasi.reader())


        var satir = ""

        while (okuyucu.hasNextLine()){
            satir = okuyucu.nextLine()
            val kelime = satir.split("**")
            kelimelistesi.add(kelime[0])
            sozluk.put(kelime[0],kelime[1])
            //Log.d("KONTROL",kelime[1])
        }

        okuyucu.close()
    }
}
