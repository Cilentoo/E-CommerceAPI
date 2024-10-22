package br.com.serratec.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.Produto;
import br.com.serratec.model.ProdutoPedido;
import br.com.serratec.repository.PedidoRepository;
import br.com.serratec.repository.ProdutoPedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoPedidoRepository produtoPedidoRepository;

	public List<PedidoResponseDTO> listarPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResponseDTO> dtos = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			dtos.add(new PedidoResponseDTO(pedido));
		}
		return dtos;
	}

	@Transactional
	public PedidoResponseDTO inserir(PedidoRequestDTO pedidoDto) {
		Pedido pedido = new Pedido();
		pedido.setStatus(pedidoDto.getStatus());
		pedido.setCliente(pedidoDto.getCliente());

		pedido = pedidoRepository.save(pedido);

		Set<ProdutoPedido> produtoPedidos = new HashSet<>();

		for (ProdutoPedido ppDto : pedidoDto.getProdutoPedidos()) {
			ProdutoPedido pp = new ProdutoPedido();

			
			Produto produto = produtoService.buscar(ppDto.getProduto().getId());

			pp.setProduto(produto);
			pp.setPedido(pedido);

			pp.setDesconto(ppDto.getDesconto());
			pp.setQuantidade(ppDto.getQuantidade());
			pp.setValorVenda(ppDto.getValorVenda());

			produtoPedidos.add(pp);
		}


		produtoPedidoRepository.saveAll(produtoPedidos);

		pedido.setProdutoPedidos(produtoPedidos);
		pedidoRepository.save(pedido);

		return new PedidoResponseDTO(pedido);
	}
}
