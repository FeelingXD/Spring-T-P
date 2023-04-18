package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepoJPA extends JpaRepository<Member, Long> {
}
