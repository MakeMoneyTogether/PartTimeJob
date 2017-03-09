package com.partjob.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XSSCheckFilter implements Filter {

    private FilterConfig    config;
    private static String   errorPath;    // 出错跳转的目的地
    private static String[] excludePaths = {"/gzapam/login"}; // 不进行拦截的url
    private static String[] safeless = {
            "<script", // 需要拦截的JS字符关键字
            "</script", "<iframe", "</iframe", "<frame", "</frame", "set-cookie", "%3cscript", "%3c/script",
            "%3ciframe", "%3c/iframe", "%3cframe", "%3c/frame", "src=\"javascript:", "<body", "</body", "%3cbody",
            "%3c/body",
                                     // "<",
                                     // ">",
                                     // "</",
                                     // "/>",
                                     // "%3c",
                                     // "%3e",
                                     // "%3c/",
                                     // "/%3e"
                                     };

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException,
            ServletException {
        Enumeration params = req.getParameterNames();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // String basePath = request.getScheme() + "://" +
        // request.getServerName() + ":" + request.getServerPort() + "/";

        // 获取context name，确认是哪个应用
        String contextName = request.getContextPath().substring(1);
        errorPath = "/"+contextName+"/fail";
        
        boolean isSafe = true;
        String requestUrl = request.getRequestURI();
        // String queryUrl = request.getQueryString();
        // System.out.println("params:" + params + " , requestUrl:" + requestUrl
        // + " , queryUrl" + queryUrl);
        if (isSafe(requestUrl)) {
            requestUrl = requestUrl.substring(requestUrl.indexOf("/"));
            if (!excludeUrl(requestUrl)) {
                while (params.hasMoreElements()) {
                    String cache = req.getParameter((String) params.nextElement());
                    if (null != cache && cache.length() > 0) {
                        if (!isSafe(cache)) {
                            isSafe = false;
                            break;
                        }
                    }
                }
            }
        } else {
            isSafe = false;
        }

        if (!isSafe) {
            request.setAttribute("msg", "您输入的内容含有非法字符，请检查后重新输入！");
            // 用户未登录，跳转到登录页
            response.sendRedirect(errorPath);
            
            return;
        }
        filterChain.doFilter(req, resp);
    }

    private static boolean isSafe(String str) {
        if (null != str && str.length() > 0) {
            for (String s : safeless) {
                if (str.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean excludeUrl(String url) {
        if (excludePaths != null && excludePaths.length > 0) {
            for (String path : excludePaths) {
                if (url.toLowerCase().equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        errorPath = config.getInitParameter("errorPath");
        String excludePath = config.getInitParameter("excludePaths");
        if (null != excludePath && excludePath.length() > 0) {
            excludePaths = excludePath.split(",");
        }
    }
}
