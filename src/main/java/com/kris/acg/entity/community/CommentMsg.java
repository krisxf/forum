package com.kris.acg.entity.community;

import com.kris.acg.entity.user.UserBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 13:35
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentMsg {
    @ApiModelProperty("id")
    Integer id;
    @ApiModelProperty("评论id")
    Long commentId;
    @ApiModelProperty("内容")
    String content;
    @ApiModelProperty("谁评论的")
    Long fromUserId;
    @ApiModelProperty("评论给谁的")
    Long toUserId;
    @ApiModelProperty("评论所属话题id")
    Long topicId;
    @ApiModelProperty("评论所属话题的标题")
    String topicTitle;
    @ApiModelProperty("创建时间")
    Date createTime;
    @ApiModelProperty("评论者的具体信息")
    UserBasic user;
}
