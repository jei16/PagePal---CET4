package com.example.pagepal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagepal.R
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<android.os.Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val Item_Sent = 2
    val Item_Receive = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1){

            val view: View = LayoutInflater.from(context).inflate(R.layout.activity_receive_message, parent,false)
            return RecieveViewHolder(view)

        }else{

            val view: View = LayoutInflater.from(context).inflate(R.layout.activity_sent_message, parent,false)
            return SentViewHolder(view)

        }

    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){

            return Item_Sent

        }else{

            return Item_Receive

        }

    }

    override fun getItemCount(): Int {

        return messageList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        }else{
            val viewHolder = holder as RecieveViewHolder
            holder.receiveMessage.text = currentMessage.message

        }

    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)

    }

    class RecieveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)

    }
}