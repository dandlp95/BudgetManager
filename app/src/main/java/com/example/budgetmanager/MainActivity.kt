package com.example.budgetmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddBudget : Button = findViewById(R.id.btnAddBudget)
        val budgetView : EditText = findViewById(R.id.budgetAmount)

        btnAddBudget.setOnClickListener{

            addBudget(budgetView)
        }
    }

    fun addBudget(budget : EditText){

        var textBudget = budget.text.toString();

        val intent = Intent(this, AddExpense::class.java)
        intent.putExtra("Budget", textBudget);
        startActivity(intent);
    }
}