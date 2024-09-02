package org.demir.dormitory.service;

public interface VerificationCodeService {
    String generateVerificationCode(String userId);
    boolean verifyCode(String userId, String code);

    String generateMailVerificationCode(String username,String mail);
    boolean verifyMailCode(String mail, String code);


}
