package com.example.pagepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pagepal.databinding.ActivityDetailedInfoPostedBinding
import com.squareup.picasso.Picasso


class DetailedInfoPosted : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedInfoPostedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedInfoPostedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookName = intent.getStringExtra("book_name")
        val author = intent.getStringExtra("author")
        val edition = intent.getStringExtra("edition")
        val pubyr = intent.getStringExtra("pubyr")
        val isbn = intent.getStringExtra("isbn")
        val caption = intent.getStringExtra("caption")
        val imgUri = intent.getStringExtra("img_uri")

        // Now you can use these values to populate the views in the layout
        // For example, you can set their text values:
        binding.detailedBooktitle.text = bookName
        binding.detailedAuthor.text = author
        binding.detailedEdition.text = edition
        binding.detailedPubyr.text = pubyr
        binding.detailedIsbn.text = isbn
        binding.detailedCaption.text = caption

        // Load the book image using Picasso or any other image loading library
        Picasso.get().load(imgUri).into(binding.detailedBookimg)


        binding.detailedBorrow.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("position", intent.getIntExtra("position", -1))
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        }
    }

