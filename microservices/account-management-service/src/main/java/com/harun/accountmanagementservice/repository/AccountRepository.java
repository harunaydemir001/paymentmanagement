package com.harun.accountmanagementservice.repository;

import com.harun.common.dto.AccountDTO;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Account;
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
public interface AccountRepository extends JPABaseRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    default Page<Account> findByFilter(Pageable pageable, AccountDTO accountDTO) {
        return findAll(specification(accountDTO), pageable);
    }


    default Specification<Account> specification(AccountDTO accountDTO) {
        return (Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(accountDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), accountDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getAccountType())) {
                Predicate predicate = criteriaBuilder.equal(root.get("accountType"), accountDTO.getAccountType());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getBalance())) {
                Predicate predicate = criteriaBuilder.equal(root.get("balance"), accountDTO.getBalance());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getIban())) {
                Predicate predicate = criteriaBuilder.equal(root.get("iban"), accountDTO.getIban());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getVersion())) {
                Predicate predicate = criteriaBuilder.equal(root.get("version"), accountDTO.getVersion());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getBankUserId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("bankUserId"), accountDTO.getBankUserId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getCreatedAt())) {
                Predicate predicate = criteriaBuilder.equal(root.get("createdAt"), accountDTO.getCreatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(accountDTO.getUpdatedAt())) {
                Predicate predicate = criteriaBuilder.equal(root.get("updatedAt"), accountDTO.getUpdatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;

        };
    }
}
