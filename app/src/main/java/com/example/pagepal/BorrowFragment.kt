package com.example.pagepal

import LendBooksInfoAdapter
import android.content.Intent
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
import com.example.pagepal.databinding.FragmentBorrowPageBinding
import com.example.pagepal.databinding.FragmentLendbookBinding
import com.example.pagepal.models.LendBookData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BorrowFragment : Fragment(), LendBooksInfoAdapter.OnItemClickListener {
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
        _binding = FragmentBorrowPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBorrow.setOnClickListener {
            val borrowPageContainer = requireActivity().findViewById<ViewGroup>(R.id.borrowPage)
            borrowPageContainer?.removeAllViews()

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.borrowPage, LendingFragment())
            transaction.commit()
        }

        firebaseReference = FirebaseDatabase.getInstance().getReference("Lend Books Info")
        infoList = arrayListOf()

        setUpRecyclerView()
        fetchData()
    }

    private fun setUpRecyclerView() {
        binding.postedlendbooks.apply {
            setHasFixedSize(true)
            val layoutManager = GridLayoutManager(context, 2)
            binding.postedlendbooks.layoutManager = layoutManager
            val lendAdapter = LendBooksInfoAdapter(infoList, this@BorrowFragment)
            binding.postedlendbooks.adapter = lendAdapter
        }
    }

    private fun fetchData() {
        firebaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                infoList.clear()
                if (snapshot.exists()) {
                    for (lendbooksinfoSnap in snapshot.children) {
                        val lendbooksinfo = lendbooksinfoSnap.getValue(LendBookData::class.java)
                        lendbooksinfo?.let { infoList.add(it) }
                    }
                    binding.postedlendbooks.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        })

        val lendAdapter = LendBooksInfoAdapter(infoList, this)
        binding.postedlendbooks.adapter = lendAdapter
    }

    override fun onItemClick(item: LendBookData) {
        val intent = Intent(requireContext(), DetailedInfoPosted::class.java)
        intent.putExtra("book_name", item.bookname)
        intent.putExtra("author", item.author)
        intent.putExtra("pubyr", item.pubyr)
        intent.putExtra("edition", item.edition)
        intent.putExtra("isbn", item.isbn)
        intent.putExtra("caption", item.caption)
        intent.putExtra("img_uri", item.imgUri)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}