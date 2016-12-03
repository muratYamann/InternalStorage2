package com.yamankod.internalstorage2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class main extends Activity {
	private final static String NOTES = "notes.txt";
	private EditText editText;
	private Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editText = (EditText) findViewById(R.id.editor);
		button = (Button) findViewById(R.id.close);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	@Override
	protected void onResume() {
		super.onResume();
		try {
			InputStream in = openFileInput(NOTES);
			if (in != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String str = "";
				StringBuffer buf = new StringBuffer();
				while ((str = reader.readLine()) != null) {
					buf.append(str + "\n");
				}
				in.close();
				editText.setText(buf.toString());
			}
		} catch (Exception e) {
			Toast.makeText(this, "Exception: "+ e.toString(), 2000).show();
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		try {
			OutputStreamWriter out= new OutputStreamWriter(openFileOutput(NOTES, 0));
			out.write(editText.getText().toString());
			out.close();
		} catch (Exception e) {
			Toast.makeText(this, "Exception: "+ e.toString(), 2000).show();
		}
	}
}



