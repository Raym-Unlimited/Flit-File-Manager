package com.raym.flitfilemanager.viewmodels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.Constant;
import org.jetbrains.annotations.NotNull;

public class FolderRecyclerAdapter extends RecyclerView.Adapter<FolderRecyclerAdapter.ViewHolder>{

    private final LayoutInflater mLayoutInflater;
    Context mContext;

    public FolderRecyclerAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_folders_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.folderName.setText(Constant.allFolderList.get(position).getName());
        holder.folderCreationDate.setText("1st Oct");
    }

    @Override
    public int getItemCount() {
        return Constant.allFolderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView folderName, folderCreationDate;
        public ImageView itemSelector;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.txt_folder);
            folderCreationDate = itemView.findViewById(R.id.txt_date);
            itemSelector = itemView.findViewById(R.id.circle_selector);
        }
    }
}
