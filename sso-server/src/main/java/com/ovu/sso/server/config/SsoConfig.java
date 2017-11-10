package com.ovu.sso.server.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.ovu.sso.server.auth.IAuthenticationHandler;


/**
 * 应用配置信息
 * 
 * @author Administrator
 *
 */
public class SsoConfig implements ResourceLoaderAware {

    private static Logger logger = LoggerFactory.getLogger(SsoConfig.class);

    private ResourceLoader resourceLoader;

    private IAuthenticationHandler authenticationHandler; // 鉴权处理器

    private String loginViewName = "/login"; // 登录页面视图名称

    private int tokenTimeout = 30; // 令牌有效期，单位为分钟，默认30分钟

    /**
     * 重新加载配置，以支持热部署
     * 
     * @throws Exception
     */
    public void refreshConfig() throws Exception {

        // 加载config.properties
        Properties configProperties = new Properties();

        try {
            Resource resource = resourceLoader.getResource("classpath:config.properties");
            configProperties.load(resource.getInputStream());
        } catch (IOException e) {
            logger.warn("在classpath下未找到配置文件config.properties");
        }

        // vt有效期参数
        String configTokenTimeout = (String) configProperties.get("tokenTimeout");
        if (configTokenTimeout != null) {
            try {
                tokenTimeout = Integer.parseInt(configTokenTimeout);
                logger.debug("config.properties设置tokenTimeout={}", tokenTimeout);
            } catch (NumberFormatException e) {
                logger.warn("tokenTimeout参数配置不正确");
            }
        }
    }

    /**
     * 获取当前鉴权处理器
     * 
     * @return
     */
    public IAuthenticationHandler getAuthenticationHandler() {
        return authenticationHandler;
    }

    public void setAuthenticationHandler(IAuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    /**
     * 获取登录页面视图名称
     * 
     * @return
     */
    public String getLoginViewName() {
        return loginViewName;
    }

    public void setLoginViewName(String loginViewName) {
        this.loginViewName = loginViewName;
    }

    /**
     * 获取令牌有效期，单位为分钟
     * 
     * @return
     */
    public int getTokenTimeout() {
        return tokenTimeout;
    }

    public void setTokenTimeout(int tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }

    @Override
    public void setResourceLoader(ResourceLoader loader) {
        this.resourceLoader = loader;
    }

    public void destory() {
    	
    }
}
