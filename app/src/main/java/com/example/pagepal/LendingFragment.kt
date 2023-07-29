package com.example.pagepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.pagepal.databinding.FragmentLendPageBinding


class LendingFragment : Fragment() {

    private var _binding : FragmentLendPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLendPageBinding.inflate(inflater, container, false)

        binding.btnLendMode.setOnClickListener {
            val lendingPageContainer = requireActivity().findViewById<ViewGroup>(R.id.lendingPage)


            lendingPageContainer?.removeAllViews()

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.lendingPage, BorrowFragment())
            transaction.commit()

        }

        binding.lendBookBtn.setOnClickListener {
            val lendingPageContainer = requireActivity().findViewById<ViewGroup>(R.id.lendingPage)


            lendingPageContainer?.removeAllViews()


            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.lendingPage, LendBookFragment())
            transaction.commit()



        }


        return binding.root

    }


}