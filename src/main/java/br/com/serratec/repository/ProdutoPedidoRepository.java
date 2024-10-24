package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.model.ProdutoPedido;
import br.com.serratec.model.ProdutoPedidoPK;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoPK>{

}
