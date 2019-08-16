package com.example.kyrs.data.repository

import com.example.kyrs.data.entity.request.EditUserRequest
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.data.sharedPref.AuthHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private var api: HelloMateApi,
    private val authHolder: AuthHolder
) {

    private lateinit var user: ProfileResponse

    fun loadProfile(userId: Int): Single<ProfileResponse> {
        val id = if (userId == 0) authHolder.userId else userId
        return api.getProfile(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun exit() {
        authHolder.token = ""
        authHolder.userId = 0
    }

    fun saveUserLocal(user: ProfileResponse?) {
        if (user != null) this.user = user
    }

    fun getUserLocal(): ProfileResponse {
        return user
    }

    fun editUser(user: EditUserRequest): Single<ProfileResponse> {
        return api.editUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun uploadAvatar(file: MultipartBody.Part): Single<ProfileResponse> {
        return api.uploadAvatar(authHolder.userId, file)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
