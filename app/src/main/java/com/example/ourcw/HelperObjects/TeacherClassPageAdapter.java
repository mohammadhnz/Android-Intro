package com.example.ourcw.HelperObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourcw.R;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;

import java.util.ArrayList;

public class TeacherClassPageAdapter extends RecyclerView.Adapter<TeacherClassPageAdapter.ViewHolder> {
    ArrayList<Assignment> assignments;
    private LayoutInflater inflater;
    OnNoteListener onNoteListener;
    boolean showClass = true;
    private int textSize;

    public TeacherClassPageAdapter(Context context, ArrayList<Assignment> assignments, OnNoteListener onNoteListener, boolean showClass) {
        this.assignments = assignments;
        this.inflater = LayoutInflater.from(context);
        this.onNoteListener = onNoteListener;
        this.showClass = showClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.classrooms_row, parent, false), onNoteListener);
    }


    @Override
    public int getItemCount() {
        return assignments.size();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowTextViewName.setText("Assignment Id : " + assignments.get(position).getAssignmentName());
    }

    @SuppressLint("NotifyDataSetChanged")
    void addItems(ArrayList<Assignment> assignments) {
        assignments.addAll(assignments);
        notifyDataSetChanged();
    }

    public void setTextSizes(int textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView rowTextViewName;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            rowTextViewName = itemView.findViewById(R.id.classroomIdRowId);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (showClass) {
                onNoteListener.onNoteClickShowClassPage(getAdapterPosition());
            } else onNoteListener.onNoteClickAddClass(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClickAddClass(int position);
        void onNoteClickShowClassPage(int position);
    }

}
