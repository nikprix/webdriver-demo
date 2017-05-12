package com.mykolabs.apple.pageobjects;

/**
 *
 * @author nikprix
 */
public interface SignupPage {
    
    String SIGNUP_PAGE_TITLE = "Apple - Email Subscriptions";
    
    public void subsribeButtonClick();
    public void enterEmail(String email);
    public void confirmEmail(String email);
    public void submitEmail(String email);
    public boolean isEmailAccepted(String email);
    public int extractedEmailValueLength();
    
}
