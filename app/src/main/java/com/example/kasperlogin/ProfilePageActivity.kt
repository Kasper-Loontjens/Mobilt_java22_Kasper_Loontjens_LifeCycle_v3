package com.example.kasperlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProfilePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val returnBtnProfile: Button = findViewById(R.id.returnBtnProfile)
        returnBtnProfile.setOnClickListener {
            val i = Intent(this@ProfilePageActivity, MainActivity::class.java)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()

        loadData()
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

        loadData()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedName:String = sharedPreferences.getString("name_KEY", "default")!!
        val savedPhoneNr:String = sharedPreferences.getString("phone_KEY", "default")!!
        val savedEmail:String = sharedPreferences.getString("email_KEY", "default")!!
        val savedNewsLetter:String = sharedPreferences.getString("newsLetter_KEY", "default")!!

        var profileName = findViewById<TextView>(R.id.profileName)
        var profileEmail = findViewById<TextView>(R.id.profileEmail)
        var profilePhoneNr = findViewById<TextView>(R.id.profilePhoneNr)
        var profileNewsLetter = findViewById<TextView>(R.id.profileNewsLetter)

        profileName.text = ("Userame: "+savedName)
        profileEmail.text = ("Email: " + savedEmail)
        profilePhoneNr.text = ("Phone nr: " + savedPhoneNr)
        profileNewsLetter.text = ("Agreed to newsletter: " + savedNewsLetter)

    }
}


