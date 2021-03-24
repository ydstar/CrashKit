package com.example.crashkit

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import com.crash.kit.CrashLogActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.tv_make_bug->{
                var num = 10 / 0
            }
            R.id.tv_i_crash_handler->{
                startActivity(Intent(this, CrashLogActivity::class.java))
            }
        }
    }

}