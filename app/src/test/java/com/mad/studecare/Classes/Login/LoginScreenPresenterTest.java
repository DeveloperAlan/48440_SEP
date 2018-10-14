package com.mad.studecare.Classes.Login;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginScreenPresenterTest {
    private String emailTest = "123@student.uts.edu.au";
    private String passwordTest = "123456";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void validEmail() {
        assertTrue(LoginScreenPresenter.validEmail(emailTest));
    }

    @Test
    public void validPassword() {
        assertTrue(LoginScreenPresenter.validPassword(passwordTest));
    }
}