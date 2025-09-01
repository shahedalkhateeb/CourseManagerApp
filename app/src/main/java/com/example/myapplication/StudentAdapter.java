package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.VH> {

    private final List<Student> items = new ArrayList<>();

    public void setStudents(List<Student> students) {
        items.clear();
        if (students != null) items.addAll(students);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Student s = items.get(position);
        holder.name.setText(s.name);
        holder.email.setText(s.email);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, email;
        VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtStudentName);
            email = itemView.findViewById(R.id.txtStudentEmail);
        }
    }
}
