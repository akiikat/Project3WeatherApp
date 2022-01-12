package com.rbernard.project3weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rbernard.project3weatherapp.databinding.MainActivityBinding
import com.rbernard.project3weatherapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findButton.setOnClickListener {
            val frag = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as MainFragment
            //add other params here if needed (IE: STATE, COUNTRY)
            frag.setCity(binding.editCity.text.toString())
        }

    }//end onCreate
}//end MainActivity