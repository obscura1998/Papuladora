package com.papupro.chat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase
import com.papupro.chat.ui.theme.ChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ChatTheme {
                Papuladora()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Papuladora() {
    val context = LocalContext.current

    var pares by remember { mutableStateOf(listOf(2, 4, 6, 8, 10, 15)) }
    val dbRef = FirebaseDatabase.getInstance().getReference("pares")

    LaunchedEffect(Unit) {
        dbRef.setValue(pares)
    }

    var numeroSeleccionado by remember { mutableStateOf("") }
    var indexSeleccionado by remember {  mutableStateOf(-1) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("No Estes mamando amigo") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Apoco si muy vergas amigo", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            pares.forEachIndexed { index, numero ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("• $numero")
                    Button(onClick = {
                        numeroSeleccionado = numero.toString()
                        indexSeleccionado = index
                    }) {
                        Text("Editar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = numeroSeleccionado,
                onValueChange = { numeroSeleccionado = it },
                label = { Text("Nuevo número par") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val nuevo = numeroSeleccionado.toIntOrNull()
                    if (nuevo != null && nuevo % 2 == 0 && indexSeleccionado != -1) {
                        val nuevaLista = pares.toMutableList()
                        nuevaLista[indexSeleccionado] = nuevo
                        pares = nuevaLista
                        dbRef.setValue(pares)
                        Toast.makeText(context, "Actualizado en Firebase", Toast.LENGTH_SHORT).show()
                        numeroSeleccionado = ""
                        indexSeleccionado = -1
                    } else {
                        Toast.makeText(context, "Solo se permiten números pares", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambio")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatTheme {
        Papuladora()
    }
}
