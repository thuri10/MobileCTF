package android.support.v4.app;

import android.app.Service;

/* loaded from: classes.dex */
class ServiceCompatApi24 {
    ServiceCompatApi24() {
    }

    public static void stopForeground(Service service, int flags) {
        service.stopForeground(flags);
    }
}
