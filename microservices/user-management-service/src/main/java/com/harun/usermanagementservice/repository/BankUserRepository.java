package com.harun.usermanagementservice.repository;


import com.harun.common.dto.BankUserDTO;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.BankUser;
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
public interface BankUserRepository extends JPABaseRepository<BankUser, Long>, JpaSpecificationExecutor<BankUser> {


    default Page<BankUser> findByFilter(Pageable pageable, BankUserDTO bankUserDTO) {
        return findAll(specification(bankUserDTO), pageable);
    }

    default Specification<BankUser> specification(BankUserDTO bankUserDTO) {
        return (Root<BankUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(bankUserDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), bankUserDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(bankUserDTO.getEmail())) {
                Predicate predicate = criteriaBuilder.equal(root.get("email"), bankUserDTO.getEmail());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(bankUserDTO.getFirstName())) {
                Predicate predicate = criteriaBuilder.like(root.get("firstName"), "%" + bankUserDTO.getFirstName() + "%");
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(bankUserDTO.getLastName())) {
                Predicate predicate = criteriaBuilder.like(root.get("lastName"), "%" + bankUserDTO.getLastName() + "%");
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(bankUserDTO.getPhoneNumber())) {
                Predicate predicate = criteriaBuilder.equal(root.get("phoneNumber"), bankUserDTO.getPhoneNumber());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (bankUserDTO.getGender() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("gender"), bankUserDTO.getGender());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(bankUserDTO.getOccupation())) {
                Predicate predicate = criteriaBuilder.equal(root.get("occupation"), bankUserDTO.getOccupation());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (bankUserDTO.getMonthlyIncome() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("monthlyIncome"), bankUserDTO.getMonthlyIncome());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (bankUserDTO.getCreditScore() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("creditScore"), bankUserDTO.getCreditScore());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (bankUserDTO.getCreatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("createdAt"), bankUserDTO.getCreatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (bankUserDTO.getUpdatedAt() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("updatedAt"), bankUserDTO.getUpdatedAt());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;
        };
    }

}
