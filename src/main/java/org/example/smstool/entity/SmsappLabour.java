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
  @TableName("smsApp_labour")
@ApiModel(value = "SmsappLabour对象", description = "")
public class SmsappLabour implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String uid;

    private Integer way;

    private String phone;

    private String forPhone;

    private Integer isOpen;

    private String sendTime;

    private String nickName;

    private String mail;

    private String imgUrl;

    private Integer isSend;


}
