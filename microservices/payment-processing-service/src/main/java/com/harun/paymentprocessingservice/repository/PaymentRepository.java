package com.harun.paymentprocessingservice.repository;

import com.harun.common.dto.PaymentDTO;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PaymentRepository extends JPABaseRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

    default Page<Payment> findByFilter(Pageable pageable, PaymentDTO paymentDTO) {
        return findAll(specification(paymentDTO), pageable);
    }

    default Specification<Payment> specification(PaymentDTO paymentDTO) {
        return (Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(paymentDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), paymentDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (paymentDTO.getAccountId() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("accountId"), paymentDTO.getAccountId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (paymentDTO.getTransactionId() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("transactionId"), paymentDTO.getTransactionId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }


            if (paymentDTO.getCreatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("createdAt"), paymentDTO.getCreatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (paymentDTO.getUpdatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("updatedAt"), paymentDTO.getUpdatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;
        };
    }

    @Repository
     class PaymentBatchRepository {
        private static final Logger logger = LoggerFactory.getLogger(PaymentBatchRepository.class);

        @PersistenceContext
        private EntityManager entityManager;

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void batchInsert(List<Payment> payments) {
            Payment failedPayment = new Payment();
            int batchSize = 50;
            for (int i = 0; i < payments.size(); i++) {
                try {
                    entityManager.persist(payments.get(i));
                }catch (Exception e){
                    logger.info("This payment could not be written /n {}", i);
                    e.printStackTrace();
                }

                if (i % batchSize == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
        }
    }

}
