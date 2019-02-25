package com.altimetrik.poc.demo.dao;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

public class SearchQueryCreteriaConsumer implements Consumer<SearchCriteria> {

	private Predicate predicate;
	private CriteriaBuilder builder;
	private Root r;

	public SearchQueryCreteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
		super();
		this.predicate = predicate;
		this.builder = builder;
		this.r = r;
	}

	@Override
	public void accept(SearchCriteria t) {

		if (StringUtils.equals(t.getOperation(), ">")) {
			predicate = builder.and(predicate,
					builder.greaterThanOrEqualTo(r.get(t.getKey()), t.getValue().toString()));

		} else if (StringUtils.equals(t.getOperation(), "<")) {
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(t.getKey()), t.getValue().toString()));
		}

		else if (StringUtils.equals(t.getOperation(), ":")) {

			if (r.get(t.getKey()).getJavaType() == String.class) {
				predicate = builder.and(predicate, builder.like(r.get(t.getKey()), "%" + t.getValue() + "%"));

			} else {
				predicate = builder.and(predicate, builder.equal(r.get(t.getKey()), t.getValue()));
			}
		}
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

	public CriteriaBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(CriteriaBuilder builder) {
		this.builder = builder;
	}

	public Root getR() {
		return r;
	}

	public void setR(Root r) {
		this.r = r;
	}

}