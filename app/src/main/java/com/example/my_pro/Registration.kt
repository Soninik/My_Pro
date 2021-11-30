package com.example.my_pro

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Registration (context: Context): SQLiteOpenHelper(context, "Student",null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("Create table StudentRegistration(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PASSWORD, TEXT, MOBILE INTEGER, ADDRESS TEXT, GENDER TEXT, COURSE TEXT, EMAIL TEXT, CITY TEXT)")
        p0?.execSQL("Insert into StudentRegistration(NAME, PASSWORD, MOBILE, ADDRESS, GENDER, COURSE, EMAIL, CITY) values ('nikunj',123,12345678,'rajkot','M','MCA','nikunj@gmail.com','rajkot')")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}