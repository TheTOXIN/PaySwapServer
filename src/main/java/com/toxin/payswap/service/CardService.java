package com.toxin.payswap.service;

import com.toxin.payswap.dto.CardDTO;
import com.toxin.payswap.enity.Card;
import com.toxin.payswap.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private static final long START_BILL = 1000;

    public Card register(CardDTO dto) {
        Card card = new Card();

        card.setCode(dto.getCode());
        card.setNumber(dto.getNumber());
        card.setMoney(START_BILL);

        cardRepository.save(card);

        return card;
    }

}
