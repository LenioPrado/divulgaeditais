package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityNoticeDetails;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;

public class EditalClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View v) {
        Notice notice = (Notice)v.getTag();
        Activity host = (Activity) v.getContext();
        Intent i = new Intent(v.getContext(), ActivityNoticeDetails.class);
        i.putExtra("Notice", notice);
        host.startActivity(i);
        host.finish();
    }
}
