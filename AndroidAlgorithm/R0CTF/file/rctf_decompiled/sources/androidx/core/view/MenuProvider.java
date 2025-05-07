package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* loaded from: classes.dex */
public interface MenuProvider {
    void onCreateMenu(Menu menu, MenuInflater menuInflater);

    void onMenuClosed(Menu menu);

    boolean onMenuItemSelected(MenuItem menuItem);

    void onPrepareMenu(Menu menu);

    @SynthesizedClassV2(kind = 7, versionHash = "15f1483824cf4085ddca5a8529d873fc59a8ced2cbce67fb2b3dd9033ea03442")
    /* renamed from: androidx.core.view.MenuProvider$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onPrepareMenu(MenuProvider _this, Menu menu) {
        }

        public static void $default$onMenuClosed(MenuProvider _this, Menu menu) {
        }
    }
}
