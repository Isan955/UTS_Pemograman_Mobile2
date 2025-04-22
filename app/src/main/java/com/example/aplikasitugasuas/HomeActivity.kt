package com.example.aplikasitugasuas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        val nohp = intent.getStringExtra("nohp")
        val alamat = intent.getStringExtra("alamat")


        findViewById<TextView>(R.id.tvNama).text = "Nama: $nama"
        findViewById<TextView>(R.id.tvEmail).text = "Email: $email"
        findViewById<TextView>(R.id.tvNoHp).text = "No HP: $nohp"
        findViewById<TextView>(R.id.tvAlamat).text = "Alamat: $alamat"


    }
}
