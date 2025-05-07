package com.example.test.ctf02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class MainActivity2 extends AppCompatActivity {
    Button button;
    EditText editText;
    ImageView imageView;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        init();
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.example.test.ctf02.MainActivity2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String str = MainActivity2.this.editText.getText().toString();
                Intent intent = new Intent(str);
                MainActivity2.this.sendBroadcast(intent);
            }
        });
    }

    public void init() {
        this.imageView = (ImageView) findViewById(R.id.image);
        this.imageView.setImageResource(R.drawable.timg);
        this.editText = (EditText) findViewById(R.id.pwd);
        this.button = (Button) findViewById(R.id.button);
    }
}
