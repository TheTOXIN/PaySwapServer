package com.toxin.payswap.service;

import com.google.common.hash.Hashing;
import com.toxin.payswap.dto.PayDTO;
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

    @Transactional
    public String create(SwapDTO dto) {
        String hash = getHash(dto.toString());

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

    public boolean pay(PayDTO payDTO) {
        VirtualCard virtualCard = virtcardRepository.findById(payDTO.getVirtCardId()).orElseGet(null);
        Swap swap = swapRepository.findByHash(payDTO.getHash());

        boolean isCommit = swap.getPoint() >= virtualCard.getBill();

        if (isCommit) virtualCardService.process(virtualCard, swap);

        return isCommit;
    }

    private String getHash(String string) {
        return Hashing.sha256()
            .hashString(string, StandardCharsets.UTF_8)
            .toString();
    }

}
