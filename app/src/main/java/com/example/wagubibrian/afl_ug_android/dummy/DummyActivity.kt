package com.example.wagubibrian.afl_ug_android.dummy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.R

class DummyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        Toast.makeText(this, " Successfully Login", Toast.LENGTH_LONG).show()
    }

}
