package com.example.dialog

import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dialog.databinding.ActivityMainBinding
import com.example.dialog.date.DatePicker
import java.util.Locale
import com.example.dialog.date.TimePicker as Tp

class MainActivity : AppCompatActivity(), OnDateSetListener, OnTimeSetListener {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringCountry = resources.getStringArray(R.array.Country);
        val stringProvince = listOf(
            "Jakarta",
            "Jawa Timur",
            "Jambi"
        )
        with(binding){
            val adapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item, stringCountry)
            spinnerCountry.adapter =adapter

            spinnerProvinces.adapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_list_item_1, stringProvince)

            spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(this@MainActivity,
                    stringCountry.get(p2),Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

//            pakai init biar mendefinisikan pertama kali harus apa
            datePickerActions.init(
                datePickerActions.year,
                datePickerActions.month,
                datePickerActions.dayOfMonth
            ){view , year, month, day ->
                val selectedDate = "$day/${month+1}/$year"
                Toast.makeText(this@MainActivity, selectedDate,
                    Toast.LENGTH_SHORT).show()

            }
            timePicker.setOnTimeChangedListener { view, hour, minute ->
                val selectedTime = String.format(
                    Locale.getDefault(),
                    "%02d:%02d", hour, minute
                )
                Toast.makeText(this@MainActivity,
                selectedTime, Toast.LENGTH_SHORT).show()
            }

        }



    }

    fun showCalender(view: View) {
        DatePicker().show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(p0: android.widget.DatePicker?, p1: Int, p2: Int, p3: Int) {
        Toast.makeText(this,
            "$p3/${p2+1}/$p1", Toast.LENGTH_SHORT).show()
    }

    fun showTimePicker(view: View) {
        Tp().show(supportFragmentManager, "TimePicker")
    }
// extends bisa dipake semua, implement extend method aja tapi kosong
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
    Toast.makeText(this,
        String.format(
            Locale.getDefault(),
            "%02d:%02d", p1, p2), Toast.LENGTH_SHORT).show()

    }
    fun showAlert(view: View){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sign Out")
        builder.setMessage("Are you sure to sign out?")
        builder.setPositiveButton("Yes"){_,_->
            finish()
        }
        builder.setNeutralButton("No"){dialog,_->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()


    }
}