package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.enums.CategoriaEnum;
import br.com.serratec.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByCategoriaEnum(CategoriaEnum categoriaEnum);

}
