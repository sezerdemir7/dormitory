package org.demir.dormitory.controller;

import org.demir.dormitory.dto.request.AuthRequest;
import org.demir.dormitory.dto.request.StaffRequest;
import org.demir.dormitory.entity.Staff;
import org.demir.dormitory.service.AuthService;
import org.demir.dormitory.service.StaffService;
import org.demir.dormitory.service.VerificationCodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final StaffService staffService;
    private final AuthService authenticationService;
    private final VerificationCodeService verificationCodeService;

    public AuthController(StaffService staffService, AuthService authenticationService, VerificationCodeService verificationCodeService) {
        this.staffService = staffService;
        this.authenticationService = authenticationService;
        this.verificationCodeService = verificationCodeService;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "hello world! welcome";
    }

    @PostMapping("/register")
    public Staff addUser(@RequestBody StaffRequest request){
        return staffService.createUser(request);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest request){
        return authenticationService.generateToken(request);
    }


    @PostMapping("/generate-verify-code")
    public String generateCode(@RequestParam String username,@RequestParam String mail) {
        String code = verificationCodeService.generateMailVerificationCode(username,mail);

        System.out.println("Onaylama kodu: " + code);

        return "Onaylama kodu gönderildi";
    }

    @PostMapping("/verify/mail")
    public String verify(@RequestParam String mail, @RequestParam String code) {
        boolean isVerified = verificationCodeService.verifyMailCode(mail, code);
        if (isVerified) {
            return "Doğrulama başarılı";
        } else {
            return "Geçersiz onaylama kodu";
        }
    }



    @GetMapping("/employee")
    public String getUserString(){
        return "This is employee";
    }

    @GetMapping("/manager")
    public String getAdminString(){
        return "This is Manager";
    }
}
