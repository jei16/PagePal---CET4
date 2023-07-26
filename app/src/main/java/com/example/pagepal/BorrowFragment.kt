package com.example.pagepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagepal.adapter.LendBooksInfoAdapter
import com.example.pagepal.databinding.FragmentBorrowPageBinding
import com.example.pagepal.databinding.FragmentLendbookBinding
import com.example.pagepal.models.LendBookData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BorrowFragment : Fragment() {
    private var _binding : FragmentBorrowPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var infoList: ArrayList<LendBookData>
    private lateinit var firebaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBorrowPageBinding.inflate(inflater, container, false)


        binding.btnBorrow.setOnClickListener {
            val borrowPageContainer = requireActivity().findViewById<ViewGroup>(R.id.borrowPage)

            // Clear the container's views to reset its state before adding the new fragment
            borrowPageContainer?.removeAllViews()

            // Replace the fragment without inheriting the attributes of the replaced one
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.borrowPage, LendingFragment())
            transaction.commit()
        }
        firebaseReference = FirebaseDatabase.getInstance().getReference("Lend Books Info")
        infoList = arrayListOf()

        fetchData()



        binding.postedlendbooks.apply {
            setHasFixedSize(true)
            val layoutManager = GridLayoutManager(context, 2)
            binding.postedlendbooks.layoutManager = layoutManager
        }

        return binding.root
    }

    private fun fetchData() {
        firebaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                infoList.clear()
                if (snapshot.exists()){
                    for (lendbooksinfoSnap in snapshot.children){
                        val lendbooksinfo = lendbooksinfoSnap.getValue(LendBookData::class.java)
                        infoList.add(lendbooksinfo!!)

                    }
                }
                val lendAdapter = LendBooksInfoAdapter(infoList)
                binding.postedlendbooks.adapter = lendAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, " error : $error", Toast.LENGTH_SHORT).show()
            }

        })
    }


}