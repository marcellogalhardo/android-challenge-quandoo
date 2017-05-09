package com.marcellogalhardo.quandoo.tablemap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;

import com.marcellogalhardo.quandoo.R;
import com.marcellogalhardo.quandoo.data.Table;

public class TableMapDialog {

    public static void showAvailableDialog(Context context, Table table, DialogInterface.OnClickListener onPositiveButton) {
        new AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.book_table, table.getNumber()))
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, onPositiveButton)
                .setNegativeButton(android.R.string.no, (dialog, id) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }

    public static void showUnavailableDialog(Context context, Table table) {
        new AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.table_unavailable, table.getNumber()))
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }
}
