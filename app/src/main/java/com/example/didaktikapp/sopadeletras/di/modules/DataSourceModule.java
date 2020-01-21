package com.example.didaktikapp.sopadeletras.di.modules;

import android.content.Context;

import com.example.didaktikapp.sopadeletras.data.GameDataSource;
import com.example.didaktikapp.sopadeletras.data.WordDataSource;
import com.example.didaktikapp.sopadeletras.data.sqlite.DbHelper;
import com.example.didaktikapp.sopadeletras.data.sqlite.GameDataSQLiteDataSource;
import com.example.didaktikapp.sopadeletras.data.xml.WordXmlDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class DataSourceModule {

    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Provides
    @Singleton
    GameDataSource provideGameRoundDataSource(DbHelper dbHelper) {
        return (GameDataSource) new GameDataSQLiteDataSource(dbHelper);
    }

//    @Provides
//    @Singleton
//    WordDataSource provideWordDataSource(DbHelper dbHelper) {
//        return new WordSQLiteDataSource(dbHelper);
//    }

    @Provides
    @Singleton
    WordDataSource provideWordDataSource(Context context) {
        return (WordDataSource) new WordXmlDataSource(context);
    }

}
