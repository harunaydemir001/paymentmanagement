package com.harun.paymentprocessingservice.service.impl;

import com.harun.common.dto.CardDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.entity.models.Card;
import com.harun.paymentprocessingservice.mapper.MapperGenerator;
import com.harun.paymentprocessingservice.mapper.MapperGeneratorSingleton;
import com.harun.paymentprocessingservice.mapper.PageMapper;
import com.harun.paymentprocessingservice.repository.CardRepository;
import com.harun.paymentprocessingservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private static String message = "";
    private final CardRepository cardRepository;

    @Override
    public CardDTO getCardById(Long id) {
        Card card = cardRepository.findByIdOrThrowError(id);
        return mapper.cardToCardDTO(card);
    }

    @Override
    public CardDTO updateCard(Card card) {
        Card updatedCard = cardRepository.save(card);
        return mapper.cardToCardDTO(updatedCard);
    }

    @Override
    public CardDTO saveCard(Card card) {
        Card savedCard = cardRepository.save(card);
        return mapper.cardToCardDTO(savedCard);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.findByIdOrThrowError(id);
        message = ErrorMessage.DELETION_SUCCESS.getMessage("Card", id);
        logger.info(message);
        cardRepository.deleteById(id);
    }

    @Override
    public Page<CardDTO> filter(Pageable pageable, CardDTO cardDTO) {
        Page<Card> page = cardRepository.findByFilter(pageable, cardDTO);
        List<CardDTO> cardDTOList = mapper.cardToCardDTO(page.getContent());
        return PageMapper.toPage(page, cardDTOList);
    }
}
