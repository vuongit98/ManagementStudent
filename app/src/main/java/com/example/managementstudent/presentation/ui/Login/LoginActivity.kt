package com.example.managementstudent.presentation.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.managementstudent.databinding.ActivityLoginBinding
import com.example.managementstudent.presentation.viewmodels.Login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.regex.Pattern
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLoginBinding
    val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        lifecycleScope.launchWhenCreated {
            loginViewModel._stateLogin.collectLatest {
                if (it.isLoading == true) {
                    Toast.makeText(this@LoginActivity, "Loading...", Toast.LENGTH_SHORT).show()

                }
                else if (it.error.length > 0) {
                    Toast.makeText(this@LoginActivity, "${it.error}", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@LoginActivity, "Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewBinding.btnLogin.setOnClickListener {
            val strEmail = viewBinding.edtEmail.text.toString().trim()
            val strPassword = viewBinding.edtPassword.text.toString().trim()
            if (!TextUtils.isEmpty(strEmail)&&Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {

                if (!TextUtils.isEmpty(strPassword)) {
                    loginViewModel.loginAccount(strEmail,strPassword)
                }else {
                    Toast.makeText(this, "You are wrong password ", Toast.LENGTH_SHORT).show()

                }
            }else {
                Toast.makeText(this, "You are wrong email format", Toast.LENGTH_SHORT).show()
            }
        }
    }
}