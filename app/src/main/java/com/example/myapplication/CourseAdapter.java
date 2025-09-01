package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.VH> {

    public interface OnCourseClick {
        void onClick(Course course);
    }

    private final List<Course> items = new ArrayList<>();
    private final OnCourseClick listener;

    public CourseAdapter(OnCourseClick listener) {
        this.listener = listener;
    }

    public void setCourses(List<Course> courses) {
        items.clear();
        if (courses != null) items.addAll(courses);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Course c = items.get(position);
        holder.title.setText(c.title);
        holder.description.setText(c.description);
        holder.itemView.setOnClickListener(v -> listener.onClick(c));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, description;
        VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtCourseTitle);
            description = itemView.findViewById(R.id.txtCourseDescription);
        }
    }
}
