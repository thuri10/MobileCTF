package com.example.test.ctf02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private Button login;
    private EditText passWord;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main_1);
        this.passWord = (EditText) findViewById(R.id.passWord);
        this.login = (Button) findViewById(R.id.button);
        this.login.setOnClickListener(new View.OnClickListener() { // from class: com.example.test.ctf02.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String str = MainActivity.this.passWord.getText().toString();
                Check check = new Check();
                if (check.checkPassword(str)) {
                    Toast.makeText(MainActivity.this, "Good,Please go on!", 0).show();
                    Intent intent = new Intent(MainActivity.this, (Class<?>) MainActivity2.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                    return;
                }
                Toast.makeText(MainActivity.this, "Failed", 0).show();
            }
        });
    }
}
