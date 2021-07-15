package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val button1:Button=findViewById(R.id.buttonDatePicker)
        buttonDatePicker.setOnClickListener { view->
            clickDatePicker(view)
            }
    }
    fun clickDatePicker(view: View){
        //object of Calendar class
        //getInstance()-> provides calendar
        val mycalendar=Calendar.getInstance()
        val year=mycalendar.get(Calendar.YEAR)
        val month=mycalendar.get(Calendar.MONTH)
        val day=mycalendar.get(Calendar.DAY_OF_MONTH)
        //DatePickerDialog is a key class of pickers used for providing user ready-made dialog box
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            //setting the selected date format to our text view i.e tvSelectedDate
            tvSelectedDate.text = selectedDate
            //It is simpleDateFormat used to make standard form. You can change accordingly.
            //Basically we created object of SimpleDateFormat and then called methods of it
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            //Changing whole format to minutes
            val selectedDateInMinutes=theDate!!.time/60000
            // !!-> non-null exception handler. If value is null then it converts that into non-null type or just throws
            //exception instead of crashing of application
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate!!.time/60000
            val differenceInMinute=currentDateInMinutes-selectedDateInMinutes
            //divided by 60*24 to convert minutes into days
            val days=differenceInMinute/(60*24)
            tvSelectedDateInMinutes.text = days.toString()

        },year,month,day)
        dpd.datePicker.maxDate = Date().time-86400000
        dpd.show()
    }
}