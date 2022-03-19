package com.example.ourcw.HelperObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourcw.R;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;

import java.util.ArrayList;

public class AssignmentsRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentsRecyclerViewAdapter.ViewHolder2> {
    ArrayList<Assignment> assignments;
    private LayoutInflater inflater;
    OnNoteListener onNoteListener;

    public AssignmentsRecyclerViewAdapter(Context context, ArrayList<Assignment> assignments, OnNoteListener onNoteListener) {
        this.assignments = assignments;
        this.inflater = LayoutInflater.from(context);
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder2(inflater.inflate(R.layout.classrooms_row, parent, false), onNoteListener);
    }


    @Override
    public int getItemCount() {
        return assignments.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.rowTextView.setText(assignments.get(position).getAssignmentName());
    }

    @SuppressLint("NotifyDataSetChanged")
    void addItems(ArrayList<Assignment> names) {
        assignments.addAll(names);
        notifyDataSetChanged();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rowTextView;
        OnNoteListener onNoteListener;

        public ViewHolder2(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            rowTextView = itemView.findViewById(R.id.classroomsRowId);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClickGoAssignmentPage(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClickGoAssignmentPage(int position);
    }
}
