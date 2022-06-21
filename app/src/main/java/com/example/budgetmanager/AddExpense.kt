package com.example.budgetmanager

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddExpense : AppCompatActivity() {

    private var expenseList = ArrayList<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        var intent = intent;
        val budget = intent.getStringExtra("Budget");

        val BudgetView:TextView = findViewById(R.id.displayBudget);
        BudgetView.text = "$" + budget;

        val btnAddExpense : Button = findViewById(R.id.btnAddExp);
        val btnReset : Button = findViewById(R.id.reset)

        btnAddExpense.setOnClickListener {

            val rootLayout : LinearLayout = findViewById(R.id.parentLayout)
            val subLayout = LinearLayout(this)

            subLayout.setHorizontalGravity(Gravity.CENTER)

            val newExpenseName : EditText = findViewById(R.id.expenseName);
            val newExpenseAmount : EditText = findViewById(R.id.expenseAmount);

            val expenseTitle = TextView(this)
            expenseTitle.text = newExpenseName.text.toString() + " "

            val expenseAmount = TextView(this)
            expenseAmount.text = "$" + newExpenseAmount.text.toString()

            expenseTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)
            expenseAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)

            subLayout.addView(expenseTitle)
            subLayout.addView(expenseAmount)

            subLayout.orientation = LinearLayout.HORIZONTAL

            rootLayout.addView(subLayout)

            val expenseData = Expense();
            expenseData.expenseAmount = newExpenseAmount.text.toString()
            expenseData.expenseTitle = newExpenseName.text.toString()

            expenseList.add(expenseData)

            var totalExpense : Float = 0.0F
            for (objectExpense in expenseList) {
                var expenseAmount = objectExpense.expenseAmount.toFloat()

                totalExpense += expenseAmount
            }

            var budgetFloat = budget?.toFloat()
            if (budgetFloat != null) {
                var remainingBudget = budgetFloat.minus(totalExpense)

                BudgetView.text = "$" + remainingBudget.toString()

                if (remainingBudget < 0){
                    BudgetView.setTextColor(Color.parseColor("#E71D36"));
                } else {
                    BudgetView.setTextColor(Color.parseColor("#4F86C6"));
                }
            }
        }
        btnReset.setOnClickListener{
            finish();
            startActivity(getIntent());
        }
    }
}
