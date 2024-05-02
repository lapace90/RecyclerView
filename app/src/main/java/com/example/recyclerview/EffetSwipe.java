package com.example.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class EffetSwipe extends ItemTouchHelper.SimpleCallback {
    private List<Items> itemS;
    private MonAdapter mAdapter;
    private Context context;
    private Items itemsName;
    private List<Items> archiveListItems;

    public EffetSwipe(int dragDirs, int swipeDirs, Context context,List<Items> itemS, MonAdapter mAdapter) {
        super(dragDirs, swipeDirs);
        this.context = context;
        this.itemS = itemS;
        this.mAdapter = mAdapter;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAbsoluteAdapterPosition();

        switch (direction) {
            case ItemTouchHelper.LEFT:
                break;
            case ItemTouchHelper.RIGHT:
                itemsName = itemS.get(pos);
                archiveListItems.add(itemsName);
                itemS.remove(pos);
                mAdapter.notifyItemRemoved(pos);

                Snackbar.make(viewHolder.itemView, "Archivage en cours", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                archiveListItems.remove(archiveListItems.lastIndexOf(itemsName));
                                itemS.add(pos, itemsName);
                                mAdapter.notifyItemInserted(pos);
                            }
                        }).show();
                break;
        }

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeRightActionIcon(R.drawable.baseline_archive_24)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.yellow))
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.red))
                .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                .create()
                .decorate();
    }
}
