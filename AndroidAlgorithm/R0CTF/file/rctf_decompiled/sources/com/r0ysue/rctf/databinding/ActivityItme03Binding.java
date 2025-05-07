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
public final class ActivityItme03Binding implements ViewBinding {
    public final LinearLayout LayoutButton;
    public final ImageView LogoImage;
    public final LinearLayout PassWordLayout;
    public final TextView TitleText;
    public final LinearLayout UserNameLayout;
    public final Button buttonItem03;
    public final EditText editText03;
    private final ConstraintLayout rootView;
    public final TextView textView03;

    private ActivityItme03Binding(ConstraintLayout rootView, LinearLayout LayoutButton, ImageView LogoImage, LinearLayout PassWordLayout, TextView TitleText, LinearLayout UserNameLayout, Button buttonItem03, EditText editText03, TextView textView03) {
        this.rootView = rootView;
        this.LayoutButton = LayoutButton;
        this.LogoImage = LogoImage;
        this.PassWordLayout = PassWordLayout;
        this.TitleText = TitleText;
        this.UserNameLayout = UserNameLayout;
        this.buttonItem03 = buttonItem03;
        this.editText03 = editText03;
        this.textView03 = textView03;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityItme03Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityItme03Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_itme03, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityItme03Binding bind(View rootView) {
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
                            id = R.id.button_item_03;
                            Button buttonItem03 = (Button) ViewBindings.findChildViewById(rootView, R.id.button_item_03);
                            if (buttonItem03 != null) {
                                id = R.id.editText_03;
                                EditText editText03 = (EditText) ViewBindings.findChildViewById(rootView, R.id.editText_03);
                                if (editText03 != null) {
                                    id = R.id.textView_03;
                                    TextView textView03 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView_03);
                                    if (textView03 != null) {
                                        return new ActivityItme03Binding((ConstraintLayout) rootView, LayoutButton, LogoImage, PassWordLayout, TitleText, UserNameLayout, buttonItem03, editText03, textView03);
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
