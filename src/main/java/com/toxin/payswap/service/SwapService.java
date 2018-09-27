package com.toxin.payswap.service;

import com.google.common.hash.Hashing;
import com.toxin.payswap.dto.PayDTO;
import com.toxin.payswap.dto.SwapDTO;
import com.toxin.payswap.dto.TransactionDTO;
import com.toxin.payswap.enity.Swap;
import com.toxin.payswap.enity.User;
import com.toxin.payswap.enity.VirtualCard;
import com.toxin.payswap.repository.SwapRepository;
import com.toxin.payswap.repository.UserRepository;
import com.toxin.payswap.repository.VirtcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service
public class SwapService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SwapRepository swapRepository;

    @Autowired
    private VirtcardRepository virtcardRepository;

    @Autowired
    private VirtualCardService virtualCardService;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public String create(SwapDTO dto) {
        String hash = getHash(dto.toString());

        User user = userRepository.findById(dto.getUserId()).orElseGet(null);

        VirtualCard virtualCard = new VirtualCard();
        virtcardRepository.save(virtualCard);

        Swap swap = new Swap();
        swap.setVirtualCard(virtualCard);
        swap.setCreator(user);
        swap.setDescription(dto.getDescription());
        swap.setPoint(dto.getPoint());
        swap.setHash(hash);

        swapRepository.save(swap);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setCount(dto.getCount());
        transactionDTO.setHash(hash);
        transactionDTO.setUserId(dto.getUserId());

        transactionService.add(transactionDTO);

        return hash;
    }

    @Transactional
    public boolean pay(PayDTO payDTO) {
        Swap swap = swapRepository.findByHash(payDTO.getHash());
        VirtualCard virtualCard = swap.getVirtualCard();

        boolean isCommit = virtualCard.getBill() >= swap.getPoint();

        if (isCommit) {
            virtualCardService.process(virtualCard, swap);
            swap.setFinished(true);
            swapRepository.save(swap);
        }

        return isCommit;
    }

    private String getHash(String string) {
        return Hashing.sha256()
            .hashString(string, StandardCharsets.UTF_8)
            .toString();
    }

}
