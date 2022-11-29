package com.kyc.emailtest;

import com.kyc.emailtest.entity.Member;
import com.kyc.emailtest.repository.MemberRepository;
import com.kyc.emailtest.service.EmailDuplicationException;
import com.kyc.emailtest.service.MemberService;
import com.kyc.emailtest.util.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmailtestApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    @Test
    public void redisTest () throws Exception {
        //given
        String email = "test@test.com";
        String code = "aaa111";

        //when
        redisUtil.setDataExpire(email, code, 60 * 60L);

        //then
        Assertions.assertTrue(redisUtil.existData("test@test.com"));
        Assertions.assertFalse(redisUtil.existData("test1@test.com"));
        Assertions.assertEquals(redisUtil.getData(email), "aaa111");

    }

    @Test
    public void jpaTest() throws Exception {
        //given
        String email = "test@test.com";

        //when
        Member member = new Member(email);
        memberRepository.save(member);

        //then
        Assertions.assertTrue(memberRepository.existsMemberByEmail(email));
    }

    @Test
    public void emailTest() throws Exception {
        //given
        String email = "test1@test.com";

        //when
        Member member = new Member(email);
        memberRepository.save(member);

        //then
        Assertions.assertThrows(EmailDuplicationException.class, () -> {
            memberService.verifyEmailExist(email);
        });


    }
}
