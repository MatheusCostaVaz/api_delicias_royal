package com.deliciaroyal.api.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Page<Produto> findAllByAtivoTrue(Pageable paginacao);
	
//	@Query("""
//            select p.nome 
//            from Produto p
//            where
//            p.nome = :nome
//        """)
//	Produto consultaPorNome(String nome);
	
    @Query("""
            select p.ativo
            from Produto p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
	
}
