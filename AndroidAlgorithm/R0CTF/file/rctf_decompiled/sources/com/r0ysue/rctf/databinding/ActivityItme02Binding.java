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
public final class ActivityItme02Binding implements ViewBinding {
    public final LinearLayout LayoutButton;
    public final ImageView LogoImage;
    public final LinearLayout PassWordLayout;
    public final TextView TitleText;
    public final LinearLayout UserNameLayout;
    public final Button buttonItem02;
    public final EditText editText02;
    private final ConstraintLayout rootView;
    public final TextView textView02;

    private ActivityItme02Binding(ConstraintLayout rootView, LinearLayout LayoutButton, ImageView LogoImage, LinearLayout PassWordLayout, TextView TitleText, LinearLayout UserNameLayout, Button buttonItem02, EditText editText02, TextView textView02) {
        this.rootView = rootView;
        this.LayoutButton = LayoutButton;
        this.LogoImage = LogoImage;
        this.PassWordLayout = PassWordLayout;
        this.TitleText = TitleText;
        this.UserNameLayout = UserNameLayout;
        this.buttonItem02 = buttonItem02;
        this.editText02 = editText02;
        this.textView02 = textView02;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityItme02Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityItme02Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_itme02, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityItme02Binding bind(View rootView) {
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
                            id = R.id.button_item_02;
                            Button buttonItem02 = (Button) ViewBindings.findChildViewById(rootView, R.id.button_item_02);
                            if (buttonItem02 != null) {
                                id = R.id.editText_02;
                                EditText editText02 = (EditText) ViewBindings.findChildViewById(rootView, R.id.editText_02);
                                if (editText02 != null) {
                                    id = R.id.textView_02;
                                    TextView textView02 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView_02);
                                    if (textView02 != null) {
                                        return new ActivityItme02Binding((ConstraintLayout) rootView, LayoutButton, LogoImage, PassWordLayout, TitleText, UserNameLayout, buttonItem02, editText02, textView02);
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
