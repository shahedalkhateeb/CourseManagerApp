package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddStudentDialogFragment extends DialogFragment {

    private static final String ARG_COURSE_ID = "arg_course_id";

    public static AddStudentDialogFragment newInstance(int courseId) {
        AddStudentDialogFragment f = new AddStudentDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COURSE_ID, courseId);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_student, null);
        EditText etName = v.findViewById(R.id.editStudentName);
        EditText etEmail = v.findViewById(R.id.editStudentEmail);

        int courseId = getArguments() != null ? getArguments().getInt(ARG_COURSE_ID, -1) : -1;

        return new AlertDialog.Builder(requireContext())
                .setTitle("Add Student")
                .setView(v)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    if (!name.isEmpty() && courseId != -1) {
                        Student student = new Student(courseId, name, email);
                        new Thread(() -> {
                            AppDatabase db = AppDatabase.getDatabase(requireContext());
                            db.studentDao().insert(student);
                        }).start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
