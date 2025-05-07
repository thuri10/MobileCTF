package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.TaskStackBuilder;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: NavController.kt */
@Metadata(d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000 Ç\u00012\u00020\u0001:\u0006Ç\u0001È\u0001É\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010r\u001a\u00020\u00162\u0006\u0010s\u001a\u0002022\b\u0010t\u001a\u0004\u0018\u00010\\2\u0006\u0010\u0015\u001a\u00020\u00072\u000e\b\u0002\u0010u\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0002J\u0010\u0010v\u001a\u00020\u00162\u0006\u0010w\u001a\u00020cH\u0016J\u0012\u0010x\u001a\u0002062\b\b\u0001\u0010y\u001a\u00020\u001fH\u0007J\u0010\u0010x\u001a\u0002062\u0006\u0010z\u001a\u00020 H\u0007J\u0012\u0010{\u001a\u0002062\b\b\u0001\u0010y\u001a\u00020\u001fH\u0003J\b\u0010|\u001a\u00020}H\u0016J\b\u0010~\u001a\u000206H\u0002J\u0011\u0010\u007f\u001a\u00020\u00162\u0007\u0010\u0080\u0001\u001a\u000206H\u0017J\u0015\u0010\u0081\u0001\u001a\u0004\u0018\u0001022\b\b\u0001\u0010y\u001a\u00020\u001fH\u0007J\u0014\u0010\u0081\u0001\u001a\u0004\u0018\u0001022\u0007\u0010\u0082\u0001\u001a\u00020 H\u0007J\u0015\u0010\u0083\u0001\u001a\u0004\u0018\u00010 2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u0013\u0010\u0086\u0001\u001a\u00020\u00072\b\b\u0001\u0010y\u001a\u00020\u001fH\u0016J\u000f\u0010\u0086\u0001\u001a\u00020\u00072\u0006\u0010z\u001a\u00020 J\u0015\u0010\u0087\u0001\u001a\u00030\u0088\u00012\t\b\u0001\u0010\u0089\u0001\u001a\u00020\u001fH\u0016J\u0015\u0010\u008a\u0001\u001a\u0002062\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0017J \u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u000f\u0010\u008e\u0001\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0018H\u0002J\u001b\u0010\u008f\u0001\u001a\u00020\u00162\u0007\u0010\u0090\u0001\u001a\u00020\u00072\u0007\u0010\u0091\u0001\u001a\u00020\u0007H\u0002J\u0013\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0084\u0001\u001a\u00030\u0093\u0001H\u0017J\u001f\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0084\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0017J+\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0084\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0017J\u0013\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0017J\u001f\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0017J+\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0017J4\u0010\u0092\u0001\u001a\u00020\u00162\u0006\u0010s\u001a\u0002022\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\\2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0003J\u0013\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0017J\u001f\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0017J\u001d\u0010\u0092\u0001\u001a\u00020\u00162\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0017J\u0014\u0010\u0092\u0001\u001a\u00020\u00162\t\b\u0001\u0010\u009d\u0001\u001a\u00020\u001fH\u0017J\u001f\u0010\u0092\u0001\u001a\u00020\u00162\t\b\u0001\u0010\u009d\u0001\u001a\u00020\u001f2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\\H\u0017J+\u0010\u0092\u0001\u001a\u00020\u00162\t\b\u0001\u0010\u009d\u0001\u001a\u00020\u001f2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\\2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0017J7\u0010\u0092\u0001\u001a\u00020\u00162\t\b\u0001\u0010\u009d\u0001\u001a\u00020\u001f2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\\2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0017J-\u0010\u0092\u0001\u001a\u00020\u00162\u0006\u0010z\u001a\u00020 2\f\b\u0002\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\f\b\u0002\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0007J+\u0010\u0092\u0001\u001a\u00020\u00162\u0006\u0010z\u001a\u00020 2\u001a\u0010\u009e\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u009f\u0001\u0012\u0004\u0012\u00020\u00160\u0012¢\u0006\u0003\b \u0001J\t\u0010¡\u0001\u001a\u000206H\u0017J\u0014\u0010¢\u0001\u001a\u00020\u00162\t\u0010£\u0001\u001a\u0004\u0018\u00010\\H\u0003J\t\u0010¤\u0001\u001a\u000206H\u0017J\u001c\u0010¤\u0001\u001a\u0002062\b\b\u0001\u0010y\u001a\u00020\u001f2\u0007\u0010¥\u0001\u001a\u000206H\u0017J%\u0010¤\u0001\u001a\u0002062\b\b\u0001\u0010y\u001a\u00020\u001f2\u0007\u0010¥\u0001\u001a\u0002062\u0007\u0010¦\u0001\u001a\u000206H\u0017J%\u0010¤\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020 2\u0007\u0010¥\u0001\u001a\u0002062\t\b\u0002\u0010¦\u0001\u001a\u000206H\u0007J'\u0010§\u0001\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00072\u000e\u0010¨\u0001\u001a\t\u0012\u0004\u0012\u00020\u00160©\u0001H\u0000¢\u0006\u0003\bª\u0001J'\u0010«\u0001\u001a\u0002062\b\b\u0001\u0010y\u001a\u00020\u001f2\u0007\u0010¥\u0001\u001a\u0002062\t\b\u0002\u0010¦\u0001\u001a\u000206H\u0003J-\u0010¬\u0001\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00072\t\b\u0002\u0010¦\u0001\u001a\u0002062\u000f\b\u0002\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\u0018H\u0002J\u0015\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0000¢\u0006\u0003\b¯\u0001J\u0011\u0010°\u0001\u001a\u00020\u00162\u0006\u0010w\u001a\u00020cH\u0016J\u0014\u0010±\u0001\u001a\u00020\u00162\t\u0010²\u0001\u001a\u0004\u0018\u00010\\H\u0017J5\u0010³\u0001\u001a\u0002062\u0007\u0010´\u0001\u001a\u00020\u001f2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\\2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0002J\u000b\u0010¦\u0001\u001a\u0004\u0018\u00010\\H\u0017J\u001b\u0010@\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\t2\t\u0010£\u0001\u001a\u0004\u0018\u00010\\H\u0017J\u0013\u0010@\u001a\u00020\u00162\t\b\u0001\u0010µ\u0001\u001a\u00020\u001fH\u0017J\u001e\u0010@\u001a\u00020\u00162\t\b\u0001\u0010µ\u0001\u001a\u00020\u001f2\t\u0010£\u0001\u001a\u0004\u0018\u00010\\H\u0017J\u0012\u0010¶\u0001\u001a\u00020\u00162\u0007\u0010·\u0001\u001a\u00020MH\u0017J\u0012\u0010¸\u0001\u001a\u00020\u00162\u0007\u0010¹\u0001\u001a\u00020`H\u0017J\u0013\u0010º\u0001\u001a\u00020\u00162\b\u0010»\u0001\u001a\u00030¼\u0001H\u0017J\t\u0010½\u0001\u001a\u000206H\u0002J\t\u0010¾\u0001\u001a\u000206H\u0002J\u001a\u0010¿\u0001\u001a\u0004\u0018\u00010\u00072\u0007\u0010\u0090\u0001\u001a\u00020\u0007H\u0000¢\u0006\u0003\bÀ\u0001J\u000f\u0010Á\u0001\u001a\u00020\u0016H\u0000¢\u0006\u0003\bÂ\u0001J\t\u0010Ã\u0001\u001a\u00020\u0016H\u0002J\u0019\u0010\u0081\u0001\u001a\u0004\u0018\u000102*\u0002022\b\b\u0001\u0010y\u001a\u00020\u001fH\u0002Jb\u0010Ä\u0001\u001a\u00020\u0016*\n\u0012\u0006\b\u0001\u0012\u0002020Y2\r\u0010Å\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u00012$\b\u0002\u0010Æ\u0001\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u0012H\u0002JL\u0010«\u0001\u001a\u00020\u0016*\n\u0012\u0006\b\u0001\u0012\u0002020Y2\u0006\u0010g\u001a\u00020\u00072\u0007\u0010¦\u0001\u001a\u0002062$\b\u0002\u0010Æ\u0001\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020\u00160\u0012H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00188WX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010 0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010!\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00180\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010*\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00070.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u00101\u001a\u0004\u0018\u0001028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002060\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010=\u001a\u00020\t2\u0006\u0010=\u001a\u00020\t8W@WX\u0096\u000e¢\u0006\f\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001c\u0010B\u001a\u00020C8@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010N\u001a\u00020I8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bO\u0010PR$\u0010S\u001a\u00020\u000b2\u0006\u0010S\u001a\u00020\u000b8V@WX\u0096\u000e¢\u0006\f\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR&\u0010X\u001a\u001a\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002020Y\u0012\b\u0012\u00060ZR\u00020\u00000\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u0004\u0018\u00010`X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010a\u001a\b\u0012\u0004\u0012\u00020c0bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010f\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010h\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bi\u0010,R\u0010\u0010j\u001a\u0004\u0018\u00010kX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0m8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bn\u0010o\u001a\u0004\bp\u0010q¨\u0006Ê\u0001"}, d2 = {"Landroidx/navigation/NavController;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_currentBackStackEntryFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Landroidx/navigation/NavBackStackEntry;", "_graph", "Landroidx/navigation/NavGraph;", "_navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "_visibleEntries", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "activity", "Landroid/app/Activity;", "addToBackStackHandler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "backStackEntry", "", "backQueue", "Lkotlin/collections/ArrayDeque;", "getBackQueue", "()Lkotlin/collections/ArrayDeque;", "backStackEntriesToDispatch", "", "backStackMap", "", "", "", "backStackStates", "Landroidx/navigation/NavBackStackEntryState;", "backStackToRestore", "", "Landroid/os/Parcelable;", "[Landroid/os/Parcelable;", "childToParentEntries", "getContext", "()Landroid/content/Context;", "currentBackStackEntry", "getCurrentBackStackEntry", "()Landroidx/navigation/NavBackStackEntry;", "currentBackStackEntryFlow", "Lkotlinx/coroutines/flow/Flow;", "getCurrentBackStackEntryFlow", "()Lkotlinx/coroutines/flow/Flow;", "currentDestination", "Landroidx/navigation/NavDestination;", "getCurrentDestination", "()Landroidx/navigation/NavDestination;", "deepLinkHandled", "", "destinationCountOnBackStack", "getDestinationCountOnBackStack", "()I", "dispatchReentrantCount", "enableOnBackPressedCallback", "entrySavedState", "graph", "getGraph", "()Landroidx/navigation/NavGraph;", "setGraph", "(Landroidx/navigation/NavGraph;)V", "hostLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", "getHostLifecycleState$navigation_runtime_release", "()Landroidx/lifecycle/Lifecycle$State;", "setHostLifecycleState$navigation_runtime_release", "(Landroidx/lifecycle/Lifecycle$State;)V", "inflater", "Landroidx/navigation/NavInflater;", "lifecycleObserver", "Landroidx/lifecycle/LifecycleObserver;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "navInflater", "getNavInflater", "()Landroidx/navigation/NavInflater;", "navInflater$delegate", "Lkotlin/Lazy;", "navigatorProvider", "getNavigatorProvider", "()Landroidx/navigation/NavigatorProvider;", "setNavigatorProvider", "(Landroidx/navigation/NavigatorProvider;)V", "navigatorState", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavController$NavControllerNavigatorState;", "navigatorStateToRestore", "Landroid/os/Bundle;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "onDestinationChangedListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/navigation/NavController$OnDestinationChangedListener;", "parentToChildCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "popFromBackStackHandler", "popUpTo", "previousBackStackEntry", "getPreviousBackStackEntry", "viewModel", "Landroidx/navigation/NavControllerViewModel;", "visibleEntries", "Lkotlinx/coroutines/flow/StateFlow;", "getVisibleEntries$annotations", "()V", "getVisibleEntries", "()Lkotlinx/coroutines/flow/StateFlow;", "addEntryToBackStack", "node", "finalArgs", "restoredEntries", "addOnDestinationChangedListener", "listener", "clearBackStack", "destinationId", "route", "clearBackStackInternal", "createDeepLink", "Landroidx/navigation/NavDeepLinkBuilder;", "dispatchOnDestinationChanged", "enableOnBackPressed", "enabled", "findDestination", "destinationRoute", "findInvalidDestinationDisplayNameInDeepLink", "deepLink", "", "getBackStackEntry", "getViewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "navGraphId", "handleDeepLink", "intent", "Landroid/content/Intent;", "instantiateBackStack", "backStackState", "linkChildToParent", "child", "parent", "navigate", "Landroid/net/Uri;", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "request", "Landroidx/navigation/NavDeepLinkRequest;", "args", "directions", "Landroidx/navigation/NavDirections;", "resId", "builder", "Landroidx/navigation/NavOptionsBuilder;", "Lkotlin/ExtensionFunctionType;", "navigateUp", "onGraphCreated", "startDestinationArgs", "popBackStack", "inclusive", "saveState", "popBackStackFromNavigator", "onComplete", "Lkotlin/Function0;", "popBackStackFromNavigator$navigation_runtime_release", "popBackStackInternal", "popEntryFromBackStack", "savedState", "populateVisibleEntries", "populateVisibleEntries$navigation_runtime_release", "removeOnDestinationChangedListener", "restoreState", "navState", "restoreStateInternal", "id", "graphResId", "setLifecycleOwner", "owner", "setOnBackPressedDispatcher", "dispatcher", "setViewModelStore", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "tryRelaunchUpToExplicitStack", "tryRelaunchUpToGeneratedStack", "unlinkChildFromParent", "unlinkChildFromParent$navigation_runtime_release", "updateBackStackLifecycle", "updateBackStackLifecycle$navigation_runtime_release", "updateOnBackPressedCallbackEnabled", "navigateInternal", "entries", "handler", "Companion", "NavControllerNavigatorState", "OnDestinationChangedListener", "navigation-runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public class NavController {
    private static final String KEY_BACK_STACK = "android-support-nav:controller:backStack";
    private static final String KEY_BACK_STACK_DEST_IDS = "android-support-nav:controller:backStackDestIds";
    private static final String KEY_BACK_STACK_IDS = "android-support-nav:controller:backStackIds";
    private static final String KEY_BACK_STACK_STATES_IDS = "android-support-nav:controller:backStackStates";
    private static final String KEY_BACK_STACK_STATES_PREFIX = "android-support-nav:controller:backStackStates:";
    public static final String KEY_DEEP_LINK_ARGS = "android-support-nav:controller:deepLinkArgs";
    public static final String KEY_DEEP_LINK_EXTRAS = "android-support-nav:controller:deepLinkExtras";
    public static final String KEY_DEEP_LINK_HANDLED = "android-support-nav:controller:deepLinkHandled";
    public static final String KEY_DEEP_LINK_IDS = "android-support-nav:controller:deepLinkIds";
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    private static final String TAG = "NavController";
    private final MutableSharedFlow<NavBackStackEntry> _currentBackStackEntryFlow;
    private NavGraph _graph;
    private NavigatorProvider _navigatorProvider;
    private final MutableStateFlow<List<NavBackStackEntry>> _visibleEntries;
    private Activity activity;
    private Function1<? super NavBackStackEntry, Unit> addToBackStackHandler;
    private final ArrayDeque<NavBackStackEntry> backQueue;
    private final List<NavBackStackEntry> backStackEntriesToDispatch;
    private final Map<Integer, String> backStackMap;
    private final Map<String, ArrayDeque<NavBackStackEntryState>> backStackStates;
    private Parcelable[] backStackToRestore;
    private final Map<NavBackStackEntry, NavBackStackEntry> childToParentEntries;
    private final Context context;
    private final Flow<NavBackStackEntry> currentBackStackEntryFlow;
    private boolean deepLinkHandled;
    private int dispatchReentrantCount;
    private boolean enableOnBackPressedCallback;
    private final Map<NavBackStackEntry, Boolean> entrySavedState;
    private Lifecycle.State hostLifecycleState;
    private NavInflater inflater;
    private final LifecycleObserver lifecycleObserver;
    private LifecycleOwner lifecycleOwner;

    /* renamed from: navInflater$delegate, reason: from kotlin metadata */
    private final Lazy navInflater;
    private final Map<Navigator<? extends NavDestination>, NavControllerNavigatorState> navigatorState;
    private Bundle navigatorStateToRestore;
    private final OnBackPressedCallback onBackPressedCallback;
    private OnBackPressedDispatcher onBackPressedDispatcher;
    private final CopyOnWriteArrayList<OnDestinationChangedListener> onDestinationChangedListeners;
    private final Map<NavBackStackEntry, AtomicInteger> parentToChildCount;
    private Function1<? super NavBackStackEntry, Unit> popFromBackStackHandler;
    private NavControllerViewModel viewModel;
    private final StateFlow<List<NavBackStackEntry>> visibleEntries;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean deepLinkSaveState = true;

    /* compiled from: NavController.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Landroidx/navigation/NavController$OnDestinationChangedListener;", "", "onDestinationChanged", "", "controller", "Landroidx/navigation/NavController;", "destination", "Landroidx/navigation/NavDestination;", "arguments", "Landroid/os/Bundle;", "navigation-runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments);
    }

    @JvmStatic
    @NavDeepLinkSaveStateControl
    public static final void enableDeepLinkSaveState(boolean z) {
        INSTANCE.enableDeepLinkSaveState(z);
    }

    @NavControllerVisibleEntries
    public static /* synthetic */ void getVisibleEntries$annotations() {
    }

    public final void navigate(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        navigate$default(this, route, null, null, 6, null);
    }

    public final void navigate(String route, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(route, "route");
        navigate$default(this, route, navOptions, null, 4, null);
    }

    public final boolean popBackStack(String route, boolean z) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStack$default(this, route, z, false, 4, null);
    }

    public NavController(Context context) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Sequence $this$firstOrNull$iv = SequencesKt.generateSequence(context, new Function1<Context, Context>() { // from class: androidx.navigation.NavController$activity$1
            @Override // kotlin.jvm.functions.Function1
            public final Context invoke(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it instanceof ContextWrapper) {
                    return ((ContextWrapper) it).getBaseContext();
                }
                return null;
            }
        });
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                Context it2 = (Context) element$iv;
                if (it2 instanceof Activity) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        this.activity = (Activity) element$iv;
        this.backQueue = new ArrayDeque<>();
        MutableStateFlow<List<NavBackStackEntry>> MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._visibleEntries = MutableStateFlow;
        this.visibleEntries = FlowKt.asStateFlow(MutableStateFlow);
        this.childToParentEntries = new LinkedHashMap();
        this.parentToChildCount = new LinkedHashMap();
        this.backStackMap = new LinkedHashMap();
        this.backStackStates = new LinkedHashMap();
        this.onDestinationChangedListeners = new CopyOnWriteArrayList<>();
        this.hostLifecycleState = Lifecycle.State.INITIALIZED;
        this.lifecycleObserver = new LifecycleEventObserver() { // from class: androidx.navigation.NavController$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                NavController.m47lifecycleObserver$lambda2(NavController.this, lifecycleOwner, event);
            }
        };
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: androidx.navigation.NavController$onBackPressedCallback$1
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                NavController.this.popBackStack();
            }
        };
        this.enableOnBackPressedCallback = true;
        this._navigatorProvider = new NavigatorProvider();
        this.navigatorState = new LinkedHashMap();
        this.entrySavedState = new LinkedHashMap();
        this._navigatorProvider.addNavigator(new NavGraphNavigator(this._navigatorProvider));
        this._navigatorProvider.addNavigator(new ActivityNavigator(this.context));
        this.backStackEntriesToDispatch = new ArrayList();
        this.navInflater = LazyKt.lazy(new Function0<NavInflater>() { // from class: androidx.navigation.NavController$navInflater$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NavInflater invoke() {
                NavInflater navInflater;
                navInflater = NavController.this.inflater;
                return navInflater == null ? new NavInflater(NavController.this.getContext(), NavController.this._navigatorProvider) : navInflater;
            }
        });
        MutableSharedFlow<NavBackStackEntry> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
        this._currentBackStackEntryFlow = MutableSharedFlow$default;
        this.currentBackStackEntryFlow = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    public final Context getContext() {
        return this.context;
    }

    public NavGraph getGraph() {
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()".toString());
        }
        if (navGraph != null) {
            return navGraph;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.navigation.NavGraph");
    }

    public void setGraph(NavGraph graph) {
        Intrinsics.checkNotNullParameter(graph, "graph");
        setGraph(graph, (Bundle) null);
    }

    public ArrayDeque<NavBackStackEntry> getBackQueue() {
        return this.backQueue;
    }

    public final StateFlow<List<NavBackStackEntry>> getVisibleEntries() {
        return this.visibleEntries;
    }

    private final void linkChildToParent(NavBackStackEntry child, NavBackStackEntry parent) {
        this.childToParentEntries.put(child, parent);
        if (this.parentToChildCount.get(parent) == null) {
            this.parentToChildCount.put(parent, new AtomicInteger(0));
        }
        AtomicInteger atomicInteger = this.parentToChildCount.get(parent);
        Intrinsics.checkNotNull(atomicInteger);
        atomicInteger.incrementAndGet();
    }

    public final NavBackStackEntry unlinkChildFromParent$navigation_runtime_release(NavBackStackEntry child) {
        Intrinsics.checkNotNullParameter(child, "child");
        NavBackStackEntry parent = this.childToParentEntries.remove(child);
        if (parent == null) {
            return null;
        }
        AtomicInteger atomicInteger = this.parentToChildCount.get(parent);
        Integer count = atomicInteger != null ? Integer.valueOf(atomicInteger.decrementAndGet()) : null;
        if (count != null && count.intValue() == 0) {
            NavigatorProvider $this$get$iv = this._navigatorProvider;
            String name$iv = parent.getDestination().getNavigatorName();
            Navigator navGraphNavigator = $this$get$iv.getNavigator(name$iv);
            NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(navGraphNavigator);
            if (navControllerNavigatorState != null) {
                navControllerNavigatorState.markTransitionComplete(parent);
            }
            this.parentToChildCount.remove(parent);
        }
        return parent;
    }

    public final void setHostLifecycleState$navigation_runtime_release(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "<set-?>");
        this.hostLifecycleState = state;
    }

    public final Lifecycle.State getHostLifecycleState$navigation_runtime_release() {
        if (this.lifecycleOwner == null) {
            return Lifecycle.State.CREATED;
        }
        return this.hostLifecycleState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lifecycleObserver$lambda-2, reason: not valid java name */
    public static final void m47lifecycleObserver$lambda2(NavController this$0, LifecycleOwner noName_0, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(event, "event");
        Lifecycle.State targetState = event.getTargetState();
        Intrinsics.checkNotNullExpressionValue(targetState, "event.targetState");
        this$0.hostLifecycleState = targetState;
        if (this$0._graph != null) {
            Iterator it = this$0.getBackQueue().iterator();
            while (it.hasNext()) {
                NavBackStackEntry entry = (NavBackStackEntry) it.next();
                entry.handleLifecycleEvent(event);
            }
        }
    }

    /* renamed from: getNavigatorProvider, reason: from getter */
    public NavigatorProvider get_navigatorProvider() {
        return this._navigatorProvider;
    }

    public void setNavigatorProvider(NavigatorProvider navigatorProvider) {
        Intrinsics.checkNotNullParameter(navigatorProvider, "navigatorProvider");
        if (!getBackQueue().isEmpty()) {
            throw new IllegalStateException("NavigatorProvider must be set before setGraph call".toString());
        }
        this._navigatorProvider = navigatorProvider;
    }

    static /* synthetic */ void navigateInternal$default(NavController navController, Navigator navigator, List list, NavOptions navOptions, Navigator.Extras extras, Function1 function1, int i, Object obj) {
        Function1 function12;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigateInternal");
        }
        if ((i & 8) == 0) {
            function12 = function1;
        } else {
            function12 = new Function1<NavBackStackEntry, Unit>() { // from class: androidx.navigation.NavController$navigateInternal$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry) {
                    invoke2(navBackStackEntry);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NavBackStackEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            };
        }
        navController.navigateInternal(navigator, list, navOptions, extras, function12);
    }

    private final void navigateInternal(Navigator<? extends NavDestination> navigator, List<NavBackStackEntry> list, NavOptions navOptions, Navigator.Extras navigatorExtras, Function1<? super NavBackStackEntry, Unit> function1) {
        this.addToBackStackHandler = function1;
        navigator.navigate(list, navOptions, navigatorExtras);
        this.addToBackStackHandler = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void popBackStackInternal$default(NavController navController, Navigator navigator, NavBackStackEntry navBackStackEntry, boolean z, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popBackStackInternal");
        }
        if ((i & 4) != 0) {
            function1 = new Function1<NavBackStackEntry, Unit>() { // from class: androidx.navigation.NavController$popBackStackInternal$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry2) {
                    invoke2(navBackStackEntry2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NavBackStackEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            };
        }
        navController.popBackStackInternal(navigator, navBackStackEntry, z, function1);
    }

    private final void popBackStackInternal(Navigator<? extends NavDestination> navigator, NavBackStackEntry popUpTo, boolean saveState, Function1<? super NavBackStackEntry, Unit> function1) {
        this.popFromBackStackHandler = function1;
        navigator.popBackStack(popUpTo, saveState);
        this.popFromBackStackHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NavController.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Landroidx/navigation/NavController$NavControllerNavigatorState;", "Landroidx/navigation/NavigatorState;", "navigator", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "(Landroidx/navigation/NavController;Landroidx/navigation/Navigator;)V", "getNavigator", "()Landroidx/navigation/Navigator;", "addInternal", "", "backStackEntry", "Landroidx/navigation/NavBackStackEntry;", "createBackStackEntry", "destination", "arguments", "Landroid/os/Bundle;", "markTransitionComplete", "entry", "pop", "popUpTo", "saveState", "", "popWithTransition", "push", "navigation-runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class NavControllerNavigatorState extends NavigatorState {
        private final Navigator<? extends NavDestination> navigator;
        final /* synthetic */ NavController this$0;

        public NavControllerNavigatorState(NavController this$0, Navigator<? extends NavDestination> navigator) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(navigator, "navigator");
            this.this$0 = this$0;
            this.navigator = navigator;
        }

        public final Navigator<? extends NavDestination> getNavigator() {
            return this.navigator;
        }

        @Override // androidx.navigation.NavigatorState
        public void push(NavBackStackEntry backStackEntry) {
            Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
            NavigatorProvider $this$get$iv = this.this$0._navigatorProvider;
            String name$iv = backStackEntry.getDestination().getNavigatorName();
            Navigator destinationNavigator = $this$get$iv.getNavigator(name$iv);
            if (Intrinsics.areEqual(destinationNavigator, this.navigator)) {
                Function1 handler = this.this$0.addToBackStackHandler;
                if (handler != null) {
                    handler.invoke(backStackEntry);
                    addInternal(backStackEntry);
                    return;
                } else {
                    Log.i(NavController.TAG, "Ignoring add of destination " + backStackEntry.getDestination() + " outside of the call to navigate(). ");
                    return;
                }
            }
            Object obj = this.this$0.navigatorState.get(destinationNavigator);
            if (obj == null) {
                throw new IllegalStateException(("NavigatorBackStack for " + backStackEntry.getDestination().getNavigatorName() + " should already be created").toString());
            }
            NavControllerNavigatorState navigatorBackStack = (NavControllerNavigatorState) obj;
            navigatorBackStack.push(backStackEntry);
        }

        public final void addInternal(NavBackStackEntry backStackEntry) {
            Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
            super.push(backStackEntry);
        }

        @Override // androidx.navigation.NavigatorState
        public NavBackStackEntry createBackStackEntry(NavDestination destination, Bundle arguments) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            return NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, this.this$0.getContext(), destination, arguments, this.this$0.getHostLifecycleState$navigation_runtime_release(), this.this$0.viewModel, null, null, 96, null);
        }

        @Override // androidx.navigation.NavigatorState
        public void pop(final NavBackStackEntry popUpTo, final boolean saveState) {
            Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
            NavigatorProvider $this$get$iv = this.this$0._navigatorProvider;
            String name$iv = popUpTo.getDestination().getNavigatorName();
            Navigator destinationNavigator = $this$get$iv.getNavigator(name$iv);
            if (Intrinsics.areEqual(destinationNavigator, this.navigator)) {
                Function1 handler = this.this$0.popFromBackStackHandler;
                if (handler != null) {
                    handler.invoke(popUpTo);
                    super.pop(popUpTo, saveState);
                    return;
                } else {
                    this.this$0.popBackStackFromNavigator$navigation_runtime_release(popUpTo, new Function0<Unit>() { // from class: androidx.navigation.NavController$NavControllerNavigatorState$pop$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            super/*androidx.navigation.NavigatorState*/.pop(popUpTo, saveState);
                        }
                    });
                    return;
                }
            }
            Object obj = this.this$0.navigatorState.get(destinationNavigator);
            Intrinsics.checkNotNull(obj);
            ((NavControllerNavigatorState) obj).pop(popUpTo, saveState);
        }

        @Override // androidx.navigation.NavigatorState
        public void popWithTransition(NavBackStackEntry popUpTo, boolean saveState) {
            Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
            super.popWithTransition(popUpTo, saveState);
            this.this$0.entrySavedState.put(popUpTo, Boolean.valueOf(saveState));
        }

        @Override // androidx.navigation.NavigatorState
        public void markTransitionComplete(NavBackStackEntry entry) {
            NavControllerViewModel navControllerViewModel;
            Intrinsics.checkNotNullParameter(entry, "entry");
            boolean z = true;
            boolean savedState = Intrinsics.areEqual(this.this$0.entrySavedState.get(entry), (Object) true);
            super.markTransitionComplete(entry);
            this.this$0.entrySavedState.remove(entry);
            if (!this.this$0.getBackQueue().contains(entry)) {
                this.this$0.unlinkChildFromParent$navigation_runtime_release(entry);
                if (entry.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    entry.setMaxLifecycle(Lifecycle.State.DESTROYED);
                }
                Iterable $this$none$iv = this.this$0.getBackQueue();
                if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                    Iterator<NavBackStackEntry> it = $this$none$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object element$iv = it.next();
                        NavBackStackEntry it2 = (NavBackStackEntry) element$iv;
                        if (Intrinsics.areEqual(it2.getId(), entry.getId())) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z && !savedState && (navControllerViewModel = this.this$0.viewModel) != null) {
                    navControllerViewModel.clear(entry.getId());
                }
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
                this.this$0._visibleEntries.tryEmit(this.this$0.populateVisibleEntries$navigation_runtime_release());
                return;
            }
            if (!getIsNavigating()) {
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
                this.this$0._visibleEntries.tryEmit(this.this$0.populateVisibleEntries$navigation_runtime_release());
            }
        }
    }

    public void addOnDestinationChangedListener(OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.add(listener);
        if (!getBackQueue().isEmpty()) {
            NavBackStackEntry backStackEntry = getBackQueue().last();
            listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
        }
    }

    public void removeOnDestinationChangedListener(OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.remove(listener);
    }

    public boolean popBackStack() {
        if (getBackQueue().isEmpty()) {
            return false;
        }
        NavDestination currentDestination = getCurrentDestination();
        Intrinsics.checkNotNull(currentDestination);
        return popBackStack(currentDestination.getId(), true);
    }

    public boolean popBackStack(int destinationId, boolean inclusive) {
        return popBackStack(destinationId, inclusive, false);
    }

    public boolean popBackStack(int destinationId, boolean inclusive, boolean saveState) {
        boolean popped = popBackStackInternal(destinationId, inclusive, saveState);
        return popped && dispatchOnDestinationChanged();
    }

    public static /* synthetic */ boolean popBackStack$default(NavController navController, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popBackStack");
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return navController.popBackStack(str, z, z2);
    }

    public final boolean popBackStack(String route, boolean inclusive, boolean saveState) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStack(NavDestination.INSTANCE.createRoute(route).hashCode(), inclusive, saveState);
    }

    static /* synthetic */ boolean popBackStackInternal$default(NavController navController, int i, boolean z, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popBackStackInternal");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return navController.popBackStackInternal(i, z, z2);
    }

    private final boolean popBackStackInternal(int destinationId, boolean inclusive, final boolean saveState) {
        NavDestination foundDestination;
        if (getBackQueue().isEmpty()) {
            return false;
        }
        List<Navigator> popOperations = new ArrayList();
        Iterator iterator = CollectionsKt.reversed(getBackQueue()).iterator();
        while (true) {
            if (!iterator.hasNext()) {
                foundDestination = null;
                break;
            }
            NavDestination destination = ((NavBackStackEntry) iterator.next()).getDestination();
            Navigator navigator = this._navigatorProvider.getNavigator(destination.getNavigatorName());
            if (inclusive || destination.getId() != destinationId) {
                popOperations.add(navigator);
            }
            if (destination.getId() == destinationId) {
                foundDestination = destination;
                break;
            }
        }
        if (foundDestination == null) {
            String destinationName = NavDestination.INSTANCE.getDisplayName(this.context, destinationId);
            Log.i(TAG, "Ignoring popBackStack to destination " + destinationName + " as it was not found on the current back stack");
            return false;
        }
        final Ref.BooleanRef popped = new Ref.BooleanRef();
        final ArrayDeque savedState = new ArrayDeque();
        for (Navigator navigator2 : popOperations) {
            final Ref.BooleanRef receivedPop = new Ref.BooleanRef();
            List popOperations2 = popOperations;
            popBackStackInternal(navigator2, getBackQueue().last(), saveState, new Function1<NavBackStackEntry, Unit>() { // from class: androidx.navigation.NavController$popBackStackInternal$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry) {
                    invoke2(navBackStackEntry);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NavBackStackEntry entry) {
                    Intrinsics.checkNotNullParameter(entry, "entry");
                    Ref.BooleanRef.this.element = true;
                    popped.element = true;
                    this.popEntryFromBackStack(entry, saveState, savedState);
                }
            });
            if (!receivedPop.element) {
                break;
            }
            popOperations = popOperations2;
        }
        if (saveState) {
            if (!inclusive) {
                Sequence $this$forEach$iv = SequencesKt.takeWhile(SequencesKt.generateSequence(foundDestination, new Function1<NavDestination, NavDestination>() { // from class: androidx.navigation.NavController$popBackStackInternal$3
                    @Override // kotlin.jvm.functions.Function1
                    public final NavDestination invoke(NavDestination destination2) {
                        Intrinsics.checkNotNullParameter(destination2, "destination");
                        NavGraph parent = destination2.getParent();
                        boolean z = false;
                        if (parent != null && parent.getStartDestId() == destination2.getId()) {
                            z = true;
                        }
                        if (z) {
                            return destination2.getParent();
                        }
                        return null;
                    }
                }), new Function1<NavDestination, Boolean>() { // from class: androidx.navigation.NavController$popBackStackInternal$4
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(NavDestination destination2) {
                        Map map;
                        Intrinsics.checkNotNullParameter(destination2, "destination");
                        map = NavController.this.backStackMap;
                        return Boolean.valueOf(!map.containsKey(Integer.valueOf(destination2.getId())));
                    }
                });
                for (Object element$iv : $this$forEach$iv) {
                    NavDestination destination2 = (NavDestination) element$iv;
                    Map<Integer, String> map = this.backStackMap;
                    Integer valueOf = Integer.valueOf(destination2.getId());
                    NavBackStackEntryState firstOrNull = savedState.firstOrNull();
                    map.put(valueOf, firstOrNull == null ? null : firstOrNull.getId());
                }
            }
            if (!savedState.isEmpty()) {
                NavBackStackEntryState firstState = savedState.first();
                NavDestination firstStateDestination = findDestination(firstState.getDestinationId());
                Sequence $this$forEach$iv2 = SequencesKt.takeWhile(SequencesKt.generateSequence(firstStateDestination, new Function1<NavDestination, NavDestination>() { // from class: androidx.navigation.NavController$popBackStackInternal$6
                    @Override // kotlin.jvm.functions.Function1
                    public final NavDestination invoke(NavDestination destination3) {
                        Intrinsics.checkNotNullParameter(destination3, "destination");
                        NavGraph parent = destination3.getParent();
                        boolean z = false;
                        if (parent != null && parent.getStartDestId() == destination3.getId()) {
                            z = true;
                        }
                        if (z) {
                            return destination3.getParent();
                        }
                        return null;
                    }
                }), new Function1<NavDestination, Boolean>() { // from class: androidx.navigation.NavController$popBackStackInternal$7
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(NavDestination destination3) {
                        Map map2;
                        Intrinsics.checkNotNullParameter(destination3, "destination");
                        map2 = NavController.this.backStackMap;
                        return Boolean.valueOf(!map2.containsKey(Integer.valueOf(destination3.getId())));
                    }
                });
                for (Object element$iv2 : $this$forEach$iv2) {
                    NavDestination destination3 = (NavDestination) element$iv2;
                    this.backStackMap.put(Integer.valueOf(destination3.getId()), firstState.getId());
                    firstStateDestination = firstStateDestination;
                }
                this.backStackStates.put(firstState.getId(), savedState);
            }
        }
        updateOnBackPressedCallbackEnabled();
        return popped.element;
    }

    public final void popBackStackFromNavigator$navigation_runtime_release(NavBackStackEntry popUpTo, Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        int popIndex = getBackQueue().indexOf(popUpTo);
        if (popIndex < 0) {
            Log.i(TAG, "Ignoring pop of " + popUpTo + " as it was not found on the current back stack");
            return;
        }
        if (popIndex + 1 != getBackQueue().size()) {
            popBackStackInternal(getBackQueue().get(popIndex + 1).getDestination().getId(), true, false);
        }
        popEntryFromBackStack$default(this, popUpTo, false, null, 6, null);
        onComplete.invoke();
        updateOnBackPressedCallbackEnabled();
        dispatchOnDestinationChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void popEntryFromBackStack$default(NavController navController, NavBackStackEntry navBackStackEntry, boolean z, ArrayDeque arrayDeque, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popEntryFromBackStack");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            arrayDeque = new ArrayDeque();
        }
        navController.popEntryFromBackStack(navBackStackEntry, z, arrayDeque);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void popEntryFromBackStack(NavBackStackEntry popUpTo, boolean saveState, ArrayDeque<NavBackStackEntryState> savedState) {
        StateFlow<Set<NavBackStackEntry>> transitionsInProgress;
        Set<NavBackStackEntry> value;
        NavControllerViewModel navControllerViewModel;
        NavBackStackEntry entry = getBackQueue().last();
        if (!Intrinsics.areEqual(entry, popUpTo)) {
            throw new IllegalStateException(("Attempted to pop " + popUpTo.getDestination() + ", which is not the top of the back stack (" + entry.getDestination() + ')').toString());
        }
        getBackQueue().removeLast();
        Navigator navigator = get_navigatorProvider().getNavigator(entry.getDestination().getNavigatorName());
        NavControllerNavigatorState state = this.navigatorState.get(navigator);
        boolean transitioning = true;
        if (!((state == null || (transitionsInProgress = state.getTransitionsInProgress()) == null || (value = transitionsInProgress.getValue()) == null || !value.contains(entry)) ? false : true) && !this.parentToChildCount.containsKey(entry)) {
            transitioning = false;
        }
        if (entry.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            if (saveState) {
                entry.setMaxLifecycle(Lifecycle.State.CREATED);
                savedState.addFirst(new NavBackStackEntryState(entry));
            }
            if (!transitioning) {
                entry.setMaxLifecycle(Lifecycle.State.DESTROYED);
                unlinkChildFromParent$navigation_runtime_release(entry);
            } else {
                entry.setMaxLifecycle(Lifecycle.State.CREATED);
            }
        }
        if (saveState || transitioning || (navControllerViewModel = this.viewModel) == null) {
            return;
        }
        navControllerViewModel.clear(entry.getId());
    }

    public final boolean clearBackStack(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return clearBackStack(NavDestination.INSTANCE.createRoute(route).hashCode());
    }

    public final boolean clearBackStack(int destinationId) {
        boolean cleared = clearBackStackInternal(destinationId);
        return cleared && dispatchOnDestinationChanged();
    }

    private final boolean clearBackStackInternal(int destinationId) {
        Iterable $this$forEach$iv = this.navigatorState.values();
        for (Object element$iv : $this$forEach$iv) {
            NavControllerNavigatorState state = (NavControllerNavigatorState) element$iv;
            state.setNavigating(true);
        }
        boolean restored = restoreStateInternal(destinationId, null, null, null);
        Iterable $this$forEach$iv2 = this.navigatorState.values();
        for (Object element$iv2 : $this$forEach$iv2) {
            NavControllerNavigatorState state2 = (NavControllerNavigatorState) element$iv2;
            state2.setNavigating(false);
        }
        return restored && popBackStackInternal(destinationId, true, false);
    }

    public boolean navigateUp() {
        Intent intent;
        if (getDestinationCountOnBackStack() == 1) {
            Activity activity = this.activity;
            Bundle extras = (activity == null || (intent = activity.getIntent()) == null) ? null : intent.getExtras();
            if ((extras != null ? extras.getIntArray(KEY_DEEP_LINK_IDS) : null) != null) {
                return tryRelaunchUpToExplicitStack();
            }
            return tryRelaunchUpToGeneratedStack();
        }
        return popBackStack();
    }

    private final boolean tryRelaunchUpToExplicitStack() {
        if (!this.deepLinkHandled) {
            return false;
        }
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        Intrinsics.checkNotNull(extras);
        int[] intArray = extras.getIntArray(KEY_DEEP_LINK_IDS);
        Intrinsics.checkNotNull(intArray);
        Intrinsics.checkNotNullExpressionValue(intArray, "extras!!.getIntArray(KEY_DEEP_LINK_IDS)!!");
        List deepLinkIds = ArraysKt.toMutableList(intArray);
        ArrayList deepLinkArgs = extras.getParcelableArrayList(KEY_DEEP_LINK_ARGS);
        int leafDestinationId = ((Number) CollectionsKt.removeLast(deepLinkIds)).intValue();
        if (deepLinkArgs != null) {
        }
        if (deepLinkIds.isEmpty()) {
            return false;
        }
        NavDestination $this$tryRelaunchUpToExplicitStack_u24lambda_u2d9 = findDestination(getGraph(), leafDestinationId);
        if ($this$tryRelaunchUpToExplicitStack_u24lambda_u2d9 instanceof NavGraph) {
            leafDestinationId = NavGraph.INSTANCE.findStartDestination((NavGraph) $this$tryRelaunchUpToExplicitStack_u24lambda_u2d9).getId();
        }
        NavDestination currentDestination = getCurrentDestination();
        if (!(currentDestination != null && leafDestinationId == currentDestination.getId())) {
            return false;
        }
        NavDeepLinkBuilder navDeepLinkBuilder = createDeepLink();
        Bundle arguments = BundleKt.bundleOf(TuplesKt.to(KEY_DEEP_LINK_INTENT, intent));
        Bundle it = extras.getBundle(KEY_DEEP_LINK_EXTRAS);
        if (it != null) {
            arguments.putAll(it);
        }
        navDeepLinkBuilder.setArguments(arguments);
        List $this$forEachIndexed$iv = deepLinkIds;
        int index = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = index + 1;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int deepLinkId = ((Number) item$iv).intValue();
            navDeepLinkBuilder.addDestination(deepLinkId, deepLinkArgs == null ? null : (Bundle) deepLinkArgs.get(index));
            index = index$iv;
        }
        navDeepLinkBuilder.createTaskStackBuilder().startActivities();
        Activity activity2 = this.activity;
        if (activity2 == null) {
            return true;
        }
        activity2.finish();
        return true;
    }

    private final boolean tryRelaunchUpToGeneratedStack() {
        NavDestination currentDestination = getCurrentDestination();
        Intrinsics.checkNotNull(currentDestination);
        int destId = currentDestination.getId();
        for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getStartDestId() != destId) {
                Bundle args = new Bundle();
                Activity activity = this.activity;
                if (activity != null) {
                    Intrinsics.checkNotNull(activity);
                    if (activity.getIntent() != null) {
                        Activity activity2 = this.activity;
                        Intrinsics.checkNotNull(activity2);
                        Uri data = activity2.getIntent().getData();
                        if (data != null) {
                            Activity activity3 = this.activity;
                            Intrinsics.checkNotNull(activity3);
                            args.putParcelable(KEY_DEEP_LINK_INTENT, activity3.getIntent());
                            NavGraph navGraph = this._graph;
                            Intrinsics.checkNotNull(navGraph);
                            Activity activity4 = this.activity;
                            Intrinsics.checkNotNull(activity4);
                            Intent intent = activity4.getIntent();
                            Intrinsics.checkNotNullExpressionValue(intent, "activity!!.intent");
                            NavDestination.DeepLinkMatch matchingDeepLink = navGraph.matchDeepLink(new NavDeepLinkRequest(intent));
                            if (matchingDeepLink != null) {
                                Bundle destinationArgs = matchingDeepLink.getDestination().addInDefaultArgs(matchingDeepLink.getMatchingArgs());
                                args.putAll(destinationArgs);
                            }
                        }
                    }
                }
                TaskStackBuilder parentIntents = NavDeepLinkBuilder.setDestination$default(new NavDeepLinkBuilder(this), parent.getId(), (Bundle) null, 2, (Object) null).setArguments(args).createTaskStackBuilder();
                parentIntents.startActivities();
                Activity activity5 = this.activity;
                if (activity5 == null) {
                    return true;
                }
                activity5.finish();
                return true;
            }
            destId = parent.getId();
        }
        return false;
    }

    private final int getDestinationCountOnBackStack() {
        Iterable $this$count$iv = getBackQueue();
        if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
            return 0;
        }
        int count$iv = 0;
        for (Object element$iv : $this$count$iv) {
            NavBackStackEntry entry = (NavBackStackEntry) element$iv;
            if ((!(entry.getDestination() instanceof NavGraph)) && (count$iv = count$iv + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return count$iv;
    }

    private final boolean dispatchOnDestinationChanged() {
        while (!getBackQueue().isEmpty() && (getBackQueue().last().getDestination() instanceof NavGraph)) {
            popEntryFromBackStack$default(this, getBackQueue().last(), false, null, 6, null);
        }
        NavBackStackEntry lastBackStackEntry = getBackQueue().lastOrNull();
        if (lastBackStackEntry != null) {
            this.backStackEntriesToDispatch.add(lastBackStackEntry);
        }
        this.dispatchReentrantCount++;
        updateBackStackLifecycle$navigation_runtime_release();
        int i = this.dispatchReentrantCount - 1;
        this.dispatchReentrantCount = i;
        if (i == 0) {
            List<NavBackStackEntry> dispatchList = CollectionsKt.toMutableList((Collection) this.backStackEntriesToDispatch);
            this.backStackEntriesToDispatch.clear();
            for (NavBackStackEntry backStackEntry : dispatchList) {
                Iterator<OnDestinationChangedListener> it = this.onDestinationChangedListeners.iterator();
                while (it.hasNext()) {
                    OnDestinationChangedListener listener = it.next();
                    listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
                }
                this._currentBackStackEntryFlow.tryEmit(backStackEntry);
            }
            this._visibleEntries.tryEmit(populateVisibleEntries$navigation_runtime_release());
        }
        return lastBackStackEntry != null;
    }

    public final void updateBackStackLifecycle$navigation_runtime_release() {
        StateFlow<Set<NavBackStackEntry>> transitionsInProgress;
        Set<NavBackStackEntry> value;
        List<NavBackStackEntry> backStack = CollectionsKt.toMutableList((Collection) getBackQueue());
        if (backStack.isEmpty()) {
            return;
        }
        NavDestination nextResumed = ((NavBackStackEntry) CollectionsKt.last(backStack)).getDestination();
        NavDestination nextStarted = null;
        if (nextResumed instanceof FloatingWindow) {
            Iterator iterator = CollectionsKt.reversed(backStack).iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    break;
                }
                NavDestination destination = ((NavBackStackEntry) iterator.next()).getDestination();
                if (!(destination instanceof NavGraph) && !(destination instanceof FloatingWindow)) {
                    nextStarted = destination;
                    break;
                }
            }
        }
        HashMap upwardStateTransitions = new HashMap();
        for (NavBackStackEntry entry : CollectionsKt.reversed(backStack)) {
            Lifecycle.State currentMaxLifecycle = entry.getMaxLifecycle();
            NavDestination destination2 = entry.getDestination();
            if (nextResumed != null && destination2.getId() == nextResumed.getId()) {
                if (currentMaxLifecycle != Lifecycle.State.RESUMED) {
                    Navigator navigator = get_navigatorProvider().getNavigator(entry.getDestination().getNavigatorName());
                    NavControllerNavigatorState state = this.navigatorState.get(navigator);
                    Boolean transitioning = null;
                    if (state != null && (transitionsInProgress = state.getTransitionsInProgress()) != null && (value = transitionsInProgress.getValue()) != null) {
                        transitioning = Boolean.valueOf(value.contains(entry));
                    }
                    if (!Intrinsics.areEqual((Object) transitioning, (Object) true)) {
                        AtomicInteger atomicInteger = this.parentToChildCount.get(entry);
                        if (!(atomicInteger != null && atomicInteger.get() == 0)) {
                            upwardStateTransitions.put(entry, Lifecycle.State.RESUMED);
                        }
                    }
                    upwardStateTransitions.put(entry, Lifecycle.State.STARTED);
                }
                nextResumed = nextResumed.getParent();
            } else if (nextStarted != null && destination2.getId() == nextStarted.getId()) {
                if (currentMaxLifecycle == Lifecycle.State.RESUMED) {
                    entry.setMaxLifecycle(Lifecycle.State.STARTED);
                } else if (currentMaxLifecycle != Lifecycle.State.STARTED) {
                    upwardStateTransitions.put(entry, Lifecycle.State.STARTED);
                }
                nextStarted = nextStarted.getParent();
            } else {
                entry.setMaxLifecycle(Lifecycle.State.CREATED);
            }
        }
        for (NavBackStackEntry entry2 : backStack) {
            Lifecycle.State newState = (Lifecycle.State) upwardStateTransitions.get(entry2);
            if (newState != null) {
                entry2.setMaxLifecycle(newState);
            } else {
                entry2.updateState();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<androidx.navigation.NavBackStackEntry> populateVisibleEntries$navigation_runtime_release() {
        /*
            Method dump skipped, instructions count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.populateVisibleEntries$navigation_runtime_release():java.util.List");
    }

    public NavInflater getNavInflater() {
        return (NavInflater) this.navInflater.getValue();
    }

    public void setGraph(int graphResId) {
        setGraph(getNavInflater().inflate(graphResId), (Bundle) null);
    }

    public void setGraph(int graphResId, Bundle startDestinationArgs) {
        setGraph(getNavInflater().inflate(graphResId), startDestinationArgs);
    }

    public void setGraph(NavGraph graph, Bundle startDestinationArgs) {
        Intrinsics.checkNotNullParameter(graph, "graph");
        if (!Intrinsics.areEqual(this._graph, graph)) {
            NavGraph previousGraph = this._graph;
            if (previousGraph != null) {
                Iterable savedBackStackIds = new ArrayList(this.backStackMap.keySet());
                Iterable $this$forEach$iv = savedBackStackIds;
                for (Object element$iv : $this$forEach$iv) {
                    Integer id = (Integer) element$iv;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    clearBackStackInternal(id.intValue());
                }
                int $i$f$forEach = previousGraph.getId();
                popBackStackInternal$default(this, $i$f$forEach, true, false, 4, null);
            }
            this._graph = graph;
            onGraphCreated(startDestinationArgs);
            return;
        }
        int size = graph.getNodes().size();
        int i = 0;
        while (i < size) {
            int i2 = i;
            i++;
            NavDestination newDestination = graph.getNodes().valueAt(i2);
            NavGraph navGraph = this._graph;
            Intrinsics.checkNotNull(navGraph);
            navGraph.getNodes().replace(i2, newDestination);
            Iterable $this$filter$iv = getBackQueue();
            Collection destination$iv$iv = new ArrayList();
            for (NavBackStackEntry navBackStackEntry : $this$filter$iv) {
                NavBackStackEntry currentEntry = navBackStackEntry;
                if (newDestination != null && currentEntry.getDestination().getId() == newDestination.getId()) {
                    destination$iv$iv.add(navBackStackEntry);
                }
            }
            Iterable $this$forEach$iv2 = (List) destination$iv$iv;
            for (Object element$iv2 : $this$forEach$iv2) {
                NavBackStackEntry entry = (NavBackStackEntry) element$iv2;
                Intrinsics.checkNotNullExpressionValue(newDestination, "newDestination");
                entry.setDestination(newDestination);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0187  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onGraphCreated(android.os.Bundle r17) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.onGraphCreated(android.os.Bundle):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
    
        if ((r1.length == 0) != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean handleDeepLink(android.content.Intent r29) {
        /*
            Method dump skipped, instructions count: 585
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.handleDeepLink(android.content.Intent):boolean");
    }

    private final String findInvalidDestinationDisplayNameInDeepLink(int[] deepLink) {
        NavGraph node;
        NavGraph graph = this._graph;
        int length = deepLink.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            int i2 = i;
            i++;
            int destinationId = deepLink[i2];
            if (i2 == 0) {
                NavGraph navGraph = this._graph;
                Intrinsics.checkNotNull(navGraph);
                node = navGraph.getId() == destinationId ? this._graph : null;
            } else {
                Intrinsics.checkNotNull(graph);
                node = graph.findNode(destinationId);
            }
            if (node == null) {
                return NavDestination.INSTANCE.getDisplayName(this.context, destinationId);
            }
            if (i2 != deepLink.length - 1 && (node instanceof NavGraph)) {
                NavDestination navDestination = node;
                while (true) {
                    graph = (NavGraph) navDestination;
                    Intrinsics.checkNotNull(graph);
                    if (graph.findNode(graph.getStartDestId()) instanceof NavGraph) {
                        navDestination = graph.findNode(graph.getStartDestId());
                    }
                }
            }
        }
    }

    public NavDestination getCurrentDestination() {
        NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
        if (currentBackStackEntry == null) {
            return null;
        }
        return currentBackStackEntry.getDestination();
    }

    public final NavDestination findDestination(int destinationId) {
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        Intrinsics.checkNotNull(navGraph);
        if (navGraph.getId() == destinationId) {
            return this._graph;
        }
        NavBackStackEntry lastOrNull = getBackQueue().lastOrNull();
        NavGraph destination = lastOrNull != null ? lastOrNull.getDestination() : null;
        if (destination == null) {
            NavGraph navGraph2 = this._graph;
            Intrinsics.checkNotNull(navGraph2);
            destination = navGraph2;
        }
        NavDestination currentNode = destination;
        return findDestination(currentNode, destinationId);
    }

    private final NavDestination findDestination(NavDestination $this$findDestination, int destinationId) {
        NavGraph currentGraph;
        if ($this$findDestination.getId() == destinationId) {
            return $this$findDestination;
        }
        if ($this$findDestination instanceof NavGraph) {
            currentGraph = (NavGraph) $this$findDestination;
        } else {
            currentGraph = $this$findDestination.getParent();
            Intrinsics.checkNotNull(currentGraph);
        }
        return currentGraph.findNode(destinationId);
    }

    public final NavDestination findDestination(String destinationRoute) {
        NavGraph currentGraph;
        Intrinsics.checkNotNullParameter(destinationRoute, "destinationRoute");
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        Intrinsics.checkNotNull(navGraph);
        if (Intrinsics.areEqual(navGraph.getRoute(), destinationRoute)) {
            return this._graph;
        }
        NavBackStackEntry lastOrNull = getBackQueue().lastOrNull();
        NavGraph destination = lastOrNull != null ? lastOrNull.getDestination() : null;
        if (destination == null) {
            NavGraph navGraph2 = this._graph;
            Intrinsics.checkNotNull(navGraph2);
            destination = navGraph2;
        }
        NavDestination currentNode = destination;
        if (currentNode instanceof NavGraph) {
            currentGraph = (NavGraph) currentNode;
        } else {
            currentGraph = currentNode.getParent();
            Intrinsics.checkNotNull(currentGraph);
        }
        return currentGraph.findNode(destinationRoute);
    }

    public void navigate(int resId) {
        navigate(resId, (Bundle) null);
    }

    public void navigate(int resId, Bundle args) {
        navigate(resId, args, (NavOptions) null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions) {
        navigate(resId, args, navOptions, (Navigator.Extras) null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavOptions finalNavOptions = navOptions;
        NavGraph currentNode = getBackQueue().isEmpty() ? this._graph : getBackQueue().last().getDestination();
        if (currentNode == null) {
            throw new IllegalStateException("no current navigation node");
        }
        int destId = resId;
        NavAction navAction = currentNode.getAction(resId);
        Bundle combinedArgs = null;
        if (navAction != null) {
            if (finalNavOptions == null) {
                finalNavOptions = navAction.getNavOptions();
            }
            destId = navAction.getDestinationId();
            Bundle navActionArgs = navAction.getDefaultArguments();
            if (navActionArgs != null) {
                combinedArgs = new Bundle();
                combinedArgs.putAll(navActionArgs);
            }
        }
        if (args != null) {
            if (combinedArgs == null) {
                combinedArgs = new Bundle();
            }
            combinedArgs.putAll(args);
        }
        if (destId == 0 && finalNavOptions != null && finalNavOptions.getPopUpToId() != -1) {
            popBackStack(finalNavOptions.getPopUpToId(), finalNavOptions.getPopUpToInclusive());
            return;
        }
        if (!(destId != 0)) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo".toString());
        }
        NavDestination node = findDestination(destId);
        if (node != null) {
            navigate(node, combinedArgs, finalNavOptions, navigatorExtras);
            return;
        }
        String dest = NavDestination.INSTANCE.getDisplayName(this.context, destId);
        if (!(navAction == null)) {
            throw new IllegalArgumentException(("Navigation destination " + dest + " referenced from action " + NavDestination.INSTANCE.getDisplayName(getContext(), resId) + " cannot be found from the current destination " + currentNode).toString());
        }
        throw new IllegalArgumentException("Navigation action/destination " + dest + " cannot be found from the current destination " + currentNode);
    }

    public void navigate(Uri deepLink) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate(new NavDeepLinkRequest(deepLink, null, null));
    }

    public void navigate(Uri deepLink, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate(new NavDeepLinkRequest(deepLink, null, null), navOptions, (Navigator.Extras) null);
    }

    public void navigate(Uri deepLink, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate(new NavDeepLinkRequest(deepLink, null, null), navOptions, navigatorExtras);
    }

    public void navigate(NavDeepLinkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        navigate(request, (NavOptions) null);
    }

    public void navigate(NavDeepLinkRequest request, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(request, "request");
        navigate(request, navOptions, (Navigator.Extras) null);
    }

    public void navigate(NavDeepLinkRequest request, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(request, "request");
        NavGraph navGraph = this._graph;
        Intrinsics.checkNotNull(navGraph);
        NavDestination.DeepLinkMatch deepLinkMatch = navGraph.matchDeepLink(request);
        if (deepLinkMatch != null) {
            NavDestination destination = deepLinkMatch.getDestination();
            Bundle args = destination.addInDefaultArgs(deepLinkMatch.getMatchingArgs());
            if (args == null) {
                args = new Bundle();
            }
            NavDestination node = deepLinkMatch.getDestination();
            Intent $this$navigate_u24lambda_u2d30 = new Intent();
            $this$navigate_u24lambda_u2d30.setDataAndType(request.getUri(), request.getMimeType());
            $this$navigate_u24lambda_u2d30.setAction(request.getAction());
            args.putParcelable(KEY_DEEP_LINK_INTENT, $this$navigate_u24lambda_u2d30);
            navigate(node, args, navOptions, navigatorExtras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches request " + request + " cannot be found in the navigation graph " + this._graph);
    }

    private final void navigate(final NavDestination node, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean popped;
        boolean z;
        NavDestination destination;
        Iterable $this$forEach$iv = this.navigatorState.values();
        Iterator it = $this$forEach$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            NavControllerNavigatorState state = (NavControllerNavigatorState) element$iv;
            state.setNavigating(true);
        }
        boolean launchSingleTop = false;
        final Ref.BooleanRef navigated = new Ref.BooleanRef();
        if (navOptions != null && navOptions.getPopUpToId() != -1) {
            boolean popped2 = popBackStackInternal(navOptions.getPopUpToId(), navOptions.getPopUpToInclusive(), navOptions.getPopUpToSaveState());
            popped = popped2;
        } else {
            popped = false;
        }
        final Bundle finalArgs = node.addInDefaultArgs(args);
        if ((navOptions != null && navOptions.getRestoreState()) && this.backStackMap.containsKey(Integer.valueOf(node.getId()))) {
            navigated.element = restoreStateInternal(node.getId(), finalArgs, navOptions, navigatorExtras);
            z = false;
        } else {
            NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
            Navigator navigator = this._navigatorProvider.getNavigator(node.getNavigatorName());
            if (navOptions != null && navOptions.getSingleTop()) {
                if ((currentBackStackEntry == null || (destination = currentBackStackEntry.getDestination()) == null || node.getId() != destination.getId()) ? false : true) {
                    unlinkChildFromParent$navigation_runtime_release(getBackQueue().removeLast());
                    NavBackStackEntry newEntry = new NavBackStackEntry(currentBackStackEntry, finalArgs);
                    getBackQueue().addLast(newEntry);
                    NavGraph parent = newEntry.getDestination().getParent();
                    if (parent != null) {
                        linkChildToParent(newEntry, getBackStackEntry(parent.getId()));
                    }
                    navigator.onLaunchSingleTop(newEntry);
                    launchSingleTop = true;
                    z = false;
                }
            }
            NavBackStackEntry backStackEntry = NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, this.context, node, finalArgs, getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
            z = false;
            navigateInternal(navigator, CollectionsKt.listOf(backStackEntry), navOptions, navigatorExtras, new Function1<NavBackStackEntry, Unit>() { // from class: androidx.navigation.NavController$navigate$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry) {
                    invoke2(navBackStackEntry);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NavBackStackEntry it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    Ref.BooleanRef.this.element = true;
                    NavController.addEntryToBackStack$default(this, node, finalArgs, it2, null, 8, null);
                }
            });
        }
        updateOnBackPressedCallbackEnabled();
        Iterable $this$forEach$iv2 = this.navigatorState.values();
        for (Object element$iv2 : $this$forEach$iv2) {
            NavControllerNavigatorState state2 = (NavControllerNavigatorState) element$iv2;
            state2.setNavigating(z);
        }
        if (popped || navigated.element || launchSingleTop) {
            dispatchOnDestinationChanged();
        } else {
            updateBackStackLifecycle$navigation_runtime_release();
        }
    }

    private final boolean restoreStateInternal(int id, final Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavBackStackEntry navBackStackEntry;
        NavDestination destination;
        if (!this.backStackMap.containsKey(Integer.valueOf(id))) {
            return false;
        }
        final String backStackId = this.backStackMap.get(Integer.valueOf(id));
        CollectionsKt.removeAll(this.backStackMap.values(), new Function1<String, Boolean>() { // from class: androidx.navigation.NavController$restoreStateInternal$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String it) {
                return Boolean.valueOf(Intrinsics.areEqual(it, backStackId));
            }
        });
        ArrayDeque backStackState = this.backStackStates.remove(backStackId);
        final List entries = instantiateBackStack(backStackState);
        List<List> entriesGroupedByNavigator = new ArrayList();
        List $this$filterNot$iv = entries;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            if (!(((NavBackStackEntry) element$iv$iv).getDestination() instanceof NavGraph)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            NavBackStackEntry entry = (NavBackStackEntry) element$iv;
            List previousEntryList = (List) CollectionsKt.lastOrNull(entriesGroupedByNavigator);
            String previousNavigatorName = null;
            if (previousEntryList != null && (navBackStackEntry = (NavBackStackEntry) CollectionsKt.last(previousEntryList)) != null && (destination = navBackStackEntry.getDestination()) != null) {
                previousNavigatorName = destination.getNavigatorName();
            }
            if (Intrinsics.areEqual(previousNavigatorName, entry.getDestination().getNavigatorName())) {
                previousEntryList.add(entry);
            } else {
                entriesGroupedByNavigator.add(CollectionsKt.mutableListOf(entry));
            }
        }
        final Ref.BooleanRef navigated = new Ref.BooleanRef();
        for (List entryList : entriesGroupedByNavigator) {
            Navigator navigator = this._navigatorProvider.getNavigator(((NavBackStackEntry) CollectionsKt.first(entryList)).getDestination().getNavigatorName());
            final Ref.IntRef lastNavigatedIndex = new Ref.IntRef();
            navigateInternal(navigator, entryList, navOptions, navigatorExtras, new Function1<NavBackStackEntry, Unit>() { // from class: androidx.navigation.NavController$restoreStateInternal$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry2) {
                    invoke2(navBackStackEntry2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NavBackStackEntry entry2) {
                    List restoredEntries;
                    Intrinsics.checkNotNullParameter(entry2, "entry");
                    Ref.BooleanRef.this.element = true;
                    int entryIndex = entries.indexOf(entry2);
                    if (entryIndex != -1) {
                        restoredEntries = entries.subList(lastNavigatedIndex.element, entryIndex + 1);
                        lastNavigatedIndex.element = entryIndex + 1;
                    } else {
                        restoredEntries = CollectionsKt.emptyList();
                    }
                    this.addEntryToBackStack(entry2.getDestination(), args, entry2, restoredEntries);
                }
            });
        }
        return navigated.element;
    }

    private final List<NavBackStackEntry> instantiateBackStack(ArrayDeque<NavBackStackEntryState> backStackState) {
        List backStack = new ArrayList();
        NavBackStackEntry lastOrNull = getBackQueue().lastOrNull();
        NavGraph destination = lastOrNull == null ? null : lastOrNull.getDestination();
        if (destination == null) {
            destination = getGraph();
        }
        NavDestination navDestination = destination;
        if (backStackState != null) {
            ArrayDeque<NavBackStackEntryState> $this$forEach$iv = backStackState;
            for (Object element$iv : $this$forEach$iv) {
                NavBackStackEntryState state = (NavBackStackEntryState) element$iv;
                NavDestination node = findDestination(navDestination, state.getDestinationId());
                if (node == null) {
                    String dest = NavDestination.INSTANCE.getDisplayName(getContext(), state.getDestinationId());
                    throw new IllegalStateException(("Restore State failed: destination " + dest + " cannot be found from the current destination " + navDestination).toString());
                }
                backStack.add(state.instantiate(getContext(), node, getHostLifecycleState$navigation_runtime_release(), this.viewModel));
                navDestination = node;
            }
        }
        return backStack;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void addEntryToBackStack$default(NavController navController, NavDestination navDestination, Bundle bundle, NavBackStackEntry navBackStackEntry, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addEntryToBackStack");
        }
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        navController.addEntryToBackStack(navDestination, bundle, navBackStackEntry, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addEntryToBackStack(NavDestination node, Bundle finalArgs, NavBackStackEntry backStackEntry, List<NavBackStackEntry> restoredEntries) {
        ArrayDeque hierarchy;
        NavDestination newDest;
        NavBackStackEntry navBackStackEntry;
        Bundle bundle;
        NavDestination destination;
        Object element$iv;
        NavGraph parent;
        Object element$iv2;
        NavBackStackEntry navBackStackEntry2;
        Bundle bundle2;
        Bundle bundle3 = finalArgs;
        NavBackStackEntry navBackStackEntry3 = backStackEntry;
        NavDestination newDest2 = backStackEntry.getDestination();
        if (!(newDest2 instanceof FloatingWindow)) {
            while (!getBackQueue().isEmpty() && (getBackQueue().last().getDestination() instanceof FloatingWindow) && popBackStackInternal$default(this, getBackQueue().last().getDestination().getId(), true, false, 4, null)) {
            }
        }
        ArrayDeque hierarchy2 = new ArrayDeque();
        Object obj = null;
        if (node instanceof NavGraph) {
            NavDestination destination2 = newDest2;
            while (true) {
                Intrinsics.checkNotNull(destination2);
                NavGraph parent2 = destination2.getParent();
                if (parent2 == null) {
                    parent = parent2;
                    hierarchy = hierarchy2;
                    newDest = newDest2;
                    navBackStackEntry = navBackStackEntry3;
                    bundle = bundle3;
                } else {
                    ListIterator iterator$iv = restoredEntries.listIterator(restoredEntries.size());
                    while (true) {
                        if (iterator$iv.hasPrevious()) {
                            element$iv2 = iterator$iv.previous();
                            NavBackStackEntry restoredEntry = (NavBackStackEntry) element$iv2;
                            if (Intrinsics.areEqual(restoredEntry.getDestination(), parent2)) {
                                break;
                            }
                        } else {
                            element$iv2 = null;
                            break;
                        }
                    }
                    NavBackStackEntry entry = (NavBackStackEntry) element$iv2;
                    if (entry != null) {
                        newDest = newDest2;
                        navBackStackEntry2 = navBackStackEntry3;
                        bundle2 = bundle3;
                    } else {
                        newDest = newDest2;
                        navBackStackEntry2 = navBackStackEntry3;
                        bundle2 = bundle3;
                        entry = NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, this.context, parent2, finalArgs, getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
                    }
                    hierarchy2.addFirst(entry);
                    if (!(!getBackQueue().isEmpty()) || getBackQueue().last().getDestination() != parent2) {
                        navBackStackEntry = navBackStackEntry2;
                        bundle = bundle2;
                        parent = parent2;
                        hierarchy = hierarchy2;
                    } else {
                        navBackStackEntry = navBackStackEntry2;
                        bundle = bundle2;
                        parent = parent2;
                        hierarchy = hierarchy2;
                        popEntryFromBackStack$default(this, getBackQueue().last(), false, null, 6, null);
                    }
                }
                NavGraph destination3 = parent;
                if (destination3 == null || destination3 == node) {
                    break;
                }
                destination2 = destination3;
                hierarchy2 = hierarchy;
                navBackStackEntry3 = navBackStackEntry;
                bundle3 = bundle;
                newDest2 = newDest;
            }
        } else {
            hierarchy = hierarchy2;
            newDest = newDest2;
            navBackStackEntry = navBackStackEntry3;
            bundle = bundle3;
        }
        NavDestination destination4 = hierarchy.isEmpty() ? newDest : ((NavBackStackEntry) hierarchy.first()).getDestination();
        NavGraph destination5 = destination4;
        while (destination5 != null && findDestination(destination5.getId()) == null) {
            NavGraph parent3 = destination5.getParent();
            if (parent3 != null) {
                ListIterator iterator$iv2 = restoredEntries.listIterator(restoredEntries.size());
                while (true) {
                    if (iterator$iv2.hasPrevious()) {
                        element$iv = iterator$iv2.previous();
                        NavBackStackEntry restoredEntry2 = (NavBackStackEntry) element$iv;
                        if (Intrinsics.areEqual(restoredEntry2.getDestination(), parent3)) {
                            break;
                        }
                    } else {
                        element$iv = null;
                        break;
                    }
                }
                NavBackStackEntry entry2 = (NavBackStackEntry) element$iv;
                if (entry2 == null) {
                    entry2 = NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, this.context, parent3, parent3.addInDefaultArgs(bundle), getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
                }
                hierarchy.addFirst(entry2);
            }
            destination5 = parent3;
        }
        if (hierarchy.isEmpty()) {
            destination = newDest;
        } else {
            destination = ((NavBackStackEntry) hierarchy.last()).getDestination();
        }
        NavDestination overlappingDestination = destination;
        while (!getBackQueue().isEmpty() && (getBackQueue().last().getDestination() instanceof NavGraph) && ((NavGraph) getBackQueue().last().getDestination()).findNode(overlappingDestination.getId(), false) == null) {
            popEntryFromBackStack$default(this, getBackQueue().last(), false, null, 6, null);
        }
        NavBackStackEntry firstEntry = getBackQueue().firstOrNull();
        if (firstEntry == null) {
            firstEntry = (NavBackStackEntry) hierarchy.firstOrNull();
        }
        if (!Intrinsics.areEqual(firstEntry == null ? null : firstEntry.getDestination(), this._graph)) {
            ListIterator iterator$iv3 = restoredEntries.listIterator(restoredEntries.size());
            while (true) {
                if (!iterator$iv3.hasPrevious()) {
                    break;
                }
                Object element$iv3 = iterator$iv3.previous();
                NavBackStackEntry restoredEntry3 = (NavBackStackEntry) element$iv3;
                NavDestination destination6 = restoredEntry3.getDestination();
                NavGraph navGraph = this._graph;
                Intrinsics.checkNotNull(navGraph);
                if (Intrinsics.areEqual(destination6, navGraph)) {
                    obj = element$iv3;
                    break;
                }
            }
            NavBackStackEntry entry3 = (NavBackStackEntry) obj;
            if (entry3 == null) {
                NavBackStackEntry.Companion companion = NavBackStackEntry.INSTANCE;
                Context context = this.context;
                NavGraph navGraph2 = this._graph;
                Intrinsics.checkNotNull(navGraph2);
                NavGraph navGraph3 = navGraph2;
                NavGraph navGraph4 = this._graph;
                Intrinsics.checkNotNull(navGraph4);
                entry3 = NavBackStackEntry.Companion.create$default(companion, context, navGraph3, navGraph4.addInDefaultArgs(bundle), getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
            }
            hierarchy.addFirst(entry3);
        }
        Iterable $this$forEach$iv = hierarchy;
        for (Object element$iv4 : $this$forEach$iv) {
            NavBackStackEntry entry4 = (NavBackStackEntry) element$iv4;
            Navigator navigator = this._navigatorProvider.getNavigator(entry4.getDestination().getNavigatorName());
            NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(navigator);
            if (navControllerNavigatorState == null) {
                throw new IllegalStateException(("NavigatorBackStack for " + node.getNavigatorName() + " should already be created").toString());
            }
            NavControllerNavigatorState navigatorBackStack = navControllerNavigatorState;
            navigatorBackStack.addInternal(entry4);
        }
        getBackQueue().addAll(hierarchy);
        getBackQueue().add(navBackStackEntry);
        Iterable $this$forEach$iv2 = CollectionsKt.plus((Collection<? extends NavBackStackEntry>) hierarchy, navBackStackEntry);
        for (Object element$iv5 : $this$forEach$iv2) {
            NavBackStackEntry it = (NavBackStackEntry) element$iv5;
            NavGraph parent4 = it.getDestination().getParent();
            if (parent4 != null) {
                linkChildToParent(it, getBackStackEntry(parent4.getId()));
            }
        }
    }

    public void navigate(NavDirections directions) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        navigate(directions.getActionId(), directions.getArguments(), (NavOptions) null);
    }

    public void navigate(NavDirections directions, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        navigate(directions.getActionId(), directions.getArguments(), navOptions);
    }

    public void navigate(NavDirections directions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        Intrinsics.checkNotNullParameter(navigatorExtras, "navigatorExtras");
        navigate(directions.getActionId(), directions.getArguments(), (NavOptions) null, navigatorExtras);
    }

    public final void navigate(String route, Function1<? super NavOptionsBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(builder, "builder");
        navigate$default(this, route, NavOptionsBuilderKt.navOptions(builder), null, 4, null);
    }

    public static /* synthetic */ void navigate$default(NavController navController, String str, NavOptions navOptions, Navigator.Extras extras, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigate");
        }
        if ((i & 2) != 0) {
            navOptions = null;
        }
        if ((i & 4) != 0) {
            extras = null;
        }
        navController.navigate(str, navOptions, extras);
    }

    public final void navigate(String route, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(route, "route");
        NavDeepLinkRequest.Builder.Companion companion = NavDeepLinkRequest.Builder.INSTANCE;
        String $this$toUri$iv = NavDestination.INSTANCE.createRoute(route);
        Uri parse = Uri.parse($this$toUri$iv);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
        navigate(companion.fromUri(parse).build(), navOptions, navigatorExtras);
    }

    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    public Bundle saveState() {
        Bundle b = null;
        ArrayList navigatorNames = new ArrayList();
        Bundle navigatorState = new Bundle();
        for (Map.Entry<String, Navigator<? extends NavDestination>> entry : this._navigatorProvider.getNavigators().entrySet()) {
            String name = entry.getKey();
            Navigator value = entry.getValue();
            Bundle savedState = value.onSaveState();
            if (savedState != null) {
                navigatorNames.add(name);
                navigatorState.putBundle(name, savedState);
            }
        }
        if (!navigatorNames.isEmpty()) {
            b = new Bundle();
            navigatorState.putStringArrayList(KEY_NAVIGATOR_STATE_NAMES, navigatorNames);
            b.putBundle(KEY_NAVIGATOR_STATE, navigatorState);
        }
        if (!getBackQueue().isEmpty()) {
            if (b == null) {
                b = new Bundle();
            }
            Parcelable[] backStack = new Parcelable[getBackQueue().size()];
            int index = 0;
            Iterator it = getBackQueue().iterator();
            while (it.hasNext()) {
                NavBackStackEntry backStackEntry = (NavBackStackEntry) it.next();
                backStack[index] = new NavBackStackEntryState(backStackEntry);
                index++;
            }
            b.putParcelableArray(KEY_BACK_STACK, backStack);
        }
        if (!this.backStackMap.isEmpty()) {
            if (b == null) {
                b = new Bundle();
            }
            int[] backStackDestIds = new int[this.backStackMap.size()];
            ArrayList backStackIds = new ArrayList();
            int index2 = 0;
            for (Map.Entry<Integer, String> entry2 : this.backStackMap.entrySet()) {
                int destId = entry2.getKey().intValue();
                String id = entry2.getValue();
                backStackDestIds[index2] = destId;
                backStackIds.add(id);
                index2++;
            }
            b.putIntArray(KEY_BACK_STACK_DEST_IDS, backStackDestIds);
            b.putStringArrayList(KEY_BACK_STACK_IDS, backStackIds);
        }
        if (!this.backStackStates.isEmpty()) {
            if (b == null) {
                b = new Bundle();
            }
            ArrayList backStackStateIds = new ArrayList();
            for (Map.Entry<String, ArrayDeque<NavBackStackEntryState>> entry3 : this.backStackStates.entrySet()) {
                String id2 = entry3.getKey();
                ArrayDeque backStackStates = entry3.getValue();
                backStackStateIds.add(id2);
                Parcelable[] states = new Parcelable[backStackStates.size()];
                ArrayDeque $this$forEachIndexed$iv = backStackStates;
                int stateIndex = 0;
                for (Object item$iv : $this$forEachIndexed$iv) {
                    int index$iv = stateIndex + 1;
                    if (stateIndex < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    NavBackStackEntryState backStackState = (NavBackStackEntryState) item$iv;
                    states[stateIndex] = backStackState;
                    stateIndex = index$iv;
                }
                b.putParcelableArray(Intrinsics.stringPlus(KEY_BACK_STACK_STATES_PREFIX, id2), states);
            }
            b.putStringArrayList(KEY_BACK_STACK_STATES_IDS, backStackStateIds);
        }
        if (this.deepLinkHandled) {
            if (b == null) {
                b = new Bundle();
            }
            b.putBoolean(KEY_DEEP_LINK_HANDLED, this.deepLinkHandled);
        }
        return b;
    }

    public void restoreState(Bundle navState) {
        int[] backStackDestIds;
        if (navState == null) {
            return;
        }
        navState.setClassLoader(this.context.getClassLoader());
        this.navigatorStateToRestore = navState.getBundle(KEY_NAVIGATOR_STATE);
        this.backStackToRestore = navState.getParcelableArray(KEY_BACK_STACK);
        this.backStackStates.clear();
        int[] backStackDestIds2 = navState.getIntArray(KEY_BACK_STACK_DEST_IDS);
        ArrayList backStackIds = navState.getStringArrayList(KEY_BACK_STACK_IDS);
        if (backStackDestIds2 != null && backStackIds != null) {
            int index = 0;
            int i = 0;
            int length = backStackDestIds2.length;
            while (i < length) {
                int item$iv = backStackDestIds2[i];
                i++;
                int index$iv = index + 1;
                this.backStackMap.put(Integer.valueOf(item$iv), backStackIds.get(index));
                index = index$iv;
            }
        }
        Iterable backStackStateIds = navState.getStringArrayList(KEY_BACK_STACK_STATES_IDS);
        if (backStackStateIds != null) {
            Iterable $this$forEach$iv = backStackStateIds;
            for (Object element$iv : $this$forEach$iv) {
                String id = (String) element$iv;
                Parcelable[] backStackState = navState.getParcelableArray(Intrinsics.stringPlus(KEY_BACK_STACK_STATES_PREFIX, id));
                if (backStackState == null) {
                    backStackDestIds = backStackDestIds2;
                } else {
                    Map<String, ArrayDeque<NavBackStackEntryState>> map = this.backStackStates;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    ArrayDeque $this$restoreState_u24lambda_u2d46_u24lambda_u2d45 = new ArrayDeque(backStackState.length);
                    Iterator it = ArrayIteratorKt.iterator(backStackState);
                    while (it.hasNext()) {
                        Parcelable parcelable = (Parcelable) it.next();
                        if (parcelable == null) {
                            throw new NullPointerException("null cannot be cast to non-null type androidx.navigation.NavBackStackEntryState");
                        }
                        $this$restoreState_u24lambda_u2d46_u24lambda_u2d45.add((NavBackStackEntryState) parcelable);
                        backStackDestIds2 = backStackDestIds2;
                    }
                    backStackDestIds = backStackDestIds2;
                    Unit unit = Unit.INSTANCE;
                    map.put(id, $this$restoreState_u24lambda_u2d46_u24lambda_u2d45);
                }
                backStackDestIds2 = backStackDestIds;
            }
        }
        this.deepLinkHandled = navState.getBoolean(KEY_DEEP_LINK_HANDLED);
    }

    public void setLifecycleOwner(LifecycleOwner owner) {
        Lifecycle lifecycle;
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (Intrinsics.areEqual(owner, this.lifecycleOwner)) {
            return;
        }
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.removeObserver(this.lifecycleObserver);
        }
        this.lifecycleOwner = owner;
        owner.getLifecycle().addObserver(this.lifecycleObserver);
    }

    public void setOnBackPressedDispatcher(OnBackPressedDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        if (Intrinsics.areEqual(dispatcher, this.onBackPressedDispatcher)) {
            return;
        }
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null) {
            throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()".toString());
        }
        this.onBackPressedCallback.remove();
        this.onBackPressedDispatcher = dispatcher;
        dispatcher.addCallback(lifecycleOwner, this.onBackPressedCallback);
        Lifecycle $this$setOnBackPressedDispatcher_u24lambda_u2d48 = lifecycleOwner.getLifecycle();
        $this$setOnBackPressedDispatcher_u24lambda_u2d48.removeObserver(this.lifecycleObserver);
        $this$setOnBackPressedDispatcher_u24lambda_u2d48.addObserver(this.lifecycleObserver);
    }

    public void enableOnBackPressed(boolean enabled) {
        this.enableOnBackPressedCallback = enabled;
        updateOnBackPressedCallbackEnabled();
    }

    private final void updateOnBackPressedCallbackEnabled() {
        this.onBackPressedCallback.setEnabled(this.enableOnBackPressedCallback && getDestinationCountOnBackStack() > 1);
    }

    public void setViewModelStore(ViewModelStore viewModelStore) {
        Intrinsics.checkNotNullParameter(viewModelStore, "viewModelStore");
        if (Intrinsics.areEqual(this.viewModel, NavControllerViewModel.INSTANCE.getInstance(viewModelStore))) {
            return;
        }
        if (!getBackQueue().isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call".toString());
        }
        this.viewModel = NavControllerViewModel.INSTANCE.getInstance(viewModelStore);
    }

    public ViewModelStoreOwner getViewModelStoreOwner(int navGraphId) {
        if (this.viewModel == null) {
            throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().".toString());
        }
        NavBackStackEntry lastFromBackStack = getBackStackEntry(navGraphId);
        if (!(lastFromBackStack.getDestination() instanceof NavGraph)) {
            throw new IllegalArgumentException(("No NavGraph with ID " + navGraphId + " is on the NavController's back stack").toString());
        }
        return lastFromBackStack;
    }

    public NavBackStackEntry getBackStackEntry(int destinationId) {
        Object element$iv;
        List $this$lastOrNull$iv = getBackQueue();
        ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
        while (true) {
            if (iterator$iv.hasPrevious()) {
                element$iv = iterator$iv.previous();
                NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                if (entry.getDestination().getId() == destinationId) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        NavBackStackEntry lastFromBackStack = (NavBackStackEntry) element$iv;
        if (lastFromBackStack == null) {
            throw new IllegalArgumentException(("No destination with ID " + destinationId + " is on the NavController's back stack. The current destination is " + getCurrentDestination()).toString());
        }
        return lastFromBackStack;
    }

    public final NavBackStackEntry getBackStackEntry(String route) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(route, "route");
        List $this$lastOrNull$iv = getBackQueue();
        ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
        while (true) {
            if (iterator$iv.hasPrevious()) {
                element$iv = iterator$iv.previous();
                NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                if (Intrinsics.areEqual(entry.getDestination().getRoute(), route)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        NavBackStackEntry lastFromBackStack = (NavBackStackEntry) element$iv;
        if (lastFromBackStack == null) {
            throw new IllegalArgumentException(("No destination with route " + route + " is on the NavController's back stack. The current destination is " + getCurrentDestination()).toString());
        }
        return lastFromBackStack;
    }

    public NavBackStackEntry getCurrentBackStackEntry() {
        return getBackQueue().lastOrNull();
    }

    public final Flow<NavBackStackEntry> getCurrentBackStackEntryFlow() {
        return this.currentBackStackEntryFlow;
    }

    public NavBackStackEntry getPreviousBackStackEntry() {
        Object element$iv;
        Iterator iterator = CollectionsKt.reversed(getBackQueue()).iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }
        Sequence $this$firstOrNull$iv = SequencesKt.asSequence(iterator);
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                if (!(entry.getDestination() instanceof NavGraph)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        return (NavBackStackEntry) element$iv;
    }

    /* compiled from: NavController.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/navigation/NavController$Companion;", "", "()V", "KEY_BACK_STACK", "", "KEY_BACK_STACK_DEST_IDS", "KEY_BACK_STACK_IDS", "KEY_BACK_STACK_STATES_IDS", "KEY_BACK_STACK_STATES_PREFIX", "KEY_DEEP_LINK_ARGS", "KEY_DEEP_LINK_EXTRAS", "getKEY_DEEP_LINK_EXTRAS$annotations", "KEY_DEEP_LINK_HANDLED", "KEY_DEEP_LINK_IDS", "KEY_DEEP_LINK_INTENT", "KEY_NAVIGATOR_STATE", "KEY_NAVIGATOR_STATE_NAMES", "TAG", "deepLinkSaveState", "", "enableDeepLinkSaveState", "", "saveState", "navigation-runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getKEY_DEEP_LINK_EXTRAS$annotations() {
        }

        private Companion() {
        }

        @JvmStatic
        @NavDeepLinkSaveStateControl
        public final void enableDeepLinkSaveState(boolean saveState) {
            NavController.deepLinkSaveState = saveState;
        }
    }
}
