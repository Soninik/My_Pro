package com.example.my_pro

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.util.regex.Pattern

class StudentRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)

        var ed3 = findViewById<EditText>(R.id.ed3)
        var edpas = findViewById<EditText>(R.id.edpas)
        var edphone = findViewById<EditText>(R.id.edphone)
        var edaddress = findViewById<EditText>(R.id.edaddress)
        var atv = findViewById<AutoCompleteTextView>(R.id.av1)
        var atv2 = findViewById<AutoCompleteTextView>(R.id.av2)
        var email = findViewById<EditText>(R.id.email)
        var tv4 = findViewById<TextView>(R.id.tv4)
        var submit = findViewById<Button>(R.id.submit)

        var helper = Registration(applicationContext)
        var db = helper.writableDatabase
        var rs: Cursor? = contentResolver.query(
            AcronymProvider.CONTENT_URI,
            arrayOf(
                AcronymProvider._ID,
                AcronymProvider.NAME,
                AcronymProvider.PASSWORD,
                AcronymProvider.MOBILE,
                AcronymProvider.ADDRESS,
                AcronymProvider.GENDER,
                AcronymProvider.CITY,
                AcronymProvider.COURSE
            ), null, null, null
        )




      edphone.addTextChangedListener(object : TextWatcher{
          override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

          }

          override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              if(mobileValidate(edphone.text.toString()))
                  submit.isEnabled = true
              else{
                  submit.isEnabled = false
                    edphone.setError("Invaild Number")
              }
          }

          override fun afterTextChanged(p0: Editable?) {

          }

      })


        var city = arrayOf(
            "Ahmedabad",
            "Surat",
            "Vadodara",
            "Rajkot",
            "Bhavnagar",
            "Jamnagar",
            "Junagadh",
            "Gandhinagar",
            "Gandhidham",
            "Radhanpar",
            "Ratanpar",
            "Anand",
            "Navsari",
            "Morbi",
            "Nadiad",
            "Surendranagar",
            "Bharuch",
            "Mehsana",
            "Bhuj",
            "Porbandar",
            "Palanpur",
            "Valsad",
            "Vapi",
            "Gondal",
            "Veraval",
            "Godhra",
            "Patan",
            "Kalol",
            "Dahod",
            "Botad",
            "Amreli",
            "Deesa",
            "Jetpur",
            "Bardoli",
            "Umreth",
            "Modasa"
        )
        var adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city)
        atv.setAdapter(adapter2)


        var course = arrayOf(
            "BBA- Bachelor of Business Administration",
            "BMS- Bachelor of Management Science",
            "BFA- Bachelor of Fine Arts",
            "BEM- Bachelor of Event Management",
            "Integrated Law Course- BA + LL.B",
            "BJMC- Bachelor of Journalism and Mass Communication",
            "BFD- Bachelor of Fashion Designing",
            "BSW- Bachelor of Social Work",
            "BBS- Bachelor of Business Studies",
            "BTTM- Bachelor of Travel and Tourism Management",
            "B.Sc- Interior Design",
            "B.Sc.- Hospitality and Hotel Administration",
            "Bachelor of Design (B. Design)",
            "Bachelor of Performing Arts",
            "BA in History",
            "BE/B.Tech- Bachelor of Technology",
            "B.Arch- Bachelor of Architecture",
            "BCA- Bachelor of Computer Applications",
            "B.Sc.- Information Technology",
            "B.Sc- Nursing",
            "BPharma- Bachelor of Pharmacy",
            "\n" +
                    "B.Sc- Interior Design",
            "BDS- Bachelor of Dental Surgery",
            "Animation, Graphics and Multimedia",
            "B.Sc. – Nutrition & Dietetics",
            "BPT- Bachelor of Physiotherapy",
            "B.Sc- Applied Geology",
            "B.Sc- Applied Geology",
            "B.Sc.- Physics",
            "B.Sc. Chemistry",
            "B.Sc. Mathematics",
            "Master of Computer Application",
            "Master of Business Administration",
            "Master of Science"
        )
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, course)
        atv2.setAdapter(adapter)

        val cv = ContentValues()
        var rg = findViewById<RadioGroup>(R.id.radiogroup)
        rg.setOnCheckedChangeListener { radioGroup, i ->
            var rb = findViewById<RadioButton>(i)
            if(rb!=null)
                cv.put(AcronymProvider.GENDER, rb.text.toString())
        }


        submit.setOnClickListener {
            if (ed3.text.toString().isNotBlank() && edpas.text.toString()
                    .isNotBlank() && edphone.text.toString().isNotBlank()
                && edaddress.text.toString().isNotBlank() && atv.text.toString()
                    .isNotBlank() && tv4.text.toString().isNotBlank()
                && atv2.text.toString().isNotBlank() && email.text.toString().isNotBlank()
            ) {


                cv.put(AcronymProvider.NAME, ed3.text.toString())
                cv.put(AcronymProvider.PASSWORD, edpas.text.toString())
                cv.put(AcronymProvider.MOBILE, edphone.text.toString())
                cv.put(AcronymProvider.ADDRESS, edaddress.text.toString())

                cv.put(AcronymProvider.COURSE, atv.text.toString())
                cv.put(AcronymProvider.CITY, atv2.text.toString())
                cv.put(AcronymProvider.EMAIL, email.text.toString())
                contentResolver.insert(AcronymProvider.CONTENT_URI, cv)
                rs?.requery()
                Toast.makeText(applicationContext, "Submit Record", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {

                Toast.makeText(applicationContext, "All Fields Are Mandetory..!", Toast.LENGTH_LONG)
                    .show()
            }
        }


        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(p0).matches())
                    email.setError("Invaild Email Address")
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun mobileValidate(toString: String): Boolean {
     var p = Pattern.compile("[7-9][0-9]{9}")
        var m = p.matcher(toString)
        return m.matches()
    }
}