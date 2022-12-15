package com.akash.spring_junit_test_app.user.ExpHandeller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandeller {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NullPointerException.class)
    public String ShowError(Model model, HttpSession session) {
        model.addAttribute("msg", "No Data found");
        session.setAttribute("msg", "No Data Found !........");
        return "error";
    }
}
