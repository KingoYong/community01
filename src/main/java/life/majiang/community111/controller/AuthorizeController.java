package life.majiang.community111.controller;

import life.majiang.community111.dto.AccessTokenDTO;
import life.majiang.community111.dto.GithubUser;
import life.majiang.community111.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId("b54517c89ed435aea376");
        accessTokenDTO.setClientSecret("dee0485a3091b6a82fdce501d64e8890b278b497");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getId());
        return "index";
    }

}
