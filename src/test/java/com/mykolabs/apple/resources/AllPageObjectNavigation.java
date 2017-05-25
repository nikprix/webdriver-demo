package com.mykolabs.apple.resources;

/**
 * Provides navigation methods for application under test.
 *
 * @author nikprix
 */
public class AllPageObjectNavigation extends AllPageObjectData {

    /**
     * Navigates to Signup page from Instructions page.
     */
    public void navigateToSignUp() {

        // Getting Instructions page will also verifies that this page loaded, via isLoaded() 
        instructionsPage = getInstructionsPage();
        instructionsPage.loadEmailSubscriptions();
        
        // Getting Signup page will also verifies that this page loaded, via isLoaded()
        signupPage = getSignupPage();
    }

}
