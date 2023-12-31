package com.route.contacts_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var nameEt : EditText
    private lateinit var phoneEt : EditText
    private lateinit var descriptionEt : EditText
    private lateinit var saveBtn:Button
    private lateinit var name:String
    private lateinit var phone:String
    private lateinit var des:String
    private lateinit var contactsList : ArrayList<ContactsData>
    private lateinit var myAdapter:ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMainViews()


        //contactsList.add(ContactsData("....","0000","...."))
        recyclerViewPrepare()
        saveBtn.setOnClickListener {
            validateCases()
            myAdapter.notifyItemInserted(contactsList.size-1)
            clearViews()
        }

    }
    private fun validateCases(){
        val nameInput = nameEt.text.toString().trim()
        val phoneInput = phoneEt.text.toString().trim()

        if(nameEt.text.isEmpty() || phoneEt.text.isEmpty()) {
            Toast.makeText(this,"Please Enter Number or  Name",Toast.LENGTH_SHORT).show()
        }
        des=descriptionEt.text.toString()
        if(nameEt.text.isNotEmpty() && phoneEt.text.isNotEmpty())
        {
            if(phoneInput.length==11 && nameInput.length>=3){
                phone=phoneEt.text.toString()
                name=nameEt.text.toString()
                contactsList.add(ContactsData(name,phone,des))//when variables added in contactsList go to onBindViewHolder then display in recyclerView
            }else{
                Toast.makeText(this,"Please Enter Valid Number or Valid Name",Toast.LENGTH_SHORT).show()
            }

        }
        else if(nameEt.text.isEmpty() && phoneEt.text.isEmpty()){
            Toast.makeText(this,"PLease enter name and phone number ",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"can not add data",Toast.LENGTH_SHORT).show()
        }
    }
    private fun clearViews(){
        nameEt.text.clear()
        phoneEt.text.clear()
        descriptionEt.text.clear()
        nameEt.text.isEmpty()
        phoneEt.text.isEmpty()
        descriptionEt.text.isEmpty()
    }
    private fun recyclerViewPrepare(){
        recyclerView=findViewById(R.id.recyclerViewContacts)
        contactsList=ArrayList()
        myAdapter= ContactsAdapter(contactsList)
        recyclerView.adapter=myAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        myAdapter.itemClickInterface = object : ContactsAdapter.ItemClickInterface{
            override fun onClickItem(contactsData: ContactsData, position: Int) {
                startDetailsActivity(contactsData.name,contactsData.phone,contactsData.description)
            }

        }
    }
    private fun initMainViews(){
        nameEt=findViewById(R.id.nameET)
        phoneEt=findViewById(R.id.phoneNumberET)
        descriptionEt=findViewById(R.id.descriptionET)
        saveBtn=findViewById(R.id.saveContactBtn)
    }
    private fun startDetailsActivity(name : String,phone:String,des:String){
        val intent = Intent(this@MainActivity,DetailsContactsActivity::class.java)
        intent.putExtra(constants.CONTACT_NAME,name)
        intent.putExtra(constants.CONTACT_PHONE,phone)
        intent.putExtra(constants.CONTACT_DESCRIPTION,des)
        startActivity(intent)
    }
}