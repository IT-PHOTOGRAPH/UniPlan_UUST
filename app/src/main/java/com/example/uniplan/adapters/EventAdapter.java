package com.example.uniplan.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniplan.R;
import com.example.uniplan.database.DatabaseHelper;
import com.example.uniplan.models.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    Context context;
    List<Event> eventList;
    DatabaseHelper databaseHelper;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Event event = eventList.get(position);

        holder.tvTitle.setText(event.getTitle());

        holder.tvDescription.setText(event.getDescription());

        holder.tvDate.setText(event.getDate());

        holder.tvTime.setText(event.getTime());

        holder.itemView.setOnLongClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Delete event?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        databaseHelper.deleteEvent(event.getId());

                        eventList.remove(position);

                        notifyDataSetChanged();

                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvDate, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}