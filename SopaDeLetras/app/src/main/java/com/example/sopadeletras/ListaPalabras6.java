package com.example.sopadeletras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ListaPalabras6 extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tema: Capitales");
        builder.setMessage("1. Atenas.\t2.Berlin.\n3.Bogota.\t4.Caracas.\n5.Cayena.\t6.Dublin." +
                "\n7.Lima.\t8.Moscu.\n9.Oslo.\t10.Panama.\n11.Praga.\t12.Quito.\n13.Roma.\t14.Santiago." +
                "\n15.Tokio.\t16.Viena.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
