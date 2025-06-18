package com.papupro.chat

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashscreen)
        MostrarBienvenida()
    }
    fun MostrarBienvenida(){
        object: CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //TODO("Not yet implemented")
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, Inicio::class.java)
                startActivity(intent)
                finish()

            }

        }.start()
    }

}