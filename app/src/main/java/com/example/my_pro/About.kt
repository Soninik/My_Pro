package com.example.my_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class About : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportFragmentManager.beginTransaction().replace(R.id.frame1, AboutFragment()).commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var drawer = findViewById<DrawerLayout>(R.id.drawer)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        var College = arrayOf("Aboutus","C-Lanauage")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, College)
        var lv = findViewById<ListView>(R.id.lv1)
        lv.adapter = adapter

        lv.setOnItemClickListener { parent, view, position, id ->
            drawer.closeDrawers()
            when(position){
                0 -> supportFragmentManager.beginTransaction().replace(R.id.frame1, AboutFragment()).commit()
                1 -> supportFragmentManager.beginTransaction().replace(R.id.frame1, cFragment()).commit()

            }
        }
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

}