package org.asesauta.sil.android;

import java.util.ArrayList;
import java.util.List;

import org.asesauta.anapalabra.Silabeador;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {
    private final String TAG = "silabas";
    private final String SILABAS = "sílabas: ";
    private final String TONICA = "sílaba tónica: ";
    private String palabra;
    private ArrayList<String> silabas;
    private String tonica;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout silabasResult = (LinearLayout) findViewById(R.id.LinearLayoutSilabas);
		silabasResult.setVisibility(LinearLayout.INVISIBLE);
		LinearLayout tonicaResult = (LinearLayout) findViewById(R.id.LinearLayoutTonica);
		tonicaResult.setVisibility(LinearLayout.INVISIBLE);
        
        Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(this);
        
        if (savedInstanceState!=null) {
    		Log.i(TAG, "recuperando la instancia");
    		EditText input = (EditText) findViewById(R.id.EditText01);
    		palabra = savedInstanceState.getString("palabra");
    		tonica = savedInstanceState.getString("tonica");
    		silabas = savedInstanceState.getStringArrayList("silabas");
    		input.setText(palabra);
    		updateScreen();
        }
    }
    
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.i(TAG, "guardando el estado");
		outState.putString("palabra", palabra);
		outState.putStringArrayList("silabas", silabas);
		outState.putString("tonica", tonica);
	}

	@Override
	public void onClick(View v)
	{
		Log.i(TAG, "click");
		EditText input = (EditText) findViewById(R.id.EditText01);
		Silabeador silabeador = new Silabeador();
		palabra = input.getText().toString();
		silabas = silabeador.silabear(palabra.trim());
		tonica = silabas.get(silabeador.tonica(silabas));
		updateScreen();
	}
	
	/**
	 * Se llama al pulsar el botón 'silabear' o al restaurar el estado.
	 */
	public void updateScreen()
	{
		if (silabas!=null && silabas.size()>0) {
			LinearLayout silabasResult = (LinearLayout) findViewById(R.id.LinearLayoutSilabas);
			silabasResult.setVisibility(LinearLayout.VISIBLE);
			TextView outputSilabas = (TextView) findViewById(R.id.TextView01);
			outputSilabas.setText(SILABAS+formatSilabas(silabas));
		}
		if (tonica!=null) {
			TextView outputTonica = (TextView) findViewById(R.id.TextView02);
			outputTonica.setText(TONICA+tonica);
			LinearLayout tonicaResult = (LinearLayout) findViewById(R.id.LinearLayoutTonica);
			tonicaResult.setVisibility(LinearLayout.VISIBLE);
		}
	}
	
	public String formatSilabas(List<String> silabas) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<silabas.size(); i++) {
			sb.append(silabas.get(i));
			if (i!=silabas.size()-1) sb.append("-"); 
		}
		return sb.toString();
	}
}