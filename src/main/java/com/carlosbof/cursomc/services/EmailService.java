package com.carlosbof.cursomc.services;

import com.carlosbof.cursomc.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

}
