package cn.sweetyhut.user.controller;

import cn.sweetyhut.user.domain.User;
import cn.sweetyhut.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author Macer
 * @version V1.0
 * @date 2018/05/18 16:49
 */
@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        HttpStatus status = userService.addUser(user) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(user, status);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody User user) {
        HttpStatus status = userService.verifyUser(user) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(user, status);
    }
}
