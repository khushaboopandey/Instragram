package com.example.instragram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("3dC4BDJehnYwpMlYSA6d2fKaGItkZ4bimsXSuuTa")
                // if defined
                .clientKey("98hSgtFvAF5FUs047wxso4APenkgbFcEvzfnWl5k")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
