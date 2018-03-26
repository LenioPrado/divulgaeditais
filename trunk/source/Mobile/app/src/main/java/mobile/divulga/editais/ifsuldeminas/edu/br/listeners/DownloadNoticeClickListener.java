package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.view.View;
import android.widget.Toast;

public class DownloadNoticeClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Efetuar download do edital", Toast.LENGTH_LONG).show();
    }
}
