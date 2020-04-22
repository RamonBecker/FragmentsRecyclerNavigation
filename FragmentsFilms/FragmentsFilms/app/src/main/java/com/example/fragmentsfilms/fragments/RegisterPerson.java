package com.example.fragmentsfilms.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.fragmentsfilms.R;
import com.example.fragmentsfilms.controller.ControllerAtor;
import com.example.fragmentsfilms.controller.ControllerDiretor;
import com.example.fragmentsfilms.entites.Ator;
import com.example.fragmentsfilms.entites.Diretor;

import java.util.Calendar;

public class RegisterPerson extends Fragment {
    private TextView titulo;
    private EditText namePerson;
    private EditText dataNascimentoPerson;
    private Button buttonRegister;
    public static String typeRegister = "";
    private ControllerDiretor controllerDiretor;
    private ControllerAtor controllerAtor;
    private DatePickerDialog picker;
    private String nomeText;
    private  String dataNascimentoText;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    public RegisterPerson(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_person, container, false);

        namePerson = view.findViewById(R.id.editTextNamePerson);
        dataNascimentoPerson = view.findViewById(R.id.editTextAnoNascimento);
        titulo = view.findViewById(R.id.textViewTituloPerson);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        controllerAtor = ControllerAtor.getInstance();
        controllerDiretor = ControllerDiretor.getInstance();

        dataNascimentoPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dataNascimentoPerson.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = "";
                nomeText =  String.valueOf(namePerson.getText());
                dataNascimentoText = String.valueOf(dataNascimentoPerson.getText());

                fragmentTransaction = fragmentManager.beginTransaction();

                if(typeRegister.contentEquals("Cadastrar Diretor")){
                    Log.i("log", "Cadastrar Diretor:"+nomeText + " "+ dataNascimentoText);
                    Diretor diretor = new Diretor(nomeText , dataNascimentoText, R.drawable.pessoa);
                    controllerDiretor.addDiretor(diretor);
                    aux = "Diretor";
                    fragmentTransaction.replace(R.id.container_fragment, new RecyclerFragmentDiretor());
                }

                else if(typeRegister.contentEquals("Cadastrar Ator")){

                    Log.i("log", "Cadastrar Ator:"+nomeText + " "+ dataNascimentoText);
                    Ator ator = new Ator(nomeText,dataNascimentoText, R.drawable.pessoa);
                    controllerAtor.addAtor(ator);
                    fragmentTransaction.replace(R.id.container_fragment, new RecyclerFragmentAtor());
                    aux = "Ator";
                }
                Toast.makeText(getContext(), aux+" cadastrado com sucesso !", Toast.LENGTH_SHORT).show();
                fragmentTransaction.commit();
            }
        });

        titulo.setText(typeRegister);

        return view;
    }
}
