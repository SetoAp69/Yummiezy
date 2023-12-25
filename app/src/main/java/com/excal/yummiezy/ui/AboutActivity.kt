package com.excal.yummiezy.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.excal.yummiezy.R
import com.excal.yummiezy.databinding.ActivityAboutBinding
import com.excal.yummiezy.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(){
            var intent: Intent = Intent(this@AboutActivity,MainActivity::class.java)
            startActivity(intent)

        }



    }

}//