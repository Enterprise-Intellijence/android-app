package com.enterprise.android_app.model.loginRegistration

import io.swagger.client.apis.UserControllerApi



data class UserState(
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
)


class UserController(){
    val Controller = UserControllerApi()


    fun authenticate() {
            val Map = Controller.authenticate("username1","password1")
            println(Map)
        }

    }


