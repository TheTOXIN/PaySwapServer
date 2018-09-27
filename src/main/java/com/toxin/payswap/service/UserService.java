package com.toxin.payswap.service;

import com.toxin.payswap.dto.RegDTO;
import com.toxin.payswap.enity.Card;
import com.toxin.payswap.enity.User;
import com.toxin.payswap.repository.CardRepository;
import com.toxin.payswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardService cardService;

    @Transactional
    public UUID reg(RegDTO dto) {
        User user = new User();

        Card card = cardService.register(dto.getCard());

        user.setCard(card);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        cardRepository.save(card);
        userRepository.save(user);

        userRepository.flush();

        return user.getId();
    }

}
