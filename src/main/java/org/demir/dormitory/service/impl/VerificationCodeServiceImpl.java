package org.demir.dormitory.service.impl;

import org.demir.dormitory.entity.Staff;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.service.RabbitMQProducer;
import org.demir.dormitory.service.StaffService;
import org.demir.dormitory.service.VerificationCodeService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StaffService staffService;
    private final RabbitMQProducer rabbitMQProducer;
    private static final String CODE_PREFIX = "verification_code:";
    private static final long CODE_EXPIRE_TIME = 5;

    public VerificationCodeServiceImpl(RedisTemplate<String, Object> redisTemplate, StaffService staffService, RabbitMQProducer rabbitMQProducer) {
        this.redisTemplate = redisTemplate;
        this.staffService = staffService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public String generateVerificationCode(String userId) {
        String code = String.format("%06d", new Random().nextInt(999999));
        String key = CODE_PREFIX + userId;
        redisTemplate.opsForValue().set(key, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
        return code;
    }

    public boolean verifyCode(String userId, String code) {
        String key = CODE_PREFIX + userId;
        String savedCode = (String) redisTemplate.opsForValue().get(key);
        return code.equals(savedCode);
    }

    @Override
    public String generateMailVerificationCode(String username,String mail) {
        if (!staffService.getByMail(username,mail)) {
            throw new BadRequestException("Mail does not exist");
        }

        String code = String.format("%06d", new Random().nextInt(999999));
        String key = CODE_PREFIX + mail;
        redisTemplate.opsForValue().set(key, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);


        rabbitMQProducer.sendVerificationCodeToQueue(mail, code);

        return code;
    }

    @Override
    public boolean verifyMailCode(String mail, String code) {
        String key = CODE_PREFIX + mail;
        String savedCode = (String) redisTemplate.opsForValue().get(key);

        return code.equals(savedCode);
    }
}
