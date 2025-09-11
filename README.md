# MVVM Example with Hilt Dependency Injection

This project demonstrates a proper implementation of the MVVM (Model-View-ViewModel) architectural pattern with Hilt dependency injection in Android.

## Architecture Overview

### MVVM Components

1. **Model** (`app/src/main/java/com/example/mvvm_exemple/model/`)
   - `Note.java` - Data class representing a note entity

2. **View** (`app/src/main/java/com/example/mvvm_exemple/ui/`)
   - `MainActivity.java` - Main activity hosting NavHostFragment
   - `NotesListFragment.java` - Fragment displaying list of notes
   - `NoteCreateFragment.java` - Fragment for creating new notes
   - `NotesAdapter.java` - RecyclerView adapter for notes list

3. **ViewModel** (`app/src/main/java/com/example/mvvm_exemple/viewmodel/`)
   - `NotesViewModel.java` - ViewModel managing UI-related data with `@HiltViewModel`

4. **Repository** (`app/src/main/java/com/example/mvvm_exemple/data/repository/`)
   - `NotesRepository.java` - Data source abstraction with `@Singleton`

### Hilt Dependency Injection

#### Key Components:

1. **Application Class**
   - `MvvmApplication.java` - Application class annotated with `@HiltAndroidApp`

2. **Dependency Injection Module**
   - `RepositoryModule.java` - Provides singleton repository instance

3. **Entry Points**
   - `MainActivity` - Annotated with `@AndroidEntryPoint`
   - `NotesListFragment` - Annotated with `@AndroidEntryPoint`
   - `NoteCreateFragment` - Annotated with `@AndroidEntryPoint`

## Implementation Details

### Dependency Injection Setup

```java
@HiltAndroidApp
public class MvvmApplication extends Application {
}
```

```java
@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public NotesRepository provideNotesRepository() {
        return new NotesRepository();
    }
}
```

### ViewModel with Hilt

```java
@HiltViewModel
public class NotesViewModel extends ViewModel {
    private final NotesRepository repo;

    @Inject
    public NotesViewModel(NotesRepository repo) {
        this.repo = repo;
    }
    // ...
}
```

### Fragment with Hilt

```java
@AndroidEntryPoint
public class NotesListFragment extends Fragment {
    private NotesViewModel vm;
    
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        // Hilt automatically provides the ViewModel with injected dependencies
        vm = new ViewModelProvider(this).get(NotesViewModel.class);
        // ...
    }
}
```

### Repository as Singleton

```java
@Singleton
public class NotesRepository {
    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public NotesRepository() {
        // Hilt will inject this constructor
    }
    // ...
}
```

## Data Binding Integration

The project uses Android Data Binding for better separation of concerns:

- Layout files wrapped in `<layout>` tags
- ViewModels exposed to layouts via `<variable>` declarations
- Fragments use `DataBindingUtil.inflate()` for layout inflation
- Proper lifecycle management with `binding.setLifecycleOwner(this)`

## Key Benefits of This Implementation

### 1. **Proper Separation of Concerns**
- **Model**: Handles data and business logic
- **View**: Handles UI presentation
- **ViewModel**: Mediates between View and Model

### 2. **Dependency Injection with Hilt**
- Eliminates manual ViewModelFactory creation
- Provides singleton repository across the app
- Automatic dependency resolution
- Improved testability

### 3. **Lifecycle Awareness**
- ViewModels survive configuration changes
- LiveData provides lifecycle-aware data observation
- Proper resource cleanup in fragments

### 4. **Improved Code Maintainability**
- Clear architectural boundaries
- Reduced boilerplate code
- Centralized dependency management
- Easy to test and mock dependencies

## Build Configuration

### Project-level `build.gradle`:
```gradle
buildscript {
    dependencies {
        classpath 'com.android.tools.build:gradle:7.4.2'
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.48'
    }
}
```

### App-level `build.gradle`:
```gradle
apply plugin: 'com.android.application'
apply plugin: 'dagger.hilt.android.plugin'

android {
    buildFeatures {
        dataBinding true
        viewBinding true
    }
}

dependencies {
    // Hilt
    implementation 'com.google.dagger:hilt-android:2.48'
    annotationProcessor 'com.google.dagger:hilt-android-compiler:2.48'
    
    // Lifecycle components
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
    // ...
}
```

## Usage

1. **Adding Notes**: Navigate to create fragment, enter title and content, save
2. **Viewing Notes**: Main fragment displays all notes in a RecyclerView
3. **Data Persistence**: Notes are stored in memory via Repository (LiveData)

## Testing Considerations

With Hilt integration, the app becomes much more testable:

- Repository can be easily mocked for unit tests
- ViewModels can be tested in isolation
- UI tests can use Hilt test modules
- Dependency injection makes components loosely coupled

This implementation follows Android architecture best practices and provides a solid foundation for scalable Android applications.