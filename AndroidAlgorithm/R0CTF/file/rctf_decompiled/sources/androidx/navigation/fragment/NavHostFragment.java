package androidx.navigation.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NavHostFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016H\u0015J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0017J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0015J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0012H\u0015J&\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010&\u001a\u00020\u0019H\u0016J\"\u0010'\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0017J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\tH\u0017J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u001eH\u0017J\u001a\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/navigation/fragment/NavHostFragment;", "Landroidx/fragment/app/Fragment;", "Landroidx/navigation/NavHost;", "()V", "containerId", "", "getContainerId", "()I", "defaultNavHost", "", "graphId", "isPrimaryBeforeOnCreate", "Ljava/lang/Boolean;", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navHostController", "Landroidx/navigation/NavHostController;", "viewParent", "Landroid/view/View;", "createFragmentNavigator", "Landroidx/navigation/Navigator;", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateNavController", "onCreateNavHostController", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onInflate", "attrs", "Landroid/util/AttributeSet;", "onPrimaryNavigationFragmentChanged", "isPrimaryNavigationFragment", "onSaveInstanceState", "outState", "onViewCreated", "view", "Companion", "navigation-fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public class NavHostFragment extends Fragment implements NavHost {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost";
    public static final String KEY_GRAPH_ID = "android-support-nav:fragment:graphId";
    private static final String KEY_NAV_CONTROLLER_STATE = "android-support-nav:fragment:navControllerState";
    public static final String KEY_START_DESTINATION_ARGS = "android-support-nav:fragment:startDestinationArgs";
    private boolean defaultNavHost;
    private int graphId;
    private Boolean isPrimaryBeforeOnCreate;
    private NavHostController navHostController;
    private View viewParent;

    @JvmStatic
    public static final NavHostFragment create(int i) {
        return INSTANCE.create(i);
    }

    @JvmStatic
    public static final NavHostFragment create(int i, Bundle bundle) {
        return INSTANCE.create(i, bundle);
    }

    @JvmStatic
    public static final NavController findNavController(Fragment fragment) {
        return INSTANCE.findNavController(fragment);
    }

    @Override // androidx.navigation.NavHost
    public final NavController getNavController() {
        NavHostController navHostController = this.navHostController;
        if (navHostController == null) {
            throw new IllegalStateException("NavController is not available before onCreate()".toString());
        }
        if (navHostController != null) {
            return navHostController;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.navigation.NavHostController");
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (this.defaultNavHost) {
            getParentFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c8  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            android.content.Context r0 = r7.requireContext()
            java.lang.String r1 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            androidx.navigation.NavHostController r1 = new androidx.navigation.NavHostController
            r1.<init>(r0)
            r7.navHostController = r1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r2 = r7
            androidx.lifecycle.LifecycleOwner r2 = (androidx.lifecycle.LifecycleOwner) r2
            r1.setLifecycleOwner(r2)
        L19:
            boolean r1 = r0 instanceof android.content.ContextWrapper
            if (r1 == 0) goto L44
            boolean r1 = r0 instanceof androidx.activity.OnBackPressedDispatcherOwner
            if (r1 == 0) goto L36
            androidx.navigation.NavHostController r1 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r2 = r0
            androidx.activity.OnBackPressedDispatcherOwner r2 = (androidx.activity.OnBackPressedDispatcherOwner) r2
            androidx.activity.OnBackPressedDispatcher r2 = r2.getOnBackPressedDispatcher()
            java.lang.String r3 = "context as OnBackPressed…).onBackPressedDispatcher"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.setOnBackPressedDispatcher(r2)
            goto L44
        L36:
            r1 = r0
            android.content.ContextWrapper r1 = (android.content.ContextWrapper) r1
            android.content.Context r1 = r1.getBaseContext()
            java.lang.String r2 = "context.baseContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0 = r1
            goto L19
        L44:
            androidx.navigation.NavHostController r1 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.Boolean r2 = r7.isPrimaryBeforeOnCreate
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L61
            if (r2 == 0) goto L59
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L61
            r2 = 1
            goto L62
        L59:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Boolean"
            r1.<init>(r2)
            throw r1
        L61:
            r2 = 0
        L62:
            r1.enableOnBackPressed(r2)
            r1 = 0
            r7.isPrimaryBeforeOnCreate = r1
            androidx.navigation.NavHostController r2 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            androidx.lifecycle.ViewModelStore r5 = r7.getViewModelStore()
            java.lang.String r6 = "viewModelStore"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r2.setViewModelStore(r5)
            androidx.navigation.NavHostController r2 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r7.onCreateNavHostController(r2)
            r2 = 0
            java.lang.String r5 = "android-support-nav:fragment:graphId"
            if (r8 == 0) goto Laf
            java.lang.String r6 = "android-support-nav:fragment:navControllerState"
            android.os.Bundle r2 = r8.getBundle(r6)
            java.lang.String r6 = "android-support-nav:fragment:defaultHost"
            boolean r6 = r8.getBoolean(r6, r4)
            if (r6 == 0) goto La9
            r7.defaultNavHost = r3
            androidx.fragment.app.FragmentManager r3 = r7.getParentFragmentManager()
            androidx.fragment.app.FragmentTransaction r3 = r3.beginTransaction()
            r6 = r7
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            androidx.fragment.app.FragmentTransaction r3 = r3.setPrimaryNavigationFragment(r6)
            r3.commit()
        La9:
            int r3 = r8.getInt(r5)
            r7.graphId = r3
        Laf:
            if (r2 == 0) goto Lb9
            androidx.navigation.NavHostController r3 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r3.restoreState(r2)
        Lb9:
            int r3 = r7.graphId
            if (r3 == 0) goto Lc8
            androidx.navigation.NavHostController r1 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r3 = r7.graphId
            r1.setGraph(r3)
            goto Le6
        Lc8:
            android.os.Bundle r3 = r7.getArguments()
            if (r3 != 0) goto Lcf
            goto Ld3
        Lcf:
            int r4 = r3.getInt(r5)
        Ld3:
            if (r3 != 0) goto Ld6
            goto Ldc
        Ld6:
            java.lang.String r1 = "android-support-nav:fragment:startDestinationArgs"
            android.os.Bundle r1 = r3.getBundle(r1)
        Ldc:
            if (r4 == 0) goto Le6
            androidx.navigation.NavHostController r5 = r7.navHostController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r5.setGraph(r4, r1)
        Le6:
            super.onCreate(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.NavHostFragment.onCreate(android.os.Bundle):void");
    }

    protected void onCreateNavHostController(NavHostController navHostController) {
        Intrinsics.checkNotNullParameter(navHostController, "navHostController");
        onCreateNavController(navHostController);
    }

    @Deprecated(message = "Override {@link #onCreateNavHostController(NavHostController)} to gain\n      access to the full {@link NavHostController} that is created by this NavHostFragment.")
    protected void onCreateNavController(NavController navController) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        NavigatorProvider $this$plusAssign$iv = navController.get_navigatorProvider();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        Navigator navigator$iv = new DialogFragmentNavigator(requireContext, childFragmentManager);
        $this$plusAssign$iv.addNavigator(navigator$iv);
        NavigatorProvider $this$plusAssign$iv2 = navController.get_navigatorProvider();
        $this$plusAssign$iv2.addNavigator(createFragmentNavigator());
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        NavHostController navHostController = this.navHostController;
        if (navHostController != null) {
            if (navHostController == null) {
                return;
            }
            navHostController.enableOnBackPressed(isPrimaryNavigationFragment);
            return;
        }
        this.isPrimaryBeforeOnCreate = Boolean.valueOf(isPrimaryNavigationFragment);
    }

    @Deprecated(message = "Use {@link #onCreateNavController(NavController)}")
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        return new FragmentNavigator(requireContext, childFragmentManager, getContainerId());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Context context = inflater.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "inflater.context");
        FragmentContainerView containerView = new FragmentContainerView(context);
        containerView.setId(getContainerId());
        return containerView;
    }

    private final int getContainerId() {
        int id = getId();
        if (id != 0 && id != -1) {
            return id;
        }
        return R.id.nav_host_fragment_container;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (!(view instanceof ViewGroup)) {
            throw new IllegalStateException(("created host view " + view + " is not a ViewGroup").toString());
        }
        Navigation.setViewNavController(view, this.navHostController);
        if (view.getParent() != null) {
            Object parent = view.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            View view2 = (View) parent;
            this.viewParent = view2;
            Intrinsics.checkNotNull(view2);
            if (view2.getId() == getId()) {
                View view3 = this.viewParent;
                Intrinsics.checkNotNull(view3);
                Navigation.setViewNavController(view3, this.navHostController);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        super.onInflate(context, attrs, savedInstanceState);
        TypedArray $this$use$iv = context.obtainStyledAttributes(attrs, androidx.navigation.R.styleable.NavHost);
        Intrinsics.checkNotNullExpressionValue($this$use$iv, "context.obtainStyledAttr…yleable.NavHost\n        )");
        int graphId = $this$use$iv.getResourceId(androidx.navigation.R.styleable.NavHost_navGraph, 0);
        if (graphId != 0) {
            this.graphId = graphId;
        }
        Unit unit = Unit.INSTANCE;
        $this$use$iv.recycle();
        TypedArray $this$use$iv2 = context.obtainStyledAttributes(attrs, R.styleable.NavHostFragment);
        Intrinsics.checkNotNullExpressionValue($this$use$iv2, "context.obtainStyledAttr…tyleable.NavHostFragment)");
        boolean defaultHost = $this$use$iv2.getBoolean(R.styleable.NavHostFragment_defaultNavHost, false);
        if (defaultHost) {
            this.defaultNavHost = true;
        }
        Unit unit2 = Unit.INSTANCE;
        $this$use$iv2.recycle();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        NavHostController navHostController = this.navHostController;
        Intrinsics.checkNotNull(navHostController);
        Bundle navState = navHostController.saveState();
        if (navState != null) {
            outState.putBundle(KEY_NAV_CONTROLLER_STATE, navState);
        }
        if (this.defaultNavHost) {
            outState.putBoolean(KEY_DEFAULT_NAV_HOST, true);
        }
        int i = this.graphId;
        if (i != 0) {
            outState.putInt(KEY_GRAPH_ID, i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        View it = this.viewParent;
        if (it != null && Navigation.findNavController(it) == this.navHostController) {
            Navigation.setViewNavController(it, null);
        }
        this.viewParent = null;
    }

    /* compiled from: NavHostFragment.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/navigation/fragment/NavHostFragment$Companion;", "", "()V", "KEY_DEFAULT_NAV_HOST", "", "KEY_GRAPH_ID", "KEY_NAV_CONTROLLER_STATE", "KEY_START_DESTINATION_ARGS", "create", "Landroidx/navigation/fragment/NavHostFragment;", "graphResId", "", "startDestinationArgs", "Landroid/os/Bundle;", "findNavController", "Landroidx/navigation/NavController;", "fragment", "Landroidx/fragment/app/Fragment;", "navigation-fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final NavHostFragment create(int i) {
            return create$default(this, i, null, 2, null);
        }

        private Companion() {
        }

        @JvmStatic
        public final NavController findNavController(Fragment fragment) {
            Dialog dialog;
            Window window;
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            for (Fragment findFragment = fragment; findFragment != null; findFragment = findFragment.getParentFragment()) {
                if (findFragment instanceof NavHostFragment) {
                    NavHostController navHostController = ((NavHostFragment) findFragment).navHostController;
                    if (navHostController != null) {
                        return navHostController;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type androidx.navigation.NavController");
                }
                Fragment primaryNavFragment = findFragment.getParentFragmentManager().getPrimaryNavigationFragment();
                if (primaryNavFragment instanceof NavHostFragment) {
                    NavHostController navHostController2 = ((NavHostFragment) primaryNavFragment).navHostController;
                    if (navHostController2 != null) {
                        return navHostController2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type androidx.navigation.NavController");
                }
            }
            View view = fragment.getView();
            if (view != null) {
                return Navigation.findNavController(view);
            }
            View view2 = null;
            DialogFragment dialogFragment = fragment instanceof DialogFragment ? (DialogFragment) fragment : null;
            if (dialogFragment != null && (dialog = dialogFragment.getDialog()) != null && (window = dialog.getWindow()) != null) {
                view2 = window.getDecorView();
            }
            View dialogDecorView = view2;
            if (dialogDecorView != null) {
                return Navigation.findNavController(dialogDecorView);
            }
            throw new IllegalStateException("Fragment " + fragment + " does not have a NavController set");
        }

        public static /* synthetic */ NavHostFragment create$default(Companion companion, int i, Bundle bundle, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                bundle = null;
            }
            return companion.create(i, bundle);
        }

        @JvmStatic
        public final NavHostFragment create(int graphResId, Bundle startDestinationArgs) {
            Bundle b = null;
            if (graphResId != 0) {
                b = new Bundle();
                b.putInt(NavHostFragment.KEY_GRAPH_ID, graphResId);
            }
            if (startDestinationArgs != null) {
                if (b == null) {
                    b = new Bundle();
                }
                b.putBundle(NavHostFragment.KEY_START_DESTINATION_ARGS, startDestinationArgs);
            }
            NavHostFragment result = new NavHostFragment();
            if (b != null) {
                result.setArguments(b);
            }
            return result;
        }
    }
}
