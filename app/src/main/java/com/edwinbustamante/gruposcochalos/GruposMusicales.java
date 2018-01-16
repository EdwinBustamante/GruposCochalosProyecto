package com.edwinbustamante.gruposcochalos;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edwinbustamante.gruposcochalos.Adaptadores.GruposMusicalesAdaptador;
import com.edwinbustamante.gruposcochalos.GruposMusicalesModelo.GruposMusicalesModelo;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GruposMusicales.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class GruposMusicales extends Fragment implements SearchView.OnQueryTextListener {

    private OnFragmentInteractionListener mListener;
    ArrayList<GruposMusicalesModelo> listaBuscador = new ArrayList<>();
    ///creamos los objetos para poder utilizarlos
    private RecyclerView recyclerViewGruposMusicales;
    private GruposMusicalesAdaptador gruposMusicalesAdaptador;

    public GruposMusicales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_grupos_musicales, container, false);
        recyclerViewGruposMusicales = (RecyclerView) vista.findViewById(R.id.recyclerGruposMusicales);
        recyclerViewGruposMusicales.setLayoutManager(new LinearLayoutManager(getActivity()));
        gruposMusicalesAdaptador = new GruposMusicalesAdaptador(obtenerGrupos());
        recyclerViewGruposMusicales.setAdapter(gruposMusicalesAdaptador);

        setHasOptionsMenu(true);

        return vista;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_grupos_musicales, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.registrar:
                Toast.makeText(getContext(), "jaja hola como estas", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.salir:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ///aniadimos el cada contenido de los grupos musicales
    public List<GruposMusicalesModelo> obtenerGrupos() {
        List<GruposMusicalesModelo> lista = new ArrayList<>();
        Map hora = ServerValue.TIMESTAMP;

        lista.add(new GruposMusicalesModelo("Bonanza", R.drawable.login, hora, R.drawable.login, "sfsf", "sdf", 4453143, "noce que sera"));

        lista.add(new GruposMusicalesModelo("Caral", R.drawable.login, ServerValue.TIMESTAMP, R.drawable.login, "sfsf", "sdf", 7453589, "noce que sera"));
        lista.add(new GruposMusicalesModelo("Luz", R.drawable.medico, ServerValue.TIMESTAMP, R.drawable.login, "sfsf", "sdf", 7434634, "noce que sera"));
        lista.add(new GruposMusicalesModelo("Mision Rescate", R.drawable.mr, ServerValue.TIMESTAMP, R.drawable.mr, "sfsf", "sdf", 75495889, "noce que sera"));



        return lista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //de aqui abajo todo es del menu


    //METODOS DEL BUSCADOR
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {

            ArrayList<GruposMusicalesModelo> listafiltrada = filter(listaBuscador, newText);
            gruposMusicalesAdaptador.setFilter(listafiltrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private ArrayList<GruposMusicalesModelo> filter(ArrayList<GruposMusicalesModelo> lista, String texto) {
        ArrayList<GruposMusicalesModelo> listafiltrada = new ArrayList<>();
        try {
            texto = texto.toLowerCase();
            for (GruposMusicalesModelo grupos : lista) {
                String grupos2 = grupos.getNombreGrupo().toLowerCase();
                if (grupos2.contains(texto)) {
                    listafiltrada.add(grupos);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listafiltrada;
    }


}
