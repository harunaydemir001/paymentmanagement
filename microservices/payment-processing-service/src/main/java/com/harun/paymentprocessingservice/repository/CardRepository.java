package com.harun.paymentprocessingservice.repository;

import com.harun.common.dto.CardDTO;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Card;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public interface CardRepository extends JPABaseRepository<Card, Long>, JpaSpecificationExecutor<Card> {

    default Page<Card> findByFilter(Pageable pageable, CardDTO cardDTO) {
        return findAll(specification(cardDTO), pageable);
    }

    default Specification<Card> specification(CardDTO cardDTO) {
        return (Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(cardDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), cardDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (cardDTO.getCardNumber() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("cardNumber"), cardDTO.getCardNumber());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (cardDTO.getAccount() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("account"), cardDTO.getAccount());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }


            if (cardDTO.getCvv() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("cvv"), cardDTO.getCvv());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (cardDTO.getUpdatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("updatedAt"), cardDTO.getUpdatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (cardDTO.getCreatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("createdAt"), cardDTO.getCreatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }


            if (cardDTO.getBankUser() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("bankUser"), cardDTO.getBankUser());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (cardDTO.getExpiryDate() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("expiryDate"), cardDTO.getExpiryDate());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;
        };
    }
}
