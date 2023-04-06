package com.example.registration

import android.app.Activity
import android.app.PendingIntent
import android.content.*
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.registration.Models.User
import com.example.registration.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent = Intent(this, MapActivity::class.java)
        binding.butRegister.setOnClickListener{

            showRegisterWindow()

        }
        binding.butSignIn.setOnClickListener{
            showSignInWindow()
        }

    }
    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            FirebaseAuth.getInstance().signInWithCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d(TAG, "onCodeSent:$verificationId !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")

            var storedVerificationId = verificationId
            var resendToken = token

            startActivity(intent)
            finish()
        }
    }


    private fun showRegisterWindow() {
        var dialog:AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Зарегистрироваться")
        dialog.setMessage("Введите все данные для регистраци!")
        var inflater:LayoutInflater = LayoutInflater.from(this)
        var register_window:View = inflater.inflate(R.layout.register_window, null)
        dialog.setView(register_window)

        var email: TextInputEditText = register_window.findViewById(R.id.emailField)
        var password: TextInputEditText = register_window.findViewById(R.id.passField)
        var name: TextInputEditText = register_window.findViewById(R.id.nameField)
        var phone: TextInputEditText = register_window.findViewById(R.id.phoneField)

        var phone_validate: TextInputLayout = register_window.findViewById(R.id.textInputLayoutPhone)
        phone_validate.setEndIconOnClickListener{
            Snackbar.make(binding.rootElement, "Номер проверен!",Snackbar.LENGTH_SHORT).show()
            var phones:String = "+7${phone.text}"
            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(phones)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        dialog.setNegativeButton("Отменить", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
            Snackbar.make(binding.rootElement, "Регистрация отменена!",Snackbar.LENGTH_SHORT).show()
        })
        dialog.setPositiveButton("Добавить",DialogInterface.OnClickListener { dialogInterface, i ->
            if (TextUtils.isEmpty(name.text.toString())){
                Snackbar.make(binding.rootElement, "Введите Имя",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(phone.text.toString())){
                Snackbar.make(binding.rootElement, "Введите Телефон",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(email.text.toString())){
                Snackbar.make(binding.rootElement, "Введите вашу почту",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (password.text.toString().length < 6){
                Snackbar.make(binding.rootElement, "Введите пароль, который более 5 символов",Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //регистрация пользователя
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnSuccessListener {
                    var user: User = User()
                    user.setName(name.text.toString())
                    user.setPhone(phone.text.toString())
                    user.setEmail(email.text.toString())
                    user.setPassword(password.text.toString())

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user).addOnSuccessListener {
                        Snackbar.make(binding.rootElement, "Пользователь добавлен!",Snackbar.LENGTH_SHORT).show()
                    } //Добавленрие пользователя в таблицу
                }.addOnFailureListener{
                    Snackbar.make(binding.rootElement, "Ошибка регистрации!", Snackbar.LENGTH_SHORT).show()
                }
        })

        dialog.show()
    }



    private fun showSignInWindow() {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Войти")
        dialog.setMessage("Введите даные для входа")
        var inflater: LayoutInflater = LayoutInflater.from(this)
        var sign_in_window: View = inflater.inflate(R.layout.sign_in_window, null)
        dialog.setView(sign_in_window)

        var email: TextInputEditText = sign_in_window.findViewById(R.id.emailField)
        var password: TextInputEditText = sign_in_window.findViewById(R.id.passField)

        dialog.setNegativeButton("Отменить", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
            Snackbar.make(binding.rootElement, "Вход отменен!", Snackbar.LENGTH_SHORT).show()

        })
        dialog.setPositiveButton("Войти", DialogInterface.OnClickListener { dialogInterface, i ->
            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(binding.rootElement, "Введите вашу почту", Snackbar.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (password.text.toString().length < 5) {
                Snackbar.make(
                    binding.rootElement,
                    "Введите пароль, который более 5 символов",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnSuccessListener {
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Snackbar.make(binding.rootElement, "Ошибка входа!", Snackbar.LENGTH_SHORT).show()
                }
        })
        dialog.show()
    }

}







