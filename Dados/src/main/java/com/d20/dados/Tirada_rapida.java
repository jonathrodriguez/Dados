package com.d20.dados;

import android.content.*;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.*;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.LinearLayout.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import java.util.Collections.*;

public class Tirada_rapida extends Activity {
    public final static String EXTRA_DADOS = "com.d20.dados.DADOS";
    public final static String EXTRA_DADOS_TYPES = "com.d20.dados.DADOS_TYPES";

    private static final ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(new Integer[] {100, 20, 12, 10, 8, 6, 4}));
    //private static int[] array = {100, 20, 12, 10, 8, 6, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tirada_rapida);

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);

        for (int i=0; i < array.size(); i++) {
            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.HORIZONTAL);
            l2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            l2.setGravity(Gravity.CENTER);

            EditText np = new EditText(this);
            np.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            np.setInputType(InputType.TYPE_CLASS_NUMBER);
            np.setHint("0");
            np.setTag(array.get(i));
            np.setGravity(Gravity.FILL_HORIZONTAL);
            np.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(final View v, boolean hasFocus) {
                    if (v.hasFocus() && ((EditText)v).isEnabled() && ((EditText)v).isFocusable()) {
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                final InputMethodManager imm = (InputMethodManager) v.getContext()
                                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                    }
                }
            });
            l2.addView(np);

            TextView tv = new TextView(this);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            tv.setText("d".concat(Integer.toString(array.get(i))));
            l2.addView(tv);

            layout.addView(l2);
        }

        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(onClickEventHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tirada_rapida, menu);
        return true;
    }

    View.OnClickListener onClickEventHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
            ArrayList<View> elems = layout.getTouchables();

           // Integer[] dados_c = new Integer[array.size()];
           // Integer[] dados_t = new Integer[array.size()];

            String dados = "";

            for(int i = 0; i < elems.size(); i++) {
                if (((View)elems.get(i)).getClass() == EditText.class) {
                    int index = array.indexOf(((View)elems.get(i)).getTag());
                    Integer TAG = array.get(index);
                    String TEXT = ((EditText)elems.get(i)).getText().toString();
                    Integer VALUE = 0;
                    if ((TEXT != null) && (TEXT.length() > 0)) {
                        VALUE = Integer.parseInt(TEXT);
                    }
                    //dados_c[index] = VALUE;
                    //dados_t[index] = TAG;
                    if (VALUE > 0) {
                        if (dados.length() > 0) {
                            dados = dados.concat(";");
                        }
                        dados = dados.concat(VALUE.toString()).concat("d").concat(TAG.toString());
                    }
                }
            }

            Intent intent = null;
            intent = new Intent(v.getContext(), Resultado_rapido.class);
          //  Bundle bundle = new Bundle();
           // bundle.putSerializable(EXTRA_DADOS_COUNT,dados_c);
           // bundle.putSerializable(EXTRA_DADOS_TYPES,dados_t);
           // intent.putExtras(bundle);
            intent.putExtra(EXTRA_DADOS,dados);
            startActivity(intent);
        }
    };
    
}
