package com.member.model;

import java.util.List;

import com.basicDAO.BasicDAO_interface;

public interface MemberDAO_interface extends BasicDAO_interface<MemberVO> {

    public List<Integer> getMemId();

    public MemberVO login(String memEmail, String memPwd);

    public boolean selectByMemEmail(String memEmail);

    public Integer insertWithReturn(MemberVO memberVO);

}
