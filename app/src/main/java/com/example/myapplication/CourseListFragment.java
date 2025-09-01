package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseListFragment extends Fragment {

    private CourseAdapter adapter;
    private AppDatabase db;

    public CourseListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CourseAdapter(course -> {
            CourseDetailsFragment fragment = CourseDetailsFragment.newInstance(
                    course.id, course.title, course.description
            );
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);

        db = AppDatabase.getDatabase(requireContext());
        db.courseDao().getAllCourses().observe(getViewLifecycleOwner(), adapter::setCourses);

        FloatingActionButton fab = v.findViewById(R.id.fab_add_course);
        fab.setOnClickListener(view -> {
            AddCourseDialogFragment dialog = AddCourseDialogFragment.newInstance();
            dialog.show(getParentFragmentManager(), "AddCourseDialog");
        });
    }
}
