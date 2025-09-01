package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CourseDetailsFragment extends Fragment {

    private static final String ARG_ID = "course_id";
    private static final String ARG_TITLE = "course_title";
    private static final String ARG_DESC = "course_description";

    private int courseId;

    public static CourseDetailsFragment newInstance(int id, String title, String description) {
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleView = view.findViewById(R.id.course_title);
        TextView descView = view.findViewById(R.id.course_description);

        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_ID);
            String title = getArguments().getString(ARG_TITLE);
            String desc = getArguments().getString(ARG_DESC);
            titleView.setText(title);
            descView.setText(desc);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentAdapter studentAdapter = new StudentAdapter();
        recyclerView.setAdapter(studentAdapter);

        AppDatabase db = AppDatabase.getDatabase(requireContext());
        db.studentDao().getStudentsByCourse(courseId)
                .observe(getViewLifecycleOwner(), studentAdapter::setStudents);

        Button btnRegister = view.findViewById(R.id.btn_register_student);
        btnRegister.setOnClickListener(v -> {
            AddStudentDialogFragment dialog = AddStudentDialogFragment.newInstance(courseId);
            dialog.show(getParentFragmentManager(), "AddStudentDialog");
        });
    }
}
