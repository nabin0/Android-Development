package com.nabin.recyclerviewandmenu;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemDecorator extends RecyclerView.ItemDecoration {
    public final int verticalSpaceValue;

    public RecyclerItemDecorator(int verticalSpaceValue) {
        this.verticalSpaceValue = verticalSpaceValue;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceValue;
    }
}
