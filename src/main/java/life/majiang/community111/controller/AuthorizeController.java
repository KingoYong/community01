package life.majiang.community111.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-09-21 16:29
 **/
@Controller
public class AuthorizeController {
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        return "index";
    }

}
