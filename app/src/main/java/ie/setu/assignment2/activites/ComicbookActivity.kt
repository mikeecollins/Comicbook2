package ie.setu.assignment2.activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.setu.assignment2.R
import ie.setu.assignment2.databinding.ActivityComicbookBinding
import ie.setu.assignment2.helpers.showImagePicker
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.ComicbookModel
import ie.setu.assignment2.models.Location

import timber.log.Timber

class ComicbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicbookBinding
    var comicbook = ComicbookModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
    // var location = Location(52.245696, -7.139102, 15f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false


        binding = ActivityComicbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("info", "Comicbook Activity started...")




        if (intent.hasExtra("comicbook_edit")) {
            edit = true
            comicbook = intent.extras?.getParcelable("comicbook_edit")!!
            binding.comicbookTitle.setText(comicbook.title)
            binding.comicbookAuthor.setText(comicbook.author)
            binding.comicbookChapter.setText(comicbook.chapter)
            binding.btnAdd.setText(R.string.save_comicbook)
            Picasso.get()
                .load(comicbook.image)
                .into(binding.comicbookImage)
            if (comicbook.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_comicbook_image)
            }
        }



        binding.btnAdd.setOnClickListener() {
            comicbook.title = binding.comicbookTitle.text.toString()
            comicbook.author = binding.comicbookAuthor.text.toString()
            comicbook.chapter = binding.comicbookChapter.text.toString()
            if (comicbook.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_comicbook_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.comicbooks.update(comicbook.copy())
                } else {
                    app.comicbooks.create((comicbook.copy()))
                }
            }
            i("info", "Add Button Pressed: $comicbook")
            setResult(RESULT_OK)
            finish()

        }
        binding.comicbookLocation.setOnClickListener {
            i("info", "Set Location Pressed")
        }


        binding.comicbookLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)

            if (comicbook.zoom != 0f) {
                location.lat = comicbook.lat
                location.lng = comicbook.lng
                location.zoom = comicbook.zoom
            }


            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerMapCallback()
        registerImagePickerCallback()

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher, this)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_comicbook, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("info", "Got Result ${result.data!!.data}")
                            comicbook.image = result.data!!.data!!
                            Picasso.get()
                                .load(comicbook.image)
                                .into(binding.comicbookImage)
                            binding.chooseImage.setText(R.string.change_comicbook_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }


        private fun registerMapCallback() {
            mapIntentLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { result ->
                    when (result.resultCode) {
                        RESULT_OK -> {
                            if (result.data != null) {
                                Timber.i("Got Location ${result.data.toString()}")
                                val location = result.data!!.extras?.getParcelable<Location>("location")!!
                                Timber.i("Location == $location")
                                comicbook.lat = location.lat
                                comicbook.lng = location.lng
                                comicbook.zoom = location.zoom
                            } // end of if
                        }
                        RESULT_CANCELED -> { } else -> { }
                    }
                }
        }
    }