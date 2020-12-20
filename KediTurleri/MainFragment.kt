package com.example.fragmentornek2


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableRow
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.collections.HashMap


class MainFragment : Fragment() {

    val liste = HashMap<String,String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    fun kediSec(view: View){

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            val cagri = Intent(activity,DetailsActivity::class.java)
            cagri.putExtra("KediTuru",view.tag.toString())
            cagri.putExtra("Aciklama",liste[view.tag.toString()])
            startActivity(cagri)
        } else {
            val detFrg:DetailsFragment = fragmentManager?.findFragmentById(R.id.fragmentDetails) as DetailsFragment
            detFrg.DetayGoster(view.tag.toString(),liste[view.tag.toString()]!!)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dosya = resources.openRawResource(R.raw.data)
        val s = Scanner(dosya)

        while (s.hasNextLine()){
            val satir = s.nextLine()
            val dizi = satir.split(":")
            liste.put(dizi[0],dizi[1])
        }
        s.close()
        dosya.close()

        for (i:Int in 0..tableLayout.childCount-1) {
            val satir:TableRow = tableLayout.getChildAt(i) as TableRow
            for (j in 0..satir.childCount-1){
                val btn:ImageButton = satir.getChildAt(j) as ImageButton
                btn.setOnClickListener {
                    kediSec(it)
                }
            }
        }

    }

}
