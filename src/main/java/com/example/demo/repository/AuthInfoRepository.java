package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.AuthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {

    Optional<AuthInfoEntity> findByLogin(String username);
}
