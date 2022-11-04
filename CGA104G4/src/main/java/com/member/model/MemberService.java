package com.member.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MemberService {

    private MemberDAO_interface dao;

    public MemberService() {
        this.dao = new MemberDAO();
    }

    public List<MemberVO> getAll() {

        return dao.getAll();
    }

    public List<Integer> getMemId() {

        return dao.getMemId();
    }

    public MemberVO findByPrimaryKey(Integer memId) {

        return dao.findByPrimaryKey(memId);

    }

    public void update(Integer memId, String memEmail, String memPwd, Integer accStat, String memName, String memMobile,
                       String memCity, String memDist, String memAdr, Integer memToken) {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemId(memId);
        memberVO.setMemEmail(memEmail);
        memberVO.setMemPwd(memPwd);
        memberVO.setAccStat(accStat);
        memberVO.setMemName(memName);
        memberVO.setMemMobile(memMobile);
        memberVO.setMemCity(memCity);
        memberVO.setMemDist(memDist);
        memberVO.setMemAdr(memAdr);
        memberVO.setMemToken(memToken);

        dao.update(memberVO);

    }

    //以下是MemberLoginServlet使用到的方法
    public Integer insertWithReturn(String memEmail, String memPwd, String memName, String memMobile, String memCity,
                           String memDist, String memAdr) {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemEmail(memEmail);
        memberVO.setMemPwd(memPwd);
        memberVO.setMemName(memName);
        memberVO.setMemMobile(memMobile);
        memberVO.setMemCity(memCity);
        memberVO.setMemDist(memDist);
        memberVO.setMemAdr(memAdr);

        return dao.insertWithReturn(memberVO);
    }

    public MemberVO login(String memEmail, String memPwd) {
        return dao.login(memEmail, memPwd);

    }

    public boolean selectByMemEmail(String memEmail){
        return dao.selectByMemEmail(memEmail);
    }

    public boolean forgetPwd(String memEmail) {
        boolean  hadMemEmail = dao.selectByMemEmail(memEmail);

        if(!hadMemEmail){
        return false;
        }

        //TODO
        //待完成信箱寄信與生成密碼並更改密碼
            return true;
    }
}
