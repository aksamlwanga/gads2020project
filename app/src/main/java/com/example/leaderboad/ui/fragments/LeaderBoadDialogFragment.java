package com.example.leaderboad.ui.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.leaderboad.R;
import com.example.leaderboad.activities.SubmitActivity;

public class LeaderBoadDialogFragment extends DialogFragment {
    public static LeaderBoadDialogFragment newInstance() {

        return new LeaderBoadDialogFragment();

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // retrieve display dimensions
        final Activity activity =getActivity();
        Rect displayRectangle = new Rect();
        final Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        View v = null;
        if(getArguments() != null) {
            if (getArguments().getString("confirm") == "confirm") {
                v = inflater.inflate(R.layout.success, container, false);
                v.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
                v.setMinimumHeight((int) (displayRectangle.height() * 0.4f));
            } else if  (getArguments().getString("fail") == "fail") {
                v = inflater.inflate(R.layout.fail, container, false);
                v.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
                v.setMinimumHeight((int) (displayRectangle.height() * 0.4f));
            }
        } else {
            v = inflater.inflate(R.layout.custom_action, container, false);
            v.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
            v.setMinimumHeight((int) (displayRectangle.height() * 0.55f));

        }

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.rounded_dialog);
        getDialog().getWindow().setDimAmount(0.1f);




        return v;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        setMainVisibility();
    }

    private void setMainVisibility() {
        SubmitActivity.setMainVisibility();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        setMainVisibility();
    }
}


