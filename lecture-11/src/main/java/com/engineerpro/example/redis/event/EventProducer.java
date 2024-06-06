package com.engineerpro.example.redis.event;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.engineerpro.example.redis.config.MessageQueueConfig;
import com.engineerpro.example.redis.dto.event.AfterCreatePostEvent;
import com.engineerpro.example.redis.dto.event.LikePostEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventProducer {
  @Autowired
  RabbitTemplate rabbitTemplate;

  @Autowired
  ObjectMapper objectMapper;

  public void sendAfterCreatePostEvent(AfterCreatePostEvent event) {

    try {
      String msg = objectMapper.writeValueAsString(event);
      rabbitTemplate.convertAndSend(MessageQueueConfig.queueAfterCreatePost, msg);
      log.info("sent sendAfterCreatePostEvent");
    } catch (JsonProcessingException | AmqpException e) {
      log.error("Cannot send after create post event, postId={}", event.getPostId());
    }
  }

  public void sendLikePostEvent(LikePostEvent event) {
    try {
      rabbitTemplate.convertAndSend(MessageQueueConfig.QUEUE_LIKE_POST,
          objectMapper.writeValueAsString(event));
      log.info("sent LikePostEvent");
    } catch (JsonProcessingException | AmqpException e) {
      log.error("Cannot send after like post event, profileId={} postId={}", event.getProfileId(), event.getPostId());
    }
  }
}
