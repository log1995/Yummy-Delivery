package com.yummy.delivery.controller;

import com.yummy.delivery.dto.SellerDTO;
import com.yummy.delivery.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yummy.delivery.httpStatus.httpComponent.RESPONSE_ENTITY_OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    /* 아이디(이메일) 중복확인 */
    @GetMapping("/signup/{email}")
    public void checkSameEmail(@RequestParam("email") String email) {
        sellerService.checkSameEmail(email);
    }

    /* 회원가입 */
    @PostMapping("/signup")
    public void signUp(@RequestBody SellerDTO sellerDTO) {
        sellerService.checkNullData(sellerDTO);    //  회원정보를 모두 기입했는지 확인하는 메서드
        sellerService.checkPasswordLength(sellerDTO);  //  비밀번호를 8자리 이상 기입했는지 확인하는 메서드
        sellerService.signUp(sellerDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody SellerDTO sellerDTO) {
        sellerService.login(sellerDTO);
        return RESPONSE_ENTITY_OK;
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        sellerService.logout();
        return RESPONSE_ENTITY_OK;
    }
}
