package com.example.didaktikapp.sopadeletras.features;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private WordSearchViewModel mWordSearchViewModel;

    public ViewModelFactory(WordSearchViewModel wordSearchViewModel) {
        mWordSearchViewModel = wordSearchViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if (modelClass.isAssignableFrom(WordSearchViewModel.class)) {
            return (T) mWordSearchViewModel;
        }
        throw new IllegalArgumentException("Unknown view model");
    }
}
