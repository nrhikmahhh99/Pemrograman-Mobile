package com.example.api_nurhikmah

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var imageViewFoto: ImageView
    private lateinit var textViewNama: TextView
    private lateinit var textViewNIM: TextView
    private lateinit var textViewFakultas: TextView
    private lateinit var textViewProdi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewFoto = findViewById(R.id.imageViewFoto)
        textViewNama = findViewById(R.id.textViewNama)
        textViewNIM = findViewById(R.id.textViewNIM)
        textViewFakultas = findViewById(R.id.textViewFakultas)
        textViewProdi = findViewById(R.id.textViewProdi)

        val url = "https://tesapinurhikmah.free.beeceptor.com/data"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(url,
            {response ->
                val jsonData = response.getJSONObject("data")
                textViewNama.text = jsonData.getString("nama")
                textViewNIM.text = jsonData.getString("nim")
                textViewFakultas.text = jsonData.getString("fakultas")
                textViewProdi.text = jsonData.getString("prodi")

                val fotoUrl = jsonData.getString("foto")
                Glide.with(this)
                    .load(fotoUrl)
                    .into(imageViewFoto)
            }, { error ->
                textViewNama.text = "Gagal mengambil data"
            })

        requestQueue.add(jsonObjectRequest)
    }
}