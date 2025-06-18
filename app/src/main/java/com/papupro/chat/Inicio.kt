package com.papupro.chat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Inicio : AppCompatActivity() {

    private lateinit var Bton_ir_registros : Button
    private lateinit var Bton_ir_logeo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        Bton_ir_registros=findViewById(R.id.Bton_ir_registros)
        Bton_ir_logeo=findViewById(R.id.Bton_ir_logeo)

        Bton_ir_registros.setOnClickListener {
          val intent = Intent(this@Inicio, RegistroActivity::class.java)
            Toast.makeText(applicationContext,"Registros", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        Bton_ir_logeo.setOnClickListener {
            val intent = Intent(this@Inicio, LoginActivity::class.java)
            Toast.makeText(applicationContext,"Login", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}