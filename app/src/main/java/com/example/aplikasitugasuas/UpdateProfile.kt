package com.example.aplikasitugasuas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aplikasitugasuas.data.AppDatabase
import com.example.aplikasitugasuas.data.User
import com.example.aplikasitugasuas.data.UserDao
import kotlinx.coroutines.launch

class UpdateProfile : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private var userId: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(16, 16, 16, 16)

        val etName = EditText(this)
        etName.hint = "Nama"
        etName.setPadding(16, 16, 16, 16)
        linearLayout.addView(etName)

        val etEmail = EditText(this)
//        etEmail.hint = "Email"
//        etEmail.setPadding(16, 16, 16, 16)
//        linearLayout.addView(etEmail)

        val etPhone = EditText(this)
        etPhone.hint = "No HP"
        etPhone.setPadding(16, 16, 16, 16)
        linearLayout.addView(etPhone)

        val etAddress = EditText(this)
        etAddress.hint = "Alamat"
        etAddress.setPadding(16, 16, 16, 16)
        linearLayout.addView(etAddress)

        val btnUpdate = Button(this)
        btnUpdate.text = "Update Profile"
        linearLayout.addView(btnUpdate)

        setContentView(linearLayout)

        val name = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("nohp")
        val address = intent.getStringExtra("alamat")

        etName.setText(name)
        etEmail.setText(email)
        etPhone.setText(phone)
        etAddress.setText(address)

        userDao = AppDatabase.getDatabase(this).userDao()

        btnUpdate.setOnClickListener {
            lifecycleScope.launch {
                val currentUser = userDao.getUserByEmail(etEmail.text.toString())

                if (currentUser != null) {
                    val updatedUser = User(
                        id = currentUser.id,
                        name = etName.text.toString(),
                        email = etEmail.text.toString(),
                        phone = etPhone.text.toString(),
                        address = etAddress.text.toString(),
                        password = currentUser.password
                    )

                    userDao.updateUser(updatedUser)
                    Toast.makeText(this@UpdateProfile, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@UpdateProfile, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
