package com.toxin.payswap.service;

import com.toxin.payswap.dto.SwapDTO;
import com.toxin.payswap.enity.Swap;
import com.toxin.payswap.enity.User;
import com.toxin.payswap.enity.VirtualCard;
import com.toxin.payswap.repository.SwapRepository;
import com.toxin.payswap.repository.UserRepository;
import com.toxin.payswap.repository.VirtcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class SwapService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SwapRepository swapRepository;

    @Autowired
    private VirtcardRepository virtcardRepository;

    @Transactional
    public UUID create(SwapDTO dto) {
        UUID hash = UUID.randomUUID();

        User user = userRepository.findById(dto.getUserId()).orElseGet(null);

        VirtualCard virtualCard = new VirtualCard();

        virtualCard.setBill(virtualCard.getBill() + dto.getCount());


        virtcardRepository.save(virtualCard);
        
        Swap swap = new Swap();

        swap.setVirtualCard(virtualCard);
        swap.setCreator(user);
        swap.setDescription(dto.getDescription());
        swap.setPoint(dto.getPoint());
        swap.setHash(hash);


        swapRepository.save(swap);

        return hash;
    }

}
