package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.MemberEntity;
import ToyProject.CloneCodingVelog.domain.repository.article.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.member.MemberRepository;
import ToyProject.CloneCodingVelog.web.dto.AddMemberDto;
import ToyProject.CloneCodingVelog.web.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ToyProject.CloneCodingVelog.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleJpaRepository articleJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(name = LOGIN_MEMBER, required = false) MemberEntity member, Model model) {

        if (member == null) {
            model.addAttribute("loginMemberDto", new LoginMemberDto());
            return "home";
        }

        model.addAttribute("member", member);

        return "loginHome";
    }

    @PostMapping("/")
    public String login(
            @Validated @ModelAttribute(name = "loginMemberDto") LoginMemberDto loginMemberDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            return "home";
        }

        MemberEntity member = memberRepository.findByMemberId(loginMemberDto.getMemberId());

        if (member == null) {
            log.info("????????? ??????: ????????? ????????????.");
            bindingResult.reject("loginFail", "????????? ?????? ??????????????? ?????? ????????????.");
            return "home";
        }

        HttpSession session = request.getSession();  // ????????? ?????? ??? ?????? ????????????.
        session.setAttribute(LOGIN_MEMBER, member);  // LOGIN_MEMBER ????????? ?????? ??????.

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/add-member")
    public String addMemberForm(Model model) {
        model.addAttribute("addMemberDto", new AddMemberDto());
        return "domain/addMember";
    }

    @PostMapping("/add-member")
    public String addMember(@Validated @ModelAttribute(name = "addMemberDto") AddMemberDto addMemberDto,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "domain/addMember";
        }

        //?????? ????????? ?????? ??????
        MemberEntity findByMemberId = memberRepository.findByMemberId(addMemberDto.getMemberId());
        if (findByMemberId == null) {
            memberRepository.save(addMemberDto.toEntity());
        } else {
            bindingResult.rejectValue("memberId", "", "????????? ??????????????????.");
            return "domain/addMember";
        }

        return "redirect:/";
    }
}
