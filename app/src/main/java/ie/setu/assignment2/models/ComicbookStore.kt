package ie.setu.assignment2.models

interface ComicbookStore {
    fun findAll(): List<ComicbookModel>
    fun create(comicbook: ComicbookModel)
    fun update(placemark: ComicbookModel)
}