package com.example.mvvm_exemple.ui.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvvm_exemple.R;
import com.example.mvvm_exemple.data.repository.NotesRepository;
import com.example.mvvm_exemple.viewmodel.NotesViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private NotesViewModelFactory notesFactory; // exposée aux fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // héberge simplement le NavHostFragment

        NotesRepository repo = new NotesRepository();
        // Une seule Factory partagée
        notesFactory = new NotesViewModelFactory(repo);
    }

    public NotesViewModelFactory getNotesFactory() {
        return notesFactory;
    }

}