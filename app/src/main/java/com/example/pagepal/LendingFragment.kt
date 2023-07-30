package com.example.pagepal

import LendBooksInfoAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pagepal.databinding.FragmentLendPageBinding
import com.example.pagepal.models.LendBookData
import com.example.pagepal.models.SharedViewModel


class LendingFragment : Fragment(), LendBooksInfoAdapter.OnItemClickListener {

    private var _binding : FragmentLendPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var lendingAdapter: LendBooksInfoAdapter
    private lateinit var lendingList: ArrayList<LendBookData>
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun addRemovedItem(item: LendBookData) {
        val lendAdapter = binding.requestedBooks.adapter as LendBooksInfoAdapter
        lendAdapter.addItem(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLendPageBinding.inflate(inflater, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
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
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Initialize your RecyclerView
        lendingList = arrayListOf()
        lendingAdapter = LendBooksInfoAdapter(lendingList, this /* or other listener */)
        binding.requestedBooks.layoutManager = GridLayoutManager(context, 2) // 2 columns
        binding.requestedBooks.adapter = lendingAdapter

        // Observe the removed items
        sharedViewModel.removedItems.observe(viewLifecycleOwner, Observer { items ->
            // Update the lending list with the new items
            lendingList.clear()
            lendingList.addAll(items)

            // Notify the adapter that the data has changed
            lendingAdapter.notifyDataSetChanged()
        })
    }
    override fun onItemClick(item: LendBookData, position: Int) {
        lendingAdapter.removeItem(position)
        // Here you can also add code to add the item to another list
        sharedViewModel.addLendingItem(item)
    }





}