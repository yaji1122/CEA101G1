package com.members.controller;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.members.model.MembersService;
import com.members.model.MembersVO;

import mail.MailAuthenticate;
import mail.MailService;
import security.SecureUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet("/ResetPwd")
public class ResetPwd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	MembersVO member = null;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        MembersService mbSvc = new MembersService();
        response.setContentType("application/json; charset=utf-8");
        
        JsonParser parser = new JsonParser();
        JsonObject formDataObj = (JsonObject) parser.parse(request.getReader());
        
        String userEnteredCaptchaCode = formDataObj.get("userEnteredCaptchaCode").getAsString();
        String captchaId = formDataObj.get("captchaId").getAsString();
        String email = formDataObj.get("email").getAsString();
        member = mbSvc.getOneByMbEmail(email);
        System.out.print(email);
        // create a captcha instance to be used for the captcha validation
        SimpleCaptcha captcha = SimpleCaptcha.load(request);
        // execute the captcha validation
        boolean isHuman = captcha.validate(userEnteredCaptchaCode, captchaId);
        
        // create an object that stores the validation result
        BasicValidationResult validationResult = new BasicValidationResult();
        
        if (isHuman == false) {
            // captcha validation failed
            validationResult.setSuccess(false);
            // TODO: consider logging the attempt
        } else if (member == null) {
        	validationResult.setIsMember(false);
        } else {
            // captcha validation succeeded
            validationResult.setSuccess(true);
            validationResult.setIsMember(true);
            SecureUtils security = new SecureUtils();
            MailService mail = new MailService(); //發送驗證郵件
            byte[] salt = null;
			try {
				salt = security.getSalt();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Encoder encoder = Base64.getEncoder();
			String mb_salt = encoder.encodeToString(salt);
			String newpwd = MailAuthenticate.getAuthCode();
			String mb_pwd = security.getSecurePassword(newpwd, salt);
            mbSvc.updatePwd(member.getMb_id(), mb_pwd, mb_salt);
            mail.sendMail(email, "戴蒙會員密碼重設", "您的臨時密碼為：" + newpwd + "，使用臨時密碼登入後，請記得更改個人密碼。");
        }
        
        try {
            // return the json string with the validation result to the frontend
            out.write(gson.toJson(validationResult));
        } catch(Exception ex) {
            out.write(ex.getMessage());
        } finally {
            out.close();
        }
    }
}

class BasicValidationResult {
    private boolean success;
    private boolean isMember;

    public BasicValidationResult() {
    }

    public boolean getSuccess() {
        return success;
    }
    
    public boolean getIsMember() {
    	return isMember;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public void setIsMember(boolean isMember) {
    	this.isMember = isMember;
    }
}
