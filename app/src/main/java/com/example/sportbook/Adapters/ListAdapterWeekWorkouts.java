package com.example.sportbook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportbook.Interfaces.WorkoutClickListener;
import com.example.sportbook.Models.Workout;
import com.example.sportbook.R;

import java.util.List;

public class ListAdapterWeekWorkouts extends RecyclerView.Adapter<ListAdapterWeekWorkouts.ViewHolder> {
    final LayoutInflater inflater;
    final List<Workout> workouts;
    static WorkoutClickListener clickListener;


    public ListAdapterWeekWorkouts(Context context, List<Workout> workouts) {
        this.workouts = workouts;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_week_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterWeekWorkouts.ViewHolder holder, int position) {
        Workout workout=workouts.get(position);
        holder.day.setText(workout.getDay());
        holder.workout.setText(workout.getWorkout_note());
        holder.title.setText(workout.getTitle());
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setClickListener(WorkoutClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView day, title, workout;

        ViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.textview_day);
            workout = view.findViewById(R.id.textview_daily_workout);
            title = view.findViewById(R.id.textview_title);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onLongClick(view, position);
                    return true;
                }
            }
            return false;
        }
    }
}
