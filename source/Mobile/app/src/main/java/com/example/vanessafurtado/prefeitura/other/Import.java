package com.example.vanessafurtado.prefeitura.other;


  import android.graphics.Bitmap;
          import android.graphics.BitmapFactory;
          import android.util.Log;

  import com.example.vanessafurtado.prefeitura.model.Edital;

  import org.json.JSONArray;
          import org.json.JSONException;
          import org.json.JSONObject;

          import java.io.IOException;
          import java.io.InputStream;
          import java.net.URL;
  import java.text.DateFormat;
  import java.text.FieldPosition;
  import java.text.ParsePosition;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.Date;

public class Import {

    public ArrayList<Edital> getInformacao(String end){
        String json;
        ArrayList<Edital> editais = new ArrayList<>();
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        editais = parseJson(json);

        return editais;
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
}