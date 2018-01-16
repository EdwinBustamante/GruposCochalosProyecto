package com.edwinbustamante.gruposcochalos;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ContactarDialogFragment extends DialogFragment {


    private String nameGrupo;
    private Button atras,llamar;
    private TextView bienvenida;
    private  int numeroCel;
    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View vista = inflater.inflate(R.layout.contactar_dialog_full, container, false);
        atras = (Button) vista.findViewById(R.id.atras);
        llamar=(Button)vista.findViewById(R.id.llamar);
        bienvenida = (TextView) vista.findViewById(R.id.bienvenida);
        bienvenida.setText("Hola desea contactar con " + nameGrupo + " ? Contacte ahora mismo al "+numeroCel);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+numeroCel));
                if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(), "NO TIENE PERMISOS PARA HACER LLAMADA CONTACTESE CON EL ADMINISTRADOR", Toast.LENGTH_SHORT).show();
                 requestPermission();
                }else{
                    startActivity(i);
                }
            }
            private void requestPermission(){
                ActivityCompat.requestPermissions(getActivity(),new String[Integer.parseInt(Manifest.permission.CALL_PHONE)],1);
            }
        });
        return vista;
    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    public void setNameToolbar(String nameToolbar) {
        this.nameGrupo = nameToolbar;
    }

    public void setNumeroCel(int numeroCel) {
        this.numeroCel = numeroCel;
    }
}

