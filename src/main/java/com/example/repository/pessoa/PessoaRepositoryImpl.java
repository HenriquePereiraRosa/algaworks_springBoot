/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.pessoa;

import com.example.model.Pessoa;
import com.example.repository.filter.PessoaFilter;
import com.example.repository.projection.ResumoPessoa;
import java.util.ArrayList;
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
public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    @PersistenceContext // Enable the consults | search Method
    private EntityManager manager;
    
    @Override
    public Page<Pessoa> search(PessoaFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);
        
        // criate the restrictions
        Predicate[] predicates = criateRestrictions(filter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Pessoa> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }
    
    @Override
    public Page<ResumoPessoa> resume(PessoaFilter filter, Pageable pageable) {
        
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoPessoa> criteria = builder.createQuery(ResumoPessoa.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);
        
        criteria.select(builder.construct(ResumoPessoa.class, 
            root.get("id"),
            root.get("nome"),
            root.get("endereco").get("cidade"),
            root.get("endereco").get("estado"),
            root.get("ativo")));

        // criate the restrictions
        Predicate[] predicates = criateRestrictions(filter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<ResumoPessoa> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Predicate[] criateRestrictions(PessoaFilter filter,
                            CriteriaBuilder builder, Root<Pessoa> root) {
        List<Predicate> predicates = new ArrayList<>();
        
        if(!StringUtils.isEmpty(filter.getNome())) {
            // where descricao like %sometext%
            predicates.add(builder.like(builder.lower(root.get("nome")),
                    "%" + filter.getNome().toLowerCase() + "%"));
            
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
    
    private Long total(PessoaFilter filter) {
       CriteriaBuilder builder = manager.getCriteriaBuilder();
       CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
       Root<Pessoa> root = criteria.from(Pessoa.class);
       
       Predicate[] predicates = criateRestrictions(filter, builder, root);
       criteria.where(predicates);
       criteria.select(builder.count(root));
       
       return manager.createQuery(criteria).getSingleResult();
    }

    
}
