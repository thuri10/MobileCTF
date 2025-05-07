<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->


<!--code_chunk_output -->


- [new tool: Fart12 custom automatic batch repair script released] (#new toolfart12 Custom Automatic Batch Repair script released) - [Fart12 fearless environment detection reasons] - [one line command automatically repairs alldex] - (#one line commands automatically repaired alldex) - (example demonstrates complete decoupling repair process) (#example showing complete decoupage repair procedure) - - [first out of the whole shell] - - (first off the entire shell) - / (# again off the shell extract) - &#reassembly of the shells) -


<!-- /code_chunk_output -->






### Old version of Fart12 custom version of jadex shortcomings


The previously launched Fart12 custom version of jadx can automatically reconstruct and merge the dex files from Fart 12 with the function body bin files obtained during the active call, repair and merge into a new dex.


[] (pic/01.png)


After repairing, the size of thedex file will be increased, and the function body will be converted from the originalnop to the true function logic code.


[] (pic/02.png)


The tool is useful, but there are some limitations.比如：


2. There is also a problem of fixing time, using different decal dump points to fix the decal, some of which may fail to fix.


On the second point above, let me explain.Theoretically, in the second generation function filling shell decoupling process, in fact, no matter which time point dump down the overalldex, the content should be the same, or in some lagging down the time dump of thedex, should be more complete than earlier period dump, because in the run-up of the app will trigger some function of decryption and restoration.


Sometimes, however, when repairing the total dex from the late point dump, there is a repair failure, which is because some shells, after the overall load is completed, have done some damage to the dex structure in the memory, to combat some of the total dump tools.For example, the destruction of the file header of thedex, it can cause even after the decommissioning of thejadx,geb and other reverse tools can not recognize thisdex, can also fight tools such as frida-dexdump, for the memory of the index file head.The code is as follows:


```ts /* https://github.com/hluwa/frida-dexdump/blob/d4b7d24a8ce0dada17fb1ce9849a8c1cffcb2cae/agent/src/search.ts#L115 */


Process.enumerateRanges('r--').forEach(function (range: RangeDetails) { try {Memory.scanSync (range.base, range.size, "64 65 78 0a 30?? 00").forEach(function (match) {if (range.file & & range.file.path & & (range.file.path.startsWith("/data/dalvik-cache/") | | range.file.path.startsWith("/system/")))


```

There are many fields that can randomly fill some unnecessary junk data, to construct a deformeddex, so that the deducteddex can not be distinguished and reverse compiled, this situation will affect the success rate when repairing using the late point dumpeddex.




### New tool: Fart12 customized automatic batch repair script release


Make an advertisement before introducing a new tool.


### # Fart12 is not afraid of environmental detection


Fart12 has no root, no userdebug debugging system, no fingerprints of any string characteristics such as fart, and there is a Google App Store, he is a normal human cell phone system.


One line commands automatically fix all thedex


This automated repair tool is customized for the Fart12 decoupled ROM and automates one-click repair and all thedex andbin files for all of the donated FART10 and FART12 decouped files, no longer requiring one repair.The specific use process is as follows: 


1. After using the whitelist.txt file, after completing the active call of the class to be repaired, directly `adb pull /sdcard/ooxx/packagename`, obtain the complete directory file, under which the directory has FART10 and FART12 shell-out and all the recovereddex,txt,bin files, the catalogue content is roughly as follows:


[] (pic/03.png)


2. Start cmd, execute java -jar repireall folderpath to begin the consolidation of all thedex andbin files under the directory, the following figure to begin to repair the screenshot, then start the automated repair of thedex usingbin files one by one.


[] (pic/04.png)


3. When the repair is completed, the Rrepire all dexfile end! prompt is printed, representing the automated repair has been completed.


[] (pic/05.png)


The repaireddex is located in the repire directory under the current directory, which is shown below, as well as a copy of the recovereddex file.


[] (pic/06.png)!


When the repair is complete, only need to open with jadx, jeb, gda etc.


## Case demonstration of complete decolletion repair process


First, install test.apk to your Fart12 phone.


``` $ adb install test.apk `` ''


# # # First take the whole shell off


After the installation is completed, press the icon of the App, click on "Application Information", in the "Permissions" column, change the permissions for "Documents and Media" to "Allow all files to be managed", and any hint will click "Permit".Finally, "Documents and Media" appears in the "Allowed" column.


> Of course, some apps do not have the permission to apply for documents and media, and de-shell ROM has already dealt with this point.When you install the app, you will apply for this permission, so don't worry about it.


Then click on Open the App, so that the entire shell is done.Adb enters the phone, and you can see the entiredex file that has been removed at different times, and the class list of txt files.


``` $ cd /sdcard/ooxx/com.yunmai.valueoflife $ ls `` ''


[] (pic/08.png)


The four main function names registered in AndroidManifest.xml can be searched directly in the folder, which are generally not to be confused.For example, `com.yunmai.valueoflife.MainActivity`.


[] (pic/09.png)


The command used is as follows:


"` $ grep -ril "MainActivity" * ```


[] (pic/10.png)


If the class we're looking for exists in the `8461968_classlist_LoadMethod.txt` file, then you drag it to the computer and open a countercompilation.It was found that most class names and method names were confused.


[] (pic/11.png)


Switching to the small tab, you can see the methods are alsonop, which is a typical extracting shell characteristic, can not see the actual logical process of the method.


[] (pic/12.png)


The next step is to solve the problem of extracting the shell and restore the function body.


♪ ♪ Take off the shell again ♪


Dump the shell, using the method of loading the class and then dump the function to the bin file.Finally, the functions in the bin file are filled back into thedex, and the completedex file is formed, so that the logic of the methods in the diagram above is restored.


And in the decoupled program, the white list 'Whitelist' mode is selected.Why don't you take it all out right now?This is reasonable, for three reasons:


1. Some apps are relatively large, tens of thousands and tens of millions of types of methods, full recovery is time-consuming, the system cannot support will kill the application or collapse itself.


2. Some shells will write some `App` unnecessary junk categories, write some exit code in the junk category, these junk classes will never be loaded in the normal usage process of the app, in which case if Fart12 loads it, stumbles on the mine, the app crashes out.


Some shells also hook functions in Art's class loading process to detect which classes are being loaded, such as LoadMethod or LinkCode, and withdraw when the class list is discovered, preventing subsequent actively loaded class methods from occurring.


[] (pic/13.png)


In our case, the above situation does not exist, so first learn how to fully recover.In the `/sdcard/ooxx/com.yunmai.valueoflife` directory, execute:


``` $ cat *classlist* >> whitelist.txt `` ''


Do not rest assured to see if the function class name has been filled in in 'whitelist.txt'.


Open 'logcat' and wait a little while to see the log of 'fart' appearing in the log.This is the function file in the `dump` class.


``` ActivityThread: sleep over and start fartCA.XI.K0$XI ActivityThread: try loadCA.XI.XI


[] (pic/14.png)


The latest generated bin file can be viewed several times with the `ls-alit` command.


[] (pic/15.png)


The dump process can last for minutes to hours, until the word 'fart run over' appears in 'logcat', and the entire folder is dragged to the computer.


``` %adb pull /sdcard/ooxx/com.yunmai.valueoflife `` ''


### Reorganizedex


With the release of the new tool, the reorganization ofdex has become unusually simple, with a single command:


`` '' %java -jar repireall.jar com.yunmai.valueoflife '' ''


[] (pic/16.png)


> Of course it is necessary to install the JDK environment in advance on the computer.


The restructuring takes some time, and the tool will automatically use the functions in the bin file to fill in the correspondingdex file, the tip to restructure is:`Repire all dexfile end!Please enjoy!`


Reorganization completion generates the `repire` folder under the directory, containing all repaireddex files.For example, we have seen before the missing function, that is the effect, the function's operating logic has a complete overview.


[] (pic/17.png)


In addition, you will also be wondering, what is the difference between the three documentsdex,LoadMethod,OpenCommen?


[] (pic/18.png)


In fact, they are in different decoy times dumping down the entiredex file, the preamble also explains some cases that will dump down the deformeddex.So give more time to have a higher tolerance of error, which means: always one is right.


### Reorganizedex on your phone using FartFix


The enthusiastic netizens made the above repair tool into an app, called FartFix, can be repaired directly on the phone, without dragging to the computer to repair, to avoid the pain of installing Java environment on the computer.The interface is as follows:


[] (pic/19.png)


After the process of actively invoking the dump function is completed, click on the App open, give the folder read-write management permission as requested, enter the path where thedex is to be repaired, and click on start processing.


``` %adb shell input text /sdcard/ooxx/com.yunmai.valueoflife `` ''


The app is relatively simple, with no hints throughout, not even in the logcat.A little more than a moment, you can find that the fixeddex has been generated in the `/sdcard/ooxx/repire` directory, as shown in the figure.


[] (pic/20.png)


It can then be dragged to the computer for analysis.


# # The circumvention of the detection mechanism of the shell


Speaking of Shell above, there are some methods to detect and counter Fart12 active calls, and accordingly we have some ways to bypass these tests.There are two main approaches:


1. The scope is narrowed.For example, just write a few, dozens, hundreds of category names in the `whitelist.txt`, only those you want to see.Shell has no chance of blocking the loading of business code, otherwise the app itself will collapse.Or just handle all the classes in onedex:


``` $ cp 8461968_classlist.txt whitelist. txt `` ''


2. Skip the detection class.In the logcat log itself, you can see the name of the category you try to load when the crash occurs, bypassing that category.The class can be deleted from 'whitelist.txt'.Empty all the remaining files, and then click App again.

