package com.example.test.ctf02;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
public class NextContent extends AppCompatActivity {
    ImageView imageView;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_content);
        init();
        Change();
    }

    public void init() {
        this.imageView = (ImageView) findViewById(R.id.imageview);
    }

    public void Change() {
        String strFile = getApplicationContext().getDatabasePath("img.jpg").getAbsolutePath();
        try {
            File f = new File(strFile);
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStream is = getApplicationContext().getResources().getAssets().open("timg_2.zip");
            FileOutputStream fos = new FileOutputStream(strFile);
            byte[] buffer = new byte[1024];
            while (true) {
                int count = is.read(buffer);
                if (count <= 0) {
                    break;
                } else {
                    fos.write(buffer, 0, count);
                }
            }
            fos.flush();
            fos.close();
            is.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.imageView.setImageBitmap(BitmapFactory.decodeFile(strFile));
    }
}
