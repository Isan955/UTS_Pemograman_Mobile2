package com.example.aplikasitugasuas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aplikasitugasuas.data.AppDatabase
import com.example.aplikasitugasuas.data.UserDao
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userDao = AppDatabase.getDatabase(this).userDao()
        email = intent.getStringExtra("email").toString()

        findViewById<Button>(R.id.btnUpdateProfile).setOnClickListener {
            val intent = Intent(this, UpdateProfile::class.java).apply {
                putExtra("email", email)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val user = userDao.getUserByEmail(email)
            user?.let {
                findViewById<TextView>(R.id.tvNama).text = "Nama: ${it.name}"
                findViewById<TextView>(R.id.tvEmail).text = "Email: ${it.email}"
                findViewById<TextView>(R.id.tvNoHp).text = "No HP: ${it.phone}"
                findViewById<TextView>(R.id.tvAlamat).text = "Alamat: ${it.address}"
            }
        }
    }
}

