package com.example.demo.repository;

import com.example.demo.domain.TeUsers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/21 23:42
 */
public interface TeUsersRepository extends JpaRepository<TeUsers,Integer> {
}
