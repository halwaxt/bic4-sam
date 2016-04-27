package at.technikum.bic4a16.servlets;

import javax.servlet.http.HttpServletResponse;

public class ResponseHelper {
    private ResponseHelper() {}

    public static void SetAccessControlHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

}
