package com.zavgorodnyaya.springrest.controller;

import com.zavgorodnyaya.springrest.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zavgorodnyaya.springrest.service.RestUserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/local-users")
public class RestUserController {

    private final RestUserService userService;

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        userService.getAllUsers();
        return new ResponseEntity<>("Get all users", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>("User updated", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }




//    @GetMapping("/save")
//    public String saveUser() {
//        User user = new User();
//        user.setId(3L);
//        user.setName("James");
//        user.setLastName("Brown");
//        user.setAge((byte) 30);
//        return userService.saveUser(user);
//    }
//
//    @GetMapping("/update")
//    public String updateUser() {
//        User user = new User();
//        user.setId(3L);
//        user.setName("Thomas");
//        user.setLastName("Shelby");
//        user.setAge((byte) 30);
//        return userService.updateUser(user);
//    }
//
//    @GetMapping("/delete")
//    public String deleteUser() {
//        return userService.deleteUser(3L);
//    }
}
