import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        System.out.println("Lista criada com sucesso!");

        funcionarios.add(new Funcionario(
                "Maria",
                LocalDate.of(2000, 10, 18),
                new BigDecimal("2009.44"),
                "Operador"
        ));

        funcionarios.add(new Funcionario(
                "João",
                LocalDate.of(1990, 5, 12),
                new BigDecimal("2284.38"),
                "Operador"
        ));

        funcionarios.add(new Funcionario(
                "Caio",
                LocalDate.of(1961, 5, 2),
                new BigDecimal("9836.14"),
                "Coordenador"
        ));

        funcionarios.add(new Funcionario(
                "Miguel",
                LocalDate.of(1988, 10, 14),
                new BigDecimal("19119.88"),
                "Diretor"
        ));

        funcionarios.add(new Funcionario(
                "Alice",
                LocalDate.of(1995, 01, 05),
                new BigDecimal("2234.68"),
                "Recepcionista"
        ));

        funcionarios.add(new Funcionario(
                "Heitor",
                LocalDate.of(1999, 11, 19),
                new BigDecimal("1582.72"),
                "Operador"
        ));

        funcionarios.add(new Funcionario(
                "Arthur",
                LocalDate.of(1993, 3, 31),
                new BigDecimal("4071.84"),
                "Contador"
        ));

        funcionarios.add(new Funcionario(
                "Laura",
                LocalDate.of(1994, 7, 8),
                new BigDecimal("3017.45"),
                "Gerente"
        ));

        funcionarios.add(new Funcionario(
                "Heloísa",
                LocalDate.of(2003, 5, 24),
                new BigDecimal("1606.85"),
                "Eletricista"
        ));

        funcionarios.add(new Funcionario(
                "Helena",
                LocalDate.of(1996, 9, 2),
                new BigDecimal("2799.93"),
                "Gerente"
        ));

        System.out.println("Total de Funcionários: " + funcionarios.size());
        funcionarios.removeIf(f -> f.nome.equals("João"));

        System.out.println("Total após remover João: " + funcionarios.size());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);



        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        for (Funcionario func : funcionarios) {
            System.out.println(
                    "Nome: " + func.nome +
                            ", Data Nascimento: " + func.dataNascimento.format(formatter) +
                            ", Salário: " + nf.format(func.salario) +
                            ", Função: " + func.funcao
            );
        }

        for (Funcionario f : funcionarios) {
            f.salario = f.salario
                    .multiply(new BigDecimal("1.10"))
                    .setScale(2, RoundingMode.HALF_UP);

        }

            Map<String, List<Funcionario>> agrupados = new HashMap<>();

            for (Funcionario f : funcionarios) {
                if (!agrupados.containsKey(f.funcao)) {
                    agrupados.put(f.funcao, new ArrayList<>());
                }
                agrupados.get(f.funcao).add(f);
            }

            System.out.println("\n=== FUNCIONÁRIOS POR FUNÇÃO ===");
            for (String funcao : agrupados.keySet()) {
                System.out.println("Função: " + funcao);
                for (Funcionario f2 : agrupados.get(funcao)) {
                    System.out.println(" - " + f2.nome);
                }
            }

            System.out.println("\n=== ANIVERSARIANTES ===");
            for (Funcionario f : funcionarios) {
                int mes = f.dataNascimento.getMonthValue();
                if (mes == 10 || mes == 12) {
                    System.out.println("Aniversariante: " + f.nome);
                }
            }

            Funcionario maisVelho = funcionarios.get(0);
            for (Funcionario f3 : funcionarios) {
                if (f3.dataNascimento.isBefore(maisVelho.dataNascimento)) {
                    maisVelho = f3;
                }
            }

            int idade = java.time.Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
            System.out.println("\n=== FUNCIONÁRIO MAIS VELHO ===");
            System.out.println("Funcionário com maior idade: " + maisVelho.nome);
            System.out.println("Idade: " + idade);

        funcionarios.sort((f1, f2) -> f1.nome.compareTo(f2.nome));

        System.out.println("\n=== ORDEM ALFABÉTICA ===");
        for (Funcionario f4 : funcionarios) {
            System.out.println(f4.nome);

        }

        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.salario);
        }

        System.out.println("\nTotal dos salários: " + nf.format(totalSalarios));


        BigDecimal salarioMinimo = new BigDecimal("1212");

        System.out.println("\n=== SALÁRIOS MÍNIMOS ===");
        for (Funcionario f4 : funcionarios) {
            BigDecimal qtdSalarios = f4.salario.divide(salarioMinimo, 2, java.math.RoundingMode.HALF_UP);

            System.out.println(f4.nome + " ganha " + qtdSalarios + " salários mínimos");
        }
    }
}

