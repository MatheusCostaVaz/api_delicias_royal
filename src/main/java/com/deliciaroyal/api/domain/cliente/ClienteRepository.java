package com.deliciaroyal.api.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    //Busca todos os clientes ativos (com o campo 'ativo' igual a 'true') de forma paginada.
	Page<Cliente> findAllByAtivoTrue(Pageable paginacao);
	
    //Verifica se um cliente com o ID fornecido está ativo ou não.
	@Query("""
            select p.ativo
            from Cliente p
            where
            p.id = :id
            """)
	Boolean findAtivoById(Long id); //Método para verificar se um cliente está ativo pelo seu ID, usando uma consulta JPQL
}
