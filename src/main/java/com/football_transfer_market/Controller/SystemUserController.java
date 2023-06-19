package com.football_transfer_market.Controller;


import com.football_transfer_market.Models.ApiResponse;
import com.football_transfer_market.Models.SystemUser;
import com.football_transfer_market.Service.SystemUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SystemUserController {

    private final SystemUserService systemUserService;


    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<SystemUser>> createUser(@RequestBody SystemUser systemUser) {
        return new ResponseEntity<>(new ApiResponse<>(
                systemUserService.saveUser(systemUser),
                "U ruajt me sukses"),
                                    HttpStatus.OK);
    }
}
