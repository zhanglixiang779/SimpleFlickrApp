package com.example.gavinzhang.simpleflickrapp.ui.master.data.models

data class Item(
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String
)