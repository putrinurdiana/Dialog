package com.example.dialog.date

import android.app.DatePickerDialog as dpd
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DatePicker : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year =calendar.get(Calendar.YEAR)
        val month =calendar.get(Calendar.MONTH)
        val day =calendar.get(Calendar.DAY_OF_MONTH)
        return dpd(
            requireActivity(),
            activity as OnDateSetListener,
            year, month, day
        )
//        tidak muncul tadi karena di bagian main activity, datepicker belum menjadi bagiain main activty


    }
}