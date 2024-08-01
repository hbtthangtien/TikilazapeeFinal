/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User.Oauth_Account;
import Model.User.ProfileUserGoogle;
import Model.User.Role;
import Model.User.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.Oauth_Config;

/**
 *
 * @author hbtth
 */
public class UserGoogleService {

    private static String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Oauth_Config.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", Oauth_Config.GOOGLE_CLIENT_ID)
                        .add("client_secret", Oauth_Config.GOOGLE_APP_SERCRET)
                        .add("redirect_uri", Oauth_Config.GOOGLE_REDIRECT_URL)
                        .add("code", code)
                        .add("grant_type", Oauth_Config.GOOGLE_GRANT_TYPE)
                        .build()).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    private static ProfileUserGoogle getUser(final String code) throws IOException {
        String accessToken = getToken(code);
        String link = Oauth_Config.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ProfileUserGoogle user = new Gson().fromJson(response, ProfileUserGoogle.class);
        return user;
    }
    public static User getUserFromLoginGoogle(final String code) throws IOException{
        Role role = new Role();
        role.setRole_id(3);
        ProfileUserGoogle profile = getUser(code);
        Oauth_Account account = new Oauth_Account();
        account.setOauth_user_id(profile.getId());
        account.setFullname(profile.getName());
        account.setEmail(profile.getEmail());
        account.setAuth(true);
        account.setFrom(1);
        account.setRole(role);
        account.setImage("image\\image_avatar_user\\avataruser(2).jpg");
        return account;
    }
}
