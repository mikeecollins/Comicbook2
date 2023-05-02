package ie.setu.assignment2.activites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.assignment2.R
import ie.setu.assignment2.adapters.ComicbookAdapter
import ie.setu.assignment2.adapters.ComicbookListener
import ie.setu.assignment2.databinding.ActivityComicbookListBinding
import ie.setu.assignment2.databinding.CardComicbookBinding
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.ComicbookModel

class ComicbookListActivity : AppCompatActivity(), ComicbookListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityComicbookListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicbookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ComicbookAdapter(app.comicbooks.findAll(), this)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, ComicbookActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.comicbooks.findAll().size)
            }

        }

    override fun onComicbookClick(comicbook: ComicbookModel) {
        val launcherIntent = Intent(this, ComicbookActivity::class.java)
        launcherIntent.putExtra("comicbook_edit", comicbook)
        getClickResult.launch(launcherIntent)

    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.comicbooks.findAll().size)
            }
        }
}









