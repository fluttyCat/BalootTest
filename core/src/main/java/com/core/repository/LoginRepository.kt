package com.core.repository

import com.core.api.LoginApi
import com.core.base.BaseRepository

abstract class LoginRepository : BaseRepository {

}

class LoginRepositoryImpl(private val loginApi: LoginApi) : LoginRepository() {


}