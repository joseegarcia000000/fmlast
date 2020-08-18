package com.estebangarcia.fmlast.VISTA.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class alcanceActivity:AppCompatActivity(),CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext

        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job= Job()

    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        job=Job()
    }
    open fun cuadroDialogo(){
        job= Job()
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}