package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.CardDTO;
import com.harun.entity.models.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {
    CardDTO getCardById(Long id);

    CardDTO updateCard(Card card);

    CardDTO saveCard(Card card);

    void deleteCard(Long id);

    Page<CardDTO> filter(Pageable pageable, CardDTO cardDTO);
}
