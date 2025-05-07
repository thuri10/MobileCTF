# Attack World - Beginners - Android Basics

Open the interface after APP as shown in Figure 3-1:

![](./images/24.png) Figure 3-1

Directly use Jadx-gui to open APP, find the logic of button clicks, as shown in Figure 3-2:

![](./images/25.png) Figure 3-2

The specific logic of the examination is shown in Figure 3-3:

![](./images/26.png) Figure 3-3 

We use python here to reverse push, as shown in Figure 3-4:

![](./images/27.png) Figure 3-4

Then enter the password in the password box to the next interface, as shown in Figure 3-5:

![](./images/28.png) Figure 3-5

Normal logic requires reversal of the display code here, and we look at the processing logic on this side, as shown in Figure 3-6:

![](./images/29.png) Figure 3-6

When the button is pressed, a broadcast is sent, and we see where it is accepted, as shown in Figure 3-7:

![](./images/30.png) Figure 3-7

Open this category of receiving broadcasts, as shown in Figure 3-8:

![](./images/31.png) Figure 3-8

It can be found to do nothing, just simply call the NextConent class, and then click the button and find no effect.The contents of NextConent Activity are as shown in Figure 3-9:

![](./images/32.png) Figure 3-9

It actually opens an image here, and we pull this Activity directly with the objection to see what it works, as shown in Figure 3-10, 3, 11:

![](./images/33.png) Figure 3-10

![](./images/34.png) Figure 3-11

The resulting result is as shown in Figure 3-12:

![](./images/35.png) Figure 3-12


