package com.example.mvvm_exemple.di;

import com.example.mvvm_exemple.data.repository.NotesRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public NotesRepository provideNotesRepository() {
        return new NotesRepository();
    }
}