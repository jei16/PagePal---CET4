package com.example.pagepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class BorrowFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v =   inflater.inflate(R.layout.activity_borrow_page, container, false)

        val bt = v.findViewById<Button>(R.id.btnBorrow)
        bt.setOnClickListener {

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.borrowPage, LendingFragment())
            transaction.commit()

        }


        return v
    }


}