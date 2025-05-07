# Six-layer locking machine

The resource for the case is located in apk/01/six.apk 

## 1. Login bypass

On the login page, we need to enter username and password, and according to the results of the jadx re-compilation, a function encrypted the user name, as shown in Figure 1-1:

![](./images/1.png) Figure 1-1 

The contents of a function are as shown in Figure 1-2:

![](./images/2.png) Figure 1-2

So to bypass login authentication, just need Hook a function to get its return value entered in the password, the username is entered at will.

Hook code as shown in Figure 1-3 below:

![](./images/3.png) Figure 1-3


## 2. The first layer of locking machine bypasses

The page of the first layer lock is as shown in Figure 1-4:

![](./images/4.png) Figure 1-4

The result of the reverse compilation is as shown in Figure 1-5:

![](./images/5.png) Figure 1-5

We can just Hook a function and let it return "R4jS,LLOrLE7/5B+Z6fsl65yj6BgC6YWz66gO6g2t65Pk6a+P65NK44NNROl0wNOLLLL=" without any specific algorithm details.


# Second layer lock bypasses

We look directly at the result of the reverse compilation of the second layer lock machine, as shown in Figure 1-6:

![](./images/6.png) Figure 1-6

We just need to make both of the variables in the if statement true, and in this class, we see that it has a reserve interface, we can use the frida operation directly, the code is as follows:

```js 
function Activity2(){let FridaActivity2 = Java.use("com.example.androiddemo.Activity.FridaActivity2"); // Configure via interface // Fridaactivity2.setStatic_bool_var() // Setup FridaActivity2 directly.static_bool_var.value = true Java.choose ("com.example.androiddemo.Activity.FridaActivity2",{ onMatch:function(ins){ // Configure via interface // ins.setBool_var() // Set ins directly.bool_var.value = true }, onComplete:function(){

}
```

# # Third layer lock bypasses

The third layer locking machine has made some improvements on the second layer base, as shown in Figure 1-7:

![](./images/7.png) Figure 1-7

The variable name is the same as the function name, and if we set the value directly using the method in the previous issue, frida will report an error:


```js 
let FridaActivity3 = Java.use ("com.example.androiddemo.Active.FridaActive3"); // This will report an error to FridaAktivity3.same_name_bool_var.value = true 
// 
```

The correct practice is to add a downline => "_" before renaming the variable.

```js 
FridaActivity3._same_name_bool_var.value = true 
```

# # The fourth layer of lockthrough

Verification code for the fourth layer lock as shown in Figure 1-8

![](./images/8.png) Figure 1-8

The logic of which is tested is multiple functions in the internal class, as shown in Figure 1-9:

![](./images/9.png) Figure 1-9

Two solutions are offered here:

- First: Do the Hook in a row, it's simpler, there's no code in it.

- Second: by reflecting get all the methods of the internal class, then Hook returns true, code as shown below:

```js
function Activity4(){
        var class_name = "com.example.androiddemo.Activity.FridaActivity4$InnerClasses"; 
        var all_methods = Java.use(class_name).class.getDeclaredMethods(); console.log("all_methods => ", all_ methods); 
        for (var i = 0; i < all_ Methods.length; i++) {
                var method = all_methhods[i];
                 console.log ("single method => ", method);
                  var substring = method.toString().substr(method.toString().indexOf(class_name) + class_name.length + 1);
                  var finalMethodString = substring.substr(0, substring.indexOf("("));
                  console.log("finalMethodString => ", finalMethadString); Java.use(class_name)[finalMathodStrin].implementation = function ()
         { return true }; } 
```


# # The fifth layer of lockthrough

The logic of the fifth layer is as shown in Figure 1-10:

![](./images/10.png) Figure 1-10

Continue to getDynamicDexCheck function, as shown in Figure 1-11:

![](./images/11.png) Figure 1-11

Continue with the loadDex function, as shown in Figure 1-12. A dex is dynamically loaded here.The core logic to bypass is that the dex that hook loads dynamically returns true.

![](./images/12.png) Figure 1-12

Dynamic Load Dex is loaded with DexClassLoader, unlike the ClassLoader that the APP currently uses, so we're going to switch Class Loader to Hook.

The code is as follows:

```js
 function Activity5(){ // var methods = Java.enumerateMethods("*!check") // console.log(JSON.stringify(methods, null, 2)) Java.enumerateClassLoaders({onMatch: function (loader) {try {if ( loader.findClass ("com.example.androiddemo.Dynamic. DynamicCheck") {
    
                    Java.classFactory.loader = loader;
    
                } catch (error) { console.log("continuing :" + error)}, onComplete: function () {console. log("EnumerateClassloader END")}) Java.use("com.example.androiddemo.Dynamic.DynamicCheck").check.implementation = function()
```

## The sixth layer of lockthrough

The sixth layer lock is very simple, as shown in Figure 1-13, as long as the check function in the three classes in the Hook diagram returns true.

![](./images/13.png) Figure 1-13

# Registry logging bypassed

The resource for the case is located at apk/02/registerLogin.apk 

Title Requirements:

The app uses machine code authentication to try to bypass machine code verification in order to use the app function.After bypassing, enter your account password and you will get flag._**


- After installing and opening the APP, locate the click event directly to the login button, as in Figure 1-14, the core processing is in MyWaitTimerTask

![](./images/14.png) Figure 1-14

Here, CheckUrl will check the url, it's suspicious, let's track this function.

![](./images/15.png) Figure 1-15

The check logic of the CheckUrl function: if it returns "OK" normally, then "NG", which also matches the detection logic in the previous image

![](./images/16.png) Figure 1-16

hook This function returns "OK"

![](./images/17.png) Figure 1-17

The final result is as shown in Figure 1-18: the flag is displayed correctly on the screen, regardless of what we enter.

![](./images/18.png) Figure 1-18
