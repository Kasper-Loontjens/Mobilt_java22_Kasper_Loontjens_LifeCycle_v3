package com.example.kasperlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button: Button = findViewById(R.id.returnBtnReg)
        button.setOnClickListener {
            val i = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(i)
        }
    }


    override fun onStart() {
        super.onStart()


        var editUsernameText = findViewById(R.id.editUsernameText) as EditText
        var editPasswordText = findViewById(R.id.editPasswordText) as EditText
        var editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress) as EditText
        var editTextPhone = findViewById(R.id.editTextPhone) as EditText
        var checkBox = findViewById(R.id.checkBox) as CheckBox

        val confirmRegBtn: Button = findViewById(R.id.confirmRegBtn)
        confirmRegBtn.setOnClickListener {

            var name = editUsernameText.text
            var pass = editPasswordText.text
            var email = editTextTextEmailAddress.text
            var phoneNr = editTextPhone.text
            var newsLetter = checkBox.isChecked

            if (checkEmailRegex() && name.isNotEmpty() && pass.isNotEmpty() && phoneNr.isNotEmpty()){

                val db = Firebase.firestore
                val user = hashMapOf(
                    "name" to name.toString(),
                    "password" to pass.toString(),
                    "email" to email.toString(),
                    "phoneNr" to phoneNr.toString(),
                    "newsLetter" to newsLetter.toString()
                )

                db.collection("users").add(user).addOnSuccessListener{ documentReference ->
                    Log.d("bob", "document added with id: ${documentReference.id} ")
                    Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
                    editUsernameText.setText("")
                    editPasswordText.setText("")
                    editTextTextEmailAddress.setText("")
                    editTextPhone.setText("")

                }.addOnFailureListener{ e ->
                    Log.w("bob", "error adding msg", e)
                    Toast.makeText(this, "Ya fucked it up", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Ya fucked it up", Toast.LENGTH_SHORT).show()
            }



        }

    }



    private fun checkEmailRegex(): Boolean {
        var editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress) as EditText
        var str: String = editTextTextEmailAddress.text.toString()
        // email regex
        // letters, numbers, or certain special characters,
        // followed by the "@" symbol,
        // followed by one or more characters letters, numbers, or certain special characters,
        // and ending with a "." and at least two more characters, letters only
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        return emailRegex.containsMatchIn(str)
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}