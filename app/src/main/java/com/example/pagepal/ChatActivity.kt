package com.example.pagepal

import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagepal.adapter.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: TextView
    private lateinit var btnSend: Button
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var dbRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_pal_page)

        val name = intent.getStringExtra("name")
        val receiveruid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        dbRef = FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverRoom + senderUid
        receiverRoom = senderUid + receiveruid

        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.inputMessage)
        btnSend = findViewById(R.id.sendButton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        dbRef.child("chats").child(senderRoom!!).child("message")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){

                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)

                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        btnSend.setOnClickListener{

            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid)

            dbRef.child("chats").child(senderRoom!!).child("message").push()
                .setValue(messageObject).addOnSuccessListener {
                    dbRef.child("chats").child(receiverRoom!!).child("message").push()
                }
            messageBox.setText("")
        }
    }
}