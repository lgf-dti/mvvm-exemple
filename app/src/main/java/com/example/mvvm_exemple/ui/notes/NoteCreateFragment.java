package com.example.mvvm_exemple.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mvvm_exemple.R;
import com.example.mvvm_exemple.databinding.FragmentNoteCreateBinding;
import com.example.mvvm_exemple.viewmodel.NotesViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NoteCreateFragment extends Fragment {

    private NotesViewModel vm;
    private FragmentNoteCreateBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_create, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        // Use Hilt to inject the ViewModel
        vm = new ViewModelProvider(this).get(NotesViewModel.class);
        
        // Set the ViewModel in data binding
        binding.setViewModel(vm);
        binding.setLifecycleOwner(this);

        binding.btnSave.setOnClickListener(btn -> {
            String title = binding.etTitle.getText().toString().trim();
            String content = binding.etContent.getText().toString().trim();

            // Simple validation
            if (title.isEmpty()) {
                binding.etTitle.setError("Title is required");
                return;
            }
            if (content.isEmpty()) {
                binding.etContent.setError("Content is required");
                return;
            }

            vm.add(title, content);
            NavHostFragment.findNavController(this).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
