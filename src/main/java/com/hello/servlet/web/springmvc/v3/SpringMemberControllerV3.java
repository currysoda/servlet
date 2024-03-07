package com.hello.servlet.web.springmvc.v3;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members") // 공통 Path
public class SpringMemberControllerV3 {
	private final MemberRepository memberRepository = MemberRepository.getInstance();

	@GetMapping("/new-form")
	public String newForm() {
		return "new-form";
	}

	@PostMapping("/save")
	public String save(
			@RequestParam("username") String username,
			@RequestParam("age") int age,
			Model model) {

		Member member = new Member(username, age);
		memberRepository.save(member);

		model.addAttribute("member", member);
		return "save-result";
	}

	@GetMapping("")
	public String members(Model model) {
		List<Member> members = memberRepository.findAll();

		model.addAttribute("members", members);
		return "members";
	}


}
