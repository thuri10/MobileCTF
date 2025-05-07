package com.r0ysue.rctf.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.r0ysue.rctf.R;

/* loaded from: classes5.dex */
public final class ActivityItem01Binding implements ViewBinding {
    public final LinearLayout LayoutButton;
    public final ImageView LogoImage;
    public final LinearLayout PassWordLayout;
    public final TextView TitleText;
    public final LinearLayout UserNameLayout;
    public final Button buttonItem01;
    public final EditText editText;
    private final ConstraintLayout rootView;
    public final TextView textView;

    private ActivityItem01Binding(ConstraintLayout rootView, LinearLayout LayoutButton, ImageView LogoImage, LinearLayout PassWordLayout, TextView TitleText, LinearLayout UserNameLayout, Button buttonItem01, EditText editText, TextView textView) {
        this.rootView = rootView;
        this.LayoutButton = LayoutButton;
        this.LogoImage = LogoImage;
        this.PassWordLayout = PassWordLayout;
        this.TitleText = TitleText;
        this.UserNameLayout = UserNameLayout;
        this.buttonItem01 = buttonItem01;
        this.editText = editText;
        this.textView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityItem01Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityItem01Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_item01, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityItem01Binding bind(View rootView) {
        int id = R.id.LayoutButton;
        LinearLayout LayoutButton = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.LayoutButton);
        if (LayoutButton != null) {
            id = R.id.LogoImage;
            ImageView LogoImage = (ImageView) ViewBindings.findChildViewById(rootView, R.id.LogoImage);
            if (LogoImage != null) {
                id = R.id.PassWordLayout;
                LinearLayout PassWordLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.PassWordLayout);
                if (PassWordLayout != null) {
                    id = R.id.TitleText;
                    TextView TitleText = (TextView) ViewBindings.findChildViewById(rootView, R.id.TitleText);
                    if (TitleText != null) {
                        id = R.id.UserNameLayout;
                        LinearLayout UserNameLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.UserNameLayout);
                        if (UserNameLayout != null) {
                            id = R.id.button_item_01;
                            Button buttonItem01 = (Button) ViewBindings.findChildViewById(rootView, R.id.button_item_01);
                            if (buttonItem01 != null) {
                                id = R.id.editText;
                                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.editText);
                                if (editText != null) {
                                    id = R.id.textView;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                                    if (textView != null) {
                                        return new ActivityItem01Binding((ConstraintLayout) rootView, LayoutButton, LogoImage, PassWordLayout, TitleText, UserNameLayout, buttonItem01, editText, textView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
