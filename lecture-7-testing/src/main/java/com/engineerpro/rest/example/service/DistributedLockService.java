package com.engineerpro.rest.example.service;

import org.springframework.stereotype.Service;

@Service
public interface DistributedLockService {
  boolean accquireLock(String idempotentKey);

  void releaseLock(String idempotentKey);
}
