package com.example.pagepal.models

data class LendBookData(
    val lendbookId: String = "",
    val bookname: String = "",
    val author: String = "",
    val edition: String = "",
    val pubyr: String = "",
    val isbn: String = "",
    val caption: String = "",
    val imgUri: String? = null,
    val userId: String? = null

    )
