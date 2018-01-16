package com.edwinbustamante.gruposcochalos.Adaptadores;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edwinbustamante.gruposcochalos.ContactarDialogFragment;
import com.edwinbustamante.gruposcochalos.FullImage.FullImageActivity;
import com.edwinbustamante.gruposcochalos.GruposMusicalesModelo.GruposMusicalesModelo;
import com.edwinbustamante.gruposcochalos.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by EDWIN on 25/09/2017.
 */

public class GruposMusicalesAdaptador extends RecyclerView.Adapter<GruposMusicalesAdaptador.ViewHolder> {

    private Typeface textype;//tipo de letra para el Login
    private FragmentActivity myContex;

    public static class Informacion extends DialogFragment {
        public Informacion() {
        }

        public void getInformacion(Context context) {
            Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show();

        }


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreGrupo, horaAgregado;
        ImageView imgPortada;
        Button ubicar, contactar, informacion;
        CircleImageView fotoDePerfil;
        Context context;
        PhotoViewAttacher mAttacher;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            nombreGrupo = (TextView) itemView.findViewById(R.id.nombreGrupo);
            imgPortada = (ImageView) itemView.findViewById(R.id.portada);
            ubicar = (Button) itemView.findViewById(R.id.ubicar);
            contactar = (Button) itemView.findViewById(R.id.contactar);
            informacion = (Button) itemView.findViewById(R.id.informacion);
            fotoDePerfil = (CircleImageView) itemView.findViewById(R.id.fotoDePerfil);
            horaAgregado = (TextView) itemView.findViewById(R.id.fechaDeAgregacion);
            horaAgregado.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_public_black_18dp, 0);//poniendo icono de la hpra
            //derecha
           //es el que hace que la imageen sea de grande
            // mAttacher = new PhotoViewAttacher(imgPortada);

        }

        public void setOnclikListeners(final int numeroCelular, final int imagenPortada) {

            fotoDePerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullImageActivity.class);
                    intent.putExtra("foto", imagenPortada);
                    context.startActivity(intent);
                }
            });
            imgPortada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(context, FullImageActivity.class);
                    i.putExtra("foto", imagenPortada);
                    context.startActivity(i);

                }
            });

            ubicar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(context, "FUI PRESIONADO", Toast.LENGTH_SHORT).show();
                }
            });
            contactar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    ContactarDialogFragment newFragment = new ContactarDialogFragment();
                    newFragment.setNumeroCel(numeroCelular);
                    newFragment.setNameToolbar(nombreGrupo.getText().toString());
                    // The device is smaller, so show the fragment fullscreen
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    // For a little polish, specify a transition animation
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    // To make it fullscreen, use the 'content' root view as the container
                    // for the fragment, which is always the root view for the activity
                    transaction.add(android.R.id.content, newFragment)
                            .addToBackStack(null).commit();

                }
            });
            informacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public List<GruposMusicalesModelo> gruposMusicalesLista;

    public GruposMusicalesAdaptador(List<GruposMusicalesModelo> gruposMusicalesLista) {
        this.gruposMusicalesLista = gruposMusicalesLista;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);//le estamos pasando un iten del recycler vies para que se anime
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    //HACE REFERENCIA AL ITEM DE LAYOUT
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grupos_musicales, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    ///REALIZA LAS MODIFICACIONES PARA CADA ITEM
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombreGrupo.setText(gruposMusicalesLista.get(position).getNombreGrupo());
        holder.imgPortada.setImageResource(gruposMusicalesLista.get(position).getImgPortada());
        holder.fotoDePerfil.setImageResource(gruposMusicalesLista.get(position).getPerfil());


        Long codigoHora = gruposMusicalesLista.get(position).getHora();
        Date d = new Date(codigoHora);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss a");//se da el formato en este caso la hora los minutos y los segundos
        java.text.SimpleDateFormat mes = new java.text.SimpleDateFormat("MM");
        java.text.SimpleDateFormat fecha = new java.text.SimpleDateFormat("dd");
        java.text.SimpleDateFormat anio = new java.text.SimpleDateFormat("yyyy");
        String mesLiteral = "";
        switch (mes.format(d)) {
            case "1":
                mesLiteral = "ene.";
                break;
            case "2":
                mesLiteral = "feb.";
                break;
            case "3":
                mesLiteral = "mar.";
                break;
            case "4":
                mesLiteral = "abr.";
                break;
            case "5":
                mesLiteral = "may.";
                break;
            case "6":
                mesLiteral = "jun.";
                break;
            case "7":
                mesLiteral = "jul.";
                break;
            case "8":
                mesLiteral = "agos.";
                break;
            case "9":
                mesLiteral = "setp.";
                break;
            case "10":
                mesLiteral = "oct.";
                break;
            case "11":
                mesLiteral = "nov.";
                break;

            case "12":
                mesLiteral = "dic.";
                break;
        }

        holder.horaAgregado.setText("Agredado el " + fecha.format(d) + " de " + mesLiteral + " de " + anio.format(d) + " a las " + sdf.format(d));
        //aniadimos eventos
        int numeroCel = gruposMusicalesLista.get(position).getNumeroCel();
        int imageViewPortada = gruposMusicalesLista.get(position).getImgPortada();
        //pasamos lo que necesitamos para poder ver
        holder.setOnclikListeners(numeroCel, imageViewPortada);
    }

    //cantidad de elementos   que se proceran en este caso es la cantidad que hay en la lista
    @Override
    public int getItemCount() {
        return gruposMusicalesLista.size();
    }

    public void setFilter(ArrayList<GruposMusicalesModelo> listagrupos) {
        this.gruposMusicalesLista = new ArrayList<>();
        this.gruposMusicalesLista.addAll(listagrupos);
        notifyDataSetChanged();
    }
}
