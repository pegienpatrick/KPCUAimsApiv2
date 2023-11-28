package org.aims.kpcu.controllers;

import org.aims.kpcu.models.manager.ManagerLoginRequest;
import org.aims.kpcu.models.manager.ManagerLoginResponse;
import org.aims.kpcu.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2/manager")
public class ManageRoleController {

    @Autowired
    private ManagerService managerService;


    @PostMapping("/login")
    public ResponseEntity<ManagerLoginResponse> login(@RequestBody @Valid ManagerLoginRequest managerLoginRequest, BindingResult result)
    {
        return managerService.login(managerLoginRequest);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("hello too");
    }




}
