package com.alex.theguide.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alex.theguide.R;

public class MyDialogFragment extends DialogFragment {
    private onListItemClickListener listener;

    public MyDialogFragment() {
    }

    //Interface for click on elements of the dialog.
    public interface onListItemClickListener {
        void onLinkAsFavoriteClick();

        void onLinkGetPermalinkClick();

        void onLinkDeleteClick();
    }

    public void setOnListItemClickListener(onListItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogLayout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);

        View.OnClickListener itemClickListener = obtainButtonsClickListener();
        LinearLayout linkAsFavorite = dialogLayout.findViewById(R.id.linkAsFavorite);
        linkAsFavorite.setOnClickListener(itemClickListener);
        LinearLayout linkGetPermalink = dialogLayout.findViewById(R.id.linkGetPermalink);
        linkGetPermalink.setOnClickListener(itemClickListener);
        LinearLayout linkDelete = dialogLayout.findViewById(R.id.linkDelete);
        linkDelete.setOnClickListener(itemClickListener);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(dialogLayout);
        return alert.create();
    }

    private View.OnClickListener obtainButtonsClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.linkAsFavorite:
                        if (listener != null) {
                            listener.onLinkAsFavoriteClick();
                        }
                        dismiss();
                        break;
                    case R.id.linkGetPermalink:
                        if (listener != null) {
                            listener.onLinkGetPermalinkClick();
                        }
                        dismiss();
                        break;
                    case R.id.linkDelete:
                        if (listener != null) {
                            listener.onLinkDeleteClick();
                        }
                        dismiss();
                        break;
                }
            }
        };
    }
}
