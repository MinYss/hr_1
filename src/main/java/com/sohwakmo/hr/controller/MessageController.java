package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.dto.MessageSendDto;
import com.sohwakmo.hr.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    /**
     * 받은쪽지함으로 이동시키기
     * @return
     */
    @GetMapping("/receiveList")
    public String receiveList(Integer employeeNo, Model model) {
        log.info("receiveList()");

        // 로그인한 사원 번호 임시 값
        employeeNo = 2;
        log.info("employeeNo = {}", employeeNo);

        List<Message> messageList = messageService.read(employeeNo);
        log.info("messageList = {}", messageList);

        model.addAttribute("messageList", messageList);

        return "/message/receiveList";
    }

    /**
     * 쪽지 작성 페이지로 이동
     * @return
     */
    @GetMapping("/writeMessage")
    public String writeMessage() {
        log.info("writeMessage()");

        return "/message/writeMessage";
    }

//    /**
//     * 쪽지 보내고 받은쪽지함으로 이동.
//     * @return
//     */
//    @PostMapping("/sendMessage")
//    public String sendMessage(MessageSendDto dto, @RequestParam("files") List<MultipartFile> files) throws IOException {
//        log.info("sendMessage(dto = {}, files = {})", dto, files);
//
//        Message message = MessageSendDto.builder()
//                .senderNo(dto.getSenderNo()).messageType(dto.getMessageType()).title(dto.getTitle()).receiveNo(dto.getReceiveNo()).content(dto.getContent())
//                .build().toEntity();
//        log.info("message = {}", message);
//
//        for (MultipartFile multipartFile : files) {
//            if(multipartFile.isEmpty()) {
//                messageService.create(message);
//            } else {
//                messageService.create(message, multipartFile);
//            }
//        }
//
//        return "redirect:/message/receiveList";
//    }

    /**
     * 쪽지 보내기 기능
     * @param dto
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/sendMessage")
    public String sendMessage(MessageSendDto dto, @RequestParam("files") List<MultipartFile> files) throws IOException {
        log.info("sendMessage(dto = {}, files = {})", dto, files);

        messageService.create(dto, files);

        return "redirect:/message/receiveList";
    }

}
