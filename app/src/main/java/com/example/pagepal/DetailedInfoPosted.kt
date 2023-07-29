package com.example.pagepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailedInfoPosted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info_posted)

        val bookName = intent.getStringExtra("book_name")
        val author = intent.getStringExtra("author")
        val edition = intent.getStringExtra("edition")
        val pubyr = intent.getStringExtra("pubyr")
        val isbn = intent.getStringExtra("isbn")
        val caption = intent.getStringExtra("caption")
        val imgUri = intent.getStringExtra("img_uri")

        // Now you can use these values to populate the views in the layout
        // For example, you can find TextViews and set their text values:
        findViewById<TextView>(R.id.detailed_booktitle).text = bookName
        findViewById<TextView>(R.id.detailed_author).text = author
        findViewById<TextView>(R.id.detailed_edition).text = edition
        findViewById<TextView>(R.id.detailed_pubyr).text = pubyr
        findViewById<TextView>(R.id.detailed_isbn).text = isbn
        findViewById<TextView>(R.id.detailed_caption).text = caption

        // Load the book image using Picasso or any other image loading library
        Picasso.get().load(imgUri).into(findViewById<ImageView>(R.id.detailed_bookimg))
    }
}