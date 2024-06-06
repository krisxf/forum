package com.kris.acg.service.Imp;

import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.mapper.CommentMsgMapper;
import com.kris.acg.service.CommentMsgService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 14:20
 **/

@Service
public class CommentMsgServiceImpl implements CommentMsgService {

    @Resource
    CommentMsgMapper commentMsgMapper;
    @Resource
    UserService userService;

    @Override
    public List<CommentMsg> queryUserCommentMsg() {
        Long userId = UserContext.getUserId();
        List<CommentMsg> commentMsgs = commentMsgMapper.selectUserCommentMsg(userId);
        for (CommentMsg c :commentMsgs
                ) {
            c.setUser(userService.searchUserBasicInfoById(c.getFromUserId()));
        }
        return commentMsgs;
    }

    @Override
    public void addCommentMsg(CommentMsg commentMsg) {
        commentMsgMapper.insertCommentMsg(commentMsg);
    }
}
