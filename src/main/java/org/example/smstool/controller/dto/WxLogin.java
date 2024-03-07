package org.example.smstool.controller.dto;

import lombok.Data;

/**
 * @author xiaodu
 */
@Data
public class WxLogin {
    private String js_code;
    private String name;
    private String url;
    private String iv;
    private String encryptedData;
}
