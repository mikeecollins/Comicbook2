package ie.setu.assignment2.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
    class ComicbookMemStore : ComicbookStore {

        val comicbooks = ArrayList<ComicbookModel>()

        override fun findAll(): List<ComicbookModel> {
            return comicbooks
        }

        override fun create(comicbook: ComicbookModel) {
            comicbook.id = getId()
            comicbooks.add(comicbook)
            logAll()
        }

        override fun update(comicbook: ComicbookModel) {
            var foundComicbook: ComicbookModel? = comicbooks.find { p -> p.id == comicbook.id }
            if (foundComicbook != null) {
                foundComicbook.title = comicbook.title
                foundComicbook.author = comicbook.author
                foundComicbook.chapter = comicbook.chapter
                logAll()
            }
        }

        fun logAll() {
            comicbooks.forEach { i("${it}") }
        }

    }



