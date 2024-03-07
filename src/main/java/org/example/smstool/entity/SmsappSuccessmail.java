package org.example.smstool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
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
  @TableName("smsApp_successmail")
@ApiModel(value = "SmsappSuccessmail对象", description = "")
public class SmsappSuccessmail implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String mail;

    private Integer isOpen;

    private String phone;

    private String forPhone;

    private String uid;

    private String sendTime;

    private String nickName;

    private String imgUrl;


}
