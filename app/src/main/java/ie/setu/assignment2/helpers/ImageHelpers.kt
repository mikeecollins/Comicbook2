package ie.setu.assignment2.helpers



import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.setu.assignment2.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_comicbook_image.toString())
    intentLauncher.launch(chooseFile)
}