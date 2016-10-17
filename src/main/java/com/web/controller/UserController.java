package com.web.controller;

import com.web.model.UserEntity;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 2016-10-17.
 */
@Controller
public class UserController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    private UserRepository userRepository;

    // 进入用户列表
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {

        List<UserEntity> userList = userRepository.findAll();

        modelMap.addAttribute("userList", userList);

        return "admin/users";
    }

    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {

        return "admin/addUser";
    }

    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity entity) {

        userRepository.saveAndFlush(entity);

        // 添加成功后，转到用户列表
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String userDetail(@ModelAttribute("id") Integer userId, ModelMap modelMap) {

        modelMap.addAttribute("user", userRepository.findOne(userId));

        return "admin/userDetail";

    }

    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@ModelAttribute("id") Integer userId, ModelMap modelMap) {

        modelMap.addAttribute("user", userRepository.findOne(userId));

        return "admin/updateUser";

    }

    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity us) {

        userRepository.updateUser(us.getNickname(), us.getFirstName(), us.getLastName(), us.getPassword(), us.getId());
        userRepository.flush();

        // 添加成功后，转到用户列表
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }

}
