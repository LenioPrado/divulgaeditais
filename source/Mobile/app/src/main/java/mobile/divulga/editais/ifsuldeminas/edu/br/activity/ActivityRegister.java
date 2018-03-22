package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.UserServiceCaller;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityRegister extends AppCompatActivity {

    EditText email, password, socialName, fantasyName, cnpj, cnae, zipCode, address, number, complement, neighbourhood, city, phonePrimary, state, phoneSecundary, resposibleName, responsibleCPF;
    Spinner companyType;
    Button cadastrar;
    String baseURL;

    String[] listaEmpresas = new String[] {
            "Selecione","Micro e Pequeno Porte","Médio Porte", "Grande porte"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getInputsFromView();
        setButtonListeners();
        fillViewSelects();
    }

    private void getInputsFromView(){
        socialName = findViewById(R.id.socialName);
        fantasyName = findViewById(R.id.fantasyName);
        cnpj = findViewById(R.id.cnpj);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        companyType = findViewById(R.id.companyType);
        cnae = findViewById(R.id.cnae);
        zipCode = findViewById(R.id.zipCode);
        address = findViewById(R.id.address);
        number = findViewById(R.id.number);
        complement = findViewById(R.id.complement);
        neighbourhood = findViewById(R.id.neighbourhood);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        phonePrimary = findViewById(R.id.phonePrimary);
        phoneSecundary = findViewById(R.id.phoneSecundary);
        resposibleName = findViewById(R.id.resposibleName);
        responsibleCPF = findViewById(R.id.responsibleCPF);
        cadastrar = findViewById(R.id.cadastrar);
    }

    private void setButtonListeners(){

        cadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (inputDataIsValid()) {
                    JSONObject user = getUserData();
                    new UserServiceCaller(v.getContext(), "user/create", RequestMethods.POST, user).execute();
                }
            }
        });
    }

    private void fillViewSelects(){
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaEmpresas);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        companyType.setAdapter(adapterTipo);
    }

    private JSONObject getUserData(){
        JSONObject user = new JSONObject();
        try {
            user.put("socialName", socialName.getText().toString());
            user.put("fantasyName", fantasyName.getText().toString());

            //user.put("companyType", companyType.getSelectedItem().toString());

            //TODO: Corrigir pois está hardcoded

            user.put("companyType", "PE");

            user.put("type", "empresa");
            user.put("email", email.getText().toString());
            user.put("password", password.getText().toString());
            user.put("cnpj", cnpj.getText().toString());
            user.put("state", state.getText().toString());
            user.put("cnae", cnae.getText().toString());
            user.put("zipCode", zipCode.getText().toString());
            user.put("address", address.getText().toString());
            user.put("number", number.getText().toString());
            user.put("complement", complement.getText().toString());
            user.put("neighborhood", neighbourhood.getText().toString());
            user.put("city", city.getText().toString());
            user.put("phonePrimary", phonePrimary.getText().toString());
            user.put("phoneSecondary", phoneSecundary.getText().toString());
            user.put("responsibleName", resposibleName.getText().toString());
            user.put("responsibleCpf", responsibleCPF.getText().toString());

            Log.d("######", user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    private boolean inputDataIsValid(){
        if(socialName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Nome Social", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fantasyName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Nome Fantasia", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((password.getText().toString().equalsIgnoreCase(""))||(password.getText().toString().length()<5)){
            Toast.makeText(this, "Digite uma Senha válida", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cnpj.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CNPJ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cnae.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CNAE", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(zipCode.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CEP", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(address.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Endereço", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(number.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Numero", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(neighbourhood.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Bairro", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(city.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite uma cidade", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phonePrimary.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Telefone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(state.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Estado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(resposibleName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite o nome do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(responsibleCPF.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite o CPF do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityIndex.class);
        startActivity(i);
        this.finish();
        super.onBackPressed();  // optional depending on your needs
    }
}