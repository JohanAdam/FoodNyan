package com.nyan.foodie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//TODO Update hilt to 2.35, but having issue with Non-android module(Domain module).
//TODO There also a problem where Hilt can't handle multi-module project. It having a problem with leaking classes into other Gradle modules.

@HiltAndroidApp
class App: Application()