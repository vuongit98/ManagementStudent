package com.example.managementstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.managementstudent.Utils.Utils
import com.example.managementstudent.presentation.ui.Login.LoginActivity
import com.example.managementstudent.presentation.viewmodels.Login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(Looper.getMainLooper())
        if (Utils.checkNetworkActive(this@MainActivity) == true){

            handler.postDelayed( {
                loginViewModel.checkAgain()
                lifecycleScope.launchWhenCreated {
                    loginViewModel._stateLogin.collectLatest {
                        if (it.isLoading == true) {
                            Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_SHORT).show()
                        }
                        else if (it.error.length > 0) {
                            Toast.makeText(this@MainActivity, "${it.error}", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(this@MainActivity, "Successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } ,2000)
        }else{
            Toast.makeText(this, "Network is disconnected !", Toast.LENGTH_SHORT).show()
        }
    }
}