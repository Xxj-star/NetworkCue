package com.skl.mvpdome.model;

import com.skl.mvpdome.view.IUserService;

public class UserService implements IUserService {
    @Override
    public String search(int hashCode) {
        return "User" + hashCode;
    }
}
