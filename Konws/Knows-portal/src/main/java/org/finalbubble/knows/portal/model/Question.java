package org.finalbubble.knows.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author finalbubble.org
 * @since 2023-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 正文
     */
    @TableField("content")
    private String content;

    /**
     * 作者昵称
     */
    @TableField("user_nick_name")
    private String userNickName;

    /**
     * 作者id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 状态，0-未回复，1-未解决，2-已解决
     */
    @TableField("status")
    private Integer status;

    /**
     * 点击量
     */
    @TableField("hits")
    private Integer hits;

    @TableField("public_status")
    private Integer publicStatus;

    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 问题的各标签id
     */
    @TableField("tag_names")
    private String tagNames;


}
