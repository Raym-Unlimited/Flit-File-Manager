package com.raym.flitfilemanager.viewmodels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.App;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AppsRecyclerAdapter extends RecyclerView.Adapter<AppsRecyclerAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    Context mContext;
    ArrayList<App> mApps = new ArrayList<>();

    public AppsRecyclerAdapter(Context context, ArrayList<App> appArrayList){
        this.mContext = context;
        this.mApps = appArrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_apks_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        App app = mApps.get(position);
        holder.appImage.setImageDrawable(mApps.get(position).getAppImage());
        holder.appName.setText(app.getAppName());
        long apkSize = mApps.get(position).getAppSize();
        holder.appSize.setText(getHumanReadableSize(apkSize));

        holder.appContainer.setOnClickListener(view -> {
            Toast.makeText(mContext, "you just clicked me", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView appImage;
        public TextView appName;
        public TextView appSize;
        public ConstraintLayout appContainer;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            appImage = itemView.findViewById(R.id.img_app_icon);
            appName = itemView.findViewById(R.id.txt_app_name);
            appSize = itemView.findViewById(R.id.txt_app_size);
            appContainer = itemView.findViewById(R.id.app_container);
        }
    }

    private String getHumanReadableSize(long apkSize) {

        String humanReadableSize = null;
        if (apkSize < 1024){
            humanReadableSize = String.format(mContext
                    .getString(R.string.app_size_bytes), (double) apkSize);
        }else if (apkSize < Math.pow(1024, 2)){
            humanReadableSize = String.format(mContext
                    .getString(R.string.app_size_kbytes), (double) apkSize/1024);
        }else if (apkSize < Math.pow(1024, 3)){
            humanReadableSize = String.format(mContext
                    .getString(R.string.app_size_mbytes), (double) apkSize/Math.pow(1024, 2));
        }else if (apkSize < Math.pow(1024, 4)){
            humanReadableSize = String.format(mContext
                    .getString(R.string.app_size_gbytes), (double) apkSize/Math.pow(1024, 3));
        }
        return humanReadableSize;
    }
}
