package com.example.junit_prac.util;


import org.springframework.stereotype.Component;

//가짜
@Component //Ioc컨테이너 등록
public class MailSenderStub implements MailSender{


    @Override
    public boolean send() {
        return true;
    }
}
