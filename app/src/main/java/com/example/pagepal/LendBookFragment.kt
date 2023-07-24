package com.example.pagepal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.pagepal.databinding.FragmentLendbookBinding
import com.example.pagepal.models.LendBookData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class LendBookFragment : Fragment() {
    private var _binding : FragmentLendbookBinding? = null
    private val binding get() = _binding!!


    private lateinit var  firebaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLendbookBinding.inflate(inflater, container, false)

        firebaseRef = FirebaseDatabase.getInstance("https://synups-pagepal-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Lend Books Info")


        binding.btnBorrowMode.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.post_a_book_lendingPage, BorrowFragment())
            transaction.commit()
        }

        val y = inflater.inflate(R.layout.fragment_lendbook, container, false)

        val addPhotos: View = y.findViewById(R.id.img_of_book)
        addPhotos.setOnClickListener {
            val imagePickerFragment = ImagePickerPABLendPageFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.imgappear, imagePickerFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }



        binding.postbooklend.setOnClickListener {

            saveData()
        }
        return binding.root
    }

    private fun saveData() {
        val bookname = binding.booktitle.text.toString()
        val author = binding.author.text.toString()
        val edition = binding.edition.text.toString()
        val pubyr = binding.pubyr.text.toString()
        val isbn = binding.isbn.text.toString()
        val caption = binding.caption.text.toString()

        if (bookname.isEmpty()) binding.booktitle.error = "Please write the name of book"
        if (author.isEmpty()) binding.author.error = "Please write the author"
        if (edition.isEmpty()) binding.edition.error = "Please write the book's edition"
        if (pubyr.isEmpty()) binding.pubyr.error = "Please write the publication year"
        if (isbn.isEmpty()) binding.isbn.error = "Please write the ISBN"

        val lendbookId = firebaseRef.push().key!!
        val lendbook = LendBookData(lendbookId, bookname, author, edition, pubyr, isbn, caption)

        firebaseRef.child(lendbookId).setValue(lendbook)
            .addOnSuccessListener(){
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
              Toast.makeText(context, "Some error occured ${it.message}", Toast.LENGTH_SHORT).show()
            }





    }


}

