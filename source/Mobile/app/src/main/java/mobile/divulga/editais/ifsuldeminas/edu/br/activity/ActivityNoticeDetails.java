package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.DownloadNoticeClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.EditalClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.NoticesCategory;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.DateUtil;

public class ActivityNoticeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_details);

        Button open = findViewById(R.id.abrir);
        open.setOnClickListener(new DownloadNoticeClickListener());

        Bundle bundle = getIntent().getExtras();
        Notice notice = (Notice)bundle.getSerializable("Notice");
        showNoticeDetails(notice);
    }

    protected void showNoticeDetails(Notice notice) {

        TextView modality = findViewById(R.id.txtModalidade);
        TextView numero = findViewById(R.id.txtNumero);
        TextView objeto = findViewById(R.id.txtObjeto);
        TextView data = findViewById(R.id.txtData);
        TextView dataPubli = findViewById(R.id.txtDataPubli);
        TextView dataFecha = findViewById(R.id.txtDataFecha);
        TextView status = findViewById(R.id.txtStatus);
        TextView tipo = findViewById(R.id.txtTipo);

        String modalityText = String.format("%s - %s",
                notice.getModality().getAcronyms(), notice.getModality().getDescription());

        modality.setText(modalityText);
        numero.setText(notice.getNumber());
        objeto.setText(notice.getObject());
        data.setText(DateUtil.createDateFormatyyyyMMdd(notice.getTradingDate()));
        dataFecha.setText(DateUtil.createDateFormatyyyyMMdd(notice.getClosingDate()));
        dataPubli.setText(DateUtil.createDateFormatyyyyMMdd(notice.getPublishingDate()));
        status.setText(notice.getStatus());

        String categoryText = "";
        for (NoticesCategory noticeCategory : notice.getNoticesCategories() ) {
            Category category = noticeCategory.getCategory();
            categoryText += category.getDescription() + "-";
        }

        tipo.setText(categoryText);
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityHome.class);
        startActivity(i);
        this.finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
