package com.example.contador

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val countLabel = findViewById<TextView>(R.id.countLabel)
        var count: Int = countLabel.text.toString().toInt()

        val incrementButton = findViewById<Button>(R.id.incrementButton)
        incrementButton.setOnClickListener {
            count += 1

            countLabel.text = count.toString()
        }

        val decrementButton = findViewById<Button>(R.id.decrementbutton)
        decrementButton.setOnClickListener {
            count -= 1

            if(count <= 0){
                LowerThanZeroAlertDialogFragment().show(supportFragmentManager, "ALERT_DIALOG")
                count = 0
            }

            countLabel.text = count.toString()
        }
    }

    class LowerThanZeroAlertDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                // Use the Builder class for convenient dialog construction.
                val builder = AlertDialog.Builder(it)
                builder.setMessage("Counter cannot go lower than zero!")
                    .setNegativeButton("Ok") { _, _ ->
                        // User cancelled the dialog.
                    }
                // Create the AlertDialog object and return it.
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}