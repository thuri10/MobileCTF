♪ Small loans every day ♪

1. In the program code, the corresponding class name for the phone number login interface (response format: com.tencent.mm.xxx.xxx) is answered> cn.forensix.daikuan.ui.login.LoginActivity

2. Decrypting libdaikuan.So used encryption algorithm AES-CBC-Pkcs5padding

3. Decrypt the encryption key used by libdaikuan.so?
A> HL202304181226XY


4. When the application logs in, the request is a JNI function called, please ask what is the corresponding Java layer function name for the JNI functions?
Answer>doPost


5. The requested IP address when logging in?



6. Hidden flags in the program?(Response format:flag:AABB123456)



Process documentation:

1. First unpack the APK file, extract the libdaikuan.so file, open it with 64 bit IDA and find it unopenable, this so is encrypted.

2. so usually loads at startup, we find the first class to load when the application starts, namely SplashActivity, in which the logic of so loading is found, as shown in Figure 2-1:

![](./images/19.png) Figure 2-1 

3. Continue to trace and see how he deals with the logic of so:

![](./images/20.png) Figure 2-2

4. Going down, you can see the encryption algorithm, using AES/CBC/PKCS5Padding, then you can also verify the key:HL202304181226XY, you are interested to restore the algority, it is placed so decrypted after /data/data/packagename directory, we can extract it directly with IDA; of course you can use frida to fix so dump

5. When opened, you can see the output table, as shown in Figure 2-3:

![](./images/21.png) Figure 2-3

Besides, we see the flag and double-click on it to see what it contains, as shown in Figure 2-4:

![](./images/22.png) Figure 2-4

6. The IP address, as shown in Figure 2 - 5, can be decrypted with a Boo64

![](./images/23.png) Figure 2-5




