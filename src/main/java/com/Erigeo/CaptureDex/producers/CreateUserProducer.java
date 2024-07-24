package com.Erigeo.CaptureDex.producers;

import com.Erigeo.CaptureDex.dto.EmailDto;
import com.Erigeo.CaptureDex.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreateUserProducer {

    final RabbitTemplate rabbitTemplate;

    public CreateUserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User userModel) {
        EmailDto emailDto = new EmailDto();
        emailDto.setReceiver(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setBody(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
