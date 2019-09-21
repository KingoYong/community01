package life.majiang.community111.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community111.dto.AccessTokenDTO;
import life.majiang.community111.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-09-21 18:04
 **/
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = Objects.requireNonNull(response.body()).string();
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user？accessToken=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = Objects.requireNonNull(response.body()).string();
            return JSON.parseObject(string, GithubUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
