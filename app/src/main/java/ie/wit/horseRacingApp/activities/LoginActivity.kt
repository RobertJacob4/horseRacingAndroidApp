package ie.wit.horseRacingApp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import ie.wit.horseRacingApp.R

class LoginActivity : AppCompatActivity() {

//    private val emailLiveData = MutableLiveData<String>()
//    private val passwordLiveData = MutableLiveData<String>()
//    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
//        this.value = false
//
//        addSource(emailLiveData){ email ->
//            val password = passwordLiveData.value
//            this.value = validateForm(email, password)
//        }
//
//        addSource(passwordLiveData) { password ->
//            val email = emailLiveData.value
//            this.value = validateForm(email, password)
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login)

        loginButton.setOnClickListener() {
            val intent = Intent(this, HorseRaceActivity::class.java)
            startActivity(intent)
        }
    }
}