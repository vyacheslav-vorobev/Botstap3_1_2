package com.slavik.SpringBoot.service;

import com.slavik.SpringBoot.dao.UserDao;
import com.slavik.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;
    @Autowired
    public UserServiceImp(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.add(user);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public User getUser(Long series){
        return userDao.getUser(series);
    }

    @Transactional
    @Override
    public  void upDate(Long id, User user) {
        userDao.upDate(id,user);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Transactional
    @Override
    public Long getIdByLogin(String login) {
        User user = userDao.getUserByLogin(login);
        return user.getId();
    }
    @Transactional
    @Override
    public User findByUserName(String login) {
        return userDao.getUserByLogin(login);
    }
}
