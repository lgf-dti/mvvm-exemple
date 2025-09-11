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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_exemple.R;
import com.example.mvvm_exemple.databinding.FragmentNoteListBinding;
import com.example.mvvm_exemple.viewmodel.NotesViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotesListFragment extends Fragment {

    private NotesViewModel vm;
    private NotesAdapter adapter;
    private FragmentNoteListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotesAdapter();
        binding.recycler.setAdapter(adapter);

        // Use Hilt to inject the ViewModel
        vm = new ViewModelProvider(this).get(NotesViewModel.class);
        
        // Set the ViewModel in data binding
        binding.setViewModel(vm);
        binding.setLifecycleOwner(this);

        vm.getNotes().observe(getViewLifecycleOwner(), adapter::submitList);

        binding.btnAdd.setOnClickListener(btn ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_list_to_create)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

