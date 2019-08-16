package com.example.kyrs.data.repository

import com.example.kyrs.data.entity.request.LoginRequest
import com.example.kyrs.data.entity.response.LoginResponse
import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.data.sharedPref.AuthHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Project HelloMate
 * Package com.example.kyrs.data.repository
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-07
 */
class AuthRepository @Inject constructor(
    private var api: HelloMateApi,
    private val authHolder: AuthHolder
) {

    fun login(login: String, password: String): Single<LoginResponse> {

        val loginRequestBody = LoginRequest(login, password)
        return api.login(loginRequestBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveAuthData(id: Int, token: String) {
        authHolder.userId = id
        authHolder.token = token
    }

    fun register(login: String, password: String): Single<LoginResponse> {

        val loginRequestBody = LoginRequest(login, password)
        return api.register(loginRequestBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}