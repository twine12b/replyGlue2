/**
 * @Author Richard Renaud
 * [notes]
 * - No length limitation are stated for username
 */
package com.replyglue.demo.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidationTest {
    private UserValidationService userValidationService;

    @Before
    public void setUp() {
        userValidationService = new UserValidationService();
    }

    @Test
    public void  test_usernameIsValid_withBadInput_shouldReturn_boolean(){
        //Given
        assertFalse(userValidationService.usernameIsValid(null));
        assertFalse(userValidationService.usernameIsValid(""));
        assertFalse(userValidationService.usernameIsValid("-"));
        assertFalse(userValidationService.usernameIsValid("9-%&"));
        assertFalse(userValidationService.usernameIsValid("abcD123*"));
    }

    @Test
    public void test_usernameIsValid_withGoodInput_shouldReturn_boolean(){
        assertTrue(userValidationService.usernameIsValid("Pdog91"));
        assertTrue(userValidationService.usernameIsValid("REJojh546576LKNJV6C54S3SRHJyg54s5bb65V7b567g67"));
        assertTrue(userValidationService.usernameIsValid("1"));
    }

    @Test
    public void test_passwordIsValid_shouldReturn_boolean(){
        assertFalse(userValidationService.passwordIsValid(""));
        assertFalse(userValidationService.passwordIsValid("abcdegpkjy"));
        assertFalse(userValidationService.passwordIsValid("abcde1gpkjy"));
        assertTrue(userValidationService.passwordIsValid("Abcde1gpkjy"));
        assertFalse(userValidationService.passwordIsValid("1235pkjy"));
    }

    @Test
    public void test_emailIsValid_shouldReturn_boolean(){
        assertFalse(userValidationService.emailIsValid(""));
        assertFalse(userValidationService.emailIsValid(null));
        assertTrue(userValidationService.emailIsValid("a@b.com"));
        assertTrue(userValidationService.emailIsValid("richard.renaud@me.com"));
    }

    @Test
    public void test_creditcardIsValid_shouldReturn_boolean() {
        assertFalse(userValidationService.creditcardIsValid(1234l));
        assertTrue(userValidationService.creditcardIsValid(1234567890123456l));
    }

    @Test
    public void test_dobIsValid_shouldReturn_boolean() {
        assertFalse(userValidationService.dobIsValid(""));
        assertFalse(userValidationService.dobIsValid("1972-35-35"));
        assertFalse(userValidationService.dobIsValid(null));
        assertTrue(userValidationService.dobIsValid("1972-09-22"));
        assertFalse(userValidationService.dobIsValid("1972-22-09"));
    }
}
