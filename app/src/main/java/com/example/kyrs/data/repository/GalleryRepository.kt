package com.example.kyrs.data.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


/**
 * Project HelloMate
 * Package com.example.kyrs.data.repository
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-07
 */
class GalleryRepository @Inject constructor(
    private val context: Context
) {

    fun loadImage(newImageUri: Uri): Single<MultipartBody.Part> {

        return Single.fromCallable { getMultipartImage(newImageUri) }
    }

    private fun getMultipartImage(newImageUri: Uri): MultipartBody.Part {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(newImageUri, proj, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }

        if (result == null) {
            result = "Not found"
        }

        val file = File(result)

        val requestFile = RequestBody.create(
            MediaType.parse(context.contentResolver.getType(newImageUri)!!),
            file
        )

        return MultipartBody.Part.createFormData("picture", file.name, requestFile)
    }
}