package org.example.smstool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小肚
 * @since 2024-03-06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("smsApp_userinfo")
@ApiModel(value = "SmsappUserinfo对象", description = "")
public class SmsappUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String phone;

    private String url;

    private Integer failCount;

    private Integer replyCount;

    private Integer successCount;

    private String openId;

    private String sessionKey;


}
