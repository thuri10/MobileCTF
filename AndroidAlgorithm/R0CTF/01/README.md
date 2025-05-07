# Theme one


> Topics


Get the flag through the countercompilation program 


![](./image/01.png)


> Reverse compilation program, read business logic code


![](./image/02.png)


From one point, we need to get the encrypted result equal to `A952C70B9F21623B8B826F0355132163`


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















