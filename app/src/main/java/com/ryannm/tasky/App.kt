package com.ryannm.tasky

import android.app.Application
import com.ryannm.tasky.data.TaskDatabase

class App: Application() {
//    public AppDatabase database;

    @Override
    override fun onCreate() {
        super.onCreate()

        TaskDatabase.init(this)
    }
}