package com.example.registration

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.example.registration.Models.User
import com.example.registration.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var dataBase:FirebaseDatabase
    private lateinit var users:DatabaseReference

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butRegister.setOnClickListener{
            showRegisterWindow()
        }
    }
    private fun showRegisterWindow() {
        var dialog:AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Зарегистрироваться")
        dialog.setMessage("Введите все данные для регистрации")
        var inflater:LayoutInflater = LayoutInflater.from(this)
        var register_window:View = inflater.inflate(R.layout.register_window, null)
        dialog.setView(register_window)

        var email: TextInputEditText = register_window.findViewById(R.id.emailField)
        var password: TextInputEditText = register_window.findViewById(R.id.passField)
        var name: TextInputEditText = register_window.findViewById(R.id.nameField)
        var phone: TextInputEditText = register_window.findViewById(R.id.phoneField)

        dialog.setNegativeButton("Отменить", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
        dialog.setPositiveButton("Добавить",DialogInterface.OnClickListener { dialogInterface, i ->
            if (TextUtils.isEmpty(email.text.toString())){
                Snackbar.make(binding.rootElement, "Введите вашу почту",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(name.text.toString())){
                Snackbar.make(binding.rootElement, "Введите ваше имя",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(phone.text.toString())){
                Snackbar.make(binding.rootElement, "Введите ваш телефон",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (password.text.toString().length < 5){
                Snackbar.make(binding.rootElement, "Введите пароль, который более 5 символов",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //регистрация пользователя
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnSuccessListener {
                    var user: User = User()
                    user.setEmail(email.text.toString())
                    user.setName(name.text.toString())
                    user.setPassword(password.text.toString())
                    user.setPhone(phone.text.toString())

                    FirebaseDatabase.getInstance().getReference("Users").child(user.getEmail()).setValue(user).addOnSuccessListener {
                        Snackbar.make(binding.rootElement, "Пользователь добавлен!",Snackbar.LENGTH_SHORT).show()
                    } //Добавленрие пользователя в таблицу
                }
        })

        dialog.show()
    }
}