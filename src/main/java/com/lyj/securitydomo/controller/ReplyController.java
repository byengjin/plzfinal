package com.lyj.securitydomo.controller;

import com.lyj.securitydomo.dto.ReplyDTO;
import com.lyj.securitydomo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/list/{postId}")
    public String listReplies(@PathVariable Long postId, Model model) {
        model.addAttribute("reply", replyService.getRepliesByPostId(postId));
        return "reply/list";
    }

    @PostMapping("/create")
    public String createReply(@ModelAttribute ReplyDTO replyDTO, @RequestParam Long postId, @RequestParam(required = false) Long parentId) {
        replyDTO.setPostId(postId);
        replyDTO.setParentId(parentId);  // 대댓글의 부모 ID 설정
        replyService.createReply(postId, replyDTO);

        return "redirect:/posting/read/" + postId;  // 수정된 리다이렉트 경로
    }


    @PostMapping("/modify/{replyId}")
    public String modifyReply(@PathVariable Long replyId, @RequestParam String content, @RequestParam Long postId) {
        replyService.modifyReply(replyId, content);
        return "redirect:/read/" + postId;
    }

    @PostMapping("/remove/{replyId}")
    public String removeReply(@PathVariable Long replyId, @RequestParam Long postId) {
        replyService.removeReply(replyId);
        return "redirect:/read/" + postId;
    }



}
