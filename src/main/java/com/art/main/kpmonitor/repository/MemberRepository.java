package com.art.main.kpmonitor.repository;

import com.art.main.kpmonitor.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    
    Optional<Member> findByEmail(String email);
    
    List<Member> findByAlertEnabledTrue();
    
    List<Member> findByAlertEnabledFalse();
    
    boolean existsByEmail(String email);
}