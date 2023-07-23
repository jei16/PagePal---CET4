package com.example.pagepal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LendBookFragment : Fragment() {

    private lateinit var  firebaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val y = inflater.inflate(R.layout.activity_post_a_book_lend_page, container, false)

        val bt = y.findViewById<Button>(R.id.btnLendMode)
        firebaseRef = FirebaseDatabase.getInstance().getReference("Lend Books Info")

        bt.setOnClickListener {

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.post_a_book_lendingPage, BorrowFragment())
            transaction.commit()

        }


        return y
    }


}