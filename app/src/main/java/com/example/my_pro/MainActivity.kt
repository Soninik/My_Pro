package com.example.my_pro

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = Registration(applicationContext)
        var db = helper.readableDatabase

        var ed1 = findViewById<EditText>(R.id.uname)
        var ed2 = findViewById<EditText>(R.id.password)

        var button = findViewById<Button>(R.id.button)
        var register = findViewById<Button>(R.id.button2)

        button.setOnClickListener {
            //var args = listOf<String>(ed1.text.toString(), ed2.text.toString()).toTypedArray()
            var rs = db.rawQuery("Select * from StudentRegistration WHERE name = ? AND password = ?",
                arrayOf(ed1.text.toString(), ed2.text.toString()))
            if(rs.moveToNext()) {
                Toast.makeText(applicationContext, "Welcome to Access", Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext, Welcome::class.java)
                startActivity(intent)
            }
            else
                Toast.makeText(applicationContext, "Sorry not Authorozied", Toast.LENGTH_LONG).show()

        }

        register.setOnClickListener {
            var intent = Intent(applicationContext, StudentRegistration::class.java)
            startActivity(intent)
        }
        /*button.setOnClickListener {
            if(ed1.text.toString().isNotBlank() && ed2.text.toString().isNotBlank()){

            }
            else{
                Toast.makeText(applicationContext, "Sorry Enter Your Details", Toast.LENGTH_LONG).show()
            }
        }*/
    }
}