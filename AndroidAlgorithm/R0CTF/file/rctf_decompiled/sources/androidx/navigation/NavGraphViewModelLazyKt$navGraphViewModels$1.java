package androidx.navigation;

import androidx.lifecycle.ViewModelProvider;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: NavGraphViewModelLazy.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 176)
/* loaded from: classes.dex */
public final class NavGraphViewModelLazyKt$navGraphViewModels$1 extends Lambda implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ Lazy<NavBackStackEntry> $backStackEntry$delegate;
    final /* synthetic */ Function0<ViewModelProvider.Factory> $factoryProducer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NavGraphViewModelLazyKt$navGraphViewModels$1(Function0<? extends ViewModelProvider.Factory> function0, Lazy<NavBackStackEntry> lazy) {
        super(0);
        this.$factoryProducer = function0;
        this.$backStackEntry$delegate = lazy;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelProvider.Factory invoke() {
        NavBackStackEntry m50navGraphViewModels$lambda0;
        Function0<ViewModelProvider.Factory> function0 = this.$factoryProducer;
        ViewModelProvider.Factory invoke = function0 == null ? null : function0.invoke();
        if (invoke != null) {
            return invoke;
        }
        m50navGraphViewModels$lambda0 = NavGraphViewModelLazyKt.m50navGraphViewModels$lambda0(this.$backStackEntry$delegate);
        return m50navGraphViewModels$lambda0.getDefaultViewModelProviderFactory();
    }
}
