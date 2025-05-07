## easyso challenge
[https://github.com/r0ysue/AndroidSecurityStudy/tree/master/Student/009](https://github.com/r0ysue/AndroidSecurityStudy/tree/master/Student/009)

## Solution
When analyzing the android Manifest file, it has only one exported activity `MainActivity`

```xml
    <activity android:name="com.roysue.easyso1.MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN"/>
            <category android:name="android.intent.category.LAUNCHER"/>
        </intent-filter>
    </activity>
```

Looking at the `com.roysue.easyso1.MainActivity` it loads a native library and compares to the hardcoded value `81d44bb042d5de9a7db2a5a856a29b5a` in the verify function.

```java

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
```

The name of the module is `roysue`

```java
 public static native String method01(String str);

    public static native String method02(String str);

    static {
        System.loadLibrary("roysue");
    }
```
