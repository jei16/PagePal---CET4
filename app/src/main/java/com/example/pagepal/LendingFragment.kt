package com.example.pagepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class LendingFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val x = inflater.inflate(R.layout.activity_lend_page, container, false)
        val btn = x.findViewById<Button>(R.id.btnLendMode)

        btn.setOnClickListener {

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.lendingPage, BorrowFragment())
            transaction.commit()

        }


        return x

    }


}