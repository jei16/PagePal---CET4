package com.example.pagepal.models

import androidx.lifecycle.ViewModel

class LendBookViewModel: ViewModel() {
    var bookname: String? = ""
    var author: String? = ""
    var edition: String? = ""
    var pubyr: String? = ""
    var isbn: String? = ""
    var caption: String? = ""
}