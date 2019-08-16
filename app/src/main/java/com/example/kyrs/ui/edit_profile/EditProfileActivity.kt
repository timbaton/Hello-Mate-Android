package com.example.kyrs.ui.edit_profile

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.di.ImagePath
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.edit_profile.EditProfilePresenter
import com.example.kyrs.presentation.edit_profile.EditProfileView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.utils.loadImage
import com.example.kyrs.utils.visible
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import toothpick.Toothpick
import java.io.IOException
import javax.inject.Inject


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.EditProfile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 */
class EditProfileActivity : BaseActivity(), EditProfileView {

    override var res: Int? = com.example.kyrs.R.layout.activity_edit_profile

    @Inject
    @field: ImagePath
    lateinit var imagePath: String

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, EditProfileActivity::class.java)

        }
    }

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    @ProvidePresenter
    fun providePresenter(): EditProfilePresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(EditProfilePresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(Scopes.Server))

        btnCancel.visible(true)
        btnBack.visible(false)

        btnCancel.setOnClickListener {
            presenter.onBackPressed()
        }

        btnAddAvatar.setOnClickListener {
            presenter.onAddAvatarClicked()
        }

        btnReady.setOnClickListener {
            presenter.onDoneClicked(
                etName.text.toString(),
                etSurname.text.toString(),
                etMail.text.toString(),
                etPhone.text.toString()
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1052
            )

        }
    }

//    TODO: handle on requestPermissionsResult

    override fun fillUserData(user: ProfileResponse) {
        etName.setText(user.name)
        etSurname.setText(user.surname)
        etMail.setText(user.mail)
        etPhone.setText(user.phone)

        val url = imagePath + user.avatar?.path
        ivAvatar.loadImage(ivAvatar, url)
    }

    override fun showGallery(galleryRequest: Int) {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, galleryRequest)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun setImage(selectedImage: Uri) {
        try {
            val bitmap =
                MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
            ivAvatar.setImageBitmap(bitmap)
        } catch (e: IOException) {
            Log.i("TAG", "Some exception $e")
        }
    }

    override fun finishActivityOk() {
        val data = Intent()
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}