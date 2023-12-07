package com.example.projeto;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Cursor notesCursor;

    public MyAdapter(Context context, Cursor notesCursor) {
        this.context = context;
        this.notesCursor = notesCursor;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        if (!notesCursor.moveToPosition(position)) {
            return;
        }

        int idColumnIndex = notesCursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int titleColumnIndex = notesCursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
        int descriptionColumnIndex = notesCursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION);
        int createdTimeColumnIndex = notesCursor.getColumnIndex(DatabaseHelper.COLUMN_CREATED_TIME);

        long id = notesCursor.getLong(idColumnIndex);
        String title = notesCursor.getString(titleColumnIndex);
        String description = notesCursor.getString(descriptionColumnIndex);
        long createdTime = notesCursor.getLong(createdTimeColumnIndex);

        holder.titleOutput.setText(title);
        holder.descriptionOutput.setText(description);

        String formattedTime = DateFormat.getDateTimeInstance().format(createdTime);
        holder.timeOutput.setText(formattedTime);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context, v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("DELETE")) {
                            // Delete the note
                            DatabaseHelper dbHelper = new DatabaseHelper(context);
                            dbHelper.deleteNote(id);
                            refreshCursor();
                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesCursor.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }

    // Helper method to refresh the cursor when data changes
    public void refreshCursor() {
        if (notesCursor != null) {
            notesCursor.close();
        }
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        notesCursor = dbHelper.getAllNotes();
        notifyDataSetChanged();
    }
}
