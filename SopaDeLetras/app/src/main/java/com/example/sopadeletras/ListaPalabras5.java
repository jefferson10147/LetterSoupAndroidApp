package com.example.sopadeletras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ListaPalabras5 extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tema: Paises");
        builder.setMessage("1. Canada.\t2.China.\n3.Egipto.\t4.Grecia.\n5.Holanda.\t6.India." +
                "\n7.Islandia.\t8.Italia.\n9.Japon.\t10.Mexico.\n11.Noruega.\t12.Rusia.\n13.Suecia.\t14.Suiza.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
