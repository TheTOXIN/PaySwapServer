package com.toxin.payswap.service;

import com.toxin.payswap.enity.Card;
import com.toxin.payswap.enity.Swap;
import com.toxin.payswap.enity.Transaction;
import com.toxin.payswap.enity.VirtualCard;
import com.toxin.payswap.repository.CardRepository;
import com.toxin.payswap.repository.VirtcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualCardService {

    @Autowired
    private VirtcardRepository virtcardRepository;

    @Autowired
    private CardRepository cardRepository;

    public void process(VirtualCard virtualCard, Swap swap) {
        virtualCard.setBill(virtualCard.getBill() - swap.getPoint());

        for (Transaction transaction : virtualCard.getTrans()) {
            Card card = transaction.getUser().getCard();
            card.setMoney(card.getMoney() - transaction.getCount());
            cardRepository.save(card);
        }

        virtcardRepository.save(virtualCard);
    }

}
