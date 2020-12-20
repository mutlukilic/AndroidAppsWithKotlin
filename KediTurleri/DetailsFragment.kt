package com.example.fragmentornek2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val act:FragmentActivity? = activity

        if (act?.intent != null) {
            val kediTuru = activity!!.intent.getStringExtra("KediTuru") ?: "van"
            val aciklama = activity!!.intent.getStringExtra("Aciklama") ?: "Bir Kedi Seçiniz..."

            //Toast.makeText(activity,"$kediTuru $aciklama", Toast.LENGTH_SHORT).show()

            DetayGoster(kediTuru,aciklama)
        }
    }

    fun DetayGoster(kediTuru:String, aciklama:String){
        textKediAdi.text = kediTuru
        textDetay.text = aciklama
        imageKedi.setImageResource(resources.getIdentifier(kediTuru, "drawable", activity!!.packageName))
    }

}
