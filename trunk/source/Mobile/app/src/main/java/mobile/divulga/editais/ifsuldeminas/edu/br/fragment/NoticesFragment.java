package mobile.divulga.editais.ifsuldeminas.edu.br.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.Arrays;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.EditalClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Edital;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class NoticesFragment extends Fragment {

    private static final String USER_ID = "userId";
    private OnFragmentInteractionListener mListener;
    private LinearLayout layout;

    public static NoticesFragment newUserNoticesFragmentInstance(int userId) {
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);

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
        }
        getNotices((Activity) getContext(), userId);
    }

    private void getNotices(final Context context, int userId){
        String endpoint = "";

        if(userId == 0){
            endpoint = "notice";
        } else {
            endpoint = "notice/listSubscribedByUserId/"+userId;
        }

        new WebService<Notice[]>(Notice[].class, context).queryList(endpoint, null, RequestMethods.GET, new ResultCallback<Notice[]>() {
            @Override
            public void onSuccess(Notice[] noticesArray) {
                if (noticesArray != null) {
                    List<Notice> notices = Arrays.asList(noticesArray);
                    fillScreenTable(notices);
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
                String error = String.format("Erro ao trabalhar com o resultado: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notices, container, false);

        layout = v.findViewById(R.id.layout);

        return v;
    }

    private int getColor(int index){
        if(index%2==0) {
            return getResources().getColor(R.color.cardview_light_background);
        } else {
            return getResources().getColor(R.color.cardview_dark_background);
        }
    }

    private int getBackground(int index){
        if(index%2==0) {
            return R.drawable.bg_circle;
        } else {
            return R.drawable.bg_light;
        }
    }

    private TextView getTextView(int index, String textContent){
        TextView textView = new TextView(getContext());

        textView.setText(textContent);
        textView.setTextSize(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        textView.setPadding(5,5,5,5);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        textView.setTextColor(getColor(index));

        return textView;
    }

    public void fillScreenTable(List<Notice> notices){

        for (int i = 0; i < notices.size(); i++) {

            Notice notice = notices.get(i);

            TextView companyName = getTextView(i, notice.getUser().getSocialName());
            TextView description = getTextView(i, notice.getObject());
            LinearLayout contentLayout = getLayout(i, notice);

            contentLayout.addView(companyName);
            contentLayout.addView(description);

            layout.addView(contentLayout);
        }
    }

    private LinearLayout getLayout(int index, Object tagContent){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.setOnClickListener(new EditalClickListener());
        layout.setBackgroundResource(getBackground(index));
        layout.setTag(tagContent);

        return layout;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
}
