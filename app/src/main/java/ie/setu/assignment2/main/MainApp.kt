package ie.setu.assignment2.main

import android.app.Application
import android.util.Log.i
import ie.setu.assignment2.models.*
import timber.log.Timber

class MainApp : Application() {


  lateinit var  comicbooks: ComicbookStore
  lateinit var users:UserJsonStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        comicbooks = ComicbookJSONStore(applicationContext)
        users = UserJson(applicationContext)
        i("info", "Comicbook started")

    }
}
