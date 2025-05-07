
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [Disadvantages of the old Fart12 customized jadx](#Disadvantages of the old fart12 customized jadx)
- [New tool: Fart12 customized automatic batch repair script released](#New tool fart12 customized automatic batch repair script released)
  - [The reason why Fart12 is not afraid of environmental testing](#fart12The reason why fart12 is not afraid of environmental testing)
  - [One line command automatically repairs all dex](#One line command automatically repairs all dex)
- [Example Demonstration of Complete Shelling and Repair Process](#Example Demonstration of Complete Shelling and Repair Process)
  - [Remove the whole shell first](#Remove the whole shell first)
  - [Re-extract the shell](#Re-extract the shell)
  - [Reorganize dex](#Reorganize dex)
  - [Reorganize dex and use FartFix on mobile phones](#Reorganize dex and use fartfix on mobile phones)
- [Bypassing the shell detection mechanism](#Bypassing the shell detection mechanism)

<!-- /code_chunk_output -->


### Disadvantages of the old Fart12 customized version of jadx

The previously launched Fart12 customized version of jadx can automatically reconstruct and merge the dex file removed by Fart12 with the function body bin file obtained during the active call process, repair and merge them into a new dex.

![](pic/01.png)

After the repair, the size of the dex file will also increase, and the function body will also change from the original nop to the real function logic code body.

![](pic/02.png)

Although the tool is easy to use, it still has some limitations. For example:

1. Only a single dex file can be repaired at a time, which is inefficient;
2. There is also the issue of repair timing. When using dex dumped from different unpacking points to repair, some repairs may fail.

Regarding the second point above, let me explain in detail. Theoretically speaking, in the process of unpacking the second-generation function body filling shell, the overall dex dumped at any time should have the same content, or the dex dumped at a later time should be more complete than the one dumped at an earlier time, because the decryption and restoration of some function bodies will be triggered during the running of the App.

However, sometimes, when using the whole dex dumped at a delayed point to repair, the repair may fail. This is because some shells damage the dex structure in memory after the whole load is completed to resist some whole dump tools. For example, damaging the dex file header can cause reverse tools such as jadx and geb to not recognize the dex even after it is removed. It can also resist tools such as frida-dexdump to traverse and search the dex file header in memory. The relevant code is as follows:

```ts
/* https://github.com/hluwa/frida-dexdump/blob/d4b7d24a8ce0dada17fb1ce9849a8c1cffcb2cae/agent/src/search.ts#L115 */

Process.enumerateRanges('r--').forEach(function (range: RangeDetails) {
        try {
            Memory.scanSync(range.base, range.size, "64 65 78 0a 30 ?? ?? 00").forEach(function (match) {
                if (range.file && range.file.path
                    && (range.file.path.startsWith("/data/dalvik-cache/") ||
                        range.file.path.startsWith("/system/"))) {
                    return;
                }
                ...
            });

```

As long as the dex structure in the memory does not affect the normal operation of the App, there are still many fields that can be randomly filled with some useless garbage data to construct a deformed dex, making the dex unrecognizable and undecompilable. This will affect the success rate of repairing using the dex dumped at the lag point.


### New tool: Fart12 custom automatic batch repair script released

Before introducing the new tool, let me give you an advertisement.

#### The reason why Fart12 is not afraid of environmental testing

Fart12's unshelled ROM does not have root, nor is it a userdebug debugging system, nor does it have any string feature fingerprints such as fart. It also has a Google Play Store. It is just a normal mobile phone system for ordinary people.

#### One line of command automatically repairs all dex

This automatic repair tool is specially customized for Fart12 unpacked ROM. It can automatically repair and merge all dex and bin files obtained by unpacking the free FART10 and FART12, without the need to repair them one by one. The specific usage process is as follows:

1. After using the whitelist.txt file to complete the active call of the class to be repaired, directly `adb pull /sdcard/ooxx/packagename` to obtain the complete directory file. In this directory, there are all the dex, txt, and bin files obtained by unpacking and repairing FART10 and FART12. The directory content is as follows:

![](pic/03.png)

2. Start cmd and execute java -jar repireall folderpath to start merging and repairing all dex and bin files in the directory. The following figure is a screenshot of the start of repair. At this time, automatic repair of dex and bin files begins one by one.

![](pic/04.png)

3. When the repair is completed, the prompt "Rrepire all dexfile end!" will be printed, which means that the automated repair has ended.

![](pic/05.png)

The repaired dex is located in the repire directory under the current directory. The following figure shows the directory and a screenshot of the repaired dex file.

![](pic/06.png)
![](pic/07.png)

After the repair is completed, you only need to use jadx, jeb, gda, etc. to open it.

### Case demonstration of the complete shell removal and repair process

First install test.apk to the Fart12 phone.

```
$ adb install test.apk
```

#### First remove the overall shell

After the installation is complete, long press the App icon, click "App Info", and in the "Permissions" column, change the "Files and Media" permission to "Allow management of all files", and click "Allow" for any prompts. Finally, "Files and Media" will appear in the "Allowed" column.

>Of course, some apps don't actually apply for file and media permissions, but the shelled ROM has already dealt with this. When the app is installed, it will apply for this permission, so don't worry about it.

At this time, click to open the App, and the whole shell has been removed. When adb enters the phone, you can see the whole dex file and the class list txt file that have been removed at different times.

```
$ cd /sdcard/ooxx/com.yunmai.valueoflife
$ ls
```

![](pic/08.png)

You can directly search for some of the four major component function names registered in AndroidManifest.xml in the folder. These function names generally cannot be obfuscated, such as `com.yunmai.valueoflife.MainActivity`.

![](pic/09.png)

The commands used are as follows:

```
$ grep -ril "MainActivity" *
```

![](pic/10.png)

If the class we are looking for exists in the file `8461968_classlist_LoadMethod.txt`, then drag `8461968_dexfile*.dex` to the computer and open a decompiler to see if it is correct. We can find that most of the class names and method names are indeed obfuscated.

![](pic/11.png)

Switch to the smali tab, and you can see that the method bodies are all nops. This is a typical shell extraction feature, and the actual logical process of the method cannot be seen.

![](pic/12.png)

The next step is to solve the problem of extracting the shell and restore the function body.

#### Remove the shell again

To unpack the shell, the class is loaded and then the function body is dumped into a bin file. Finally, the function body in the bin file is filled back into the dex file to form a complete dex file. In this way, the method logic in the figure above is restored.

And in the decompression scheme, the whitelist mode is selected. Why not just decompress the entire package? There are three reasons for this:

1. Some apps are relatively large, with tens of thousands, hundreds of thousands, or millions of classes and methods. Full recovery is time-consuming and labor-intensive. If the system cannot handle it, it will kill the app or crash itself.

2. Some shells will write some junk classes that are not used by the `App`, and write some exit codes in the junk classes. These junk classes will never be loaded in the normal use process of the App. If Fart12 loads them at this time, it will step on a landmine and the App will crash and exit.

3. Some shells will also hook the functions in Art's class loading process to sense which classes are being loaded, such as LoadMethod or LinkCode. Once the traversal of the class list is found, it can exit to prevent the subsequent active loading of class methods.

![](pic/13.png)

In our case, the above situation does not exist, so let's learn how to restore the full volume first. In the directory of `/sdcard/ooxx/com.yunmai.valueoflife`, execute:

```
$ cat *classlist* >> whitelist.txt
```

If you are not sure, you can check whether the `whitelist.txt` is already filled with function class method names.

Open `logcat` and wait for a while, you can see the log of `fart` in the log. At this time, it is in the function body file of the `dump` class.

```
ActivityThread: sleep over and start fart
ActivityThread: try loadClass class:XI.CA.XI.K0$XI
ActivityThread: try loadClass class:XI.CA.XI.XI
```

![](pic/14.png)

You can use the `ls -alit` command multiple times to view the latest generated bin file.

![](pic/15.png)

The dump process can last from several minutes to several hours, until the words "fart run over" appear in "logcat", indicating that the dump is complete, and then drag the entire folder to the computer.

```
% adb pull /sdcard/ooxx/com.yunmai.valueoflife
```

#### Reorganize dex

Thanks to the release of new tools, reorganizing dex has become extremely simple, just one command:

```
% java -jar repireall.jar com.yunmai.valueoflife
```

![](pic/16.png)

>Of course, it is necessary to install the JDK environment on the computer in advance.

Reorganization takes some time. The tool will automatically use the function body in the bin file to fill in the corresponding dex file. The prompt for the completion of the reorganization is: `Repire all dexfile end! Please enjoy!`

After the reorganization is completed, a folder called `repire` will be generated in the same directory, which contains all the repaired dex files. For example, if we look at the dex file that lacks the function body, we can see the business logic of the function body clearly.

![](pic/17.png)

In addition, everyone may have questions, what is the difference between the three files dex, LoadMethod, and OpenCommen?

![](pic/18.png)

In fact, they are the whole dex files dumped at different unpacking times. The previous article also explained that in some cases, abnormal dex will be dumped. Therefore, providing more opportunities can have higher fault tolerance, which means: there is always one right one.

#### Reorganize dex and use FartFix on mobile phone

Enthusiastic netizens made the above repair tool into an App, called FartFix, which can be repaired directly on the phone without dragging it to the computer for repair, saving the trouble of installing the Java environment on the computer. The interface is as follows:

![](pic/19.png)

After the above process of actively calling the dump function body is completed, click the App to open it, grant the folder read and write management permissions as required, enter the path of the dex to be repaired, and click Start Processing.

```
% adb shell input text /sdcard/ooxx/com.yunmai.valueoflife
```

The app is very crude and there is no prompt during the whole process, including in logcat. After a while, you can find that the repaired dex has been generated in the directory /sdcard/ooxx/repire, as shown in the figure.

![](pic/20.png)

Then you can drag it to your computer for analysis.

### Bypassing the shell detection mechanism

As mentioned above, the shell has some methods to detect and resist active calls of Fart12. Correspondingly, we also have some methods to bypass these detections. There are two main methods:

1. Narrow the scope. For example, only write a few, dozens, or hundreds of class names to the whitelist.txt file, and only remove the classes you want to see. The shell will never prevent the loading of business code, otherwise the App itself will crash. Or only process all classes in a dex:

```
$ cp 8461968_classlist.txt whitelist.txt
```

2. Skip the detection class. In the logcat log, you can see the class name that was attempted to be loaded when the crash occurred. Just bypass that class. You can delete that class from whitelist.txt. Clear all other files and click App to start over.