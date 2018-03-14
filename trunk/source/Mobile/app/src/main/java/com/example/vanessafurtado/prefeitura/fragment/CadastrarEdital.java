package com.example.vanessafurtado.prefeitura.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vanessafurtado.prefeitura.R;
import com.example.vanessafurtado.prefeitura.activity.ActivityHome;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastrarEdital.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastrarEdital#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarEdital extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditText numero, objeto, data;
    private Button selecionar, cadastrar;
    private Spinner modalidade;
    private File arquivo;

    Dialog screenDialog;
    static final int ID_SCREENDIALOG = 1;
    private LinearLayout braille;

    public CadastrarEdital() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Todos.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastrarEdital newInstance(String param1, String param2) {
        CadastrarEdital fragment = new CadastrarEdital();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cadastrar_edital, container, false);

        modalidade = v.findViewById(R.id.txtModalidade);

        numero = v.findViewById(R.id.txtNumero);
        objeto = v.findViewById(R.id.txtObjeto);
        data = v.findViewById(R.id.txtData);

        String[] modalidades = new String[]{"Pregão Presencial", "Pregão Eletrônico","Concorrência" ,"Tomada de Preços","Convite",
                "Concurso", "Leilão"};


        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, modalidades);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        modalidade.setAdapter(adapterTipo);

        selecionar = v.findViewById(R.id.btnSelecionar);
        cadastrar = v.findViewById(R.id.cadastrar);
        selecionar.setOnClickListener(this);
        cadastrar.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if(view == selecionar){
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

            intent.setType("application/pdf/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            startActivityForResult(intent, 1);

        }

        if(view == cadastrar){

//            Edital e = new Edital(
//                    modalidade.getSelectedItem().toString(),
//                    Integer.parseInt(numero.getText().toString()),
//                    objeto.getText().toString(),
//                    data.getText().toString()
//
//            );
           // BancoDeDados.getInstance().writeEdital(e, arquivo);

            Toast.makeText(getContext(), "Edital cadastrado com sucesso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), ActivityHome.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());

            mBuilder.setSmallIcon(R.drawable.icon)
                    .setContentTitle("Novo Edital")
                    .setContentText("Um novo edital acaba de ser cadastrado")
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder.build());

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                arquivo = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + arquivo.getName());
                String uploadedFileName = arquivo.getName().toString();

                Toast.makeText(getContext(), uploadedFileName, Toast.LENGTH_SHORT).show();


            }

        }

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
}
