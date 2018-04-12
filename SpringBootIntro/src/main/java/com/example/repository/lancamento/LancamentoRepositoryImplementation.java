/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.lancamento;

import com.example.model.Lancamento;
import com.example.repository.filter.LancamentoFilter;
//import java.time.LocalDate;  #Error
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;

/**
 *
 * @author user
 */
public class LancamentoRepositoryImplementation implements LancamentoRepositoryQuery {

    @PersistenceContext // Enable the consults
    private EntityManager manager;
    
    @Override
    public List<Lancamento> search(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        
        // criate the restrictions
        Predicate[] predicates = criateRestrictions(lancamentoFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criateRestrictions(LancamentoFilter lancamentoFilter,
                            CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        
        if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            // where descricao like %sometext%
            predicates.add(builder.like(builder.lower(root.get("descricao")),
                    "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
            
        }
        
        if(lancamentoFilter.getInitialDate() != null){
            predicates.add(
                    builder.greaterThanOrEqualTo(root.<Date>get("initialDate"),
                            lancamentoFilter.getInitialDate()));
        }
        
        if(lancamentoFilter.getFinalDate() != null){
            predicates.add(
                    builder.lessThanOrEqualTo(root.<Date>get("finallDate"),
                            lancamentoFilter.getFinalDate()));
        }        
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }
    
}
