package com.hello.servlet.web.servlet;

import com.hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

	private final MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");

		PrintWriter writer = res.getWriter();
		writer.write("""
				<!DOCTYPE html>
				<html>
				<head>
				 <meta charset="UTF-8">
				 <title>Title</title>
				</head>
				<body>
				<form action="/servlet/members/save" method="post">
				 username: <input type="text" name="username" />
				 age: <input type="text" name="age" />
				 <button type="submit">전송</button>
				</form>
				</body>
				</html>
				""");
	}
}
