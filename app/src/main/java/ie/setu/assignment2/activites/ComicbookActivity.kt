package ie.setu.assignment2.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import ie.setu.assignment2.R
import ie.setu.assignment2.databinding.ActivityComicbookBinding
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.ComicbookModel
import timber.log.Timber

class ComicbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicbookBinding
    var comicbook = ComicbookModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityComicbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        if (intent.hasExtra("placemark_edit")) {
            comicbook = intent.extras?.getParcelable("placemark_edit")!!
            binding.comicbookTitle.setText(comicbook.title)
            binding.comicbookAuthor.setText(comicbook.author)
            binding.comicbookChapter.setText(comicbook.chapter)
        }

        i("info","Placemark Activity started...")

        binding.btnAdd.setOnClickListener() {
            comicbook.title = binding.comicbookTitle.text.toString()
            comicbook.author = binding.comicbookAuthor.text.toString()
            comicbook.chapter = binding.comicbookChapter.text.toString()
            if (comicbook.title.isNotEmpty()) {
               //app.comicbooks.add(comicbook.copy())
                app.comicbooks.create(comicbook.copy())
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_comicbook, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}
