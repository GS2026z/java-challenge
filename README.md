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

## Como o sistema funciona

O **TOTVS Meeting Intelligence** simula um sistema de inteligência comercial:
a partir da transcrição de uma reunião com um cliente, o programa calcula
indicadores de risco e oportunidade e sugere insights de negócio.

Ao iniciar, o `Main`:

1. Cria um `UsuarioSistema` (responsável padrão) e exibe seus dados —
   demonstração de herança/polimorfismo via `exibirDados()`.
2. Carrega o catálogo fixo de produtos TOTVS (`ProdutoTotvs`): Protheus, RM,
   Fluig, Datasul e Winthor.
3. Exibe um menu interativo (via `Scanner`):

| Opção | Funcionalidade           | O que faz                                                              |
|------:|--------------------------|-------------------------------------------------------------------------|
| 1     | Cadastrar Cliente        | Cria um `Cliente` (nome, segmento, porte)                                |
| 2     | Cadastrar Reuniao        | Cria uma `Reuniao` (título, data, responsável) vinculada a um cliente    |
| 3     | Registrar Transcricao    | Associa o texto da conversa (`Transcricao`) à reunião                    |
| 4     | Executar Analise (IA)    | Calcula sentimento, score de risco e score de oportunidade               |
| 5     | Gerar Insights           | Cria `Insight`s de negócio a partir da última análise da reunião         |
| 6     | Consultar Historico      | Mostra reuniões, transcrição, análises e insights de um cliente          |
| 0     | Sair                     | Encerra o programa                                                       |

## Como a Análise (IA) chega ao resultado — `AnaliseService`

A "análise de IA" não usa nenhum serviço externo: ela é feita por
**contagem de palavras-chave** dentro do texto da transcrição.

1. **Normalização**: o texto é convertido para minúsculas e tem acentos e
   cedilhas removidos (ex.: `"está"` → `"esta"`, `"concorrência"` →
   `"concorrencia"`), evitando que palavras acentuadas sejam ignoradas.
2. **Separação em palavras**: o texto normalizado é dividido em palavras
   individuais.
3. **Cálculo dos scores**: para cada lista de palavras-chave, conta-se
   quantas palavras do texto aparecem na lista e calcula-se a porcentagem em
   relação ao total de palavras da transcrição:

   ```
   score = (ocorrências da lista / total de palavras do texto) * 100
   ```

   São calculados dois scores: **`scoreRisco`** (lista de risco) e
   **`scoreOportunidade`** (lista de oportunidade).

4. **Sentimento geral**: comparando os dois scores —
   - `scoreRisco > scoreOportunidade` → **"Negativo"**
   - `scoreOportunidade > scoreRisco` → **"Positivo"**
   - scores iguais (normalmente quando nenhuma palavra-chave aparece, ou
     seja, 0% e 0%) → **"Neutro"**

### Palavras-chave de RISCO (sinalizam insatisfação / risco de churn)

`cancelar`, `cancelamento`, `concorrente`, `concorrencia`, `insatisfeito`,
`insatisfacao`, `problema`, `reclamacao`, `trocar`, `ruim`, `demora`,
`demorado`, `churn`

### Palavras-chave de OPORTUNIDADE (sinalizam interesse / upsell)

`interessado`, `interesse`, `orcamento`, `comprar`, `expandir`, `upgrade`,
`upsell`, `modulo`, `investir`, `contratar`, `ampliar`

> O reconhecimento é por palavra exata (já sem acento). Variações como
> plurais ou conjugações diferentes (ex.: "cancelando", "interessada") não
> são reconhecidas pelas listas atuais.

## Como os Insights são gerados — `InsightService`

A partir da análise mais recente da reunião, o sistema gera uma lista de
`Insight`s seguindo estas regras:

1. **Produto identificado**: para cada produto do catálogo TOTVS, verifica se
   o nome do produto aparece no texto da transcrição. Se sim, gera um insight
   de prioridade **Média** vinculado a esse `ProdutoTotvs`.
2. **Risco de Churn**: se `scoreRisco >= 5%`, gera um insight de prioridade
   **Alta** alertando sinais de insatisfação/cancelamento.
3. **Oportunidade de Upsell**: se `scoreOportunidade >= 5%`, gera um insight
   de prioridade **Alta** sobre sinais de interesse em novos produtos/módulos.
4. **Observação (fallback)**: se nenhuma das regras acima gerar insight, é
   criado um insight de prioridade **Baixa** informando que nenhum padrão
   relevante foi identificado na transcrição.

## Catálogo de Produtos TOTVS

| Produto  | Categoria              |
|----------|------------------------|
| Protheus | ERP                    |
| RM       | RH/Folha de Pagamento  |
| Fluig    | Gestão de Processos    |
| Datasul  | ERP                    |
| Winthor  | ERP Varejo             |


