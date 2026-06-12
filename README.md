# TOTVS Meeting Intelligence — Projeto Java (DDD)

Projeto Java do Challenge TOTVS 2026,


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

O programa já carrega um catálogo de produtos TOTVS e cria um usuário responsável de exemplo.
O menu permite cadastrar clientes (1), cadastrar reuniões (2), registrar transcrições (3) e,
a partir desses dados, executar análise (4), gerar insights (5) e consultar o histórico do
cliente (6).
