package com.member.controller;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(name="MemberServlet",urlPatterns = {"/MemberServlet", "/member/member.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");


        switch (action) {
            case "getMemId":
                getMemId(request, response);
                break;
            case "selectById":
                selectById(request, response);
                break;
            case "getAll":
                getAll(request, response);
                break;
            case "memInfoUpdate":
                updateData(request, response);
                break;
        }
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject errorMsgsJson = new JSONObject();
        Writer out = response.getWriter();

        Integer memId = Integer.parseInt(request.getParameter("memId"));

        // 信箱帳號驗證與確認
        String memEmail = request.getParameter("memEmail");

        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
        if (memEmail == null || memEmail.trim().length() == 0) {
            errorMsgsJson.put("memEmailerror", "帳號請勿空白");
        } else if (!memEmail.trim().matches(emailReg)) {
            errorMsgsJson.put("memEmailerror", "帳號格式異常");
        }

        // 密碼驗證
        String memPwd = request.getParameter("memPwd");

        String memPwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        if (memPwd == null || memPwd.trim().length() == 0) {
            errorMsgsJson.put("memPwderror", "密碼請勿空白");
        } else if (!memPwd.trim().matches(memPwdReg)) {
            errorMsgsJson.put("memPwderror", "密碼格式異常");
        }

        // 帳號狀態
        Integer accStat = Integer.parseInt(request.getParameter("accStat"));

        // 姓名驗證
        String memName = request.getParameter("memName");

        String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
        if (memName == null || memName.trim().length() == 0) {
            errorMsgsJson.put("memNameerror", "姓名請勿空白");
        } else if (!memName.trim().matches(memNameReg)) {
            errorMsgsJson.put("memNameerror", "姓名格式異常");
        }
        //

        // 手機驗證
        String memMobile = request.getParameter("memMobile");

        String memMobileReg = "^[0][9]\\d{8}$";
        if (memMobile == null || memMobile.trim().length() == 0) {
            errorMsgsJson.put("memMobileerror", "手機號碼請勿空白");
        } else if (!memMobile.trim().matches(memMobileReg)) {
            errorMsgsJson.put("memMobileerror", "手機號碼格式異常");
        }

        // 地址驗證
        String memCity = request.getParameter("memCity");
        String memDist = request.getParameter("memDist");
        String memAdr = request.getParameter("memAdr");

        if ("null".equals(memCity) || "null".equals(memDist) || memAdr == null) {
            memCity = null;
            memDist = null;
            memAdr = null;
        }

        Integer memToken = Integer.parseInt(request.getParameter("memToken"));

        if (!errorMsgsJson.isEmpty()) {
            errorMsgsJson.put("state", "false");
            out.write(errorMsgsJson.toString());
            System.out.println(errorMsgsJson.toString());


        } else {

            MemberService memSvc = new MemberService();
            memSvc.update(memId, memEmail, memPwd, accStat, memName, memMobile, memCity, memDist, memAdr,memToken);
            JSONObject currentState = new JSONObject();
            currentState.put("state", "true");
            out.write(currentState.toString());
        }

    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberService memSvc = new MemberService();
        List<MemberVO> members = memSvc.getAll();
        Writer out = response.getWriter();
        JSONArray memberJSONArray = new JSONArray();
        Encoder encoder = Base64.getEncoder();
        for (MemberVO memberVO : members) {
            JSONObject memberJsonObject = new JSONObject();
            memberJsonObject.put("MemId", memberVO.getMemId());
            memberJsonObject.put("MemEmail", memberVO.getMemEmail());
            memberJsonObject.put("MemPwd", memberVO.getMemPwd());
            memberJsonObject.put("AccStat", memberVO.getAccStat());
            memberJsonObject.put("MemName", memberVO.getMemName());
            memberJsonObject.put("MemMobile", memberVO.getMemMobile());
            memberJsonObject.put("MemCity", memberVO.getMemCity());
            memberJsonObject.put("MemDist", memberVO.getMemDist());
            memberJsonObject.put("MemAdr", memberVO.getMemAdr());
            memberJsonObject.put("MemRegTime", memberVO.getMemRegTime().getTime());
            memberJsonObject.put("MemToken", memberVO.getMemToken());
            if (memberVO.getMemPic() != null) {
                memberJsonObject.put("MemPic", encoder.encodeToString(memberVO.getMemPic()));
            }
            memberJSONArray.put(memberJsonObject);
        }
        out.write(memberJSONArray.toString());

    }

    private void selectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer memId = Integer.parseInt(request.getParameter("memId"));
        MemberService memSvc = new MemberService();
        MemberVO memberVO = memSvc.findByPrimaryKey(memId);
        Writer out = response.getWriter();
        JSONObject memberJsonObject = new JSONObject();
        JSONArray memberJSONArray = new JSONArray();
        Encoder encoder = Base64.getEncoder();
        memberJsonObject.put("MemId", memberVO.getMemId());
        memberJsonObject.put("MemEmail", memberVO.getMemEmail());
        memberJsonObject.put("MemPwd", memberVO.getMemPwd());
        memberJsonObject.put("AccStat", memberVO.getAccStat());
        memberJsonObject.put("MemName", memberVO.getMemName());
        memberJsonObject.put("MemMobile", memberVO.getMemMobile());
        memberJsonObject.put("MemCity", memberVO.getMemCity());
        memberJsonObject.put("MemDist", memberVO.getMemDist());
        memberJsonObject.put("MemAdr", memberVO.getMemAdr());
        memberJsonObject.put("MemRegTime", memberVO.getMemRegTime().getTime());
        memberJsonObject.put("MemToken", memberVO.getMemToken());
        if (memberVO.getMemPic() != null) {
            memberJsonObject.put("MemPic", encoder.encodeToString(memberVO.getMemPic()));
        }
        memberJSONArray.put(memberJsonObject);
        out.write(memberJSONArray.toString());

    }

    private void getMemId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        MemberService memSvc = new MemberService();
        List<Integer> memIdList = memSvc.getMemId();
        JSONArray memIdJosnArray = new JSONArray();
        for (Integer memId : memIdList) {
            memIdJosnArray.put(memId);
        }
        out.write(memIdJosnArray.toString());

    }

}
