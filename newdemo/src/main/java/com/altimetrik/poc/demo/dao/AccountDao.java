package com.altimetrik.poc.demo.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.altimetrik.poc.demo.bean.Account;
import com.altimetrik.poc.demo.entity.AccountEntity;
import com.altimetrik.poc.demo.entity.AuditEntity;
import com.altimetrik.poc.demo.entity.repo.AccountRepo;

@Repository
public class AccountDao {

	@Inject
	private AccountRepo accountRepo;
	@PersistenceContext
	private EntityManager entityManager;

	public List<AccountEntity> searchUser(List<SearchCriteria> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountEntity> query = builder.createQuery(AccountEntity.class);
		Root<AccountEntity> r = query.from(AccountEntity.class);

		Predicate predicate = builder.conjunction();

		SearchQueryCreteriaConsumer searchConsumer = new SearchQueryCreteriaConsumer(predicate, builder, r);
		params.stream().forEach(searchConsumer);
		predicate = searchConsumer.getPredicate();
		query.where(predicate);

		List<AccountEntity> result = entityManager.createQuery(query).getResultList();
		return result;
	}

	public List<AuditEntity> searchAuditData(List<SearchCriteria> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuditEntity> query = builder.createQuery(AuditEntity.class);
		Root<AuditEntity> r = query.from(AuditEntity.class);

		Predicate predicate = builder.conjunction();

		SearchQueryCreteriaConsumer searchConsumer = new SearchQueryCreteriaConsumer(predicate, builder, r);
		params.stream().forEach(searchConsumer);
		predicate = searchConsumer.getPredicate();
		query.where(predicate);

		List<AuditEntity> result = entityManager.createQuery(query).getResultList();
		return result;
	}

	public AccountEntity addAcc(Account account) {
		AccountEntity accEntity = new AccountEntity();
		accEntity.setAccountNo(account.getAccountNo());
		accEntity.setMobileNo(account.getMobileNo());
		accEntity.setStatus(account.getStatus());
		accEntity.setBal(account.getBal());
		return accountRepo.save(accEntity);
	}

}
