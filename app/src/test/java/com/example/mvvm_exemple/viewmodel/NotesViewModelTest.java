package com.example.mvvm_exemple.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.mvvm_exemple.data.repository.NotesRepository;
import com.example.mvvm_exemple.model.Note;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for NotesViewModel demonstrating how Hilt improves testability
 * by allowing easy mocking of dependencies.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotesViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private NotesRepository mockRepository;

    @Mock
    private LiveData<List<Note>> mockLiveData;

    @Mock
    private Observer<List<Note>> mockObserver;

    private NotesViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mockRepository.observeNotes()).thenReturn(mockLiveData);
        
        // With Hilt, we can easily inject the mock repository
        viewModel = new NotesViewModel(mockRepository);
    }

    @Test
    public void testAddNote() {
        // Given
        String title = "Test Title";
        String content = "Test Content";

        // When
        viewModel.add(title, content);

        // Then
        verify(mockRepository).add(any(Note.class));
    }

    @Test
    public void testGetNotes() {
        // When
        LiveData<List<Note>> result = viewModel.getNotes();

        // Then
        verify(mockRepository).observeNotes();
    }

    @Test
    public void testRemoveByTitle() {
        // Given
        String title = "Test Title";

        // When
        viewModel.removeByTitle(title);

        // Then
        verify(mockRepository).removeByTitle(title);
    }

    @Test
    public void testLoadInitialData() {
        // When (load is called in constructor)
        // viewModel.load() is called automatically in constructor

        // Then
        verify(mockRepository).setAll(any(List.class));
    }
}