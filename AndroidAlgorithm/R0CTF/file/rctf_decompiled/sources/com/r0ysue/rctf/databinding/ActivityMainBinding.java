package com.r0ysue.rctf.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.r0ysue.rctf.R;

/* loaded from: classes5.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final Button btnItem01;
    public final Button btnItem02;
    public final Button btnItem03;
    private final ConstraintLayout rootView;

    private ActivityMainBinding(ConstraintLayout rootView, Button btnItem01, Button btnItem02, Button btnItem03) {
        this.rootView = rootView;
        this.btnItem01 = btnItem01;
        this.btnItem02 = btnItem02;
        this.btnItem03 = btnItem03;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView) {
        int id = R.id.btn_item_01;
        Button btnItem01 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_item_01);
        if (btnItem01 != null) {
            id = R.id.btn_item_02;
            Button btnItem02 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_item_02);
            if (btnItem02 != null) {
                id = R.id.btn_item_03;
                Button btnItem03 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_item_03);
                if (btnItem03 != null) {
                    return new ActivityMainBinding((ConstraintLayout) rootView, btnItem01, btnItem02, btnItem03);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
