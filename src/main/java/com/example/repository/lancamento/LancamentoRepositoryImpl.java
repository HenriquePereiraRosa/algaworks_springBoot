/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.lancamento;

import com.example.repository.lancamento.LancamentoRepositoryQuery;
import com.example.model.Lancamento;
import com.example.repository.filter.LancamentoFilter;
import com.example.repository.projection.ResumoLancamento;
//import java.time.LocalDate;  #Error (Aula 5.7 - MetaModels)
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

/**
 *
 * @author user
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext // Enable the consults | search Method
    private EntityManager manager;
    
    @Override
    public Page<Lancamento> search(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        
        // criate the restrictions
        Predicate[] predicates = criateRestrictions(lancamentoFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }
    
    @Override
    public Page<ResumoLancamento> resume(LancamentoFilter filter, Pageable pageable) {
        
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        
        criteria.select(builder.construct(ResumoLancamento.class, 
            root.get("id"),
            root.get("descricao"),
            root.get("dataVencimento"),
            root.get("dataPagamento"),
            root.get("valor"),
            root.get("tipo"),
            root.get("categoria").get("nome"),
            root.get("pessoa").get("nome")));

        // criate the restrictions
        Predicate[] predicates = criateRestrictions(filter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Predicate[] criateRestrictions(LancamentoFilter lancamentoFilter,
                            CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        
        if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            // where descricao like %sometext%
            predicates.add(builder.like(builder.lower(root.get("descricao")),
                    "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
            
        }
        
        if(lancamentoFilter.getDataVencimentoDe() != null){
            predicates.add(builder.greaterThanOrEqualTo(
                    root.<Date>get("dataVencimento"),
                    lancamentoFilter.getDataVencimentoDe()));
        }
        
        if(lancamentoFilter.getDataVencimentoAte() != null){
            predicates.add(builder.lessThanOrEqualTo(
                    root.<Date>get("dataVencimento"),
                    lancamentoFilter.getDataVencimentoAte()));
        }        
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePagina(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;
        
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
    
    private Long total(LancamentoFilter lancamentoFilter) {
       CriteriaBuilder builder = manager.getCriteriaBuilder();
       CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
       Root<Lancamento> root = criteria.from(Lancamento.class);
       
       Predicate[] predicates = criateRestrictions(lancamentoFilter, builder, root);
       criteria.where(predicates);
       criteria.select(builder.count(root));
       
       return manager.createQuery(criteria).getSingleResult();
    }

    
}
