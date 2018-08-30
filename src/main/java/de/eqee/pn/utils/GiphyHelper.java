package de.eqee.pn.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.eqee.pn.Config;
import de.eqee.pn.R;
import de.eqee.pn.entities.Conversational;
import de.eqee.pn.entities.Message;
import de.eqee.pn.entities.Transferable;
import de.eqee.pn.entities.TransferablePlaceholder;
import de.eqee.pn.services.XmppConnectionService;
import de.eqee.pn.ui.ShareGiphyActivity;
import de.eqee.pn.ui.ShowGiphyActivity;
import de.eqee.pn.ui.XmppActivity;


public class GiphyHelper {

    public static Pattern GIPHY_URI = Pattern.compile("giphy:(?:[\\w-_.!~*'()]|%[\\da-f][\\da-f])*", Pattern.CASE_INSENSITIVE);
    public static String GIPHY_USER_AGENT_PREFIX = "Pn Messenger ";
    public static String GIPHY_HOST = "media.giphy.com";
    public static String GIPHY_SCHEME = "https";
    public static String GIPHY_PATH = "/media";
    public static String GIPHY_MEDIA_TYPE = "gif";
    public static String GIPHY_MEDIA_SIZING = "200w_d";
    public static int GIPHY_MEDIA_WIDTH = 200;
    public static int GIPHY_MEDIA_HEIGHT = -1;

    public static String getImageURL(String giphyID) {
        return GIPHY_SCHEME + "://" + GIPHY_HOST + GIPHY_PATH + "/" + giphyID + "/" + GIPHY_MEDIA_SIZING + "." + GIPHY_MEDIA_TYPE;
    }

    public static String getGiphyID(Message message)
    {
        Matcher matcher = GIPHY_URI.matcher(message.getBody());

        if (!matcher.matches()) {
            return "";
        }

        String giphyID = "";

        try {
            giphyID = String.valueOf(message.getBody()).substring(6);
        } catch (NumberFormatException nfe) {
            return "";
        }

        return giphyID;
    }

    public static String getGiphyURL(Message message)
    {
        String giphyID = getGiphyID(message);

        if (giphyID == "") {
            return "";
        }

        String giphyURL = getImageURL(giphyID);

        return giphyURL;
    }

    public static Intent getFetchIntent(Context context) {
        return new Intent(context, ShareGiphyActivity.class);
    }
}
