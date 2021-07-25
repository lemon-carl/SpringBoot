package com.lemon.server.controller.user;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @des: Google-kaptcha 验证码
 * @author: Lemon
 * @Date : 2021/5/13 12:09
 */
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        //---------------生成验证码 begin ------------------------
        // 获取验证码文本内容
        String createText = defaultKaptcha.createText();
        System.out.println("验证码内容：" + createText);
        // 验证码字符串保存到session中
        request.getSession().setAttribute("captcha", createText);
        // 根据文本验证码内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(createText);
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //---------------生成验证码 end --------------------------

    }
}
