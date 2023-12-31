package com.route.contacts_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsContactsActivity : AppCompatActivity() {
    lateinit var name : TextView
    lateinit var phone : TextView
    lateinit var description : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_contacts)
        name= findViewById(R.id.nameForUser)
        phone=findViewById(R.id.phoneForUser)
        description=findViewById(R.id.descriptionForUser)

        val intent : Intent = intent
        name.text=intent.getStringExtra(constants.CONTACT_NAME)
        phone.text=intent.getStringExtra(constants.CONTACT_PHONE)
        description.text=intent.getStringExtra(constants.CONTACT_DESCRIPTION)
    }
}