/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

import com.towel.el.annotation.Resolvable;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserInfoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

/**
 *
 * @author krisnamourtscf
 */
@Entity
@NoArgsConstructor
public class InstagramAccount extends EntityModel implements Account {

    @Transient
    private Instagram4j instagram;
    @Transient
    private InstagramUser user;
    @Resolvable(colName = "Username")
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;
    private final String TEXT_KEY = "text=";

    public InstagramAccount(String userName, String password) throws Exception {
        this.userName = userName;
        this.password = password;
        connect();
    }

    @Override
    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        } else {
            return userName;
        }

    }

    @Override
    public String getUrlProfilePic() {
        return user.getProfile_pic_url();
    }

    @Override
    public void connect() throws Exception {
        instagram = Instagram4j.builder().username(userName).password(password).build();
        instagram.setup();
        instagram.login();
        user = instagram.sendRequest(new InstagramGetUserInfoRequest(instagram.getUserId())).getUser();
    }

    @Override
    public boolean isConnect() {
        if (instagram != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AccountTarget getUser(String userName) throws Exception {
        InstagramUser localUser = instagram.sendRequest(new InstagramSearchUsernameRequest(userName)).getUser();
        return AccountTarget.getInstaTarget(this, localUser);
    }

    @Override
    public TimeLine getUserTimeline(Long userId) throws Exception {
        TimeLine timeLine = new TimeLine();
        timeLine.setFeeds(instagram.sendRequest(new InstagramUserFeedRequest(userId)));
        return timeLine;
    }

    @Override
    public void repost(Post post) throws Exception {
        InstagramFeedItem item = post.getFeed();
        String url = getImageUrl(item.image_versions2.get("candidates").toString());
        
        String text = "source: @"+item.getUser().getUsername()+"\r\n"+item.caption.get("text").toString();
        String imagePath = saveImage(url);
        instagram.sendRequest(new InstagramUploadPhotoRequest(
                new File(imagePath),
                text)).getMedia();
    }

    private String getImageUrl(String candidates) {
        int ind = candidates.indexOf("url=");
        String retorno = candidates.substring(ind + 4);
        ind = retorno.indexOf("}");
        retorno = retorno.substring(0, ind);
        return retorno;
    }

    private String saveImage(String imageUrl) throws Exception {
        int ind0 = imageUrl.lastIndexOf("/");
        int ind1 = imageUrl.indexOf(".jpg");

        String imageName = imageUrl.substring(ind0, ind1);
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        String filename = "DATA/IMAGE/" + imageName + ".jpg";
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(response);
        fos.close();
        return filename;
    }

    /*private String extractText(String objectString) {
        int indIni = objectString.indexOf(TEXT_KEY) + TEXT_KEY.length();
        String textTmp = objectString.substring(indIni);
        System.out.println("" + textTmp);
        return textTmp.substring(0, textTmp.indexOf(","));
    }*/

}
