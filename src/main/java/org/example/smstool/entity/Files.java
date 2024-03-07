package org.example.smstool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件管理
 * </p>
 *
 * @author 小肚
 * @since 2024-03-06
 */
@Getter
@Setter
  @ApiModel(value = "File对象", description = "文件管理")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("文件名称")
      private String name;

      @ApiModelProperty("文件类型")
      private String type;

      @ApiModelProperty("文件大小")
      private Long size;

      @ApiModelProperty("下载链接")
      private String url;

      @ApiModelProperty("文件md5")
      private String md5;

      @ApiModelProperty("是否删除(0:未删除 1:已删除)")
      private Boolean isDelete;

      @ApiModelProperty("是否禁用链接（1:可用 0:不可用）")
      private Boolean enable;


}
