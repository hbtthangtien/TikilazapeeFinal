/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User.Oauth_Account;
import Model.User.Role;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import util.Oauth_Config;

/**
 *
 * @author hbtth
 */
public class UserFacebookServce {

    private static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(Oauth_Config.FACEBOOK_LINK_GET_TOKEN, Oauth_Config.FACEBOOK_APP_ID, Oauth_Config.FACEBOOK_APP_SECRET, Oauth_Config.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    private static User getUserInfo(final String code) throws IOException {
        String accessToken = getToken(code);
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Oauth_Config.FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", User.class);
    }
    public static Model.User.User getUserFromLoginFacebook(final String code) throws IOException{
        Role role = new Role();
        role.setRole_id(3);
        User userfb = getUserInfo(code);
        Oauth_Account user = new Oauth_Account();
        user.setOauth_user_id(userfb.getId());
        user.setFullname(userfb.getName());
        user.setEmail(userfb.getEmail());
        user.setFrom(2);
        user.setRole(role);
        user.setImage("image\\image_avatar_user\\avataruser(2).jpg");
        return user;
    }
}
