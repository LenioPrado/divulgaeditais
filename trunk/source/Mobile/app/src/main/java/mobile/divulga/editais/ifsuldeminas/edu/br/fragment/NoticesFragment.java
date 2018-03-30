package mobile.divulga.editais.ifsuldeminas.edu.br.fragment;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.EditalClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Edital;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.NoticesCategory;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Results;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class NoticesFragment extends Fragment implements Results{

    private static final String USER_ID = "userId";
    private OnFragmentInteractionListener mListener;
    private LinearLayout layout, preLayout;
    private Button limpar;
    private String currentTag = Utils.getTagScreenAllNotices();
    public static List<Notice> notices;
    public static ArrayList<String> categories = new ArrayList<>();
    public static ArrayList<String> insertedBy = new ArrayList<>();

    public static NoticesFragment newUserNoticesFragmentInstance(int userId, String currentTag) {
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        args.putString(Utils.getCurrentTagKey(), currentTag);

        NoticesFragment fragment = new NoticesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int userId = 0;
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
            currentTag = getArguments().getString(Utils.getCurrentTagKey());
        }
        getNotices((Activity) getContext(), userId);
    }

    private void getNotices(final Context context, int userId){
        String endpoint = "";

        if(Utils.isTagScreenAllNotices(currentTag)){
            endpoint = "notice/listNotSubscribed/"+userId;
        } else {
            endpoint = "notice/listSubscribedByUserId/"+userId;
        }

        new WebService<Notice[]>(Notice[].class, context).queryList(endpoint, null, RequestMethods.GET, new ResultCallback<Notice[]>() {
            @Override
            public void onSuccess(Notice[] noticesArray) {
                if (noticesArray != null) {
                    notices = Arrays.asList(noticesArray);
                    fillScreenTable(notices, getContext(), layout, preLayout);
                    Toast.makeText(context, "Lista de editais carregada com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                Toast.makeText(context, "Ocorreu um erro ao realizar a operação!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static List<String> categoryList(){
        for(Notice n : notices){
            List<NoticesCategory> noticesCategories = n.getNoticesCategories();
            for(NoticesCategory c : noticesCategories){
                String verify = c.getCategory().getDescription();
                if(verifyExistence(categories, verify))
                categories.add(verify);
            }
        }
        return categories;
    }

    public static List<String> insertedByList(){
        for(Notice n : notices){
            String verify = n.getUser().getSocialName();
            if(verifyExistence(insertedBy, verify))
                insertedBy.add(verify);
            }
        return insertedBy;
    }

    private static boolean verifyExistence(ArrayList<String> list, String verify){
        if(!list.isEmpty()){
            for(String s : list){
                if(s.compareToIgnoreCase(verify)==0){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Notice> setFilteredNotices(String filter, String type){
        List<Notice> filteredNotices = new ArrayList<Notice>();
        for(Notice n : notices){
            if(type.compareToIgnoreCase("Órgão")==0){
                if(n.getUser().getSocialName().compareToIgnoreCase(filter)==0){
                    filteredNotices.add(n);
                }
            }else{
                List<NoticesCategory> categories = n.getNoticesCategories();
                for(NoticesCategory c : categories){
                    if(c.getCategory().getDescription().compareToIgnoreCase(filter)==0){
                        filteredNotices.add(n);
                    }
                }
            }
        }
        return filteredNotices;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notices, container, false);

        layout = v.findViewById(R.id.layout);
        preLayout = v.findViewById(R.id.layout3);
        limpar = v.findViewById(R.id.btnLimpar);
        limpar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View v) {
                preLayout.removeAllViews();
            }
            });

        return v;
    }

    private int getColor(int index, Context context){
        if(index%2==0) {
            return context.getResources().getColor(R.color.cardview_light_background);
        } else {
            return context.getResources().getColor(R.color.cardview_dark_background);
        }
    }

    private int getBackground(int index){
        if(index%2==0) {
            return R.drawable.bg_circle;
        } else {
            return R.drawable.bg_light;
        }
    }

    private TextView getTextView(int index, String textContent, Context context){
        TextView textView = new TextView(context);

        textView.setText(textContent);
        textView.setTextSize(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        textView.setPadding(5,5,5,5);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        textView.setTextColor(getColor(index, context));

        return textView;
    }

    public void fillScreenTable(List<Notice> notices, Context context, LinearLayout layout, LinearLayout preLayout){

        if(preLayout.getChildCount() > 0)
            preLayout.removeAllViews();

        for (int i = 0; i < notices.size(); i++) {

            Notice notice = notices.get(i);

            TextView companyName = getTextView(i, notice.getUser().getSocialName(), context);
            TextView description = getTextView(i, notice.getObject(), context);
            Object[] tags = new Object[2];
            tags[0] = notice;
            tags[1] = currentTag;
            LinearLayout contentLayout = getLayout(i, tags, context);

            contentLayout.addView(companyName);
            contentLayout.addView(description);

            preLayout.addView(contentLayout);
        }
        //layout.addView(preLayout);
    }

    private LinearLayout getLayout(int index, Object[] tagContents, Context context){
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.setOnClickListener(new EditalClickListener());
        layout.setBackgroundResource(getBackground(index));
        layout.setTag(tagContents);

        return layout;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void resultadoFiltro(List<Notice> resultado, Context context) {
        fillScreenTable(resultado, context, getLayout(), getPreLayout());
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public LinearLayout getLayout(){
        return layout;
    }

    public LinearLayout getPreLayout(){
        return preLayout;
    }
}