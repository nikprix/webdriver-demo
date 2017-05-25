package com.mykolabs.apple.pageobjects;

/**
 *
 * @author nikprix
 */
public interface SignupPage {

    String SIGNUP_PAGE_TITLE = "Apple - Email Subscriptions";

    public void subsribeButtonClick();

    public void preferenceChckBxClick();

    public void enterEmail(String email);

    public void confirmEmail(String email);

    public void submitEmail(String email);

    public void submitDifferentEmails(String email1, String email2);

    public boolean isEmailAccepted(String email);

    public int extractedEmailValueLength();
    
    public String sibscribeBtnClassValue();

}
