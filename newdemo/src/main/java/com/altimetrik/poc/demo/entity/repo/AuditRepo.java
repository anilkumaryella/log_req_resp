package com.altimetrik.poc.demo.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.poc.demo.entity.AuditEntity;
import com.altimetrik.poc.demo.entity.AuditPK;

public interface AuditRepo extends JpaRepository<AuditEntity, AuditPK> {
}
