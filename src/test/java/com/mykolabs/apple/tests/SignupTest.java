package com.mykolabs.apple.tests;

import static com.mykolabs.apple.pageobjects.SignupPage.SIGNUP_PAGE_TITLE;
import com.mykolabs.apple.resources.BaseTest;
import com.mykolabs.apple.util.TimeManager;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Provides test methods for SignUp module.
 *
 * @author mykola.prykhodko
 */
@Listeners(com.mykolabs.apple.resources.Listener.class)
public class SignupTest extends BaseTest {

    @Test(enabled = false, groups = {"graphical"})
    public void testSignupPageTitle_TS01() {
        Assert.assertEquals(signupPage.driver.getTitle(), SIGNUP_PAGE_TITLE);
    }
    
    @Test(enabled = false, dataProvider = "TestValidEmails", groups = {"positive", "priority"})
    public void testValidEmails_TS02(String validEmail) {
        signupPage.submitEmail(validEmail);

        Assert.assertTrue(signupPage.isEmailAccepted(validEmail));
    }

    @Test(enabled = false, dataProvider = "TestInvalidEmails", groups = {"negative", "priority"})
    public void testInvalidEmails_TS03(String invalidEmail) {
        signupPage.submitEmail(invalidEmail);

        Assert.assertFalse(signupPage.isEmailAccepted(invalidEmail));
    }

    @Test(enabled = false, dataProvider = "TestInvalidBoundaryEmails", groups = {"negative", "priority"})
    public void testInvalidBoundaryEmails_TS04(String invalidBoundaryEmail) {
        signupPage = getSignupPage();
        signupPage.submitEmail(invalidBoundaryEmail);

        Assert.assertFalse(signupPage.isEmailAccepted(invalidBoundaryEmail));
    }

    @Test(enabled = true, groups = {"negative", "priority"})
    @Parameters({"email1", "email2"})
    public void testEmailsMismatch_TS05(String email1, String email2) {
        signupPage.enterEmail(email1);
        signupPage.confirmEmail(email2);

        Assert.assertNotEquals(signupPage.sibscribeBtnClassValue(), "btn bigblue");
        Assert.assertEquals(signupPage.sibscribeBtnClassValue(), "btn deactive");
    }


    //////////////////////////////////////////
    //////////// Data Providers //////////////
    //////////////////////////////////////////
    
    @DataProvider(name = "TestInvalidEmails")
    public Object[][] createInvalidEmailData() {
        return new Object[][]{
            {"noatnodomainemail"},
            {"@mykolabs.com"},
            {"email.mykolabs.com"},
            {"mail@mykolabs@mykolabs.com"},
            {".email@mykolabs.com"},
            {"email.@mykolabs.com"},
            {"email..email@mykolabs.com"},
            {"email@mykolabs"},
            {"email@-mykolabs.com"},
            {"email@mykolabs..com"},
            {"email@111.222.333.44444"},
            {"!@#$%^@<>+{}&*.com"},
            {"First N <email@mykolabs.com>"},
            {"email@mykolabs.com (First N)"},
            {"тестимэйл@mykolabs.com"}
        };
    }

    @DataProvider(name = "TestInvalidBoundaryEmails")
    public Object[][] createInvalidBoundaryEmailData() {
        return new Object[][]{
            {"a@b.c"},
            {"d2ertrtrffwvcnoeni429eifnldnfcpe90jdpjailskdjqpejwdlndoisjdoqvn2pejfoq0p9efiosndkpqjifl3rf24ro8r3oheiakf2490eoifleoihwlert3456789RzmdrUM4jRVuuxFjpkih8ft4Id93le94JMx5o5IvlqDVo90hAafkT1zLJK@HZV2dkDBr7gT08onUIPNzGHqBTOCyFdsfwiehodlnqwqefq6kkjeB2Kvwrfdfdv1.cn"}
        };
    }

    @DataProvider(name = "TestValidEmails")
    public Object[][] createValidEmailData() {
        String timestampNow = TimeManager.timestampNow();
        return new Object[][]{
            {timestampNow + "email@mykolabs.com"},
            {timestampNow + "first.last@mykolabs.com"},
            {timestampNow + "email@subdomain.mykolabs.com"},
            {timestampNow + "first+last@mykolabs.com"},
            {timestampNow + "1234567890@mykolabs.com"},
            {timestampNow + "email@mykolabs-one.com"},
            {timestampNow + "first__last@mykolabs.com"},
            {timestampNow + "email@mykolabs.co.uk"},
            {timestampNow + "first-last@mykolabs.com"},
            {timestampNow + "\"email\"@mykolabs.com"},
            {timestampNow + "email@172.217.1.99"},
            {timestampNow + "email@[172.217.1.99]"}
        };
    }

}
