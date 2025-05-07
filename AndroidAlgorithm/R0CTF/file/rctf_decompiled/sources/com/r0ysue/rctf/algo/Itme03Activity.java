package com.r0ysue.rctf.algo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.r0ysue.rctf.R;
import com.r0ysue.rctf.utils.AlgoHelper;

/* loaded from: classes5.dex */
public class Itme03Activity extends AppCompatActivity {
    String k = "r00000000000ysue";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itme03);
        final EditText username_et = (EditText) findViewById(R.id.editText_03);
        final TextView message_tv = (TextView) findViewById(R.id.textView_03);
        findViewById(R.id.button_item_03).setOnClickListener(new View.OnClickListener() { // from class: com.r0ysue.rctf.algo.Itme03Activity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String signValue = AlgoHelper.Sign(username_et.getText().toString());
                String result = AlgoHelper.encryp(signValue, Itme03Activity.this.k);
                Log.e("ZTAG", result.toLowerCase().substring(0, 32));
                if (result.toLowerCase().substring(0, 32).equals("134986a7705d88191c1f58abf5630ecd")) {
                    message_tv.setText("Congratulation for you!!!");
                } else {
                    message_tv.setText("Keep Going........");
                }
            }
        });
    }
}
