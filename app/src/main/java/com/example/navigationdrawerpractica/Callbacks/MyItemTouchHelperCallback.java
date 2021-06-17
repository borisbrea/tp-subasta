package com.example.navigationdrawerpractica.Callbacks;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Adaptadores.AdapterMetodoPago;
import com.example.navigationdrawerpractica.Interfaces.CallbackItemtouch;
import com.example.navigationdrawerpractica.R;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    CallbackItemtouch callbackItemtouch;
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public MyItemTouchHelperCallback(CallbackItemtouch callbackItemtouch){
        this.callbackItemtouch = callbackItemtouch;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        callbackItemtouch.itemTouchOnMode(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        callbackItemtouch.onSwiped(viewHolder, viewHolder.getAdapterPosition());
    }

    //implementar 5 metodos extras
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }else{
            final View foregroundView = ((AdapterMetodoPago.ViewHolder)viewHolder).viewB;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(actionState != ItemTouchHelper.ACTION_STATE_DRAG){
            final View foregroundView = ((AdapterMetodoPago.ViewHolder)viewHolder).viewF;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    //al ir deslizando se va ir mostrando la parte naranja
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((AdapterMetodoPago.ViewHolder)viewHolder).viewF;
        foregroundView.setBackgroundColor(ContextCompat.getColor(((
                AdapterMetodoPago.ViewHolder)viewHolder).viewF.getContext(), R.color.white));
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder != null){
            final View foregroundView = ((AdapterMetodoPago.ViewHolder)viewHolder).viewB;
            if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
                foregroundView.setBackgroundColor(Color.LTGRAY);
            }
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}
