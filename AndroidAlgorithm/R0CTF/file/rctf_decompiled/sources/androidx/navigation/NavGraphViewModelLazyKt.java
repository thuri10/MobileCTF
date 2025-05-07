package androidx.navigation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: NavGraphViewModelLazy.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0010\b\n\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0087\bø\u0001\u0000\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0010\b\n\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f²\u0006\u0016\u0010\r\u001a\u00020\u000e\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003X\u008a\u0084\u0002²\u0006\u0016\u0010\r\u001a\u00020\u000e\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003X\u008a\u0084\u0002"}, d2 = {"navGraphViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "navGraphId", "", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "navGraphRoute", "", "navigation-fragment_release", "backStackEntry", "Landroidx/navigation/NavBackStackEntry;"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NavGraphViewModelLazyKt {
    public static /* synthetic */ Lazy navGraphViewModels$default(Fragment $this$navGraphViewModels_u24default, int navGraphId, Function0 factoryProducer, int i, Object obj) {
        if ((i & 2) != 0) {
            factoryProducer = null;
        }
        Intrinsics.checkNotNullParameter($this$navGraphViewModels_u24default, "<this>");
        Lazy backStackEntry$delegate = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2($this$navGraphViewModels_u24default, navGraphId));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(backStackEntry$delegate);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy($this$navGraphViewModels_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new NavGraphViewModelLazyKt$navGraphViewModels$1(factoryProducer, backStackEntry$delegate));
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> navGraphViewModels(Fragment $this$navGraphViewModels, int navGraphId, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter($this$navGraphViewModels, "<this>");
        Lazy backStackEntry$delegate = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2($this$navGraphViewModels, navGraphId));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(backStackEntry$delegate);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy($this$navGraphViewModels, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new NavGraphViewModelLazyKt$navGraphViewModels$1(function0, backStackEntry$delegate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: navGraphViewModels$lambda-0, reason: not valid java name */
    public static final NavBackStackEntry m50navGraphViewModels$lambda0(Lazy<NavBackStackEntry> lazy) {
        return lazy.getValue();
    }

    public static /* synthetic */ Lazy navGraphViewModels$default(Fragment $this$navGraphViewModels_u24default, String navGraphRoute, Function0 factoryProducer, int i, Object obj) {
        if ((i & 2) != 0) {
            factoryProducer = null;
        }
        Intrinsics.checkNotNullParameter($this$navGraphViewModels_u24default, "<this>");
        Intrinsics.checkNotNullParameter(navGraphRoute, "navGraphRoute");
        Lazy backStackEntry$delegate = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$4($this$navGraphViewModels_u24default, navGraphRoute));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$2(backStackEntry$delegate);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy($this$navGraphViewModels_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new NavGraphViewModelLazyKt$navGraphViewModels$2(factoryProducer, backStackEntry$delegate));
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> navGraphViewModels(Fragment $this$navGraphViewModels, String navGraphRoute, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter($this$navGraphViewModels, "<this>");
        Intrinsics.checkNotNullParameter(navGraphRoute, "navGraphRoute");
        Lazy backStackEntry$delegate = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$4($this$navGraphViewModels, navGraphRoute));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$2(backStackEntry$delegate);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy($this$navGraphViewModels, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new NavGraphViewModelLazyKt$navGraphViewModels$2(function0, backStackEntry$delegate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: navGraphViewModels$lambda-1, reason: not valid java name */
    public static final NavBackStackEntry m51navGraphViewModels$lambda1(Lazy<NavBackStackEntry> lazy) {
        return lazy.getValue();
    }
}
