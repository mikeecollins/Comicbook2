package ie.setu.assignment2.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.assignment2.R
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.ComicbookModel
import ie.setu.assignment2.models.UserModel
import timber.log.Timber

class RegisterActivity : AppCompatActivity() {

    private lateinit var registercomicbook: Button
    private lateinit var comicbookusernameEditText: EditText
    private lateinit var comicbookpasswordEditText: EditText
    lateinit var app: MainApp
    var userModel = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        app = application as MainApp
        comicbookusernameEditText = findViewById(R.id.comicbook_usernameREG)
        comicbookpasswordEditText = findViewById(R.id.comicbook_passwordREG)
        registercomicbook = findViewById(R.id.register_comicbookREG)




        registercomicbook.setOnClickListener {
            val comicbookusername = comicbookusernameEditText.text.toString()
            val comicbookpassword = comicbookpasswordEditText.text.toString()
            if (comicbookusername.isNotEmpty()) {
                Timber.i("Register button pressed: $comicbookusername")
                userModel.comicbookusername = comicbookusername
                userModel.comicbookpassword=comicbookpassword
                app.users.create(userModel)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Please enter your details", Snackbar.LENGTH_LONG)
                    .show()
            }

        }





    }

}