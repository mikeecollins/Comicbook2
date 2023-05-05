package ie.setu.assignment2.activites

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ie.setu.assignment2.R

internal class StartupActivity : AppCompatActivity(){

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.startup_screen)


        Handler().postDelayed({
            val intent = Intent(this@StartupActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}





