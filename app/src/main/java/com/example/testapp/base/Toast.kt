package com.example.testapp.base

import android.content.Context
import android.widget.Toast

fun String.show(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}