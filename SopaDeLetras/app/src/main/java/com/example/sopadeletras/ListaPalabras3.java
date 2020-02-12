package com.example.sopadeletras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ListaPalabras3 extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tema: Animales");
        builder.setMessage("1. Caballo.\n2.Gato.\n3.Lobo.\n4.Loro.\n5.Mono.\n6.Oso." +
                "\n7.Perro.\n8.Toro.\n9.Vaca.\n10.Zorro.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
