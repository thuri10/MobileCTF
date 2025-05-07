package com.stub;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import com.qihoo.util.a;
import dalvik.system.DexFile;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public final class StubApp extends Application {
    private static Application a;
    private static Application b;
    private static String c;
    private static Context d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    public static boolean isMcIsolated;
    private static Map<Integer, String> j;
    private static boolean loadFromLib;
    private static boolean needX86Bridge;
    private static boolean returnIntern;
    public static String strEntryApplication;

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0069, code lost:
    
        return;
     */
    static {
        /*
            r5 = 0
            r4 = 0
            java.lang.String r0 = "۟ۘۥۘۦۧۢۗۤۜ۟ۧۥ۠ۤۜۘ۫ۙۤ۫ۥۘۚۥۡۘ"
        L4:
            int r1 = r0.hashCode()
            r2 = 315(0x13b, float:4.41E-43)
            r3 = 2063381737(0x7afcb4e9, float:6.5606405E35)
            r1 = r1 ^ r2
            r1 = r1 ^ r3
            switch(r1) {
                case -2131583321: goto L30;
                case -1799915407: goto L45;
                case -1697569852: goto L4a;
                case -1648750413: goto L18;
                case -1167242660: goto L1f;
                case -381707916: goto L24;
                case 46243227: goto L54;
                case 382327787: goto L63;
                case 515987903: goto L13;
                case 562850417: goto L40;
                case 593796390: goto L35;
                case 905807465: goto L4f;
                case 1066933978: goto L59;
                case 1363505043: goto L2b;
                case 1819818551: goto L3b;
                case 1993154248: goto L69;
                default: goto L12;
            }
        L12:
            goto L4
        L13:
            com.stub.StubApp.a = r4
            java.lang.String r0 = "ۤۘۖۙۧۖۘ۬ۗۥۥ۠ۦۗۘۘۘۢۘۛ"
            goto L4
        L18:
            java.lang.String r0 = "entryRunApplication"
            com.stub.StubApp.strEntryApplication = r0
            java.lang.String r0 = "ۨۧۨۘۙۨۚ۠ۤۧۡ۟ۜۘ۫ۙ۫ۥۗۦ۟۫۬ۚ۬ۧۗۖۖۘ"
            goto L4
        L1f:
            com.stub.StubApp.b = r4
            java.lang.String r0 = "ۧۙۙۛۜ۫ۤ۠ۜۘۦ۠۠ۥۦۤۛۥۦ۟۬ۙۦ۫ۜۘۙۖ"
            goto L4
        L24:
            java.lang.String r0 = "libjiagu"
            com.stub.StubApp.c = r0
            java.lang.String r0 = "ۗۤۨۘ۠ۧۖۢۘۜۘۧۗۜۢۧۛۗۦۘۘۦۨ۠"
            goto L4
        L2b:
            com.stub.StubApp.loadFromLib = r5
            java.lang.String r0 = "۫ۗ۟۟۬ۢۢۘۧۙۢ۫ۦۥۘ۫ۦۤ۬ۧۢۖۜ۟"
            goto L4
        L30:
            com.stub.StubApp.needX86Bridge = r5
            java.lang.String r0 = "ۗۦۗۛۙۡۥۢ۠ۙۢۖۢۜۥ۫ۘۖۜۚۥۘ"
            goto L4
        L35:
            r0 = 1
            com.stub.StubApp.returnIntern = r0
            java.lang.String r0 = "ۚ۠ۘ۬ۨۖۢۥۘ۠ۛۢۛۛۘۘۧۢۨۘ"
            goto L4
        L3b:
            com.stub.StubApp.isMcIsolated = r5
            java.lang.String r0 = "۫ۦۜۦ۠ۖۖۜۘۛۧۘۘ۠۠ۨۜ۟۫"
            goto L4
        L40:
            com.stub.StubApp.e = r4
            java.lang.String r0 = "ۥۥۧ۫ۜۗۖۢۜ۫ۤ۫ۤۚۨۤ۫ۢۛ۟ۜۘۛۥۗ"
            goto L4
        L45:
            com.stub.StubApp.f = r4
            java.lang.String r0 = "ۢۥۘۘۢ۟ۥۚۘۙۥ۠ۥۘۧۥۜۘۛۨ۟ۙۗۡۤ۫"
            goto L4
        L4a:
            com.stub.StubApp.g = r4
            java.lang.String r0 = "ۤۧ۫۫ۛۜۗۖۙۧ۫۠ۗۨۘ۟ۡۜۘۤۡۧ۬۟ۙ"
            goto L4
        L4f:
            com.stub.StubApp.h = r4
            java.lang.String r0 = "ۡ۬ۨۘ۬ۜۡۙۥۨۙ۫ۜۥ۫ۗۙ۫ۨ"
            goto L4
        L54:
            com.stub.StubApp.i = r4
            java.lang.String r0 = "ۧۨۨۙۚۦ۬ۥۗۧۢۘۚۘۦۘۚ۬ۨۘۡ۠ۖ"
            goto L4
        L59:
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            com.stub.StubApp.j = r0
            java.lang.String r0 = "ۤۙۨۙۢۚۚۤۗۥۢۥۤۡۤۗۨۘ"
            goto L4
        L63:
            com.qihoo.util.DtcLoader.init()
            java.lang.String r0 = "ۜ۠ۘۘ۬ۤۚ۠۟ۤۚۛۦۘۖۥۤۦۙۜۘ۫ۛۙۛۚۜۘ"
            goto L4
        L69:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.<clinit>():void");
    }

    private static Application a(Context context) {
        String str = "ۤۧۖۥ۟ۨ۟ۢۘۘۧۦۧۡۦۖۘ";
        while (true) {
            try {
                switch (str.hashCode() ^ (-1380612232)) {
                    case -1664004275:
                        str = "ۦۤۡ۠ۥۗ۫ۤۨۘۢ۬۫ۧۤ۟ۜۛۨۘۜۘ";
                        continue;
                    case -336487812:
                        ClassLoader classLoader = context.getClassLoader();
                        String str2 = "ۨۗۜۘ۟ۧۦۘۛ۫ۡ۬ۛۡۘۧ۟۬ۜ۠ۨ۟۫ۥ۟۫ۦ";
                        while (true) {
                            switch (str2.hashCode() ^ (-1075044657)) {
                                case -1793647795:
                                    break;
                                case -1740438324:
                                    if (classLoader != null) {
                                        str2 = "ۤ۠۟ۡ۠ۘ۟ۜۜۛۢ۬ۙۥۖ";
                                        continue;
                                    } else {
                                        str2 = "ۗۚۨۗۛۥۘۥۡ۟ۙۦۚۨۨۜۘ";
                                    }
                                case -1072062943:
                                    str2 = "ۡۢۨۘۥۗۜ۠ۗۗۧۨ۟ۜۜۤۖۢ۬";
                                    continue;
                                case 825208060:
                                    Class<?> loadClass = classLoader.loadClass(strEntryApplication);
                                    String str3 = "ۤۨۙۚۗۜۘۦۥۥۘۙۨۙ۬ۢۚۖۚۡ";
                                    while (true) {
                                        switch (str3.hashCode() ^ (-662572056)) {
                                            case -988113333:
                                                if (loadClass != null) {
                                                    str3 = "ۥۢۛۨۘۧۦۤۡۡۤۘۘۛۛۙۡۨۛۧۜۦۧۘ";
                                                    continue;
                                                } else {
                                                    str3 = "ۖۥۖۘۥۗۨۗۤ۬۬ۚۢۜۥ۟ۜۜۧ۟ۙۖۙ۬ۧ";
                                                }
                                            case 266725586:
                                                str3 = "ۖۘۜۘۥۘۘۡۥۡۧ۟ۖ۬ۗۘۘۖۧۜ۬ۙۦۘۗ۫";
                                                continue;
                                            case 577523595:
                                                break;
                                            case 967989699:
                                                b = (Application) loadClass.newInstance();
                                                break;
                                            default:
                                                continue;
                                        }
                                    }
                            }
                        }
                        break;
                    case 656487420:
                        break;
                    case 859981897:
                        if (b == null) {
                            str = "ۧ۬ۖۘۜ۫ۡۘ۬ۢۖۘۜۘۥۘۤۥۘۘۚ۟ۜ۠ۤ۟۟۫ۨۘ";
                            continue;
                        } else {
                            str = "ۡۡۡۢۢۤۛ۟ۢۛ۠ۖۘۖۘۘۘ";
                        }
                    default:
                        continue;
                }
            } catch (Exception e2) {
            }
        }
        return b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x00e9, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.a(java.lang.String, boolean, boolean):java.lang.String");
    }

    public static native void fcmark();

    public static Context getAppContext() {
        while (true) {
            switch (("۬۫۬ۗۖۚ۫ۘۜۨۦۚ۠ۦۘۨۗۧ".hashCode() ^ 656) ^ (-565941152)) {
                case 142279985:
                    return d;
            }
        }
    }

    public static String getDir() {
        while (true) {
            switch (("۫ۗۡ۠ۨۥۙۗۢۚۛ۠ۗۦ۟".hashCode() ^ 453) ^ (-2039241865)) {
                case 618028095:
                    return h;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0045, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.Context getOrigApplicationContext(android.content.Context r6) {
        /*
            r2 = 0
            java.lang.String r0 = "ۧۧ۫ۛۘۧۘۚۨ۬ۛۢۧۙۜۛ۟۫ۖۘۚۙۚۚ۠ۙ"
            r1 = r2
            r3 = r2
        L5:
            int r2 = r0.hashCode()
            r4 = 209(0xd1, float:2.93E-43)
            r5 = 665387613(0x27a9025d, float:4.6909485E-15)
            r2 = r2 ^ r4
            r2 = r2 ^ r5
            switch(r2) {
                case -1184138358: goto L45;
                case -159809295: goto L17;
                case 840706308: goto L39;
                case 1028129711: goto L14;
                case 1448649258: goto L1b;
                case 2034102061: goto L3e;
                default: goto L13;
            }
        L13:
            goto L5
        L14:
            java.lang.String r0 = "ۛۜۙۨۥۨۧ۬ۙۧۜۥۘۘ۠ۗ۬ۡۛ۫ۤۧۨۤۧ"
            goto L5
        L17:
            java.lang.String r0 = "ۤ۫ۖۘ۫ۤۙۥۢ۠ۘۥۥۖۛۚ۫۠۬۠ۥۥۘ"
            r3 = r6
            goto L5
        L1b:
            r2 = 1528813997(0x5b1fd9ad, float:4.499386E16)
            java.lang.String r0 = "ۗۤۚۖۤۚۘۢۗ۬۟ۧۙۨۖۘۡۛ۟ۚۜۢۦۤۦ"
        L20:
            int r4 = r0.hashCode()
            r4 = r4 ^ r2
            switch(r4) {
                case -1940506995: goto L36;
                case 1264858331: goto L2f;
                case 1747650322: goto L42;
                case 2020321394: goto L29;
                default: goto L28;
            }
        L28:
            goto L20
        L29:
            java.lang.String r0 = "ۤۙ۫ۥۘۧ۠ۖۘۨۖۤۡ۠ۧۦۖۘۘۧۛۖۘۤ۫ۢ۫ۙۥۘ"
            goto L20
        L2c:
            java.lang.String r0 = "ۦ۬ۘۘۛ۟ۥۚۧۤ۬ۙۙۙۚۙۛۡ۬ۛۜۘۘ"
            goto L20
        L2f:
            android.app.Application r0 = com.stub.StubApp.a
            if (r6 != r0) goto L2c
            java.lang.String r0 = "ۦۥۚۜۦۥ۟ۚۛۤۦۜۘۛۙ۟ۦۦۢ"
            goto L20
        L36:
            java.lang.String r0 = "ۢۛۙۧۥۖۢۥ۫ۤۖۙۧۛۖۚۛۜۘ"
            goto L5
        L39:
            android.app.Application r1 = com.stub.StubApp.b
            java.lang.String r0 = "ۙۘۨۡۡۙ۠ۦ۫ۜۨۥۘۨ۠۫۟۬ۢ۠ۡۘ"
            goto L5
        L3e:
            java.lang.String r0 = "۬ۜ۠ۢۗۖۚ۠۠۠ۘۘ۬ۙۖ۟ۧۢۧ۫ۖ۬ۥۘۖ۠ۥ"
            r3 = r1
            goto L5
        L42:
            java.lang.String r0 = "۬ۜ۠ۢۗۖۚ۠۠۠ۘۘ۬ۙۖ۟ۧۢۧ۫ۖ۬ۥۘۖ۠ۥ"
            goto L5
        L45:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.getOrigApplicationContext(android.content.Context):android.content.Context");
    }

    public static String getSoPath1() {
        while (true) {
            switch (("ۨۛۜۘۨۛۡۘۛۙۛۤۦۚۘۢۖۘۘۖۡۙۢ۠".hashCode() ^ 707) ^ 1629642283) {
                case 595964796:
                    return f;
            }
        }
    }

    public static String getSoPath2() {
        while (true) {
            switch (("۠ۜۚۦۨۛۗۗۜ۟ۛۖۙۘ۠ۧۥۘۘۨۡۦۥۗ".hashCode() ^ 956) ^ 1776006520) {
                case 1032523389:
                    return g;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x00c9, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getString2(int r9) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.getString2(int):java.lang.String");
    }

    public static String getString2(String str) {
        try {
            return getString2(Integer.parseInt(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static native void interface11(int i2);

    public static native Enumeration<String> interface12(DexFile dexFile);

    public static native long interface13(int i2, long j2, long j3, long j4, int i3, int i4, long j5);

    public static native String interface14(int i2);

    public static native AssetFileDescriptor interface17(AssetManager assetManager, String str);

    public static native InputStream interface18(Class cls, String str);

    public static native InputStream interface19(ClassLoader classLoader, String str);

    public static native void interface20();

    public static native void interface21(Application application);

    public static native void interface22(int i2, String[] strArr, int[] iArr);

    public static native ZipEntry interface30(ZipFile zipFile, String str);

    public static native void interface5(Application application);

    public static native String interface6(String str);

    public static native boolean interface7(Application application, Context context);

    public static native boolean interface8(Application application, Context context);

    public static boolean isX86Arch() {
        while (true) {
            switch (("ۥۤۢۡۧۛۡۦۡۛۥ۟ۘۘۦۥ".hashCode() ^ 749) ^ (-1575254813)) {
                case -2097974012:
                    return a.a();
            }
        }
    }

    public static native Location mark(LocationManager locationManager, String str);

    public static native void mark();

    public static native void mark(Location location);

    public static native void n0110();

    public static native Object n0113();

    public static native void n01130(Object obj);

    public static native boolean n01131(Object obj);

    public static native void n0113130(Object obj, int i2, Object obj2);

    public static native Object n0113133(Object obj, int i2, Object obj2);

    public static native Object n01133(Object obj);

    public static native Object n011333(Object obj, Object obj2);

    public static native Object n0113333(Object obj, Object obj2, Object obj3);

    public static native void pmark(Context context);

    public static native void rmark();

    /* JADX WARN: Code restructure failed: missing block: B:131:0x0148, code lost:
    
        java.lang.System.loadLibrary("jiagu");
     */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void attachBaseContext(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 1546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.attachBaseContext(android.content.Context):void");
    }

    public native void n11030(Object obj);

    public native void n1110();

    public native boolean n1111();

    public native void n11110(int i2);

    public native boolean n11111(boolean z);

    public native void n111130(int i2, Object obj);

    public native boolean n11113311(int i2, Object obj, Object obj2, int i3);

    public native Object n1113();

    public native void n11130(Object obj);

    public native boolean n11131(Object obj);

    public native void n111310(Object obj, int i2);

    public native Object n11133(Object obj);

    public native void n111330(Object obj, Object obj2);

    public native void n11133110(Object obj, Object obj2, boolean z, int i2);

    public native void n11133310(Object obj, Object obj2, Object obj3, int i2);

    public native Object n1113333(Object obj, Object obj2, Object obj3);

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:110:0x00ea
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.app.Application, com.stub.StubApp] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.app.Application] */
    @Override // android.app.Application
    public final void onCreate() {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stub.StubApp.onCreate():void");
    }
}
