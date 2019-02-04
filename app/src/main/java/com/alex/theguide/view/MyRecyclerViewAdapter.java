package com.alex.theguide.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.theguide.R;
import com.alex.theguide.model.FileModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<FileModel> fileModelsList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public interface OnItemClickListener {
        void onClick(FileModel fileModel);
    }

    public interface OnItemLongClickListener {
        void onLongClick();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        longClickListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, final int position) {
        View currentView = holder.itemView;
        holder.bind(fileModelsList.get(position));
        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onClick(fileModelsList.get(position));
                }
            }
        });

        currentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    longClickListener.onLongClick();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileModelsList.size();
    }

    public void setFileModelsList(List<FileModel> fileModels) {
        fileModelsList.clear();
        fileModelsList.addAll(fileModels);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIsOrange, tvIsBlue, tvFileName, tvModDate, tvLightGrey, tvDarkGrey;
        private ImageView ivIcon;
        private View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            tvIsOrange = (TextView) itemView.findViewById(R.id.tvIsOrange);
            tvIsBlue = (TextView) itemView.findViewById(R.id.tvIsBlue);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvFileName = (TextView) itemView.findViewById(R.id.tvFileName);
            tvModDate = (TextView) itemView.findViewById(R.id.tvModDate);
            tvLightGrey = (TextView) itemView.findViewById(R.id.tvLightGrey);
            tvDarkGrey = (TextView) itemView.findViewById(R.id.tvDarkGrey);

        }

        void bind(FileModel fileModel) {
            tvFileName.setText(fileModel.getFileName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            String date = dateFormat.format(fileModel.getModDate());
            tvModDate.setText("modified " + date);

            //Checking what we have file or folder isFolder property.
            if (fileModel.getIsFolder()) {
                ivIcon.setImageResource(R.drawable.folder_icon);
                tvLightGrey.setBackgroundResource(R.color.colorLightGrey);
                tvDarkGrey.setBackgroundResource(R.color.colorDarkGrey);
            } else if (String.valueOf(fileModel.getFileType()).equalsIgnoreCase("image")) {
                ivIcon.setImageResource(R.drawable.image_icon);
            } else {
                ivIcon.setImageResource(R.drawable.file_icon);
            }

            if (fileModel.isOrange() & fileModel.isBlue()) {
                tvIsOrange.setBackgroundResource(R.color.colorOrange);
                tvIsBlue.setBackgroundResource(R.color.colorBlue);
            } else if (!fileModel.isOrange() & fileModel.isBlue()) {
                tvIsOrange.setBackgroundResource(R.color.colorBlue);
                tvIsBlue.setBackgroundResource(R.color.colorBlue);
            } else if (fileModel.isOrange() & !fileModel.isBlue()) {
                tvIsOrange.setBackgroundResource(R.color.colorOrange);
                tvIsBlue.setBackgroundResource(R.color.colorOrange);
            } else {
                tvIsOrange.setBackgroundResource(R.color.colorWhite);
                tvIsBlue.setBackgroundResource(R.color.colorWhite);
            }
        }
    }
}
