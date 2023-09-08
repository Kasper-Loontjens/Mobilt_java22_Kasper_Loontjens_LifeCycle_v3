package com.example.kasperlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val registerBtn: Button = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java);
            startActivity(intent);
        }

        val loginBtn: Button = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java);
            startActivity(intent);
        }

        val profileBtn: Button = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfilePageActivity::class.java);
            startActivity(intent);
        }
    }
}