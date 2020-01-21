package com.example.didaktikapp.sopadeletras.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.didaktikapp.sopadeletras.data.GameDataSource;
import com.example.didaktikapp.sopadeletras.data.WordDataSource;
import com.example.didaktikapp.sopadeletras.features.ViewModelFactory;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(GameDataSource gameDataSource,
                                             WordDataSource wordDataSource) {
        return new ViewModelFactory(
                new WordSearchViewModel(gameDataSource, wordDataSource)
        );
    }
}
