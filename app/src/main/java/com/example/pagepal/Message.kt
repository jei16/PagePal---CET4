package com.example.pagepal

class Message {

    var messages: String? = null
    var senderId: String? = null

    constructor(){}

    constructor(message: String?, senderId: String?){
        this.messages = message
        this.senderId = senderId
    }
}