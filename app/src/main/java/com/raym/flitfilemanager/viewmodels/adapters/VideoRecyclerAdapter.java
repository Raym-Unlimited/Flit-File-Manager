package com.raym.flitfilemanager.viewmodels.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.Constant;
import org.jetbrains.annotations.NotNull;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>{

    Context mContext;
//    ArrayList<Vid> mVids = new ArrayList<>();
    LayoutInflater mLayoutInflater;

    public VideoRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
//        this.mVids = mVids;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_videos_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.vidName.setText(Constant.allVideoList.get(position).getName());
        Uri uri = Uri.fromFile(Constant.allVideoList.get(position));
        Glide.with(mContext).load(uri).into(holder.vidBitmap);
        holder.vidSize.setText("17.23 MB");
    }

    @Override
    public int getItemCount() {
//        return mVids.size();
        return Constant.allVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vidName, vidSize;
        public ImageView vidBitmap;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            vidName = itemView.findViewById(R.id.vid_name);
            vidSize = itemView.findViewById(R.id.vid_size);
            vidBitmap = itemView.findViewById(R.id.vid_bitmap);
        }
    }
}
