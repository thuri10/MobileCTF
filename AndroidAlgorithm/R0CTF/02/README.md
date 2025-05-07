# Question 2

File: ./02RCTF/file/rctf-debug.apk

## Question Requirements

> Question requirements: Enter a 6-digit number and get Congratulation for you!!!

## Problem Solving

Click the button to enter the second question, which requires you to enter a 6-digit number. Open Jadx to view the decompiled core business logic code:

![](./image/01.png)

Use the Sign function to calculate the input content. The Sign function is a native function:

![](./image/02.png)

Open the target so, let's look at the specific implementation of this function:

```c
int __fastcall ngis(_JNIEnv *a1, int a2, int a3)
{
  const char *v3; // r0
  int i; // [sp+8h] [bp-80h]
  int ByteArrayElements; // [sp+Ch] [bp-7Ch]
  int v7; // [sp+10h] [bp-78h]
  int v8; // [sp+18h] [bp-70h]
  int v9; // [sp+1Ch] [bp-6Ch]
  int MethodID; // [sp+20h] [bp-68h]
  int v11; // [sp+24h] [bp-64h]
  int v12; // [sp+28h] [bp-60h]
  int StaticMethodID; // [sp+2Ch] [bp-5Ch]
  int v14; // [sp+30h] [bp-58h]
  int StaticFieldID; // [sp+34h] [bp-54h]
  int Class; // [sp+38h] [bp-50h]
  int StringUTFChars; // [sp+54h] [bp-34h]
  char v20[8]; // [sp+58h] [bp-30h] BYREF
  __int64 v21; // [sp+60h] [bp-28h]
  __int64 v22; // [sp+68h] [bp-20h]
  __int64 v23; // [sp+70h] [bp-18h]

  StringUTFChars = _JNIEnv::GetStringUTFChars(a1, a3);
  v3 = (const char *)_strcat_chk(StringUTFChars, "Mask", -1);
  _android_log_print(4, "roysue", "strcat => %s", v3);
  Class = _JNIEnv::FindClass(a1, "android/os/Build");
  StaticFieldID = _JNIEnv::GetStaticFieldID(a1, Class, "FINGERPRINT", "Ljava/lang/String;");
  _JNIEnv::GetStaticObjectField(a1, Class, StaticFieldID);
  v14 = _JNIEnv::FindClass(a1, "java/security/MessageDigest");
  StaticMethodID = _JNIEnv::GetStaticMethodID(
                     a1,
                     v14,
                     "getInstance",
                     "(Ljava/lang/String;)Ljava/security/MessageDigest;");
  v12 = _JNIEnv::NewStringUTF(a1, "MD5");
  v11 = _JNIEnv::CallStaticObjectMethod(a1, v14, StaticMethodID, v12);
  MethodID = _JNIEnv::GetMethodID(a1, v14, "digest", "([B)[B");
  v9 = _JNIEnv::FindClass(a1, "java/lang/String");
  v8 = _JNIEnv::GetMethodID(a1, v9, "getBytes", "()[B");
  _JNIEnv::CallObjectMethod(a1, a3, v8);
  v7 = _JNIEnv::CallObjectMethod(a1, v11, MethodID);
  ByteArrayElements = _JNIEnv::GetByteArrayElements(a1, v7, 0);
  *(_QWORD *)v20 = 0LL;
  v21 = 0LL;
  v22 = 0LL;
  v23 = 0LL;
  for ( i = 0; i <= 15; ++i )
    sub_C708(&v20[2 * i], -1, "%02x", *(unsigned __int8 *)(ByteArrayElements + i));
  return _JNIEnv::NewStringUTF(a1, v20);
}
```

After browsing the code, we know that the MD5 of the NDK reflection Java layer is used, but we don’t have more time to check whether the content has been changed. Let’s verify it dynamically:

![](./image/03.png)

Then verify it in cyberchef:

![](./image/04.png)

As you can see, the results are consistent, so since it is a standard algorithm and the input is a simple number, we can try our luck in the rainbow table:

![](./image/05.png)

Then enter the result into the text box and get Congratulation for you!!! Result:

![](./image/06.png)

Of course, we can also use frida to actively call this function to violently get the value.

You can also use unidbg to do it, after all, the performance of the mobile phone is a bit poor.

The first step is to build the framework:

```java
package com.test;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.LibraryResolver;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.DynarmicFactory;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.hookzz.HookEntryInfo;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.hookzz.WrapCallback;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;

import java.io.File;

public class Test extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    public static void main(String[] args) {
        Test test = new Test();
        test.call_func();
    }
    public Test(){
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .addBackendFactory(new Unicorn2Factory(true))
                .build();
        Memory memory = emulator.getMemory();
        LibraryResolver resolver = new AndroidResolver(23);
        memory.setLibraryResolver(resolver);
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/test/rctf-debug.apk"));
        vm.setJni(this);
        vm.setVerbose(true);
        DalvikModule dm = vm.loadLibrary("rctf", true);
        dm.callJNI_OnLoad(emulator);
        module = dm.getModule();
    }

    public void call_func(){
        DvmClass klass = vm.resolveClass("com.r0ysue.rctf.utils.AlgoHelper");
        String result = klass.callStaticJniMethodObject(emulator,
                "Sign(Ljava/lang/String;)Ljava/lang/String;","r0ysue").getValue().toString();
        System.out.println(result);
    }
}

```

Then run it and find that an environment error is reported:

![](./image/07.png)

The fingerprint information is obtained here, and the environment needs to be supplemented. However, according to the dynamic verification of frida above, no other information is actually used, which means that this is hindering the operation of unidbg: Here we supplement the environment and just give some content at random:

```java
@Override
public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String signature) {
    switch (signature) {
        case "android/os/Build->FINGERPRINT:Ljava/lang/String;":{
            return new StringObject(vm,"akdjfkakjkdfk");
        }
    }
    return super.getStaticObjectField(vm, dvmClass, signature);
}
```

Finally, the result is:

![](./image/08.png)

We violently traverse the 6-digit number and get the final result (if you are familiar with Java, you can run it in multiple threads)

```java
package com.test;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.LibraryResolver;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.DynarmicFactory;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.hookzz.HookEntryInfo;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.hookzz.WrapCallback;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;

import java.io.File;

public class Test extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    public static void main(String[] args) {
        Test test = new Test();
        test.patch_log();
        // test.call_func();
        String targetHash = "508df4cb2f4d8f80519256258cfb975f";
        String input = "";
        for (int i = 100000; i <= 999999; i++) {
            input = String.format("%06d", i); //Format into a 6-digit string
            System.out.println(input);
            String res = test.call_func(input);
            if (res.equals(targetHash)) {
                System.out.println("Match found! Input: " + input);
                break;
            }
        }
    }

    public void patch_log(){
        emulator.getMemory().pointer(module.base + 0xC4BE).setInt(0,0xbf00bf00);
    }



    public Test(){
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .addBackendFactory(new Unicorn2Factory(true))
                .build();
        Memory memory = emulator.getMemory();
        LibraryResolver resolver = new AndroidResolver(23);
        memory.setLibraryResolver(resolver);
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/test/rctf-debug.apk"));
        vm.setJni(this);
        vm.setVerbose(false);
        DalvikModule dm = vm.loadLibrary("rctf", true);
        dm.callJNI_OnLoad(emulator);
        module = dm.getModule();
    }

    public String call_func(String str){
        DvmClass klass = vm.resolveClass("com.r0ysue.rctf.utils.AlgoHelper");
        String result = klass.callStaticJniMethodObject(emulator,
                "Sign(Ljava/lang/String;)Ljava/lang/String;",str).getValue().toString();
        return result;
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String signature) {
        switch (signature) {
            case "android/os/Build->FINGERPRINT:Ljava/lang/String;":{
                return new StringObject(vm,"akdjfkakjkdfk");
            }
        }
        return super.getStaticObjectField(vm, dvmClass, signature);
    }
}

```

The final result is 234567

















