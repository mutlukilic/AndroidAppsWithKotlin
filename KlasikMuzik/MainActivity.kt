package com.example.dynamicui

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sanatcilar = listOf<String>("bach","beethoven",
        "handel","mozart",
        "ravel","vivaldi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (sanatci:String in sanatcilar) {
            SanatciYukle(sanatci)
        }
    }

    fun SanatciYukle(sanatci_adi:String) {
        val kutu = layoutInflater.inflate(R.layout.mini_layout, null)
        val resim = kutu.findViewById<ImageButton>(R.id.resim)
        resim.setOnClickListener {
            //Toast.makeText(this,"Seçilen Sanatçı: $sanatci_adi",Toast.LENGTH_SHORT).show()
            BioGoster(sanatci_adi)
        }

        val isim:TextView = kutu.findViewById<TextView>(R.id.isim)
        val dinledim:CheckBox = kutu.findViewById<CheckBox>(R.id.dinledim)

        resim.setImageResource(resources.getIdentifier(sanatci_adi,"drawable",packageName))
        isim.text = sanatci_adi

        ana_layout.addView(kutu)
    }

    fun BioGoster(sanatci_adi: String){
        val dosya_id = resources.getIdentifier("${sanatci_adi}_bio","raw",packageName)
        val dosya_metin = resources.openRawResource(dosya_id).bufferedReader().readText()
        val eser_id = resources.getIdentifier(sanatci_adi,"raw",packageName)
        val mp = MediaPlayer.create(this,eser_id)

        mp.start()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Biyografi: $sanatci_adi")
        builder.setMessage(dosya_metin)
        builder.setPositiveButton("Tamam") { _, _ ->
            mp.stop()
        }
        val mesaj = builder.create()
        mesaj.show()

    }
}
