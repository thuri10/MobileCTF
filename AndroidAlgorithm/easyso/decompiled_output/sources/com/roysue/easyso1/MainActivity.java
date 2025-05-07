package com.roysue.easyso1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    public static native String method01(String str);

    public static native String method02(String str);

    static {
        System.loadLibrary("roysue");
    }

    private void showDialogAndExit(String title) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage("This is unacceptable. The app is now going to exit.");
        alertDialog.setButton(-3, "OK", new DialogInterface.OnClickListener() { // from class: com.roysue.easyso1.MainActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void verify(View view) {
        String v2 = ((EditText) findViewById(R.id.edit_text)).getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        if (method01(v2).equalsIgnoreCase("81d44bb042d5de9a7db2a5a856a29b5a")) {
            alertDialog.setTitle("Success!");
            alertDialog.setMessage("This is the correct secret.");
        } else {
            alertDialog.setTitle("Nope...");
            alertDialog.setMessage("That's not it. Try again.");
        }
        alertDialog.setButton(-3, "OK", new DialogInterface.OnClickListener() { // from class: com.roysue.easyso1.MainActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
