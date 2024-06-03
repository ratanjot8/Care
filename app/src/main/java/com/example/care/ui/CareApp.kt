package com.example.care.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.care.data.database.entity.CustomerData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareApp(careViewModel: CareViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        var age by remember { mutableStateOf("") }
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        val dateState = rememberDatePickerState()
        DatePicker(
            state = dateState
        )
        Spacer(modifier = Modifier.height(16.dp))

        var address by remember { mutableStateOf("") }
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            careViewModel.insertDataToDb(context, CustomerData(
                name = name,
                age = age.toInt(),
                dob = dateState.toString(),
                address = address
            ))
        }) {
            Text(text = "Submit")
        }
    }
}
