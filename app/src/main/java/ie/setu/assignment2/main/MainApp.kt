package ie.setu.assignment2.main

import android.app.Application
import android.util.Log.i
import ie.setu.assignment2.models.ComicbookJSONStore
import ie.setu.assignment2.models.ComicbookMemStore
import ie.setu.assignment2.models.ComicbookModel
import ie.setu.assignment2.models.ComicbookStore
import timber.log.Timber

class MainApp : Application() {


  lateinit var  comicbooks: ComicbookStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        comicbooks = ComicbookJSONStore(applicationContext)
        i("info", "Comicbook started")

    }
}
