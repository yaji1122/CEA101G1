package security;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import mail.MailAuthenticate;
import mail.MailService;
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
@WebServlet("/CaptchaCheck")
public class CaptchaCheck extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json; charset=utf-8");
        
        JsonParser parser = new JsonParser();
        JsonObject formDataObj = (JsonObject) parser.parse(request.getReader());
        
        String userEnteredCaptchaCode = formDataObj.get("userEnteredCaptchaCode").getAsString();
        String captchaId = formDataObj.get("captchaId").getAsString();
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
        }  else {
            // captcha validation succeeded
            validationResult.setSuccess(true);
        }
        
        try {
            // return the json string with the validation result to the frontend
            out.write(gson.toJson(validationResult));
        } catch(Exception ex) {
        	ex.printStackTrace();
            out.write(ex.getMessage());
        } finally {
            out.close();
        }
    }
}

class BasicValidationResult {
    private boolean success;

    public BasicValidationResult() {
    }

    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
