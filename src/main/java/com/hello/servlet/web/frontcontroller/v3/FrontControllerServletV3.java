package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

	private final Map<String, ControllerV3> controllerMap = new HashMap<>();

	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("FrontControllerServletV1.service");

		// URI 판단
		String requestURI = req.getRequestURI();

		ControllerV3 controller = controllerMap.get(requestURI);
		if(controller == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// paramMap

		Map<String, String> paramMap = createParamMap(req);
		ModelView mv = controller.process(paramMap);

		String viewName = mv.getViewName(); // 논리이름 new-form
		MyView view = viewResolver(viewName);

		view.render(mv.getModel(), req, res);
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}


	private Map<String, String> createParamMap(HttpServletRequest req) {
		Map<String, String> paramMap = new HashMap<>();
		req.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
		return paramMap;
	}
}