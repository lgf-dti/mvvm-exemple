package com.example.mvvm_exemple.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_exemple.data.repository.NotesRepository;
import com.example.mvvm_exemple.model.Note;

import java.util.List;

import java.util.Arrays;

public class NotesViewModel extends ViewModel {
    private final NotesRepository repo;

    public NotesViewModel(NotesRepository repo) {
        this.repo = repo;
    }

    public LiveData<List<Note>> getNotes() {
        return repo.observeNotes();
    }

    /** Simule un "load" : par ex. chargement initial depuis une API */
    public void load() {
        List<Note> demo = Arrays.asList(
                new Note("Bienvenue", "Ceci est votre première note."),
                new Note("Astuce", "Ajoutez un titre et un texte, puis validez !")
        );
        repo.setAll(demo);
    }

    public void add(String title, String content) {
        repo.add(new Note(title, content));
    }



    public void removeByTitle(String title) {
        repo.removeByTitle(title);
    }


}