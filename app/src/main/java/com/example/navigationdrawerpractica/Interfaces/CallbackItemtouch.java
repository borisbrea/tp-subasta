package com.example.navigationdrawerpractica.Interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface CallbackItemtouch {
    void itemTouchOnMode(int oldPosition, int newPosition);
    void onSwiped(RecyclerView.ViewHolder viewHolder, int position);

}
