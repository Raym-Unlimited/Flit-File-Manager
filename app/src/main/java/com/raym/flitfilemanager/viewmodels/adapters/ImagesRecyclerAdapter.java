package com.raym.flitfilemanager.viewmodels.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.Constant;
import org.jetbrains.annotations.NotNull;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder> {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public ImagesRecyclerAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_photos_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Uri uri = Uri.fromFile(Constant.allImageList.get(position));
        Glide.with(mContext).load(uri).into(holder.imgBitmap);
    }

    @Override
    public int getItemCount() {
        return Constant.allImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imgBitmap;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgBitmap = itemView.findViewById(R.id.img_pic);
        }
    }
}
