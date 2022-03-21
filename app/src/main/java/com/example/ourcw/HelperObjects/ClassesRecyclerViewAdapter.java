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
import com.example.ourcw.models.Classroom;

import java.util.ArrayList;

public class ClassesRecyclerViewAdapter extends RecyclerView.Adapter<ClassesRecyclerViewAdapter.ViewHolder>{
    ArrayList<Classroom> classrooms;
    private LayoutInflater inflater;
    OnNoteListener onNoteListener;
    boolean showClass = true;
    private int textSize;

    public ClassesRecyclerViewAdapter(Context context, ArrayList<Classroom> classrooms, OnNoteListener onNoteListener, boolean showClass) {
        this.classrooms = classrooms;
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
        return classrooms.size();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowTextViewName.setText("Class Id : " + classrooms.get(position).getClassId());
        //holder.rowTextViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    @SuppressLint("NotifyDataSetChanged")
    void addItems(ArrayList<Classroom> names){
        classrooms.addAll(names);
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
            if (showClass){
                onNoteListener.onNoteClickShowClassPage(getAdapterPosition());
            }else onNoteListener.onNoteClickAddClass(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClickAddClass(int position);
        void onNoteClickShowClassPage(int position);
    }

}
