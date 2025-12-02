package com.example.nossotcc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nossotcc.R;
import com.example.nossotcc.model.Folder;

import java.util.List;

public class FolderAdapter {

    private Context context;
    private GridLayout gridLayout;
    private List<Folder> folders;
    private OnFolderClick listener;

    public interface OnFolderClick {
        void onClick(Folder folder);
    }

    public FolderAdapter(Context context, GridLayout gridLayout, List<Folder> folders, OnFolderClick listener) {
        this.context = context;
        this.gridLayout = gridLayout;
        this.folders = folders;
        this.listener = listener;
        render();
    }

    public void render() {
        gridLayout.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(context);

        for (Folder folder : folders) {
            View item = inflater.inflate(R.layout.item_folder, gridLayout, false);

            ImageView icon = item.findViewById(R.id.folderIcon);
            TextView title = item.findViewById(R.id.folderName);

            icon.setImageResource(R.drawable.arquivinhos);
            title.setText(folder.getName());

            item.setOnClickListener(v -> listener.onClick(folder));

            gridLayout.addView(item);
        }
    }
}
