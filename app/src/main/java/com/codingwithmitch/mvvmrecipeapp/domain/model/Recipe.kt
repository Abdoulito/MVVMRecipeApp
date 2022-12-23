package com.codingwithmitch.mvvmrecipeapp.domain.model

data class Recipe(
    val cookingInstructions: String?=null,
    val date_added: String?=null,
    val date_updated: String?=null,
    val description: String?=null,
    val featuredImage: String?=null,
    val id: Int?=0,
    val ingredients: List<String> = listOf() ,
    val publisher: String?=null,
    val rating: Int?=0,
    val sourceUrl: String?=null,
    val title: String?=null
)