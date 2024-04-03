package com.edu.forum.application.service;

import com.edu.forum.application.model.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}