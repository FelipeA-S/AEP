package br.com.aep.GerenciadorDeResiduo.menu;

import br.com.aep.GerenciadorDeResiduo.model.Estoque;
import br.com.aep.GerenciadorDeResiduo.model.ItemRemessa;
import br.com.aep.GerenciadorDeResiduo.model.Remessa;
import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.service.EstoqueService;
import br.com.aep.GerenciadorDeResiduo.service.RemessaService;
import br.com.aep.GerenciadorDeResiduo.service.ResiduoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuPrincipal implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final ResiduoService residuoService;
    private final EstoqueService estoqueService;
    private final RemessaService remessaService;

    public MenuPrincipal(ResiduoService residuoService, EstoqueService estoqueService, RemessaService remessaService) {
        this.residuoService = residuoService;
        this.estoqueService = estoqueService;
        this.remessaService = remessaService;
    }

    @Override
    public void run(String... args) {
        int opcao;
        do {
            System.out.println("\n==================================");
            System.out.println("     GERENCIADOR DE RESÍDUOS");
            System.out.println("==================================");
            System.out.println("1 - Registrar nova remessa");
            System.out.println("2 - Consultar remessas");
            System.out.println("3 - Consultar estoque");
            System.out.println("4 - Editar remessa");
            System.out.println("5 - Excluir remessa");
            System.out.println("6 - Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Digite uma opção numérica válida.");
                scanner.next();
                System.out.print("Escolha uma opção: ");
            }

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    registrarRemessa();
                    break;
                case 2:
                    consultarRemessas();
                    break;
                case 3:
                    consultarEstoque();
                    break;
                case 4:
                    editarRemessa();
                    break;
                case 5:
                    excluirRemessa();
                    break;
                case 6:
                    remessaService.gerarRelatorio();
                    break;
                case 7:
                    System.out.println("\nEncerrando o sistema...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }

    private void consultarEstoque() {
        System.out.println("\n========== ESTOQUE DO ATERRO ==========");
        for (Estoque estoque : estoqueService.listarEstoques()) {
            System.out.println(
                    estoque.getTipoResiduo().getDescricao() +
                            " - " +
                            estoque.getQuantidadeTotal() +
                            " kg"
            );
        }
        System.out.println("=======================================");
    }

    private void registrarRemessa() {
        List<ItemRemessa> itens = new ArrayList<>();
        System.out.println("\n========== NOVA REMESSA ==========");
        for (Residuo residuo : residuoService.listarResiduos()) {
            System.out.println(residuo.getIdResiduo() + " - " + residuo.getTipoResiduo().getDescricao());
        }
        boolean adicionarOutro = true;
        while (adicionarOutro) {
            Long idResiduo = lerIdResiduo();
            Residuo residuo = residuoService.buscarPorId(idResiduo);
            if (residuo == null) {
                System.out.println("Resíduo não encontrado!");
                continue;
            }
            boolean jaAdicionado = itens.stream()
                    .anyMatch(item ->
                            item.getResiduo().getIdResiduo().equals(idResiduo)
                    );
            if (jaAdicionado) {
                System.out.println(
                        "Esse tipo de resíduo já foi adicionado à remessa."
                );
                continue;
            }
            BigDecimal quantidade = lerQuantidade();
            ItemRemessa item = new ItemRemessa(
                    null,
                    residuo,
                    quantidade
            );
            itens.add(item);
            adicionarOutro = desejaAdicionarOutro();
        }
        try {
            Remessa remessa = remessaService.cadastrarRemessa(itens);
            System.out.println("\nRemessa registrada com sucesso!");
            System.out.println("Número da remessa: " + remessa.getIdRemessa());
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao registrar remessa: " + e.getMessage());
        }
    }

    private void consultarRemessas() {
        List<Remessa> remessas = remessaService.listarRemessas();
        System.out.println("\n========== HISTÓRICO DE REMESSAS ==========");
        if (remessas.isEmpty()) {
            System.out.println("Nenhuma remessa cadastrada.");
            return;
        }
        for (Remessa remessa : remessas) {
            System.out.println("\nRemessa Nº: " + remessa.getIdRemessa());
            System.out.println("Data: " + remessa.getDataRegistro());
            System.out.println("Resíduos recebidos:");
            for (ItemRemessa item : remessa.getItens()) {
                System.out.println(
                        "- " +
                                item.getResiduo().getTipoResiduo().getDescricao() +
                                ": " +
                                item.getQuantidade() +
                                " kg"
                );
            }
            System.out.println("------------------------------------------");
        }
    }

    private void editarRemessa() {
        System.out.println("\n========== EDITAR REMESSA ==========");
        consultarRemessas();
        System.out.print("\nInforme o ID da remessa: ");
        Long idRemessa;
        try {
            idRemessa = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Remessa remessa = remessaService.buscarPorId(idRemessa);
        if (remessa == null) {
            System.out.println("Remessa não encontrada.");
            return;
        }
        System.out.println("\nInforme os novos resíduos da remessa:");
        List<ItemRemessa> novosItens = new ArrayList<>();
        boolean adicionarOutro = true;
        while (adicionarOutro) {
            for (Residuo residuo : residuoService.listarResiduos()) {
                System.out.println(
                        residuo.getIdResiduo() + " - " +
                                residuo.getTipoResiduo().getDescricao()
                );
            }
            Long idResiduo = lerIdResiduo();
            Residuo residuo = residuoService.buscarPorId(idResiduo);
            if (residuo == null) {
                System.out.println("Resíduo não encontrado!");
                continue;
            }
            boolean duplicado = novosItens.stream()
                    .anyMatch(item ->
                            item.getResiduo().getIdResiduo().equals(idResiduo)
                    );
            if (duplicado) {
                System.out.println("Resíduo já adicionado!");
                continue;
            }
            BigDecimal quantidade = lerQuantidade();
            novosItens.add(
                    new ItemRemessa(null, residuo, quantidade)
            );
            adicionarOutro = desejaAdicionarOutro();
        }
        try {
            remessaService.editarRemessa(idRemessa, novosItens);
            System.out.println("\nRemessa atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private void excluirRemessa() {
        System.out.println("\n========== EXCLUIR REMESSA ==========");
        consultarRemessas();
        System.out.print("\nInforme o ID da remessa: ");
        Long idRemessa;
        try {
            idRemessa = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        System.out.print(
                "Tem certeza que deseja excluir esta remessa? (S/N): "
        );
        String resposta = scanner.nextLine();
        if (!resposta.equalsIgnoreCase("S")) {
            System.out.println("Exclusão cancelada.");
            return;
        }
        try {
            remessaService.excluirRemessa(idRemessa);
            System.out.println("\nRemessa excluída com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private Long lerIdResiduo() {
        while (true) {
            try {
                System.out.print("\nInforme o ID do resíduo: ");
                Long id = Long.parseLong(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("O ID deve ser maior que zero.");
                    continue;
                }
                return id;
            } catch (NumberFormatException e) {
                System.out.println("Digite um ID numérico válido.");
            }
        }
    }

    private BigDecimal lerQuantidade() {
        while (true) {
            try {
                System.out.print("Informe a quantidade em kg: ");
                BigDecimal quantidade = new BigDecimal(
                        scanner.nextLine().replace(",", ".")
                );
                if (quantidade.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("A quantidade deve ser maior que zero.");
                    continue;
                }
                return quantidade;
            } catch (NumberFormatException e) {
                System.out.println("Digite uma quantidade numérica válida.");
            }
        }
    }

    private boolean desejaAdicionarOutro() {
        while (true) {
            System.out.print("Deseja adicionar outro resíduo? (S/N): ");
            String resposta = scanner.nextLine().trim();
            if (resposta.equalsIgnoreCase("S")) {
                return true;
            }
            if (resposta.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Resposta inválida. Digite S ou N.");
        }
    }

}
