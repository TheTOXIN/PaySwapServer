package com.toxin.payswap.service;

import com.toxin.payswap.dto.TransactionDTO;
import com.toxin.payswap.dto.VirtCardDTO;
import com.toxin.payswap.enity.Swap;
import com.toxin.payswap.enity.Transaction;
import com.toxin.payswap.enity.User;
import com.toxin.payswap.enity.VirtualCard;
import com.toxin.payswap.repository.SwapRepository;
import com.toxin.payswap.repository.TransactionRepository;
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

    @Autowired
    private TransactionRepository transactionRepository;

    public VirtCardDTO add(TransactionDTO dto) {
        Swap swap = swapRepository.findByHash(dto.getHash());
        User user = userRepository.findById(dto.getUserId()).orElseGet(null);

        validation(user, swap, dto);

        swap.getUsers().add(user);
        user.setSwap(swap);

        VirtualCard virtualCard = swap.getVirtualCard();
        virtualCard.setBill(virtualCard.getBill() + dto.getCount());

        virtcardRepository.save(virtualCard);
        virtcardRepository.flush();

        userRepository.save(user);
        swapRepository.save(swap);

        Transaction transaction = new Transaction();
        transaction.setVirtcard(virtualCard);
        transaction.setCount(dto.getCount());
        transaction.setUser(user);

        transactionRepository.save(transaction);

        VirtCardDTO cardDTO = new VirtCardDTO();
        cardDTO.setBill(virtualCard.getBill());
        cardDTO.setId(virtualCard.getId());

        return cardDTO;
    }

    private void validation(User user, Swap swap, TransactionDTO dto) {
        long cardMoney = user.getCard().getMoney();
        long transMoney = user.getTransactions().stream().mapToLong(Transaction::getCount).sum();

        if (cardMoney - transMoney < dto.getCount())
            throw new RuntimeException("NOT MANY: " + user.getCard().getId());
        if (swap.isFinished())
            throw new RuntimeException("SWAP FINISHED: " + swap.getHash());
    }

}
