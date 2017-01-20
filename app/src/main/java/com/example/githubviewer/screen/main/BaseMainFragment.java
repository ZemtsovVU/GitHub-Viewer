package com.example.githubviewer.screen.main;

import android.content.Context;

import com.example.githubviewer.screen.base.BaseFragment;
import com.example.githubviewer.screen.exception.NotImplementedException;

public abstract class BaseMainFragment extends BaseFragment {
    private SecondClickRegistrator secondClickRegistrator;
    private SecondClickReceiver secondClickReceiver;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            secondClickRegistrator = (SecondClickRegistrator) getActivity();
        } catch (ClassCastException e) {
            throw new NotImplementedException(
                    SecondClickRegistrator.class.getSimpleName(),
                    this.getClass().getSimpleName()
            );
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        secondClickRegistrator.registerSecondClickReceiver(secondClickReceiver);
    }

    @Override
    public void onStop() {
        super.onStop();
        secondClickRegistrator.unregisterSecondClickReceiver(secondClickReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        secondClickRegistrator = null;
    }

    protected void registerSecondClickReceiver(SecondClickReceiver secondClickReceiver) {
        this.secondClickReceiver = secondClickReceiver;
    }
}
