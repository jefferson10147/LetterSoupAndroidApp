package com.example.sopadeletras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ListaPalabras4 extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tema: Espacio Exterior");
        builder.setMessage("1. Cometa.\t2.Jupiter.\n3.Luna.\t4.Marte.\n5.Mercurio.\t6.Neptuno." +
                "\n7.Pluto.\t8.Saturno.\n9.Sol.\t10.Tierra.\n11.Venus.\t12.Urano.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
