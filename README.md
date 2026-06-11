# TOTVS Meeting Intelligence — Projeto Java (DDD)

Projeto Java referente à entrega "DDD-Java – Projeto Java (70 pontos)" do Challenge TOTVS 2026,
implementado conforme a modelagem descrita em `Documentação DDD-Java.pdf`.

## Estrutura de pacotes

```
src/
  model/
    Pessoa.java          (abstrata - superclasse de Cliente e UsuarioSistema)
    Cliente.java
    UsuarioSistema.java
    Reuniao.java
    Transcricao.java
    ProdutoTotvs.java
    AnaliseIA.java
    Insight.java
  service/
    AnaliseService.java  (calcula sentimento, score de risco e de oportunidade)
    InsightService.java  (gera insights: produtos citados, churn, upsell)
  view/
    Main.java            (menu interativo via Scanner)
```

## Como compilar

```bash
javac -d out $(find src -name "*.java")
```

## Como executar

```bash
java -cp out view.Main
```

O programa já carrega um catálogo de produtos TOTVS e um cliente/reunião/transcrição de exemplo,
permitindo testar imediatamente as opções 4 (Executar Análise), 5 (Gerar Insights) e
6 (Consultar Histórico). O menu também permite cadastrar novos clientes, reuniões e transcrições.
