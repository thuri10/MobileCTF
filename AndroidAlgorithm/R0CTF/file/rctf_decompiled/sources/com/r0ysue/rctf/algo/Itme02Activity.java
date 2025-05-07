package com.r0ysue.rctf.algo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.r0ysue.rctf.R;
import com.r0ysue.rctf.utils.AlgoHelper;

/* loaded from: classes5.dex */
public class Itme02Activity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itme02);
        final EditText username_et = (EditText) findViewById(R.id.editText_02);
        final TextView message_tv = (TextView) findViewById(R.id.textView_02);
        findViewById(R.id.button_item_02).setOnClickListener(new View.OnClickListener() { // from class: com.r0ysue.rctf.algo.Itme02Activity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AlgoHelper.Sign(username_et.getText().toString()).compareTo("508df4cb2f4d8f80519256258cfb975f") == 0) {
                    message_tv.setText("Congratulation for you!!!");
                } else {
                    message_tv.setText("Keep Going........");
                }
            }
        });
    }
}
