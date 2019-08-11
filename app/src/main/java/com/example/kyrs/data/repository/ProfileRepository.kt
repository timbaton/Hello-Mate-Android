package com.example.kyrs.data.repository

import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.data.sharedPref.AuthHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private var api: HelloMateApi,
    private val authHolder: AuthHolder
) {

    fun loadProfile(userId: Int): Single<ProfileResponse> {
        val id = if (userId == 0) authHolder.userId else userId
        return api.getProfile(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
