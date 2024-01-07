package com.example.signuppagewithdatabse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.name)
        val usernameInput = findViewById<EditText>(R.id.username)
        val emailInput = findViewById<EditText>(R.id.email)
        val passInput = findViewById<EditText>(R.id.password)
        val signUpbtn = findViewById<Button>(R.id.button)
        val signInText = findViewById<TextView>(R.id.alreadySignedIn)

        signUpbtn.setOnClickListener {
            val name = nameInput.text.toString()
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passInput.text.toString()

            val list = User(name, username, email,password)

            db = FirebaseDatabase.getInstance().getReference("user")

            db.child(username).setValue(list).addOnSuccessListener {
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "failed to register user", Toast.LENGTH_SHORT).show()
            }
        }

        signInText.setOnClickListener {
            val intent = Intent(this@MainActivity, SignInpage::class.java)
            startActivity(intent)
        }
    }
}