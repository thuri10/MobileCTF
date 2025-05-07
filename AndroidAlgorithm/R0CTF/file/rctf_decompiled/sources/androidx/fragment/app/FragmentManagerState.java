package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: androidx.fragment.app.FragmentManagerState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel in) {
            return new FragmentManagerState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int size) {
            return new FragmentManagerState[size];
        }
    };
    ArrayList<String> mActive;
    ArrayList<String> mAdded;
    BackStackRecordState[] mBackStack;
    int mBackStackIndex;
    ArrayList<String> mBackStackStateKeys;
    ArrayList<BackStackState> mBackStackStates;
    ArrayList<FragmentManager.LaunchedFragmentInfo> mLaunchedFragments;
    String mPrimaryNavActiveWho;
    ArrayList<String> mResultKeys;
    ArrayList<Bundle> mResults;
    ArrayList<FragmentState> mSavedState;

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList<>();
        this.mBackStackStates = new ArrayList<>();
        this.mResultKeys = new ArrayList<>();
        this.mResults = new ArrayList<>();
    }

    public FragmentManagerState(Parcel in) {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList<>();
        this.mBackStackStates = new ArrayList<>();
        this.mResultKeys = new ArrayList<>();
        this.mResults = new ArrayList<>();
        this.mSavedState = in.createTypedArrayList(FragmentState.CREATOR);
        this.mActive = in.createStringArrayList();
        this.mAdded = in.createStringArrayList();
        this.mBackStack = (BackStackRecordState[]) in.createTypedArray(BackStackRecordState.CREATOR);
        this.mBackStackIndex = in.readInt();
        this.mPrimaryNavActiveWho = in.readString();
        this.mBackStackStateKeys = in.createStringArrayList();
        this.mBackStackStates = in.createTypedArrayList(BackStackState.CREATOR);
        this.mResultKeys = in.createStringArrayList();
        this.mResults = in.createTypedArrayList(Bundle.CREATOR);
        this.mLaunchedFragments = in.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mSavedState);
        dest.writeStringList(this.mActive);
        dest.writeStringList(this.mAdded);
        dest.writeTypedArray(this.mBackStack, flags);
        dest.writeInt(this.mBackStackIndex);
        dest.writeString(this.mPrimaryNavActiveWho);
        dest.writeStringList(this.mBackStackStateKeys);
        dest.writeTypedList(this.mBackStackStates);
        dest.writeStringList(this.mResultKeys);
        dest.writeTypedList(this.mResults);
        dest.writeTypedList(this.mLaunchedFragments);
    }
}
