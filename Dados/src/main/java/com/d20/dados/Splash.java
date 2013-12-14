package com.d20.dados;

import android.content.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Button b1 = (Button)findViewById(R.id.button);
        Button b2 = (Button)findViewById(R.id.button2);
        b1.setOnClickListener(onClickEventHandler);
        b2.setOnClickListener(onClickEventHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    View.OnClickListener onClickEventHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView tv = (TextView)findViewById(R.id.textView);
            tv.setText(((Button)v).getText());

            Intent intent = null;

            if (((Button)v).getText().toString().compareTo(getString(R.string.btn_tiradaRapida)) == 0) {
                intent = new Intent(v.getContext(), Tirada_rapida.class);
            } else if (((Button)v).getText().toString().compareTo(getString(R.string.btn_cargar)) == 0) {
                //TODO
            }

            if (intent != null) startActivity(intent);
        }
    };
}
