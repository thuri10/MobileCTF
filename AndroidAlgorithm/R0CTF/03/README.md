# Question 3

File: ./02RCTF/file/rctf-debug.apk

> Enter 5 digits and get Congratulation for you!!!

## Decompile apk and analyze

After decompiling the apk, the business code for question 3 was found:

![](./image/01.png)

According to the business code, let's sort out the specific calculation process:

1. Get the value in the text box
2. Encryption through the Sign function
3. Then put it into the encrypt function for encryption, and pass a value into it: r00000000000ysue
4. The result is equal to 134986a7705d88191c1f58abf5630ecd

Let's see what the Sign and Encrypt functions encrypt:

![](./image/02.png)

As you can see, they are native functions. You need to go to the native layer to see the specific code. Unzip the apk and drag the so into IDA for analysis.

Note: Analyze 64-bit so;

![](./image/03.png)

We have already analyzed these functions before, so we will not focus on them here. At the same time, let's move our focus to decrypt;

Generally speaking, encryption functions and decryption functions appear together, and they are called actively here:

![](./image/04.png)

The output result is 16 bits, which is a bit strange. The md5 value should be 32 bits. It seems that this decryption function is unreliable.

Or take the normal brute force cracking approach:

There is one point you need to pay attention to here, otherwise you will make a mistake:

![](./image/05.png)

The result needs to take the lower 32 bits, be sure to pay attention to this detail; we use frida to brute force traversal:

![](./image/06.png)

Scriptwriter:

```js


function Sign(str){
    let AlgoHelper = Java.use("com.r0ysue.rctf.utils.AlgoHelper");
    return AlgoHelper["Sign"](str)
}

function encrypt(str){
    let AlgoHelper = Java.use("com.r0ysue.rctf.utils.AlgoHelper");
    return AlgoHelper["encryp"](Sign(str), "r00000000000ysue")
}

function main(){
    Java.perform(function(){
        // let AlgoHelper = Java.use("com.r0ysue.rctf.utils.AlgoHelper");
        // var str = "134986a7705d88191c1f58abf5630ecd";
        // var str1 = "r00000000000ysue";
        // var result = AlgoHelper["decrypt"](str, str1);
        // console.log("result => ", result)
        for(var i=10000;i<99999;i++){
            var result = encrypt(i.toString())
            var value = result.toLowerCase().substring(0, 32);
            if(value === "134986a7705d88191c1f58abf5630ecd"){
                console.log(i.toString())
                break
            }
        }
    })
}




setImmediate(main)
```

The final result is 22578









