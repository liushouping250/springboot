package com.example.demo.modules.test.service;

import com.example.demo.domain.Users;
import com.example.demo.repository.TeUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/22 0:02
 */
@Service
@Slf4j
public class CurdJpaService {
    @Autowired
    private TeUsersRepository teUsersRepository;


    public  Users  index(){
        Users info = teUsersRepository.findById((long) 1).orElseThrow(() -> new RuntimeException("my"));
        return  info;
    }

}
