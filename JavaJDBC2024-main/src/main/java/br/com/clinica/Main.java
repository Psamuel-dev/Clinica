package br.com.clinica;

import java.io.Console;
import java.util.List;

public class Main {

    static Console console = System.console();
    public static void main(String[] args) {
        
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(console.readLine());
            switch (opcao) {
                case 0 -> cadastrarMedico();
                case 1 -> buscarMedicos();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
        
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu de Operações ###");
        System.out.println("0. cadastrar novo medico");
        System.out.println("1. Buscar todos os medicos");
        System.out.println("5. Sair do menu");
    }

    private static void cadastrarMedico() {
        System.out.println("\n### cadastro: ###");

        System.out.println("Nome:");
        String nome = console.readLine();
        System.out.println("CRM:");
        String crm  = console.readLine();
        System.out.println("Especialidade:");
        String especialidade = console.readLine();

        Medico medico = new    Medico(nome, crm, especialidade);
        MedicoDao medicoDao = new MedicoDao();

        try {
            medicoDao.salvar(medico);
            System.out.println("Produto criado com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        
    }

    private static void buscarMedicos() {
        System.out.println("\n### Buscar Todos ###");

        MedicoDao medicoDao = new MedicoDao();

        try {
            List<Medico> medicos = medicoDao.buscarTodos();

            if(medicos != null){
                System.out.println("Lista de Medicos:");

                for (Medico medico : medicos) {
                    System.out.println("Nome: "+medico.nome());
                }

            } else {
                System.out.println("Produto não encontrado");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}