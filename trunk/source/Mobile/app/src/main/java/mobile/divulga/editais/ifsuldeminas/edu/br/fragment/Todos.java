package mobile.divulga.editais.ifsuldeminas.edu.br.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityIndex;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Divisoes;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Edital;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

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

        ActivityIndex m = new ActivityIndex();

        getNotices((Activity) getContext());
    }

    private void getNotices(final Context context){
        String endpoint = "user/create";
        final Activity host = (Activity)context;

        Class<List<Notice>> entityClass = (Class) List.class;

        new WebService<List<Notice>>(entityClass, context).query(endpoint, new ResultCallback<List<Notice>>() {
            @Override
            public void onSuccess(List<Notice> notices) {
                if (notices != null) {

                    Toast.makeText(context, "Lista de editais carregada com sucesso!", Toast.LENGTH_SHORT).show();
//                    notices
//                    ArrayList<Edital> editais = new ArrayList<>();
//                    String json = String.valueOf(result.getResult());
//                    editais = parseJson(json);
//                    preencheTabela(editais);
                }
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                String error = String.format("Erro ao trabalhar com o resultado: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todos, container, false);

        layout = v.findViewById(R.id.layout);

        return v;
    }

    private ArrayList<Edital> parseJson(String json){
        try {
            ArrayList<Edital> editais = new ArrayList<>();
            JSONArray array = new JSONArray(json);

//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date data;

            //JSONObject objArray = array.getJSONObject(0);

            for(int i=0; i < array.length(); i++) {
                Edital edital = new Edital();
                JSONObject jsonobject = array.getJSONObject(i);
                Log.i("######", "Edital numero "+i+" com valor: " +jsonobject.getString("fileName"));

                JSONObject obj = jsonobject.getJSONObject("user");

                edital.setOrgao(obj.getString("socialName"));

                obj = jsonobject.getJSONObject("modality");
                edital.setModalidade(obj.getString("acronyms"));

                obj = jsonobject.getJSONObject("companyType");
                edital.setTipoDeEmpresa(obj.getString("description"));

                edital.setNumero(jsonobject.getString("number"));
                edital.setObjeto(jsonobject.getString("object"));
                //edital.setCategoria(jsonobject.getString("category"));
                edital.setData(jsonobject.getString("tradingDate"));
                edital.setDataFechamento(jsonobject.getString("closingDate"));
                edital.setDataPublicacao(jsonobject.getString("publishingDate"));
                edital.setArquivos(jsonobject.getString("fileName"));
                edital.setStatus(jsonobject.getString("status"));
                editais.add(edital);
            }


            return editais;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
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
