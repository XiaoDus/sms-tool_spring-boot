package org.example.smstool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

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
  @TableName("smsApp_leaves")
@ApiModel(value = "SmsappLeaves对象", description = "")
public class SmsappLeaves implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String forName;

    private String nickName;

    private String imgUrl;

    private String sendTime;

    private String mail;


}
