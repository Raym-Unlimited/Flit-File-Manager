package com.raym.flitfilemanager.viewmodels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.Constant;
import org.jetbrains.annotations.NotNull;

public class AudioRecyclerAdapter extends RecyclerView.Adapter<AudioRecyclerAdapter.ViewHolder> {

    Context mContext;
    LayoutInflater mLayoutInflater;
//    String[] mItems;

    public AudioRecyclerAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_audio_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.audioName.setText(Constant.allAudioList.get(position).getName());
        holder.audioSize.setText("3 MB");
        holder.audioDownloadDate.setText("Oct 1st");
    }

    @Override
    public int getItemCount() {
        return Constant.allAudioList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView audioName, audioDownloadDate, audioSize;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            audioName = itemView.findViewById(R.id.txt_audio_name);
            audioDownloadDate = itemView.findViewById(R.id.txt_audio_download_date);
            audioSize = itemView.findViewById(R.id.txt_audio_size);
        }
    }
}
