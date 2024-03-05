
package com.example.loginpage.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.loginpage.APIResponse;
import com.example.loginpage.JwtUtils;
import com.example.loginpage.LoginRequest.LoginRequestDTO;
import com.example.loginpage.LoginRequest.SignUpRequestDTO;
import com.example.loginpage.Service.LoginService;

@Controller
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {

        // Check if the email already exists
        if (loginService.emailExists(signUpRequestDTO.getEmailId())) {
            APIResponse emailExistsResponse = new APIResponse(HttpStatus.CONFLICT.value(), "Email already exists", null);
            return ResponseEntity.status(emailExistsResponse.getStatus()).body(emailExistsResponse);
        }

        // Continue with the signup process
        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO ){

        APIResponse apiResponse = loginService.login(loginRequestDTO);
     

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @GetMapping("/get")
    public String get() {
    	return "this is login page";
    }
    

    @GetMapping("/privateApi")
    public ResponseEntity<APIResponse> privateApi(@Valid @RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        APIResponse apiResponse =new APIResponse();

        jwtUtils.verify(auth);

        apiResponse.setData("this is private api");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
