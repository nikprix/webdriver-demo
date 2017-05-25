package com.mykolabs.apple.resources;

import com.mykolabs.apple.pageobjects.InstructionsPageImpl;
import com.mykolabs.apple.pageobjects.SignupPageImpl;


/**
 * Used for getting page object instances during test phase.
 *
 * @author nikprix
 */
public abstract class AllPageObjectData {

    public InstructionsPageImpl instructionsPage;
    public SignupPageImpl signupPage;

    /**
     * Returns InstructionsPageImpl instance.
     *
     * @return
     */
    public InstructionsPageImpl getInstructionsPage() {
        return (InstructionsPageImpl) new InstructionsPageImpl().get();
    }

    /**
     * Returns SignupPageImpl instance.
     *
     * @return
     */
    public SignupPageImpl getSignupPage() {
        return (SignupPageImpl) new SignupPageImpl().get();
    }

}
