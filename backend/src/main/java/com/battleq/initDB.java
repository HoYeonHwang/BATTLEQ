package com.battleq;

import com.battleq.member.domain.entity.Authority;
import com.battleq.member.domain.entity.EmailAuth;
import com.battleq.member.domain.entity.Member;
import com.battleq.quiz.domain.entity.Quiz;
import com.battleq.quizItem.domain.QuizPointType;
import com.battleq.quizItem.domain.QuizType;
import com.battleq.quizItem.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        public void dbInit() {

            Member member = Member.builder()
                    .userName("황호연")
                    .email("HwangHoYeon" + "@naver.com")
                    .pwd(passwordEncoder.encode("test123"))
                    .regDate(LocalDateTime.now())
                    .modDate(LocalDateTime.now())
                    .emailAuth(EmailAuth.Y)
                    .nickname("영등동야생마")
                    .userInfo("저는 공황장애가 있습니다.")
                    .authority(Authority.ROLE_ADMIN).build();
            em.persist(member);

            Quiz quiz = Quiz.builder()
                    .name("배틀큐 데모 테스트")
                    .introduction("배틀큐 데모 테스트 입니다. 잘 풀어보시고 오류사항이 있으면 문의 주세요")
                    .category("IT")
                    .thumbnail("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg")
                    .view(0)
                    .member(member)
                    .build();

            em.persist(quiz);
            List<String> list = new ArrayList<>();
            list.add("보도블럭");
            list.add("영단기");
            list.add("카훗");
            list.add("배틀큐");

            QuizItem quizItem = QuizItem.builder()
                    .title("황퀴즈보도블럭의 프로젝트 이름은 무엇일까요?")
                    .content(list)
                    .answer("배틀큐")
                    .image("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg")
                    .type(QuizType.VOTE)
                    .limitTime(10)
                    .member(member)
                    .pointType(QuizPointType.DOUBLE)
                    .quiz(quiz)
                    .build();
            em.persist(quizItem);

            list = new ArrayList<>();
            list.add("황퀴즈 보도블럭의 백엔드 기술스택은 React이다.");
            quizItem = QuizItem.builder()
                    .title("황퀴즈보도블럭의 기술스택에 관한 문제")
                    .content(list)
                    .answer("false")
                    .image("https://t1.daumcdn.net/cfile/tistory/9941A1385B99240D2E")
                    .type(QuizType.OX)
                    .limitTime(10)
                    .member(member)
                    .pointType(QuizPointType.SINGLE)
                    .quiz(quiz)
                    .build();
            em.persist(quizItem);

            list = new ArrayList<>();
            list.add("황퀴즈보도블럭의 ㅎㅎㅇ 은 누구일까요?");
            quizItem = QuizItem.builder()
                    .title("황퀴즈보도블럭의 팀원의 이름을 맞춰보세요?")
                    .content(list)
                    .answer("황호연")
                    .image("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg")
                    .type(QuizType.CHOSUNG)
                    .limitTime(10)
                    .member(member)
                    .pointType(QuizPointType.TRIPLE)
                    .quiz(quiz)
                    .build();
            em.persist(quizItem);

            list = new ArrayList<>();
            list.add("황퀴즈보도블럭의 구성원은 총 몇명일까요? (X명의 숫자만 기입하세요)");
            quizItem = QuizItem.builder()
                    .title("황퀴즈보도블럭의 구성원의 숫자")
                    .content(list)
                    .answer("4")
                    .image("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg")
                    .type(QuizType.SHORTANSWER)
                    .limitTime(10)
                    .member(member)
                    .pointType(QuizPointType.SINGLE)
                    .quiz(quiz)
                    .build();
            em.persist(quizItem);
        }


    }
}
