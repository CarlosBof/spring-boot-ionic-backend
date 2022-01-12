package com.carlosbof.cursomc.services;

import java.util.Date;

import com.carlosbof.cursomc.domain.Pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage msg = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(obj.getCliente().getEmail());
        msg.setFrom(sender);
        msg.setSubject("Pedido Conmfirmado! Código: " + obj.getId());
        msg.setSentDate(new Date(System.currentTimeMillis()));
        msg.setText(obj.toString());
        return msg;
    }

}
