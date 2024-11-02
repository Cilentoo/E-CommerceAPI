package br.com.serratec.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.Produto;
import br.com.serratec.model.ProdutoPedido;
import br.com.serratec.repository.ClienteRepository;
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
	private ClienteRepository clienteRepository;

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

		Cliente cliente = clienteRepository.findById(pedidoDto.getClienteId()).orElseThrow(
				() -> new RuntimeException("Cliente não encontrado com o ID: " + pedidoDto.getClienteId()));
		pedido.setCliente(cliente);

		pedido = pedidoRepository.save(pedido);

		Set<ProdutoPedido> produtoPedidos = new HashSet<>();

		for (ProdutoPedido ppDto : pedidoDto.getProdutoPedidos()) {
			ProdutoPedido pp = new ProdutoPedido();

			Produto produto = produtoService.buscar(ppDto.getProduto().getId());

			if (produto == null) {
				throw new RuntimeException("Produto não encontrado com o ID: " + ppDto.getProduto().getId());
			}

			pp.setProduto(produto);
			pp.setPedido(pedido);

			pp.setDesconto(ppDto.getDesconto());
			pp.setQuantidade(ppDto.getQuantidade());
			
			if (ppDto.getDesconto().equals("GABRIELA10")) {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade - 10;
				pp.setValorVenda(valorVenda);
			}else if (ppDto.getDesconto().equals("CILENTO10")) {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade - 10;
				pp.setValorVenda(valorVenda);
			}else if (ppDto.getDesconto().equals("THAIS10")) {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade - 10;
				pp.setValorVenda(valorVenda);
			}else if (ppDto.getDesconto().equals("DENILSON10")) {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade - 10;
				pp.setValorVenda(valorVenda);
			}else if (ppDto.getDesconto().equals("RICARDO10")) {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade - 10;
				pp.setValorVenda(valorVenda);
			}else {
				Double valorVenda = ppDto.getValorVenda();
				Integer quantidade = ppDto.getQuantidade();
				valorVenda = valorVenda * quantidade;
				pp.setValorVenda(valorVenda);
			}

			produtoPedidos.add(pp);

		}

		for (ProdutoPedido pp : produtoPedidos) {
			produtoPedidoRepository.save(pp);
		}

		pedido.setProdutoPedidos(produtoPedidos);
		pedidoRepository.save(pedido);

		return new PedidoResponseDTO(pedido);
	}
	
	@Transactional
	public PedidoResponseDTO atualizarPedido(Long pedidoId, PedidoRequestDTO pedidoDto) {
	    Pedido pedido = pedidoRepository.findById(pedidoId)
	            .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + pedidoId));

	    pedido.setStatus(pedidoDto.getStatus());

	    Cliente cliente = clienteRepository.findById(pedidoDto.getClienteId())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + pedidoDto.getClienteId()));
	    pedido.setCliente(cliente);

	    Set<ProdutoPedido> produtoPedidos = new HashSet<>();

	    for (ProdutoPedido ppDto : pedidoDto.getProdutoPedidos()) {
	        ProdutoPedido pp = new ProdutoPedido();

	        Produto produto = produtoService.buscar(ppDto.getProduto().getId());

	        if (produto == null) {
	            throw new RuntimeException("Produto não encontrado com o ID: " + ppDto.getProduto().getId());
	        }

	        pp.setProduto(produto);
	        pp.setPedido(pedido);

	        pp.setDesconto(ppDto.getDesconto());
	        pp.setQuantidade(ppDto.getQuantidade());

	        Double valorVenda = ppDto.getValorVenda() * ppDto.getQuantidade();
	        if ("GABRIELA10".equals(ppDto.getDesconto()) ||
	            "CILENTO10".equals(ppDto.getDesconto()) ||
	            "THAIS10".equals(ppDto.getDesconto()) ||
	            "DENILSON10".equals(ppDto.getDesconto()) ||
	            "RICARDO10".equals(ppDto.getDesconto())) {
	            valorVenda -= 10;
	        }
	        
	        pp.setValorVenda(valorVenda);
	        produtoPedidos.add(pp);
	    }

	    produtoPedidoRepository.deleteAll(pedido.getProdutoPedidos());
	    produtoPedidoRepository.saveAll(produtoPedidos);

	    pedido.setProdutoPedidos(produtoPedidos);
	    pedidoRepository.save(pedido);

	    return new PedidoResponseDTO(pedido);
	}
	
	@Transactional
	public void deletarPedido(Long pedidoId) {
	    Pedido pedido = pedidoRepository.findById(pedidoId)
	            .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + pedidoId));

	    produtoPedidoRepository.deleteAll(pedido.getProdutoPedidos());
	    pedidoRepository.delete(pedido);
	}
}
