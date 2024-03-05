package com.example.loginpage.Service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginpage.APIResponse;
import com.example.loginpage.JwtUtils;
import com.example.loginpage.LoginRequest.LoginRequestDTO;
import com.example.loginpage.LoginRequest.SignUpRequestDTO;
import com.example.loginpage.UserRepository.User;
import com.example.loginpage.UserRepository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public APIResponse signUp(@Valid  SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();
        if (userRepository.existsByEmailId(signUpRequestDTO.getEmailId())) {
            apiResponse.setStatus(HttpStatus.CONFLICT.value());
            apiResponse.setData("email already exists");

            return apiResponse;
        }
        User userEntity = new User();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode( signUpRequestDTO.getPassword()));

        // store entity
        userEntity = userRepository.save(userEntity);

        // generate jwt
        String token = jwtUtils.generateJwt(userEntity);

        Map<String , Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);
//
//        // return
        return apiResponse;
    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse = new APIResponse();

        // validation

        // verify user exist with given email and password
        User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());

        // response
        if(user == null){
            apiResponse.setData("User login fail");
        }
        else {
            apiResponse.setData("User login in");

        }
     
        return apiResponse;

        // generate jwt
//        String token = jwtUtils.generateJwt(user);
//
//        Map<String , Object> data = new HashMap<>();
//        data.put("accessToken", token);
//
//        apiResponse.setData(data);
//
//        return apiResponse;
    }
    

	public boolean emailExists(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}
}