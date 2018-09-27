package com.toxin.payswap.service;

import com.toxin.payswap.dto.TransactionDTO;
import com.toxin.payswap.dto.VirtCardDTO;
import com.toxin.payswap.enity.Swap;
import com.toxin.payswap.enity.Transaction;
import com.toxin.payswap.enity.User;
import com.toxin.payswap.enity.VirtualCard;
import com.toxin.payswap.repository.SwapRepository;
import com.toxin.payswap.repository.UserRepository;
import com.toxin.payswap.repository.VirtcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private SwapRepository swapRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VirtcardRepository virtcardRepository;

    public VirtCardDTO add(TransactionDTO dto) {
        Swap swap = swapRepository.findByHash(dto.getHash());
        User user = userRepository.findById(dto.getUserId()).orElseGet(null);

        swap.getUsers().add(user);
        user.setSwap(swap);

        VirtualCard virtualCard = swap.getVirtualCard();
        virtualCard.setBill(virtualCard.getBill() + dto.getCount());

        virtcardRepository.save(virtualCard);
        virtcardRepository.flush();

        userRepository.save(user);
        swapRepository.save(swap);

        VirtCardDTO cardDTO = new VirtCardDTO();

        cardDTO.setBill(virtualCard.getBill());
        cardDTO.setId(virtualCard.getId());

        Transaction transaction = new Transaction();
        transaction.setVirtcard(virtualCard);
        transaction.setCount(dto.getCount());

        return cardDTO;
    }

}
