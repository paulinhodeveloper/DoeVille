package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.DeclaracaoDTO;
import br.org.sesisenai.DoaVille.entity.Cliente;
import br.org.sesisenai.DoaVille.entity.Declaracao;
import br.org.sesisenai.DoaVille.entity.ItemDeclaracao;
import br.org.sesisenai.DoaVille.entity.Material;
import br.org.sesisenai.DoaVille.repository.ClienteRepository;
import br.org.sesisenai.DoaVille.repository.DeclaracaoRepository;
import br.org.sesisenai.DoaVille.repository.MaterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeclaracaoService {

    private final DeclaracaoRepository declaracaoRepository;
    private final ClienteRepository clienteRepository;
    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    public DeclaracaoService(DeclaracaoRepository declaracaoRepository,
                             ClienteRepository clienteRepository,
                             MaterialRepository materialRepository,
                             ModelMapper modelMapper) {
        this.declaracaoRepository = declaracaoRepository;
        this.clienteRepository = clienteRepository;
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }

    public Declaracao salvar(DeclaracaoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (dto.getDataInicioPeriodo().isAfter(dto.getDataFimPeriodo())) {
            throw new RuntimeException("Data inicial deve ser anterior à final");
        }

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataInicioPeriodo(dto.getDataInicioPeriodo());
        declaracao.setDataFimPeriodo(dto.getDataFimPeriodo());
        declaracao.setDataDeclaracao(LocalDate.now());

        List<ItemDeclaracao> itens = dto.getItens().stream().map(itemDTO -> {
            Material material = materialRepository.findById(itemDTO.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

            if (itemDTO.getToneladasDeclaradas() <= 0) {
                throw new RuntimeException("Toneladas declaradas devem ser maiores que zero");
            }

            ItemDeclaracao item = new ItemDeclaracao();
            item.setMaterial(material);
            item.setPercentualCompensacao(material.getPercentualCompensacao());
            item.setToneladasDeclaradas(itemDTO.getToneladasDeclaradas());

            double toneladasCompensadas = itemDTO.getToneladasDeclaradas() * material.getPercentualCompensacao() / 100;
            item.setToneladasCompensacao(toneladasCompensadas);
            item.setDeclaracao(declaracao);
            return item;
        }).collect(Collectors.toList());

        declaracao.setItens(itens);
        declaracao.setTotalMateriais(itens.stream().mapToDouble(ItemDeclaracao::getToneladasDeclaradas).sum());
        declaracao.setTotalCompensacao(itens.stream().mapToDouble(ItemDeclaracao::getToneladasCompensacao).sum());

        return declaracaoRepository.save(declaracao);
    }

    public List<Declaracao> listarTodas() {
        return declaracaoRepository.findAll();
    }

    public Optional<Declaracao> buscarPorId(Long id) {
        return declaracaoRepository.findById(id);
    }

    public void excluir(Long id) {
        declaracaoRepository.deleteById(id);
    }
}