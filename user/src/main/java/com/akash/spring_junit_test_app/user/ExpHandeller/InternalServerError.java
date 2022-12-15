package com.akash.spring_junit_test_app.user.ExpHandeller;

public class InternalServerError extends Exception {
    public InternalServerError(String str) {
        // calling the constructor of parent Exception
        super(str);
    }
}
