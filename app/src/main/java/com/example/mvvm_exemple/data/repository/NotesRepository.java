package com.example.mvvm_exemple.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_exemple.model.Note;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotesRepository {
    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public NotesRepository() {
        // Hilt will inject this constructor
    }

    public LiveData<List<Note>> observeNotes() {
        return notes;
    }

    public void setAll(List<Note> list) {
        notes.setValue(new ArrayList<>(list));
    }

    public void add(Note note) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.add(0, note);
        notes.setValue(current);
    }

    public void remove(Note note) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.remove(note);
        notes.setValue(current);
    }

    public void removeByTitle(String title) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.removeIf(n -> n.getTitle().equals(title));
        notes.setValue(current);
    }
}