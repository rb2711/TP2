package com.example.tp2;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> mostrarRes;//LiveData son tipos de variables que la vista observa con observe
    private MutableLiveData<Boolean> euroDolar;
    private MutableLiveData<Boolean> dolarEuro;
    private MutableLiveData<Integer> error;

    public LiveData<String> getMostrarRes(){
        if (mostrarRes==null){
            mostrarRes = new MutableLiveData<String>();
        }
        return mostrarRes;
    }

    public LiveData<Boolean> getEuroDolar(){
        if (euroDolar==null){
            euroDolar = new MutableLiveData<Boolean>();
        }
        return euroDolar;
    }

    public LiveData<Boolean> getDolarEuro(){
        if (dolarEuro==null){
            dolarEuro = new MutableLiveData<Boolean>();
        }
        return dolarEuro;
    }
    public LiveData<Integer> getError(){
        if (error == null) {
            error=new MutableLiveData<Integer>();
        }
        return error;
    }


    public void cambiarEstadoEuroDolar(){

        euroDolar.setValue(false);
        error.setValue(View.INVISIBLE);
    }
    public void cambiarEstadoDolarEuro(){

        dolarEuro.setValue(false);
        error.setValue(View.INVISIBLE);
    }

    public void hacerCambio(String valor,String cambio){
        double val;
        double res=0;
        try {
            val = Double.parseDouble(valor);

            switch (cambio) {
                case "ED": {
                    res = val * 1.09;
                    break;
                }
                case "DE": {
                    res = val * 0.92;
                    break;
                }
            }
            mostrarRes.setValue(res + "");
            error.setValue(View.INVISIBLE);
        }catch (Exception e){
            error.setValue(View.VISIBLE);
        }
    }
}
