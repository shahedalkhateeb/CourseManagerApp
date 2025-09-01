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

public class AddCourseDialogFragment extends DialogFragment {

    public static AddCourseDialogFragment newInstance() {
        return new AddCourseDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_course, null);
        EditText etTitle = v.findViewById(R.id.editCourseTitle);
        EditText etDesc = v.findViewById(R.id.editCourseDescription);

        return new AlertDialog.Builder(requireContext())
                .setTitle("Add Course")
                .setView(v)
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = etTitle.getText().toString().trim();
                    String desc = etDesc.getText().toString().trim();
                    if (!title.isEmpty()) {
                        Course course = new Course(title, desc);
                        new Thread(() -> {
                            AppDatabase db = AppDatabase.getDatabase(requireContext());
                            db.courseDao().insert(course);
                        }).start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
