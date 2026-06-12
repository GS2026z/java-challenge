# TOTVS Meeting Intelligence — Projeto Java (DDD)

## Integrantes do Grupo: 

• RM 563995 — Azor Biagioni Tartuce
• RM 562077 — João Pedro Ribeiro Palermo
• RM 561872 — Enzo Hort Ramos
• RM 562169 — Eduardo Santa Rosa Tolenti
• RM 562334— Mateus Amaral Franze

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


