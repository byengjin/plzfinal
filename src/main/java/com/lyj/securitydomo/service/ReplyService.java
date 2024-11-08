package com.lyj.securitydomo.service;

import com.lyj.securitydomo.dto.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;

import java.util.List;

public interface ReplyService {
//    public Reply createReply(Long postId, ReplyDTO replyDTO);
    Reply createReply(Long postId, ReplyDTO replyDTO);
    Reply modifyReply(Long replyId, String content);
    void removeReply(Long replyId);
    List<Reply> getRepliesByPostId(Long postId);


}
