package com.example.fragmentsfilms.controller;

import com.example.fragmentsfilms.R;
import com.example.fragmentsfilms.entites.Ator;

import java.util.ArrayList;
import java.util.List;

public class ControllerAtor{
    private static ControllerAtor controllerAtor;
    private List<Ator> listAtor;

    private ControllerAtor(){
        listAtor = new ArrayList<>();
    }


    public static  ControllerAtor getInstance(){
        if(controllerAtor == null){
            controllerAtor = new ControllerAtor();
        }
        return controllerAtor;
    }
    private void criarAtor(){
        listAtor.add(new Ator("Joaquin Phoenix","28/10/1974", R.drawable.joaquin_phoenix));
        listAtor.add(new Ator("Mel Gibson","03/01/1956",R.drawable.mel_gibson));
        listAtor.add(new Ator("Tom Hanks", "09/07/1956", R.drawable.tom_hanks));
        listAtor.add(new Ator("Damiam Lewis","11/02/1971", R.drawable.damian_lewis));
    }

    public List<Ator> getListAtor() {
        return listAtor;
    }
}
