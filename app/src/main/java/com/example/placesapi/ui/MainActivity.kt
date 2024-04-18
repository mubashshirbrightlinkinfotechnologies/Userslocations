package com.example.placesapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.placesapi.BottomSheetFragment
import com.example.placesapi.databinding.ActivityMainBinding
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = MainViewModel()
        setupUi()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.errorResult.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loginResponse.observe(this) { response ->
            RetrofitClient.JWT_TOKEN = response.jwtToken ?: ""
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupUi() {
        with(binding) {
            loginBtn.setOnClickListener {
                viewModel.login(
                    email = etUsernameInput.text.toString().trim(),
                    password = etPasswordInput.text.toString()
                )
            }
        }
    }
}
