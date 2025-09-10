package com.example.mvvm_exemple.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mvvm_exemple.R;
import com.example.mvvm_exemple.viewmodel.NotesViewModel;

public class NoteCreateFragment extends Fragment {

    private NotesViewModel vm;
    private EditText etTitle, etContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup c, @Nullable Bundle b) {
        return inf.inflate(R.layout.fragment_note_create, c, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        vm = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        etTitle = v.findViewById(R.id.etTitle);
        etContent = v.findViewById(R.id.etContent);

        v.findViewById(R.id.btnSave).setOnClickListener(btn -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            vm.add(title, content);
            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}
