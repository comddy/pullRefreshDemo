package com.example.testapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapp.MyApplication
import com.example.testapp.R
import com.example.testapp.databinding.ActivityLoginBinding

class LoginActivity : Activity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.editName.text.toString() == "123" && binding.editPwd.text.toString() == "123") {
                MyApplication.user = MyApplication.User("123", "123")
                Toast.makeText(MyApplication.context, "登录成功", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(MyApplication.context, "用户名或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}