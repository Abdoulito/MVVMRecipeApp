package com.codingwithmitch.mvvmrecipeapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.codingwithmitch.mvvmrecipeapp.domain.model.Recipe
import com.codingwithmitch.mvvmrecipeapp.domain.network.model.RecipeDtoMapper

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*    setContent {
        Column() {
            Button(onClick = { *//*TODO*//* }) {
                Text(text = "THIS BUTTON IS COOL")
            }
        }
    } */
     setContentView(R.layout.activity_main)

        val mapper = RecipeDtoMapper()
        val recipe = Recipe()
        val recipeDTO = mapper.mapToEntity(recipe)
    }
}