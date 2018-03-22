package mobile.divulga.editais.ifsuldeminas.edu.br.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cadastrados.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cadastrados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cadastrados extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LinearLayout layoutbtn, layout;
    private String arquivo = "historico.txt";
    private int cont=0;
    private ScrollView historico;

    Bitmap bmScreen;

    Dialog screenDialog;
    static final int ID_SCREENDIALOG = 1;

    TableLayout cadastrados;

    String[] orgaos = new String[]{"Prefeitura", "Prefeitura", "Prefeitura", "DME", "DME", "DME", "DME"};

    String[] modalidades = new String[]{"Pregão Presencial", "Pregão Eletrônico","Concorrência" ,"Tomada de Preços","Convite",
            "Concurso", "Leilão"};

    String[] numero = new String[]{"25", "274", "34", "217", "15", "57", "69"};

    String[] objeto = new String[]{"aquisição de material de escritorio como cadeira, caneta, lapis", "mesa", "teclado", "mouse", "lapis", "caneta", "quadro"};

    String[] data = new String[]{"25/02/2018", "03/03/2018", "15/04/2018", "26/04/2018", "30/04/2018", "12/05/2018", "18/05/2018"};


    public Cadastrados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityHistorico.
     */
    // TODO: Rename and change types and number of parameters
    public static Cadastrados newInstance(String param1, String param2) {
        Cadastrados fragment = new Cadastrados();
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
        View v = inflater.inflate(R.layout.fragment_cadastrados, container, false);

        layout = v.findViewById(R.id.layout);

        for(int i=0; i<modalidades.length; i++){
            LinearLayout item = new LinearLayout(getContext());
            item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            TextView nome = new TextView(getContext());
            nome.setText(orgaos[i]);
            nome.setTextSize(20);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                nome.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            nome.setPadding(5,5,5,5);
            nome.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            TextView descricao = new TextView(getContext());
            descricao.setText(objeto[i]);
            descricao.setTextSize(20);
            descricao.setPadding(5,5,5,5);
            descricao.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            if(i%2==0){
                item.setBackgroundResource(R.drawable.bg_circle);
                nome.setTextColor(getResources().getColor(R.color.cardview_light_background));
                descricao.setTextColor(getResources().getColor(R.color.cardview_light_background));
            }
            else{
                item.setBackgroundResource(R.drawable.bg_light);
                nome.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                descricao.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            }
            item.setTag(numero[i]);
            item.addView(nome);
            item.addView(descricao);
            item.setOnClickListener(this);
            layout.addView(item);
        }

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

            //showDialog();
            test("Tomada de Preço");

            Toast.makeText(getContext(), "Aqui aparecerá as informações do edital", Toast.LENGTH_LONG).show();
        }


    public void test(String trecho){
        Dialog dialog = new Dialog(getContext(), R.style.Theme_AppCompat_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog = onCreateDialog(trecho);
        dialog.show();
    }

    protected Dialog onCreateDialog(String trecho) {
        screenDialog = new Dialog(getContext());
        screenDialog.setContentView(R.layout.popup);
        TextView modalidade = screenDialog.findViewById(R.id.txtModalidade);
        TextView numero = screenDialog.findViewById(R.id.txtNumero);
        TextView objeto = screenDialog.findViewById(R.id.txtObjeto);
        TextView data = screenDialog.findViewById(R.id.txtData);

        modalidade.setText(trecho);
        numero.setText("162");
        objeto.setText("mesa");
        data.setText("03/03/2018");
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
}
