package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Post;
import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.dto.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;
import com.lyj.securitydomo.repository.PostRepository;
import com.lyj.securitydomo.repository.ReplyRepository;
import com.lyj.securitydomo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


//    public ReplyServiceImpl(ReplyRepository replyRepository, ModelMapper modelMapper) {
//        this.replyRepository = replyRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public Reply createReply(Long postId, ReplyDTO replyDTO) {
        // ReplyDTO를 Reply 엔티티로 변환
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
//        Reply parent = replyRepository.findById(replyDTO.getParentId()).orElse(null);
        User user = userRepository.findByUsername("aa");



        Reply reply= Reply.builder()
                .replyId(replyDTO.getReplyId())
                .post(post)
                .userId(replyDTO.getUserId())
                .content(replyDTO.getContent())
//                .parent(parent)
                .build();
        reply.setUserId(1l);
        //Reply reply = modelMapper.map(replyDTO, Reply.class);
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 대댓글일 경우, 부모 댓글 설정
        if (replyDTO.getParentId() != null) {
            Reply parentReply = replyRepository.findById(replyDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent reply not found"));
            reply.setParent(parentReply);  // 부모 댓글 설정
        }

        reply.setPost(post);  // 해당 Post 객체 설정

        // 댓글 저장
        return replyRepository.save(reply);
    }

    @Override
    public Reply modifyReply(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        reply.setContent(content);
        return replyRepository.save(reply);
    }

    @Override
    public void removeReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public List<Reply> getRepliesByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        return replyRepository.findByPostAndParentIsNull(post);
    }


}