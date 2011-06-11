package org.asesauta.sil.android;

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
    }

	@Override
	public void onClick(View v)
	{
		Log.i(TAG, "click");
		LinearLayout silabasResult = (LinearLayout) findViewById(R.id.LinearLayoutSilabas);
		silabasResult.setVisibility(LinearLayout.VISIBLE);
		LinearLayout tonicaResult = (LinearLayout) findViewById(R.id.LinearLayoutTonica);
		tonicaResult.setVisibility(LinearLayout.VISIBLE);
		EditText input = (EditText) findViewById(R.id.EditText01);
		Silabeador silabeador = new Silabeador();
		List<String> silabas = silabeador.silabear(input.getText().toString().trim());
		String tonica = silabas.get(silabeador.tonica(silabas));
		TextView outputSilabas = (TextView) findViewById(R.id.TextView01);
		outputSilabas.setText(SILABAS+formatSilabas(silabas));
		TextView outputTonica = (TextView) findViewById(R.id.TextView02);
		outputTonica.setText(TONICA+tonica);
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