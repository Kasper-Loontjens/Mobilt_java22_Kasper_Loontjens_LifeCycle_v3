package com.example.kasperlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val returnBtnLogin: Button = findViewById(R.id.returnBtnLogin)
        returnBtnLogin.setOnClickListener {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()

        val userNameLogin = findViewById<EditText>(R.id.userNameLogin)
        val passwordLogin = findViewById<EditText>(R.id.passwordLogin)
        val loginConfirmBtn: Button = findViewById(R.id.loginConfirmBtn)

        loginConfirmBtn.setOnClickListener{
            val db = Firebase.firestore

            db.collection("users").whereEqualTo("name", userNameLogin.text.toString()).get().addOnSuccessListener{ result ->
                for (document in result){
                    Log.d("bob", "${document.id} ==> ${document.data}")
                    println(document.data.get("email"))
                    if (document.data.get("password").toString() == passwordLogin.text.toString()){
//                        Log.d("bob", "Successfully logged in")

                        saveData(document.data.get("name").toString(),document.data.get("email").toString(),document.data.get("phoneNr").toString(),document.data.get("newsLetter").toString())

                        Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()

                        val profileInnn = Intent(this@LoginActivity, ProfilePageActivity::class.java)
                        startActivity(profileInnn)
                    }else{
                        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()

                    }

                }
            }.addOnFailureListener{ exception ->
                Log.w("bob", "Error getting documents", exception)
            }
        }



    }

    public fun saveData(name : String, mail: String, phoneNrr: String, newsLetter: String){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit();
        editor.apply {
            putString("name_KEY", name)
            putString("email_KEY", mail)
            putString("phone_KEY", phoneNrr)
            putString("newsLetter_KEY", newsLetter)

        }.apply()
    }
}