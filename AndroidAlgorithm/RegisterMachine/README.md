# Attack World - Newcomer - Android 2.0


Install and open the APP, you can see the need to enter the password, we use Jadx-gui to open it, the core logic as shown in Figure 3-13:


![](./images/36.png) Figure 3-13


View the getResult function as shown in Figure 3-14:


![](./images/37.png) Figure 3-14


It's a JNI function, and we go directly to so to see its logic, as shown in Figure 3-15, the password length required to enter is 15 bits.


![](./images/38.png) Figure 3-15


Then look at the Init function, as shown in Figure 3-16:


![](./images/39.png) Figure 3-16


The contents of this function are operated on the 15-bit password entered as follows:


``` 
pw => 123 456 789 abc def 


    | | (18+), | | | 1 4 7 a d 2 5 8 b e 3 6 9 c f 
```


The rest is based on the information provided varies or comes back, the script is as follows:


```python
# (a1 [i] ^ 0x80)/2 = a1[i] flag1 = "LN^dl" for i in range(4): print(chr((ord(flag1[ i]) ^ 0 x80) // 2), end="") print("l") # result => f g o r l


result = "LN^dl" a5 = [0x20, 0x35, 0x2D, 0x16, 0x61] for i in range(4): print(chr(a5[i] ^ ord(result[i)),end=' ') print(Chr(0x61))


v7 = "AFBo}" a5 = [0x20, 0x35, 0x2D, 0x16, 0x61] for i in range(4): print(chr(a5[i] ^ ord(v7[i)),end="") print("}") #v7 => a s o y }


lst = [ ["f", "g", "o", "r", "l"], ["l", "{", "s", "R", "a"], ]


for j in range(5): print(lst[0][j], end="") print( lst[1][j],end="")




# (a1 [i] ^ 0x80)/2 = a1[i] flag1 = "LN^dl" for i in range(4): print(chr((ord(flag1[ i]) ^ 0 x80) // 2), end="") print("l") # result => f g o r l


result = "LN^dl" a5 = [0x20, 0x35, 0x2D, 0x16, 0x61] for i in range(4): print(chr(a5[i] ^ ord(result[i)),end=' ') print(Chr(0x61))


v7 = "AFBo}" a5 = [0x20, 0x35, 0x2D, 0x16, 0x61] for i in range(4): print(chr(a5[i] ^ ord(v7[i)),end="") print("}") #v7 => a s o y }


lst = [ ["f", "g", "o", "r", "l"], ["l", "{", "s", "R", "a"], ]


for j in range(5): print(lst[0][j], end="") print( lst[1][j],end="")

```
Finally, we can figure out the flag.





