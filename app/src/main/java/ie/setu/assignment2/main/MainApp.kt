package ie.setu.assignment2.main

import android.app.Application
import android.util.Log.i
import ie.setu.assignment2.models.ComicbookMemStore
import ie.setu.assignment2.models.ComicbookModel
import timber.log.Timber

class MainApp : Application() {
    //val comicbooks = ArrayList<ComicbookModel>()

    val comicbooks = ComicbookMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("info", "Placemark started")

    }
}
