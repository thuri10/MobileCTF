# Theme one


> Topics

The code for the `Item01Activity` is

```java
/* loaded from: classes5.dex */
public class Item01Activity extends AppCompatActivity {
    private String mKey = "0123456789roysue";
    TextView message_tv;
    EditText username_et;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item01);
        this.username_et = (EditText) findViewById(R.id.editText);
        this.message_tv = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button_item_01).setOnClickListener(new View.OnClickListener() { // from class: com.r0ysue.rctf.algo.Item01Activity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String result1 = null;
                try {
                    result1 = AlgoHelper.doMath(Item01Activity.this.username_et.getText().toString().getBytes("utf-8")) + "";
                    String str = AlgoHelper.doMath(result1.getBytes()) + "";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String result3 = AlgoHelper.encryp(result1, Item01Activity.this.mKey);
                if (result3.equals("8237CE97506C0FB1D389F2A906FE04FC0A")) {
                    Item01Activity.this.message_tv.setText("Congratulation for you!!!");
                } else {
                    Item01Activity.this.message_tv.setText("Keep Going........");
                }
            }
        });
    }
}
```

The `doMath` function calls the native library for further analysis

```java
public class AlgoHelper {
    public static native String Sign(String str);

    public static native String decrypt(String str, String str2);

    public static native String doMath(byte[] bArr);

    public static native String encryp(String str, String str2);

    static {
        System.loadLibrary("rctf");
    }
}
```


Get the flag through the countercompilation program 


![](./image/01.png)


> Reverse compilation program, read business logic code


![](./image/02.png)


From one point, we need to get the encrypted result equal to `8237CE97506C0FB1D389F2A906FE04FC0A`


During this process, the value of the input is encrypted twice;


Encrypted algorithms are all in AlgoHelper and are known to belong to native functions: 


![](./image/03.png)


So we find this so, and we use the IDA to open, so there are 32 bit and 64 bit, and here we analyze the 32 bit so;


![](./image/04.png)


First search for static registry functions, no results:


![](./image/05.png)


Continue to see dynamic registration, generally the dynamic registry is placed in JNI_OnLoad:


![](./image/06.png)


First we analyze the doMath function:


This function is very simple, reflecting calls Java's Base64 


![](./image/07.png)


Analyze encryp function:


According to the symbol name, it should be used AES, but it is not a standard algorithm, we need to test:


![](./image/08.png)


Testing is also very simple, we can use unidbg or frida to test;


First you need to find a standard algorithm to test directly with cyberchef:


![](./image/09.png)


Then initially call, the result is consistent, only look at the first 32bit can, the latter is filled out a set not to look


![](./image/10.png)


Once validated, this is a standard AES algorithm, then the rest of it is done, and it can be decrypted directly in the cyberchef.


![](./image/11.png)















