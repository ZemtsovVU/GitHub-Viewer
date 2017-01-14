package com.example.githubviewer.screen.main;

/**
 * Allows register and unregister SecondClickReceiver items.
 */
public interface SecondClickRegistrator {

    void registerSecondClickReceiver(SecondClickReceiver secondClickReceiver);

    void unregisterSecondClickReceiver(SecondClickReceiver secondClickReceiver);
}
