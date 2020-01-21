package com.example.didaktikapp.sopadeletras.di.component;

import com.example.didaktikapp.sopadeletras.di.modules.AppModule;
import com.example.didaktikapp.sopadeletras.di.modules.DataSourceModule;
import com.example.didaktikapp.sopadeletras.features.FullscreenActivity;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by abdularis on 18/07/17.
 */

@Singleton
@Component(modules = {AppModule.class, DataSourceModule.class})
public interface AppComponent {

    void inject(WordSearchActivity activity);

    void inject(FullscreenActivity activity);


}
