package com.route.contacts_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private var contactsList:ArrayList<ContactsData>)
    : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    lateinit var itemClickInterface:ItemClickInterface

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var nameRV : TextView = itemView.findViewById(R.id.nameRV)
         var phoneRV:TextView = itemView.findViewById(R.id.phoneRV)
         var descriptionRV:TextView = itemView.findViewById(R.id.descriptionRV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View =
            LayoutInflater.from(parent.context).inflate(R.layout.contactsitems,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacts:ContactsData = contactsList[position]
        holder.nameRV.text=contacts.name
        holder.phoneRV.text=contacts.phone
        holder.descriptionRV.text=contacts.description

        holder.nameRV.setOnClickListener(View.OnClickListener {
            itemClickInterface.onClickItem(contacts,position)
        })
    }

    interface ItemClickInterface{
        fun onClickItem(contactsData : ContactsData , position: Int)
    }

}