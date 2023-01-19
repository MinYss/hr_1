package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmployeeService employeeService;

    /**
     * 아이디 중복확인
     * @param employeeNoValue 회원가입페이지에서 전달받은
     * @return
     */
    @GetMapping("/checkNo")
    public ResponseEntity<String> checkEmployeeNo(Integer employeeNoValue) {
        log.info("employeeNoValue={}", employeeNoValue);
        // 아이디가 있는지 중복확인
        boolean doubleCheckResult = employeeService.employeeNoDoubleCheck(employeeNoValue);
        if(doubleCheckResult){
            return ResponseEntity.ok("employeeNoNotOk");
        }else{
            return ResponseEntity.ok("employeeNoOk");
        }
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(String email) {
        log.info("email={}", email);
        boolean doubleCheckResult = employeeService.emailDoubleCheck(email);
        if (doubleCheckResult) {
            return ResponseEntity.ok("emailOk");
        }else{
            return ResponseEntity.ok("emailNotOk");
        }
    }

}
