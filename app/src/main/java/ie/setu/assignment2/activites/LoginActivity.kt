package ie.setu.assignment2.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.assignment2.R
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.ComicbookModel
import ie.setu.assignment2.models.UserModel
import timber.log.Timber.i

class LoginActivity: AppCompatActivity() {

    private lateinit var comicbookusernameEditText: EditText
    private lateinit var comicbookpasswordEditText: EditText
    private lateinit var logincomicbook: Button
    private lateinit var registercomicbook: Button
    var userModel = UserModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        app = application as MainApp
        comicbookusernameEditText = findViewById(R.id.comicbook_password)
        comicbookpasswordEditText = findViewById(R.id.comicbook_username)
        logincomicbook = findViewById(R.id.login_comicbook)
        registercomicbook = findViewById(R.id.register_comicbook)

        logincomicbook.setOnClickListener {
          val comicbookusername = comicbookusernameEditText.text.toString()
          val comicbookpassword = comicbookpasswordEditText.text.toString()
            userModel.comicbookusername = comicbookusername
            userModel.comicbookpassword=comicbookpassword
            app.users.create(userModel)
            if (comicbookusername.isNotEmpty()) {
                i("login button pressed: $comicbookusername")
            } else {
                Snackbar.make(it, "Please enter your credentials", Snackbar.LENGTH_LONG)
                    .show()
            }
            var foundUser = app.users.findUser(comicbookusername,comicbookpassword)
            if (foundUser!=null) {

                val intent = Intent(this, ComicbookActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()

            }

        }

        registercomicbook.setOnClickListener {





                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)

            }
        }

}