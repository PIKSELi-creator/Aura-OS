package com.auraos.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val btnStart = findViewById<Button>(R.id.btn_start)

        btnStart.setOnClickListener {
            // 1. Отмечаем, что первый запуск успешно пройден
            val sharedPref = getSharedPreferences("AuraSettings", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isFirstRun", false).apply()

            // 2. Переходим на наш главный рабочий стол
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрываем экран настройки
        }
    }
}
