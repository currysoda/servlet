package com.hello.servlet.web.frontcontroller.v5;

import com.hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

	boolean supports(Object handler);

	ModelView handle(HttpServletRequest req, HttpServletResponse res, Object Handler) throws IOException, ServletException;
}
