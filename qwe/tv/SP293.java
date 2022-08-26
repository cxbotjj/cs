package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.utils.RSA;
import com.github.catvod.utils.okhttp.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SP293 extends Spider {

    public static String REQUEST_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9KE7n2qFTfDHkiQpIquVT4RZq\nfNOzF93qfgAxuRlNORfBiOuPCOLyPyEGBgfmztKmvyck2429Mp3fVt9WR7NIq39v\nUOJeaQjsrjeg9MnGFH6o0W+pZ99eMqKd95ErUONBvjE0o1Tc906QSHGN7HXxTACv\nWqaIrj+RUB62I4AMLwIDAQAB";
    public static String RESPONSE_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK2J1rk9jLpIk/la\nJ3rv5ngMac7CTJT2QTyL2jKnazj6Nrlp4DbBt6mhsY/nJ0/KTPE8gJN2+oSpuG3J\nCPLN/i3BvO2K7492lvSV5s325EdNTVMLPr0I/6CT6Jsq33uQm2bJr+mOTC9ahdWF\nyj+x1pEqf1NQokF8M8JHhmZyBn35AgMBAAECgYAfQk3wThr0TzCAYPHtnhr4ktvY\nR31986Gqo1Jpf2i7+a4z1dNyaZCIuLyAPFYDdGBJr+lFJtBXZy72b5jyC5L2JQ7H\nMgkQxTXTbxw53JlypJ5P4dONesjHP84A/FnqbiuXPnGUXsoqP8iw2KV2Rp5Dr4UA\nSHqdT5G7a7u2jmlOUQJBANn6Lco8MOf1p3iCxwvDDjQRJLgqg7NTlEgS2jkJkvEZ\nG3tu+ipnLQiSeYknF0eGphpSBvWjJnxQBrQNO7G0STUCQQDLzzwJpVI70jBGMF9K\nphCF7MdWBNIJycG6YiFD1NnPU1XoxK4QloyccUDDk1Q3/VUW4ncQ4H1iO8FbJw79\npr41AkEAwSv8LnAfK0HBk4PaH+tRiAT1VJygsGhOTX0flVKNvUpx13anDrL27tTY\nDhrtf36tbz/oWQGoFpzZA/GCSF6sTQJBAKlMtGqu0eivaTpk5EN2PzOAxPVHe9J1\nrl/NNCTMo0H9z9dTyeQtqxApY8sSj1sTLaOc65sxX/ZB/RRjFajO4iECQQDH2wPG\nt1U66bMdb1ueAXk0Mmxv0UGxX+f2ry2EMbMkucE0Q+YceFFc50Fjv3rh0UOajNpt\nSUgisHIdtjIiobQZ";
    public static String PARSE_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALSxFoXcb8EvbnF6\n+vpEARgSWZmHKCw+ymhySh07ndLkHwRc9YPYv0Zep//TOy+gz4ULa8xWlnaXF3yJ\nupHbv3XbYXGys9ibHNi71QdjAiwhH/AJMLFh2cdkKVWwJTBdHUXiVYvBxR238QqK\n2eaaxiFGn0rqgEK0INKfaT98H7h5AgMBAAECgYBHloWy4UQRiOY75Zp7/udkDxka\nn5bF+NaejPFaJZ2AyUqUovPOmdgmXCg5Tgn7871kG8NIwOF5+KCggby+Pw7aSnl0\nhFNCzkT/x/bHcoMXiTwkcNhAmTIJ9MtPYO++nfwce0pJrtlUfOiiBz/agW1Hh4nr\nGn/y6XTuCmRkqeU81QJBAO91+6IOJsu+aO1WJ7OSQHxzY9P+aZOEhZb4tSwnUS3Q\nTHeyMRW3cy9EmQe59g7GoFkBYjoRu/pcMHJtvDfDPKMCQQDBK/s6hb0nK0QNBMA4\nk5TgiKSSELv+snTqGFx7rdFTOKO5bVMl4tzxWZXddT7tMcR1Cp9cSnde2xurlUUc\nQQwzAkBA4YnNjeILIEtTyx00tXLNhYJN5Uf71VNQ5ZBJJ1oCfMbLQ7ey96K48vA3\nvLYjqMO3ef2AWF/DZQknZ/4wabDbAkB697tysqYJpCIqw2Z13YS9Nl7E2MBDz2hb\niOx+ktSYzIp0xKJUkMkjHmsnUbagGbk85Gkzv/ejO4muvVQEO2g/AkAmUUNkhKOh\nXxwYiiD+iSgm0D9q3kUppbWxgLkHN9noO1WQXzfxeKUzncvr35yKlhx/M+Hag/Iz\n9wDTb9FE34mK";

    private String host = "";
    private Map<String, Set<String>> parses = new HashMap<>();
    private String version293 = "1.2.9";
    private String appId293 = "10000";
    private String ua293 = "";


    private HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 10; PBEM00 Build/QKQ1.190918.001)");
        return headers;
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public void init(Context context, String extend) {
        try {
            String json = OkHttpUtil.string("https://alogin.oss-cn-beijing.aliyuncs.com/iphone.json", headers());
            JSONObject data = new JSONObject(decrypt(json, RESPONSE_PRIVATE_KEY));
            host = data.optJSONObject("data").optString("url");
            //获取巧计293配置
            String json293 = OkHttpUtil.string("https://gitea.com/Kimter/Dd/raw/branch/main/293.txt", null);
            JSONObject data293 = new JSONObject(json293);
            appId293 = data293.optString("appid");
            version293 = data293.optString("version");
            ua293 = data293.optJSONObject("headers").optString("User-Agent");
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
    }

    @Override
    public String homeContent(boolean filter) {
        JSONObject result = new JSONObject();
        try {

            String resp = OkHttpUtil.string(host + "api.php/Videonew/nav?csrf=" + URLEncoder.encode(getCsrf(getTime())), null);
            JSONObject res = new JSONObject(decrypt(resp, RESPONSE_PRIVATE_KEY));

            JSONArray list = res.getJSONArray("data");
            JSONArray classes = new JSONArray();
            for (int i = 0; i < list.length(); i++) {
                JSONObject type = list.getJSONObject(i);
                String typeName = type.getString("type_name");
                String typeId = type.getString("type_id");
                JSONObject newCls = new JSONObject();
                newCls.put("type_id", typeId);
                newCls.put("type_name", typeName);
                JSONObject typeExtend = type.optJSONObject("type_extend");
                if (filter && typeExtend != null) {
                    JSONArray filterArr = new JSONArray();
                    Map<String, String> ff = new LinkedHashMap<>();
                    ff.put("class", "剧情");
                    ff.put("area", "地区");
                    ff.put("lang", "语言");
                    ff.put("year", "年份");
                    for (Map.Entry<String, String> entry : ff.entrySet()) {
                        String classtype = typeExtend.getString(entry.getKey());
                        if (TextUtils.isEmpty(classtype)) {
                            continue;
                        }
                        JSONObject jOne = new JSONObject();
                        jOne.put("key", entry.getKey());
                        jOne.put("name", entry.getValue());
                        filterArr.put(jOne);
                        String[] split = classtype.split(",");
                        JSONArray valueArr = new JSONArray();
                        JSONObject all = new JSONObject();
                        all.put("n", "全部");
                        all.put("v", "");
                        valueArr.put(all);
                        for (String v : split) {
                            JSONObject kvo = new JSONObject();
                            kvo.put("n", v);
                            kvo.put("v", v);
                            valueArr.put(kvo);
                        }
                        jOne.put("value", valueArr);
                    }

                    if (!result.has("filters")) {
                        result.put("filters", new JSONObject());
                    }
                    result.getJSONObject("filters").put(typeId, filterArr);
                }
                classes.put(newCls);
            }
            result.put("class", classes);
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return result.toString();
    }


    @Override
    public String homeVideoContent() {
        JSONObject result = new JSONObject();
        try {
            String url = host + "api.php/Videonew/indexVideo?csrf=" + URLEncoder.encode(getCsrf(getTime()));
            JSONArray list = new JSONArray();
            JSONArray data = new JSONObject(decrypt(OkHttpUtil.string(url, headers()), RESPONSE_PRIVATE_KEY)).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONArray vods = data.getJSONObject(i).getJSONArray("vlist");
                for (int i2 = 0; i2 < vods.length(); i2++) {
                    JSONObject vr = vods.getJSONObject(i2);
                    JSONObject vod = new JSONObject();
                    vod.put("vod_id", vr.getString("vod_id"));
                    vod.put("vod_name", vr.getString("vod_name"));
                    vod.put("vod_pic", vr.getString("vod_pic"));
                    vod.put("vod_remarks", vr.getString("vod_remarks"));
                    list.put(vod);
                }
            }
            result.put("list", list);
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return result.toString();
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        JSONObject result = new JSONObject();
        try {
            String url = host + "api.php/Videonew/video" + "?class=";
            if (extend.containsKey("class")) {
                url = url + extend.get("class");
            }
            url = url + "&area=";
            if (extend.containsKey("area")) {
                url = url + extend.get("area");
            }
            url = url + "&year=";
            if (extend.containsKey("year")) {
                url = url + extend.get("year");
            }
            url = url + "&tid=" + tid + "&pg=" + pg + "&limit=20&csrf=" + URLEncoder.encode(getCsrf(getTime()));
            JSONArray data = new JSONObject(decrypt(OkHttpUtil.string(url, headers()), RESPONSE_PRIVATE_KEY)).getJSONArray("data");
            JSONArray vods = new JSONArray();
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                JSONObject vod = new JSONObject();
                vod.put("vod_id", item.getString("vod_id"));
                vod.put("vod_name", item.getString("vod_name"));
                vod.put("vod_pic", item.getString("vod_pic"));
                vod.put("vod_remarks", item.getString("vod_remarks"));
                vods.put(vod);
            }
            int parseInt = Integer.parseInt(pg);
            result.put("page", parseInt);
            if (vods.length() == 12) {
                parseInt++;
            }
            result.put("pagecount", parseInt);
            result.put("limit", 12);
            result.put("total", Integer.MAX_VALUE);
            result.put("list", vods);
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return result.toString();
    }

    @Override
    public String detailContent(List<String> ids) {
        JSONObject result = new JSONObject();
        try {
            JSONObject ckObj = new JSONObject();
            String time = getTime();
            String csrf = getCsrf(time);
            ckObj.put("time", time);
            String ck = new String(RSA.encryptByPublicKey(csrf, REQUEST_PUBLIC_KEY));
            String url = host + "api.php/Videonew/videoDetail?id=" + ids.get(0) + "&ck=" + URLEncoder.encode(ck) + "&csrf=" + URLEncoder.encode(csrf);
            JSONObject data = new JSONObject(decrypt(OkHttpUtil.string(url, headers()), RESPONSE_PRIVATE_KEY)).optJSONObject("data").optJSONObject("vod_info");
            JSONObject vod = new JSONObject();
            vod.put("vod_id", data.getString("vod_id"));
            vod.put("vod_name", data.getString("vod_name"));
            vod.put("vod_pic", data.getString("vod_pic"));
            vod.put("type_name", data.optString("vod_class"));
            vod.put("vod_year", data.optString("vod_year"));
            vod.put("vod_area", data.optString("vod_area"));
            vod.put("vod_remarks", data.optString("vod_remarks"));
            vod.put("vod_actor", data.optString("vod_actor"));
            vod.put("vod_content", data.optString("vod_content").trim());
            JSONArray playList = data.getJSONArray("vod_url_with_player");
            Map<String, String> treeMap = new LinkedHashMap<>();
            for (int i = 0; i < playList.length(); i++) {
                JSONObject source = playList.getJSONObject(i);
                String from = source.getString("code");
                String urls = source.optString("url");
                treeMap.put(from, urls);
                JSONArray parse_api = source.optJSONArray("parse_api");
                if (!parses.containsKey(from)) {
                    Set<String> apis = new HashSet<>();
                    for (int j = 0; j < parse_api.length(); j++) {
                        apis.add(parse_api.optString(j));
                    }
                    parses.put(from, apis);
                } else {
                    Set<String> apis = parses.get(from);
                    for (int j = 0; j < parse_api.length(); j++) {
                        apis.add(parse_api.optString(j));
                    }
                }
            }
            String join = TextUtils.join("$$$", treeMap.keySet());
            String join2 = TextUtils.join("$$$", treeMap.values());
            vod.put("vod_play_from", join);
            vod.put("vod_play_url", join2);
            JSONArray vods = new JSONArray();
            vods.put(vod);
            result.put("list", vods);
        } catch (Exception e) {

        }
        return result.toString();
    }

    @Override
    public String searchContent(String key, boolean quick) {
        JSONObject result = new JSONObject();
        try {
            String str2 = host + "api.php/Videonew/search?pg=1&text=" + URLEncoder.encode(key) + "&pg=1&csrf=" + URLEncoder.encode(getCsrf(getTime()));
            JSONArray jSONArray = new JSONObject(decrypt(OkHttpUtil.string(str2, headers()), RESPONSE_PRIVATE_KEY)).optJSONArray("data");
            JSONArray vods = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject vod = new JSONObject();
                vod.put("vod_id", jSONObject.getString("vod_id"));
                vod.put("vod_name", jSONObject.getString("vod_name"));
                vod.put("vod_pic", jSONObject.getString("vod_pic"));
                vod.put("vod_remarks", jSONObject.getString("vod_remarks"));
                vods.put(vod);
            }
            result.put("list", vods);
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return result.toString();
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        JSONObject result = new JSONObject();
        try {
            result.put("parse", "0");
            result.put("playUrl", "");
            SpiderDebug.log(new JSONObject(parses).toString());
            if (parses.containsKey(flag)) {
                Set<String> parseUrls = parses.get(flag);
                for (String parseUrl : parseUrls) {
                    Map<String, String> parseHeader = new HashMap<>();
                    parseHeader.put("User-Agent", ua293);
                    String json = OkHttpUtil.string(parseUrl + id + "&appId="+appId293+"&version="+version293, parseHeader);
                    JSONObject parseResult = new JSONObject(json);
                    String data = parseResult.optString("data");
                    Integer encryption = parseResult.optInt("encryption");
                    Integer code = parseResult.optInt("code");
                    if (code ==1 || (!data.equals("") && encryption>=0)) {
                        if (encryption == 1) {
                            JSONObject decryptData = new JSONObject(decrypt(data, PARSE_PRIVATE_KEY));
                            String url = decryptData.optString("url");
                            String header = decryptData.optString("header");
                            if (!TextUtils.isEmpty(url)) {
                                result.put("url", url);
                                if (!TextUtils.isEmpty(header) && url.contains("weiyun")) {
                                    result.put("header", header);
                                }
                                break;
                            }
                        } else if (encryption == 0) {
                            JSONObject playData = new JSONObject(data);
                            String url = playData.optString("url");
                            if (!TextUtils.isEmpty(url)) {
                                result.put("url", url);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return result.toString();
    }

    @Override
    public boolean isVideoFormat(String url) {
        return super.isVideoFormat(url);
    }

    @Override
    public boolean manualVideoCheck() {
        return super.manualVideoCheck();
    }

    private String getCsrf(String time) {
        return RSA.encryptByPublicKey(time, REQUEST_PUBLIC_KEY);
    }

    private String getTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private String decrypt(String data, String key) {
        return RSA.decryptByPrivateKey(data, key);
    }

}
