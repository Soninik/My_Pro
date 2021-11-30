package com.example.my_pro

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import java.net.URL

class AcronymProvider: ContentProvider() {
    companion object{
        val PROVIDER_NAME = "com.example.my_pro/AcronymProvider"
        val URL = "content://$PROVIDER_NAME/StudentRegistration"
        val CONTENT_URI = Uri.parse(URL)

        var _ID = "_id "
        var NAME = "NAME"
        var PASSWORD = "PASSWORD"
        var MOBILE = "MOBILE"
        var ADDRESS = "ADDRESS"
        var GENDER = "GENDER"
        var COURSE = "COURSE"
        var CITY = "CITY"
        var EMAIL = "EMAIL"
    }
    lateinit var db : SQLiteDatabase

    override fun onCreate(): Boolean {
       var helper = Registration(context!!)
        db = helper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return db.query("StudentRegistration", p1, p2, p3, null, null, p4)
    }

    override fun getType(p0: Uri): String? {
        return  "vnd.andriod.cursor.dir/vnd.example.StudentRegistration"
    }

    override fun insert(p0: Uri, cv: ContentValues?): Uri? {
        db.insert("StudentRegistration", null, cv);
        context?.contentResolver?.notifyChange(p0,null);
        return p0;
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        var count : Int = db.delete("StudentRegistration", p1, p2)
        context?.contentResolver?.notifyChange(p0,null);
        return  count
    }

    override fun update(p0: Uri, cv: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var count : Int = db.update("StudentRegistration", cv, p2, p3)
        context?.contentResolver?.notifyChange(p0,null);
        return  count
    }
}