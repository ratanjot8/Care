package com.example.care.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.care.data.database.entity.CustomerData
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareApp(careViewModel: CareViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        var age by remember { mutableStateOf("") }
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        /*val dateState = rememberDatePickerState()
        DatePicker(
            state = dateState
        )*/
        Spacer(modifier = Modifier.height(16.dp))

        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter.ofPattern("MMM dd yyyy".format(pickedDate))
            }
        }
        val dateDialogState = rememberMaterialDialogState()
        Button(onClick = {dateDialogState.show()}) {
            Text(text = "Pick date")
        }
        Row {
            Text(text = "Picked Date: ")
            Text(text = pickedDate.toString())
        }
        Spacer(modifier = Modifier.height(20.dp))

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") {
                    Toast.makeText(
                        context,
                        "Clicked ok",
                        Toast.LENGTH_LONG
                    ).show()
                }
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",

            ) {
                pickedDate = it
            }
        }

        var address by remember { mutableStateOf("") }
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            careViewModel.insertDataToDb(context, CustomerData(
                name = name,
                age = age.toInt(),
                dob = pickedDate.toString(),
                address = address
            ))
        }) {
            Text(text = "Submit")
        }
    }
}
