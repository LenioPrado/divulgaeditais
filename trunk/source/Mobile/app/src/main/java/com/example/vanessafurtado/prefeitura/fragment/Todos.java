package com.example.vanessafurtado.prefeitura.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.vanessafurtado.prefeitura.R;
import com.example.vanessafurtado.prefeitura.activity.ActivityIndex;
import com.example.vanessafurtado.prefeitura.model.Divisoes;
import com.example.vanessafurtado.prefeitura.model.Edital;
import com.example.vanessafurtado.prefeitura.other.Import;
import com.example.vanessafurtado.prefeitura.other.WebServiceCaller;

public class Todos extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Divisoes editais;
    private static Divisoes orgao;
    private static Divisoes categoria;
    private static Divisoes todosEditais;
    private ProgressDialog load;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TableLayout todos;
    private LinearLayout layout;


    ArrayList<Edital> listaEditais = new ArrayList<>();

    Dialog screenDialog;
    static final int ID_SCREENDIALOG = 1;


    public Todos() {
        // Required empty public constructor
    }

    public static Todos newInstance(String param1, String param2) {
        Todos fragment = new Todos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
//        editais = new Divisoes("Editais");
//        orgao = new Divisoes("Orgão");

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        GetJson json = new GetJson();
        json.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todos, container, false);

        layout = v.findViewById(R.id.layout);

        return v;
    }

    private class GetJson extends AsyncTask<Void, Void, ArrayList<Edital>> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(getContext(), "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected ArrayList<Edital> doInBackground(Void... params) {
            Import util = new Import();
            ActivityIndex m = new ActivityIndex();
            return util.getInformacao(WebServiceCaller.getBaseUrl()+"/rest/notice");
        }

        @Override
        protected void onPostExecute(ArrayList<Edital> edital){
            listaEditais = edital;
            preencheTabela(edital);
            load.dismiss();
        }
    }

//    @Override
//    public void postResult(ArrayList<Edital> edital) {
//
//        load.dismiss();
//        listaEditais = edital;
//
//        preencheTabela(edital);
//    }

    public void preencheTabela(ArrayList<Edital> edital){

        for(int i=0; i<edital.size(); i++){
            LinearLayout item = new LinearLayout(getContext());
            item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            TextView orgao = new TextView(getContext());
            orgao.setText(edital.get(i).getOrgao());
            orgao.setTextSize(20);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                orgao.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            orgao.setPadding(5,5,5,5);
            orgao.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            TextView descricao = new TextView(getContext());
            descricao.setText(edital.get(i).getObjeto());
            descricao.setTextSize(20);
            descricao.setPadding(5,5,5,5);
            descricao.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            if(i%2==0){
                item.setBackgroundResource(R.drawable.bg_circle);
                orgao.setTextColor(getResources().getColor(R.color.cardview_light_background));
                descricao.setTextColor(getResources().getColor(R.color.cardview_light_background));
            }
            else{
                item.setBackgroundResource(R.drawable.bg_light);
                orgao.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                descricao.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            }
            item.setTag(i);
            item.addView(orgao);
            item.addView(descricao);
            item.setOnClickListener(this);
            layout.addView(item);
        }
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

        //showDialog();

        int tag = Integer.parseInt(view.getTag().toString());

        test(tag);

        Toast.makeText(getContext(), "Aqui aparecerá as informações do edital", Toast.LENGTH_LONG).show();
    }


    public void test(int trecho){
        Dialog dialog = new Dialog(getContext(), R.style.Theme_AppCompat_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog = onCreateDialog(trecho);
        dialog.show();
    }

    protected Dialog onCreateDialog(int trecho) {
        screenDialog = new Dialog(getContext());
        screenDialog.setContentView(R.layout.popup);
        TextView modalidade = screenDialog.findViewById(R.id.txtModalidade);
        TextView numero = screenDialog.findViewById(R.id.txtNumero);
        TextView objeto = screenDialog.findViewById(R.id.txtObjeto);
        TextView data = screenDialog.findViewById(R.id.txtData);
        TextView dataPubli = screenDialog.findViewById(R.id.txtDataPubli);
        TextView dataFecha = screenDialog.findViewById(R.id.txtDataFecha);
        TextView status = screenDialog.findViewById(R.id.txtStatus);
        TextView tipo = screenDialog.findViewById(R.id.txtTipo);

        modalidade.setText(listaEditais.get(trecho).getModalidade());
        numero.setText(listaEditais.get(trecho).getNumero());
        objeto.setText(listaEditais.get(trecho).getObjeto());
        data.setText(listaEditais.get(trecho).getData());
        dataFecha.setText(listaEditais.get(trecho).getDataFechamento());
        dataPubli.setText(listaEditais.get(trecho).getDataPublicacao());
        status.setText(listaEditais.get(trecho).getStatus());
        tipo.setText(listaEditais.get(trecho).getTipoDeEmpresa());
        return screenDialog;
    }


    public void showDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow();
        dialog.setContentView(R.layout.popup);
        dialog.setTitle("yor title");
        dialog.setCancelable(false);

       try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
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

//
//    public void uncaughtException(Thread thread, final Throwable throwable) {
//        Log.i("########", "Entrou na thread");
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Log.i("O que chegou no Todos:", String.valueOf(Banco.getJSONObjectFromURL("http://192.168.0.105:8080/DivulgaEditais/rest/notice")));
//                } catch (IOException e) {
//                    Log.i("########", "Caiu no catch");
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    Log.i("########", "Caiu no catch");
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
}
