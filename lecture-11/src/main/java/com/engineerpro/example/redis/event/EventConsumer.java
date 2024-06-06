package com.engineerpro.example.redis.event;

import java.util.Date;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.engineerpro.example.redis.config.MessageQueueConfig;
import com.engineerpro.example.redis.dto.event.LikePostEvent;
import com.engineerpro.example.redis.model.Notification;
import com.engineerpro.example.redis.model.NotificationType;
import com.engineerpro.example.redis.model.Post;
import com.engineerpro.example.redis.model.Profile;
import com.engineerpro.example.redis.repository.NotificationRepository;
import com.engineerpro.example.redis.service.feed.PostService;
import com.engineerpro.example.redis.service.profile.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RabbitListener(queues = MessageQueueConfig.QUEUE_LIKE_POST)
public class EventConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProfileService profileService;

    @Autowired
    PostService postService;

    @Autowired
    NotificationRepository notificationRepository;

    @RabbitHandler
    public void receive(String input) throws JsonMappingException, JsonProcessingException {
        log.info(" [x] Received '" + input + "'");

        LikePostEvent event = objectMapper.readValue(input, LikePostEvent.class);
        log.info("event postId={} profileId={}", event.getPostId(), event.getProfileId());

        // throw new RuntimeException("test when error");

        Profile profile = profileService.getUserProfile(event.getProfileId());
        Post post = postService.getPost(event.getPostId());

        Notification notification = new Notification();
        notification.setFromUser(profile);
        notification.setCreatedAt(new Date());
        notification.setNotificationType(NotificationType.LIKE_YOUR_POST);
        notification.setPost(post);
        notification.setToUser(post.getCreatedBy());

        notificationRepository.save(notification);
    }
}
