package com.rabbit.foot.core;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbit.foot.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;

/**
 * <p>
 * 爬虫动作描述资源类
 * </p>
 *
 * @author LiAo
 * @since 2023-08-03
 */
@Slf4j
public abstract class ActionResources {

    public String spiderName;

    public URL url;

    protected volatile ObjectNode objectNode = null;

    // 当前使用的爬虫动作
    protected JsonNode activeRes;

    /**
     * 获取指定名称的爬虫描述
     */
    public void getSpiderActionConfigByName(String... params) {
        if (StrUtil.isEmpty(spiderName)) {
            return;
        }

        loadSpiderAction();

        getSpiderActionConfig(params);
    }

    /**
     * 获取指定名称的爬虫描述
     */
    public void getSpiderActionConfigByUrl(String... params) {
        if (ObjUtil.isEmpty(url)) {
            return;
        }
        // 验证是否成功加载资源
        loadUrlSpiderAction();

        getSpiderActionConfig(params);
    }

    /**
     * 获取指定名称的爬虫动作
     *
     * @param params 爬虫http填充
     */
    private void getSpiderActionConfig(String... params) {
        ArrayNode books = objectNode.withArray("books");

        if (books.size() <= 0) {
            throw new IllegalArgumentException("spider-action-test.json 爬虫资源为空");
        }

        for (JsonNode book : books) {
            if (spiderName.equals(book.get("name").asText())) {
                activeRes = book;
            }
        }

        // 填充请求参数
        httpParamBuild(params);
    }

    /**
     * 根据通配符填充http参数
     *
     * @param params 参数
     */
    public void httpParamBuild(String... params) {

        if (params == null || params.length == 0) {
            return;
        }

        String activeResStr = ConvertUtils.jsonNodeToStr(activeRes);

        for (int i = 0; i < params.length; i++) {
            String matchCharacter = "{params[" + i + "]}";
            activeResStr = activeResStr.replace(matchCharacter, params[i]);
        }

        activeRes = ConvertUtils.jsonStrToJsonNode(activeResStr);
    }

    /**
     * 加载爬虫动作
     */
    private void loadSpiderAction() {

        if (objectNode == null) {

            synchronized (ActionResources.class) {
                if (objectNode == null) {
                    ObjectMapper mapper = new ObjectMapper();

                    URL resource = null;

                    try {
                        resource = ActionResources.class.getClassLoader().getResource("spider-action-test.json");
                    } catch (Exception e) {
                        log.error("爬虫资源 spider-action-test.json 获取失败");
                    }

                    try {
                        objectNode = mapper.readValue(resource, ObjectNode.class);
                    } catch (IOException e) {
                        log.error("爬虫资源 spider-action-test.json 读取失败");
                    }

                    if (ObjUtil.isNull(objectNode)) {
                        throw new NullPointerException("爬虫资源 spider-action-test.json 读取为空");
                    }
                }
            }
        }
    }


    /**
     * 加载爬虫动作
     */
    private void loadUrlSpiderAction() {

        if (objectNode == null) {

            synchronized (ActionResources.class) {
                if (objectNode == null) {
                    ObjectMapper mapper = new ObjectMapper();

                    try {
                        objectNode = mapper.readValue(url, ObjectNode.class);
                    } catch (IOException e) {
                        log.error("爬虫资源 spider-action-test.json 读取失败");
                    }

                    if (ObjUtil.isNull(objectNode)) {
                        throw new NullPointerException("爬虫资源 spider-action-test.json 读取为空");
                    }
                }
            }
        }
    }

}